package org.atabero.inventory.dto.category;

import org.atabero.inventory.model.enums.CategoryStatus;

import java.time.LocalDateTime;

public record CategoryResponseDTO(
        Long id,
        String name,
        String description,
        CategoryStatus status,
        LocalDateTime createdDate,
        LocalDateTime lastModifiedDate
) {}
