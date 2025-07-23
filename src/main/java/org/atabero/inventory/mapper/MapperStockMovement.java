package org.atabero.inventory.mapper;

import org.atabero.inventory.dto.stockmovement.StockMovementResponseDTO;
import org.atabero.inventory.model.StockMovement;

/**
 * Clase utilitaria para mapear la entidad StockMovement
 * a su correspondiente DTO de respuesta.
 */
public class MapperStockMovement {

    // Constructor privado para evitar instanciación
    private MapperStockMovement() {
    }

    /**
     * Convierte una entidad StockMovement en un DTO de respuesta,
     * incluyendo las cantidades anteriores y nuevas para reflejar
     * el cambio de stock.
     *
     * @param movement         La entidad StockMovement a convertir.
     * @param previousQuantity Cantidad de stock antes del movimiento.
     * @param newQuantity      Cantidad de stock después del movimiento.
     * @return DTO que representa el movimiento de stock.
     */
    public static StockMovementResponseDTO toResponse(StockMovement movement, Integer previousQuantity, Integer newQuantity) {
        return new StockMovementResponseDTO(
                movement.getId(), movement.getProduct().getId(), movement.getProduct().getName(),
                previousQuantity, newQuantity,
                movement.getQuantityChange(), movement.getMovementType(), movement.getOperationStatus(),
                movement.getTimestamp(), movement.getNotes()
        );
    }
}
