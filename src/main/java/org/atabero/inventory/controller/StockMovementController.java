package org.atabero.inventory.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.atabero.inventory.dto.stockmovement.CreateStockMovementDTO;
import org.atabero.inventory.dto.stockmovement.StockMovementResponseDTO;
import org.atabero.inventory.model.enums.MovementType;
import org.atabero.inventory.service.StockMovementService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * Controlador REST para la gestión de movimientos de stock.
 * Permite registrar distintos tipos de movimientos como compras, ventas, ajustes, devoluciones, entre otros.
 */
@RestController
@RequestMapping("/api/v1/stock")
@RequiredArgsConstructor
public class StockMovementController {

    private final StockMovementService stockMovementService;

    /**
     * Registra una entrada de stock por compra.
     *
     * @param dto Datos para crear el movimiento de compra.
     * @return ResponseEntity con el detalle del movimiento registrado.
     */
    @PostMapping("/purchase")
    public ResponseEntity<StockMovementResponseDTO> purchase(@Valid @RequestBody CreateStockMovementDTO dto) {
        return createResponseEntity(MovementType.PURCHASE, dto);
    }

    /**
     * Registra una devolución que retorna stock al inventario.
     *
     * @param dto Datos para crear el movimiento de devolución.
     * @return ResponseEntity con el detalle del movimiento registrado.
     */
    @PostMapping("/return")
    public ResponseEntity<StockMovementResponseDTO> returnToStock(@Valid @RequestBody CreateStockMovementDTO dto) {
        return createResponseEntity(MovementType.RETURN, dto);
    }

    /**
     * Registra un ajuste positivo de stock.
     *
     * @param dto Datos para crear el movimiento de ajuste positivo.
     * @return ResponseEntity con el detalle del movimiento registrado.
     */
    @PostMapping("/adjustment-positive")
    public ResponseEntity<StockMovementResponseDTO> adjustmentPositive(@Valid @RequestBody CreateStockMovementDTO dto) {
        return createResponseEntity(MovementType.ADJUSTMENT_POSITIVE, dto);
    }

    /**
     * Registra una entrada manual de stock.
     *
     * @param dto Datos para crear el movimiento de entrada manual.
     * @return ResponseEntity con el detalle del movimiento registrado.
     */
    @PostMapping("/manual-entry")
    public ResponseEntity<StockMovementResponseDTO> manualEntry(@Valid @RequestBody CreateStockMovementDTO dto) {
        return createResponseEntity(MovementType.MANUAL_ENTRY, dto);
    }

    /**
     * Registra una salida de stock por venta.
     *
     * @param dto Datos para crear el movimiento de venta.
     * @return ResponseEntity con el detalle del movimiento registrado.
     */
    @PostMapping("/sale")
    public ResponseEntity<StockMovementResponseDTO> sale(@Valid @RequestBody CreateStockMovementDTO dto) {
        return createResponseEntity(MovementType.SALE, dto);
    }

    /**
     * Registra una salida de stock por rotura.
     *
     * @param dto Datos para crear el movimiento de rotura.
     * @return ResponseEntity con el detalle del movimiento registrado.
     */
    @PostMapping("/breakage")
    public ResponseEntity<StockMovementResponseDTO> breakage(@Valid @RequestBody CreateStockMovementDTO dto) {
        return createResponseEntity(MovementType.BREAKAGE, dto);
    }

    /**
     * Registra una salida de stock por pérdida.
     *
     * @param dto Datos para crear el movimiento de pérdida.
     * @return ResponseEntity con el detalle del movimiento registrado.
     */
    @PostMapping("/loss")
    public ResponseEntity<StockMovementResponseDTO> loss(@Valid @RequestBody CreateStockMovementDTO dto) {
        return createResponseEntity(MovementType.LOSS, dto);
    }

    /**
     * Registra un ajuste negativo de stock.
     *
     * @param dto Datos para crear el movimiento de ajuste negativo.
     * @return ResponseEntity con el detalle del movimiento registrado.
     */
    @PostMapping("/adjustment-negative")
    public ResponseEntity<StockMovementResponseDTO> adjustmentNegative(@Valid @RequestBody CreateStockMovementDTO dto) {
        return createResponseEntity(MovementType.ADJUSTMENT_NEGATIVE, dto);
    }

    /**
     * Registra una salida manual de stock.
     *
     * @param dto Datos para crear el movimiento de salida manual.
     * @return ResponseEntity con el detalle del movimiento registrado.
     */
    @PostMapping("/manual-exit")
    public ResponseEntity<StockMovementResponseDTO> manualExit(@Valid @RequestBody CreateStockMovementDTO dto) {
        return createResponseEntity(MovementType.MANUAL_EXIT, dto);
    }

    /**
     * Método auxiliar para crear la respuesta unificada para cada tipo de movimiento.
     *
     * @param type Tipo de movimiento.
     * @param dto  Datos del movimiento.
     * @return ResponseEntity con el detalle del movimiento registrado.
     */
    private ResponseEntity<StockMovementResponseDTO> createResponseEntity(MovementType type, CreateStockMovementDTO dto) {
        return ResponseEntity.ok(stockMovementService.create(type, dto));
    }
}
