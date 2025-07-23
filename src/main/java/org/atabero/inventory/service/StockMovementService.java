package org.atabero.inventory.service;

import org.atabero.inventory.dto.stockmovement.CreateStockMovementDTO;
import org.atabero.inventory.dto.stockmovement.StockMovementResponseDTO;
import org.atabero.inventory.model.enums.MovementType;

/**
 * Servicio para gestionar movimientos de stock.
 */
public interface StockMovementService {

    /**
     * Crea un movimiento de stock según el tipo y los datos proporcionados.
     *
     * @param movementType tipo de movimiento de stock (entrada, salida, ajuste, etc.).
     * @param dto          datos necesarios para crear el movimiento de stock.
     * @return un objeto {@link StockMovementResponseDTO} con la información del movimiento creado.
     */
    StockMovementResponseDTO create(MovementType movementType, CreateStockMovementDTO dto);
}
