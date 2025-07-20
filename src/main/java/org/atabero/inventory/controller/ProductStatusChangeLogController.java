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

@RestController
@RequestMapping("/api/v1/product-status-changes")
@RequiredArgsConstructor
@Validated
public class ProductStatusChangeLogController {

    private final ProductStatusChangeLogService service;

    @PostMapping
    public ResponseEntity<ProductStatusChangeLogResponseDTO> create(
            @Valid @RequestBody CreateProductStatusChangeLogDTO dto) {
        ProductStatusChangeLogResponseDTO response = service.save(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping
    public ResponseEntity<List<ProductStatusChangeLogResponseDTO>> getAll() {
        return ResponseEntity.ok(service.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductStatusChangeLogResponseDTO> getById(@PathVariable UUID id) {
        return ResponseEntity.ok(service.findById(id));
    }

    @GetMapping("/product/{productId}")
    public ResponseEntity<List<ProductStatusChangeLogResponseDTO>> getByProductId(@PathVariable Long productId) {
        return ResponseEntity.ok(service.findByProductId(productId));
    }

    @GetMapping("/previous-status/{status}")
    public ResponseEntity<List<ProductStatusChangeLogResponseDTO>> getByPreviousStatus(
            @PathVariable ProductStatus status) {
        return ResponseEntity.ok(service.findByPreviousStatus(status));
    }

    @GetMapping("/new-status/{status}")
    public ResponseEntity<List<ProductStatusChangeLogResponseDTO>> getByNewStatus(
            @PathVariable ProductStatus status) {
        return ResponseEntity.ok(service.findByNewStatus(status));
    }

    @GetMapping("/operation-status/{status}")
    public ResponseEntity<List<ProductStatusChangeLogResponseDTO>> getByOperationStatus(
            @PathVariable OperationStatus status) {
        return ResponseEntity.ok(service.findByOperationStatus(status));
    }

    @GetMapping("/date-range")
    public ResponseEntity<List<ProductStatusChangeLogResponseDTO>> getByDateRange(
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime start,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime end) {
        return ResponseEntity.ok(service.findByChangedAtBetween(start, end));
    }

    @GetMapping("/product/{productId}/operation-status/{operationStatus}")
    public ResponseEntity<List<ProductStatusChangeLogResponseDTO>> getByProductIdAndOperationStatus(
            @PathVariable Long productId,
            @PathVariable OperationStatus operationStatus) {
        return ResponseEntity.ok(service.findByProductIdAndOperationStatus(productId, operationStatus));
    }

    @GetMapping("/status-change")
    public ResponseEntity<List<ProductStatusChangeLogResponseDTO>> getByPreviousAndNewStatus(
            @RequestParam ProductStatus previousStatus,
            @RequestParam ProductStatus newStatus) {
        return ResponseEntity.ok(service.findByPreviousStatusAndNewStatus(previousStatus, newStatus));
    }

    @GetMapping("/count/operation-status/{operationStatus}")
    public ResponseEntity<Long> countByOperationStatus(@PathVariable OperationStatus operationStatus) {
        return ResponseEntity.ok(service.countByOperationStatus(operationStatus));
    }
}
