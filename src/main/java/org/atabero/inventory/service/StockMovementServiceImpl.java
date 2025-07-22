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
import org.atabero.inventory.model.enums.ProductStatus;
import org.atabero.inventory.model.enums.SupplierStatus;
import org.atabero.inventory.repository.StockMovementRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


// TODO crear todos los metodos

@Service
@RequiredArgsConstructor
public class StockMovementServiceImpl implements StockMovementService{

    private final StockMovementRepository repository;
    private final ProductService productService;


    @Override
    @Transactional
    public StockMovementResponseDTO create(MovementType movementType, CreateStockMovementDTO dto) {
        Product product;
        try {
            product = productService.getByIdFull(dto.getIdProduct());
        } catch (ProductNotFoundException e) {
            // Creacion de StockMovement
            StockMovement stockMovement = createStockMovement(null, dto, movementType, OperationStatus.ERROR, e.getMessage());
            save(stockMovement);
            throw new ProductNotFoundException(dto.getIdProduct());
        }


        // Si es salida y no hay stock suficiente, lanzar excepción
        if (!movementType.isEntry() && (product.getCurrentStock() - dto.getAmount()) < 0) {
            // Creacion de StockMovement
            String message = "No hay stock suficiente para realizar el movimiento: " +
                    "Stock actual = " + product.getCurrentStock() +
                    ", solicitado = " + dto.getAmount();
            StockMovement stockMovement = createStockMovement(product, dto, movementType, OperationStatus.ERROR, message);
            save(stockMovement);
            throw new InsufficientStockException(
                    message
            );
        }

        return switch (movementType) {
            case PURCHASE -> handlePurchase(dto, product,movementType);
            case RETURN -> handleReturn(dto, product,movementType);
            case ADJUSTMENT_POSITIVE -> handleAdjustmentPositive(dto, product,movementType);
            case MANUAL_ENTRY -> handleManualEntry(dto, product,movementType);
            case SALE -> handleSale(dto, product,movementType);
            case BREAKAGE -> handleBreakage(dto, product,movementType);
            case LOSS -> handleLoss(dto, product,movementType);
            case ADJUSTMENT_NEGATIVE -> handleAdjustmentNegative(dto, product,movementType);
            case MANUAL_EXIT -> handleManualExit(dto, product,movementType);
        };
    }

    private StockMovementResponseDTO handlePurchase(CreateStockMovementDTO dto, Product product ,MovementType type) {
        validateStockMovementBeforeProcessing(product,dto,type);
        StockMovement movement = createStockMovement(
                product,dto,type,OperationStatus.SUCCESS,"Se proceso el stock de la compra"
        );
        save(movement);
        Integer previousQuantity = product.getCurrentStock();
        Integer newQuantity  = previousQuantity + dto.getAmount();
        product.setCurrentStock(newQuantity);
        productService.modifyStock(product);
        return createMovementAndUpdateStock(movement, previousQuantity,newQuantity);
    }



    private StockMovementResponseDTO handleReturn(CreateStockMovementDTO dto, Product product,MovementType type) {
        validateStockMovementBeforeProcessing(product,dto,type);
        return null;
    }

    private StockMovementResponseDTO handleAdjustmentPositive(CreateStockMovementDTO dto, Product product,MovementType type) {
        return null;
    }

    private StockMovementResponseDTO handleManualEntry(CreateStockMovementDTO dto, Product product,MovementType type) {
        return null;

    }

    private StockMovementResponseDTO handleSale(CreateStockMovementDTO dto, Product product,MovementType type) {
        return null;
    }

    private StockMovementResponseDTO handleBreakage(CreateStockMovementDTO dto, Product product,MovementType type) {
        return null;
    }

    private StockMovementResponseDTO handleLoss(CreateStockMovementDTO dto, Product product,MovementType type) {
        return null;
    }

    private StockMovementResponseDTO handleAdjustmentNegative(CreateStockMovementDTO dto, Product product,MovementType type) {
        return null;
    }

    private StockMovementResponseDTO handleManualExit(CreateStockMovementDTO dto, Product product,MovementType type) {
        return null;
    }

    private StockMovementResponseDTO createMovementAndUpdateStock(StockMovement movement, Integer previousQuantity, Integer newQuantity) {
        return MapperStockMovement.toResponse(movement,previousQuantity,newQuantity);
    }

    // * VALIDATIONS
    private void checkStockAvailability(Product product, CreateStockMovementDTO dto, MovementType movementType) {
        switch (product.getStatus()) {
            case DISCONTINUED, BLOCKED, UNAVAILABLE -> {
                String message = "No se puede reponer stock";
                StockMovement stockMovement = createStockMovement(product, dto, movementType, OperationStatus.ERROR, message);
                save(stockMovement);
                throw new StockReplenishmentNotAllowedException(message);
            }
        }
    }

    private void validateSupplierStatus(Product product, CreateStockMovementDTO dto, MovementType movementType) {
        if (product.getSupplier().getStatus() == SupplierStatus.INACTIVE) {
            String message = "El proveedor está inactivo y no se puede registrar una entrada de stock para este producto."; 
            StockMovement stockMovement = createStockMovement(product, dto, movementType, OperationStatus.ERROR, message);
            save(stockMovement);
            throw new InactiveSupplierException(message);
        }
    }

    private void validateStockMovementBeforeProcessing(Product product, CreateStockMovementDTO dto, MovementType type) {
        validateSupplierStatus(product, dto, type);
        checkStockAvailability(product, dto, type);
    }



    // * CREACION
    private StockMovement createStockMovement(Product product, CreateStockMovementDTO dto, MovementType movementType, OperationStatus status, String message) {
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

    private void save(StockMovement stockMovement){
        repository.save(stockMovement);
    }

}
