package org.atabero.inventory.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.atabero.inventory.dto.stockmovement.CreateStockMovementDTO;
import org.atabero.inventory.dto.stockmovement.StockMovementResponseDTO;
import org.atabero.inventory.model.enums.MovementType;
import org.atabero.inventory.service.StockMovementService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/stock")
@RequiredArgsConstructor
public class StockMovementController {

    private final StockMovementService stockMovementService;

    // Entradas de stock
    @PostMapping("/purchase")
    public ResponseEntity<StockMovementResponseDTO> purchase(@Valid @RequestBody CreateStockMovementDTO dto) {
        return createResponseEntity(MovementType.PURCHASE, dto);
    }

    @PostMapping("/return")
    public ResponseEntity<StockMovementResponseDTO> returnToStock(@Valid @RequestBody CreateStockMovementDTO dto) {
        return createResponseEntity(MovementType.RETURN, dto);
    }

    @PostMapping("/adjustment-positive")
    public ResponseEntity<StockMovementResponseDTO> adjustmentPositive(@Valid @RequestBody CreateStockMovementDTO dto) {
        return createResponseEntity(MovementType.ADJUSTMENT_POSITIVE, dto);
    }

    @PostMapping("/manual-entry")
    public ResponseEntity<StockMovementResponseDTO> manualEntry(@Valid @RequestBody CreateStockMovementDTO dto) {
        return createResponseEntity(MovementType.MANUAL_ENTRY, dto);
    }

    // Salidas de stock
    @PostMapping("/sale")
    public ResponseEntity<StockMovementResponseDTO> sale(@Valid @RequestBody CreateStockMovementDTO dto) {
        return createResponseEntity(MovementType.SALE, dto);
    }

    @PostMapping("/breakage")
    public ResponseEntity<StockMovementResponseDTO> breakage(@Valid @RequestBody CreateStockMovementDTO dto) {
        return createResponseEntity(MovementType.BREAKAGE, dto);
    }

    @PostMapping("/loss")
    public ResponseEntity<StockMovementResponseDTO> loss(@Valid @RequestBody CreateStockMovementDTO dto) {
        return createResponseEntity(MovementType.LOSS, dto);
    }

    @PostMapping("/adjustment-negative")
    public ResponseEntity<StockMovementResponseDTO> adjustmentNegative(@Valid @RequestBody CreateStockMovementDTO dto) {
        return createResponseEntity(MovementType.ADJUSTMENT_NEGATIVE, dto);
    }

    @PostMapping("/manual-exit")
    public ResponseEntity<StockMovementResponseDTO> manualExit(@Valid @RequestBody CreateStockMovementDTO dto) {
        return createResponseEntity(MovementType.MANUAL_EXIT, dto);
    }

    // Método común
    private ResponseEntity<StockMovementResponseDTO> createResponseEntity(MovementType type, CreateStockMovementDTO dto) {
        return ResponseEntity.ok(stockMovementService.create(type, dto));
    }
}
