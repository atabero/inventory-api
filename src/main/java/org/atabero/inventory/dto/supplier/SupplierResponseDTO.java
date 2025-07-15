package org.atabero.inventory.dto.supplier;

import org.atabero.inventory.model.enums.SupplierStatus;

import java.time.LocalDateTime;

public record SupplierResponseDTO(
        Long id,
        String name,
        String contactInfo,
        SupplierStatus status,
        LocalDateTime createdDate,
        LocalDateTime lastModifiedDate
) {}
