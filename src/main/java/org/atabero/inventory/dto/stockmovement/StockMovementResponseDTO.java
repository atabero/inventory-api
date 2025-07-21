package org.atabero.inventory.dto.stockmovement;

import org.atabero.inventory.model.enums.OperationStatus;
import org.atabero.inventory.model.enums.MovementType;

import java.time.LocalDateTime;
import java.util.UUID;

public record StockMovementResponseDTO(
        UUID id,
        Long idProduct,
        String productName,
        Integer quantityChange,
        Integer previousQuantity,
        Integer newQuantity,
        MovementType movementType,
        OperationStatus operationStatus,
        LocalDateTime timestamp,
        String notes
) {}
