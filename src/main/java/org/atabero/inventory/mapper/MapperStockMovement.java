package org.atabero.inventory.mapper;

import org.atabero.inventory.dto.stockmovement.StockMovementResponseDTO;
import org.atabero.inventory.model.StockMovement;

public class MapperStockMovement {

    private MapperStockMovement() {
    }


    public static StockMovementResponseDTO toResponse(StockMovement movement, Integer previousQuantity, Integer newQuantity) {
        return new StockMovementResponseDTO(
                movement.getId(), movement.getProduct().getId(), movement.getProduct().getName(),
                previousQuantity, newQuantity,
                movement.getQuantityChange(), movement.getMovementType(), movement.getOperationStatus(),
                movement.getTimestamp(), movement.getNotes()
        );
    }

}
