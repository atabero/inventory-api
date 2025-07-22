package org.atabero.inventory.service;

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
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class StockMovementServiceImpl implements StockMovementService {

    private final StockMovementRepository repository;
    private final ProductService productService;

    @Override
    @Transactional
    public StockMovementResponseDTO create(MovementType movementType, CreateStockMovementDTO dto) {
        Product product;
        try {
            product = productService.getByIdFull(dto.getIdProduct());
        } catch (ProductNotFoundException e) {
            StockMovement stockMovement = buildStockMovement(null, dto, movementType, OperationStatus.ERROR, e.getMessage());
            saveStockMovement(stockMovement);
            throw new ProductNotFoundException(dto.getIdProduct());
        }

        if (!movementType.isEntry() && (product.getCurrentStock() - dto.getAmount()) < 0) {
            String message = "No hay stock suficiente para realizar el movimiento: " +
                    "Stock actual = " + product.getCurrentStock() +
                    ", solicitado = " + dto.getAmount();
            StockMovement stockMovement = buildStockMovement(product, dto, movementType, OperationStatus.ERROR, message);
            saveStockMovement(stockMovement);
            throw new InsufficientStockException(message);
        }

        return switch (movementType) {
            case PURCHASE -> processStockPurchase(dto, product, movementType);
            case RETURN -> processStockReturn(dto, product, movementType);
            case ADJUSTMENT_POSITIVE -> processPositiveStockAdjustment(dto, product, movementType);
            case MANUAL_ENTRY -> processManualStockEntry(dto, product, movementType);
            case SALE -> processStockSale(dto, product, movementType);
            case BREAKAGE -> processStockBreakage(dto, product, movementType);
            case LOSS -> processStockLoss(dto, product, movementType);
            case ADJUSTMENT_NEGATIVE -> processNegativeStockAdjustment(dto, product, movementType);
            case MANUAL_EXIT -> processManualStockExit(dto, product, movementType);
        };
    }

    private StockMovementResponseDTO processStockPurchase(CreateStockMovementDTO dto, Product product, MovementType type) {
        validateStockMovementPreconditions(product, dto, type);
        StockMovement movement = buildStockMovement(product, dto, type, OperationStatus.SUCCESS, "Se procesó el stock de la compra");
        saveStockMovement(movement);
        Integer previousQuantity = product.getCurrentStock();
        Integer newQuantity = previousQuantity + dto.getAmount();
        product.setCurrentStock(newQuantity);
        productService.modifyStock(product);
        return createMovementAndUpdateStock(movement, previousQuantity, newQuantity);
    }

    private StockMovementResponseDTO processStockReturn(CreateStockMovementDTO dto, Product product, MovementType type) {
        validateStockMovementPreconditions(product, dto, type);
        return null;
    }

    private StockMovementResponseDTO processPositiveStockAdjustment(CreateStockMovementDTO dto, Product product, MovementType type) {
        return null;
    }

    private StockMovementResponseDTO processManualStockEntry(CreateStockMovementDTO dto, Product product, MovementType type) {
        return null;
    }

    private StockMovementResponseDTO processStockSale(CreateStockMovementDTO dto, Product product, MovementType type) {
        return null;
    }

    private StockMovementResponseDTO processStockBreakage(CreateStockMovementDTO dto, Product product, MovementType type) {
        return null;
    }

    private StockMovementResponseDTO processStockLoss(CreateStockMovementDTO dto, Product product, MovementType type) {
        return null;
    }

    private StockMovementResponseDTO processNegativeStockAdjustment(CreateStockMovementDTO dto, Product product, MovementType type) {
        return null;
    }

    private StockMovementResponseDTO processManualStockExit(CreateStockMovementDTO dto, Product product, MovementType type) {
        return null;
    }

    private StockMovementResponseDTO createMovementAndUpdateStock(StockMovement movement, Integer previousQuantity, Integer newQuantity) {
        return MapperStockMovement.toResponse(movement, previousQuantity, newQuantity);
    }

    // * VALIDATIONS
    private void validateStockAvailability(Product product, CreateStockMovementDTO dto, MovementType movementType) {
        switch (product.getStatus()) {
            case DISCONTINUED, BLOCKED, UNAVAILABLE -> {
                String message = "No se puede reponer stock";
                StockMovement stockMovement = buildStockMovement(product, dto, movementType, OperationStatus.ERROR, message);
                saveStockMovement(stockMovement);
                throw new StockReplenishmentNotAllowedException(message);
            }
        }
    }

    private void validateSupplierStatusBeforeStockMovement(Product product, CreateStockMovementDTO dto, MovementType movementType) {
        if (product.getSupplier().getStatus() == SupplierStatus.INACTIVE) {
            String message = "El proveedor está inactivo y no se puede registrar una entrada de stock para este producto.";
            StockMovement stockMovement = buildStockMovement(product, dto, movementType, OperationStatus.ERROR, message);
            saveStockMovement(stockMovement);
            throw new InactiveSupplierException(message);
        }
    }

    private void validateStockMovementPreconditions(Product product, CreateStockMovementDTO dto, MovementType type) {
        validateSupplierStatusBeforeStockMovement(product, dto, type);
        validateStockAvailability(product, dto, type);
    }

    // * CREATION
    private StockMovement buildStockMovement(Product product, CreateStockMovementDTO dto, MovementType movementType, OperationStatus status, String message) {
        return StockMovement.builder()
                .product(product)
                .quantityChange(dto.getAmount())
                .movementType(movementType)
                .notes(dto.getNotes())
                .operationStatus(status)
                .operationMessage(message)
                .build();
    }

    // * REPOSITORY
    private void saveStockMovement(StockMovement stockMovement) {
        repository.save(stockMovement);
    }
}
