package org.atabero.inventory.service;

import org.atabero.inventory.dto.stockmovement.CreateStockMovementDTO;
import org.atabero.inventory.dto.stockmovement.StockMovementResponseDTO;
import org.atabero.inventory.model.enums.MovementType;

public interface StockMovementService {

    StockMovementResponseDTO create(MovementType movementType, CreateStockMovementDTO dto);
}
