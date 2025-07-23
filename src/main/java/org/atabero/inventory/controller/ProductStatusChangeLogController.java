package org.atabero.inventory.controller;

import lombok.RequiredArgsConstructor;
import org.atabero.inventory.dto.productstatuschangelog.CreateProductStatusChangeLogDTO;
import org.atabero.inventory.dto.productstatuschangelog.ProductStatusChangeLogResponseDTO;
import org.atabero.inventory.model.enums.OperationStatus;
import org.atabero.inventory.model.enums.ProductStatus;
import org.atabero.inventory.service.ProductStatusChangeLogService;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

/**
 * Controlador REST para la gestión de logs de cambios de estado de productos.
 * Permite crear registros y realizar consultas con diferentes filtros.
 */
@RestController
@RequestMapping("/api/v1/product-status-changes")
@RequiredArgsConstructor
@Validated
public class ProductStatusChangeLogController {

    private final ProductStatusChangeLogService service;

    /**
     * Crea un nuevo registro de cambio de estado de producto.
     *
     * @param dto DTO con la información para crear el registro.
     * @return ResponseEntity con el registro creado y código 201 Created.
     */
    @PostMapping
    public ResponseEntity<ProductStatusChangeLogResponseDTO> create(
            @Valid @RequestBody CreateProductStatusChangeLogDTO dto) {
        ProductStatusChangeLogResponseDTO response = service.save(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    /**
     * Obtiene todos los registros de cambios de estado.
     *
     * @return ResponseEntity con la lista de registros.
     */
    @GetMapping
    public ResponseEntity<List<ProductStatusChangeLogResponseDTO>> getAll() {
        return ResponseEntity.ok(service.findAll());
    }

    /**
     * Obtiene un registro por su ID.
     *
     * @param id UUID del registro.
     * @return ResponseEntity con el registro encontrado.
     */
    @GetMapping("/{id}")
    public ResponseEntity<ProductStatusChangeLogResponseDTO> getById(@PathVariable UUID id) {
        return ResponseEntity.ok(service.findById(id));
    }

    /**
     * Obtiene registros por ID de producto.
     *
     * @param productId ID del producto.
     * @return ResponseEntity con la lista de registros asociados.
     */
    @GetMapping("/product/{productId}")
    public ResponseEntity<List<ProductStatusChangeLogResponseDTO>> getByProductId(@PathVariable Long productId) {
        return ResponseEntity.ok(service.findByProductId(productId));
    }

    /**
     * Obtiene registros filtrando por estado anterior.
     *
     * @param status Estado anterior.
     * @return ResponseEntity con la lista filtrada.
     */
    @GetMapping("/previous-status/{status}")
    public ResponseEntity<List<ProductStatusChangeLogResponseDTO>> getByPreviousStatus(
            @PathVariable ProductStatus status) {
        return ResponseEntity.ok(service.findByPreviousStatus(status));
    }

    /**
     * Obtiene registros filtrando por nuevo estado.
     *
     * @param status Nuevo estado.
     * @return ResponseEntity con la lista filtrada.
     */
    @GetMapping("/new-status/{status}")
    public ResponseEntity<List<ProductStatusChangeLogResponseDTO>> getByNewStatus(
            @PathVariable ProductStatus status) {
        return ResponseEntity.ok(service.findByNewStatus(status));
    }

    /**
     * Obtiene registros filtrando por estado de operación.
     *
     * @param status Estado de la operación.
     * @return ResponseEntity con la lista filtrada.
     */
    @GetMapping("/operation-status/{status}")
    public ResponseEntity<List<ProductStatusChangeLogResponseDTO>> getByOperationStatus(
            @PathVariable OperationStatus status) {
        return ResponseEntity.ok(service.findByOperationStatus(status));
    }

    /**
     * Obtiene registros que hayan cambiado entre dos fechas.
     *
     * @param start Fecha y hora de inicio del rango.
     * @param end   Fecha y hora de fin del rango.
     * @return ResponseEntity con la lista filtrada.
     */
    @GetMapping("/date-range")
    public ResponseEntity<List<ProductStatusChangeLogResponseDTO>> getByDateRange(
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime start,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime end) {
        return ResponseEntity.ok(service.findByChangedAtBetween(start, end));
    }

    /**
     * Obtiene registros por ID de producto y estado de operación.
     *
     * @param productId       ID del producto.
     * @param operationStatus Estado de la operación.
     * @return ResponseEntity con la lista filtrada.
     */
    @GetMapping("/product/{productId}/operation-status/{operationStatus}")
    public ResponseEntity<List<ProductStatusChangeLogResponseDTO>> getByProductIdAndOperationStatus(
            @PathVariable Long productId,
            @PathVariable OperationStatus operationStatus) {
        return ResponseEntity.ok(service.findByProductIdAndOperationStatus(productId, operationStatus));
    }

    /**
     * Obtiene registros filtrando por estado anterior y nuevo estado.
     *
     * @param previousStatus Estado anterior.
     * @param newStatus      Nuevo estado.
     * @return ResponseEntity con la lista filtrada.
     */
    @GetMapping("/status-change")
    public ResponseEntity<List<ProductStatusChangeLogResponseDTO>> getByPreviousAndNewStatus(
            @RequestParam ProductStatus previousStatus,
            @RequestParam ProductStatus newStatus) {
        return ResponseEntity.ok(service.findByPreviousStatusAndNewStatus(previousStatus, newStatus));
    }

    /**
     * Cuenta la cantidad de registros con un estado de operación específico.
     *
     * @param operationStatus Estado de operación a contar.
     * @return ResponseEntity con el conteo.
     */
    @GetMapping("/count/operation-status/{operationStatus}")
    public ResponseEntity<Long> countByOperationStatus(@PathVariable OperationStatus operationStatus) {
        return ResponseEntity.ok(service.countByOperationStatus(operationStatus));
    }
}
