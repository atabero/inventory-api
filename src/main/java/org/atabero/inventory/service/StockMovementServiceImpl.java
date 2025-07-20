package org.atabero.inventory.service;


import lombok.RequiredArgsConstructor;
import org.atabero.inventory.dto.stockmovement.CreateStockMovementDTO;
import org.atabero.inventory.dto.stockmovement.StockMovementResponseDTO;
import org.atabero.inventory.model.Product;
import org.atabero.inventory.model.enums.MovementType;
import org.atabero.inventory.repository.StockMovementRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class StockMovementServiceImpl implements StockMovementService{

    private final StockMovementRepository repository;
    private final ProductService productService;


    @Override
    @Transactional
    public StockMovementResponseDTO create(MovementType movementType, CreateStockMovementDTO dto) {
        return switch (movementType) {
            case PURCHASE -> handlePurchase(dto);
            case RETURN -> handleReturn(dto);
            case ADJUSTMENT_POSITIVE -> handleAdjustmentPositive(dto);
            case MANUAL_ENTRY -> handleManualEntry(dto);

            case SALE -> handleSale(dto);
            case BREAKAGE -> handleBreakage(dto);
            case LOSS -> handleLoss(dto);
            case ADJUSTMENT_NEGATIVE -> handleAdjustmentNegative(dto);
            case MANUAL_EXIT -> handleManualExit(dto);
        };
    }

    private StockMovementResponseDTO handlePurchase(CreateStockMovementDTO dto) {
        // lógica de compra a proveedor
        // sumar stock, guardar movimiento, etc.
        return createMovementAndUpdateStock(dto, MovementType.PURCHASE, true);
    }

    private StockMovementResponseDTO handleReturn(CreateStockMovementDTO dto) {
        return createMovementAndUpdateStock(dto, MovementType.RETURN, true);
    }

    private StockMovementResponseDTO handleAdjustmentPositive(CreateStockMovementDTO dto) {
        return createMovementAndUpdateStock(dto, MovementType.ADJUSTMENT_POSITIVE, true);
    }

    private StockMovementResponseDTO handleManualEntry(CreateStockMovementDTO dto) {
        return createMovementAndUpdateStock(dto, MovementType.MANUAL_ENTRY, true);
    }

    private StockMovementResponseDTO handleSale(CreateStockMovementDTO dto) {
        return createMovementAndUpdateStock(dto, MovementType.SALE, false);
    }

    private StockMovementResponseDTO handleBreakage(CreateStockMovementDTO dto) {
        return createMovementAndUpdateStock(dto, MovementType.BREAKAGE, false);
    }

    private StockMovementResponseDTO handleLoss(CreateStockMovementDTO dto) {
        return createMovementAndUpdateStock(dto, MovementType.LOSS, false);
    }

    private StockMovementResponseDTO handleAdjustmentNegative(CreateStockMovementDTO dto) {
        return createMovementAndUpdateStock(dto, MovementType.ADJUSTMENT_NEGATIVE, false);
    }

    private StockMovementResponseDTO handleManualExit(CreateStockMovementDTO dto) {
        return createMovementAndUpdateStock(dto, MovementType.MANUAL_EXIT, false);
    }

    private StockMovementResponseDTO createMovementAndUpdateStock(CreateStockMovementDTO dto, MovementType type, boolean isEntry) {
        return null;
    }



}
