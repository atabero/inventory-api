package org.atabero.inventory.dto.movementlog;

import org.atabero.inventory.model.enums.MovementStatus;
import org.atabero.inventory.model.enums.MovementType;

import java.time.LocalDateTime;
import java.util.UUID;

public record MovementLogResponseDTO(
        UUID id,
        UUID idProduct,
        Integer quantityChange,
        MovementType movementType,
        MovementStatus movementStatus,
        LocalDateTime timestamp,
        String notes
) {}
