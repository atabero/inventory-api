package org.atabero.inventory.util;

import lombok.Getter;

/**
 * Clase que representa información sobre el stock antes y después de una operación.
 * Contiene la cantidad previa y la nueva cantidad del stock.
 */
@Getter
public class StockInfo {

    /** Cantidad previa de stock antes de la operación */
    private final Integer previousQuantity;

    /** Nueva cantidad de stock después de la operación */
    private final Integer newQuantity;

    /**
     * Constructor para inicializar la información de stock con las cantidades anterior y nueva.
     *
     * @param previousQuantity la cantidad de stock antes del cambio
     * @param newQuantity      la cantidad de stock después del cambio
     */
    public StockInfo(int previousQuantity, int newQuantity) {
        this.previousQuantity = previousQuantity;
        this.newQuantity = newQuantity;
    }
}
