package org.atabero.inventory.dto.product;

import org.atabero.inventory.model.Category;
import org.atabero.inventory.model.Supplier;
import org.atabero.inventory.model.enums.ProductStatus;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public record ProductResponseDTO(
        Long id,
        String name,
        String code,
        String description,
        BigDecimal price,
        Integer currentStock,
        ProductStatus status,
        String nameCategory,
        String NameSupplier,
        LocalDateTime createdDate,
        LocalDateTime lastModifiedDate
) {}
