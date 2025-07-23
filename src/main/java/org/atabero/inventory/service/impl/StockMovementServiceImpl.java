package org.atabero.inventory.service.impl;

import lombok.RequiredArgsConstructor;
import org.atabero.inventory.dto.stockmovement.CreateStockMovementDTO;
import org.atabero.inventory.dto.stockmovement.StockMovementResponseDTO;
import org.atabero.inventory.exception.product.ProductNotFoundException;
import org.atabero.inventory.exception.stockmovemen.InsufficientStockException;
import org.atabero.inventory.exception.stockmovemen.StockReplenishmentNotAllowedException;
import org.atabero.inventory.exception.supplier.InactiveSupplierException;
import org.atabero.inventory.mapper.MapperStockMovement;
import org.atabero.inventory.model.Product;
import org.atabero.inventory.model.StockMovement;
import org.atabero.inventory.model.enums.MovementType;
import org.atabero.inventory.model.enums.OperationStatus;
import org.atabero.inventory.model.enums.SupplierStatus;
import org.atabero.inventory.repository.StockMovementRepository;
import org.atabero.inventory.service.ProductService;
import org.atabero.inventory.service.StockMovementService;
import org.atabero.inventory.util.StockInfo;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.EnumMap;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class StockMovementServiceImpl implements StockMovementService {

    private final StockMovementRepository repository;
    private final ProductService productService;

    private static final Map<MovementType, String> movementMessages = new EnumMap<>(MovementType.class);

    static {
        movementMessages.put(MovementType.PURCHASE, "Se procesó el stock de la compra");
        movementMessages.put(MovementType.RETURN, "Se procesó la devolución");
        movementMessages.put(MovementType.ADJUSTMENT_POSITIVE, "Se procesó el ajuste positivo");
        movementMessages.put(MovementType.MANUAL_ENTRY, "Se procesó la entrada manual");
        movementMessages.put(MovementType.SALE, "Se registró la venta");
        movementMessages.put(MovementType.BREAKAGE, "Se registró la rotura");
        movementMessages.put(MovementType.LOSS, "Se registró la pérdida");
        movementMessages.put(MovementType.ADJUSTMENT_NEGATIVE, "Se procesó el ajuste negativo");
        movementMessages.put(MovementType.MANUAL_EXIT, "Se procesó la salida manual");
    }

    @Override
    @Transactional
    public StockMovementResponseDTO create(MovementType movementType, CreateStockMovementDTO dto) {
        Product product;
        try {
            product = productService.getByIdFull(dto.getIdProduct());
        } catch (ProductNotFoundException e) {
            StockMovement errorMovement = buildStockMovement(null, dto, movementType, OperationStatus.ERROR, e.getMessage(), null, null);
            saveStockMovement(errorMovement);
            throw new ProductNotFoundException("No se encontró el producto con ID " + dto.getIdProduct());
        }

        validateStockMovementPreconditions(product, dto, movementType);

        return processSuccessfulStockMovement(dto, product, movementType);
    }

    private StockMovementResponseDTO processSuccessfulStockMovement(CreateStockMovementDTO dto, Product product, MovementType type) {
        String message = movementMessages.getOrDefault(type, "Movimiento de stock registrado");
        StockInfo stockInfo = calculateStockQuantities(product, dto, type);
        product.setCurrentStock(stockInfo.getNewQuantity());

        StockMovement movement = buildStockMovement(
                product, dto, type, OperationStatus.SUCCESS, message,
                stockInfo.getPreviousQuantity(), stockInfo.getNewQuantity()
        );

        saveStockMovement(movement);
        productService.modifyStock(product);
        return mapToResponse(movement, stockInfo);
    }

    private StockInfo calculateStockQuantities(Product product, CreateStockMovementDTO dto, MovementType type) {
        int previousQuantity = product.getCurrentStock();
        int amount = dto.getAmount();
        int newQuantity = type.isEntry()
                ? previousQuantity + amount
                : previousQuantity - amount;

        return new StockInfo(previousQuantity, newQuantity);
    }

    private void validateStockMovementPreconditions(Product product, CreateStockMovementDTO dto, MovementType type) {
        validateSupplierStatus(product, dto, type);
        validateProductStatus(product, dto, type);
        validateStockSufficiency(product, dto, type);
    }

    private void validateSupplierStatus(Product product, CreateStockMovementDTO dto, MovementType type) {
        if (product.getSupplier().getStatus() == SupplierStatus.INACTIVE) {
            String message = "El proveedor está inactivo y no se puede registrar una entrada de stock para este producto.";
            recordErrorAndThrow(product, dto, type, message, new InactiveSupplierException(message));
        }
    }

    private void validateProductStatus(Product product, CreateStockMovementDTO dto, MovementType type) {
        switch (product.getStatus()) {
            case DISCONTINUED, BLOCKED, UNAVAILABLE -> {
                String message = "No se puede reponer stock para este producto.";
                recordErrorAndThrow(product, dto, type, message, new StockReplenishmentNotAllowedException(message));
            }
        }
    }

    private void validateStockSufficiency(Product product, CreateStockMovementDTO dto, MovementType type) {
        if (!type.isEntry() && product.getCurrentStock() < dto.getAmount()) {
            String message = "No hay stock suficiente: actual = " + product.getCurrentStock() + ", solicitado = " + dto.getAmount();
            recordErrorAndThrow(product, dto, type, message, new InsufficientStockException(message));
        }
    }

    private void recordErrorAndThrow(Product product, CreateStockMovementDTO dto, MovementType type, String message, RuntimeException ex) {
        StockMovement errorMovement = buildStockMovement(product, dto, type, OperationStatus.ERROR, message, null, null);
        saveStockMovement(errorMovement);
        throw ex;
    }

    private StockMovement buildStockMovement(Product product, CreateStockMovementDTO dto, MovementType movementType,
                                             OperationStatus status, String message, Integer previousQuantity, Integer newQuantity) {
        return StockMovement.builder()
                .product(product)
                .quantityChange(dto.getAmount())
                .movementType(movementType)
                .notes(dto.getNotes())
                .operationStatus(status)
                .previousQuantity(previousQuantity)
                .newQuantity(newQuantity)
                .operationMessage(message)
                .build();
    }

    private StockMovementResponseDTO mapToResponse(StockMovement movement, StockInfo stockInfo) {
        return MapperStockMovement.toResponse(movement, stockInfo.getPreviousQuantity(), stockInfo.getNewQuantity());
    }

    private void saveStockMovement(StockMovement stockMovement) {
        repository.save(stockMovement);
    }
}
