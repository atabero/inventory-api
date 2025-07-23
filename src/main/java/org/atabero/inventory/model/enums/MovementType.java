package org.atabero.inventory.model.enums;

import lombok.Getter;

/**
 * Enum que representa los tipos de movimientos de stock.
 * <p>
 * Los movimientos se clasifican en dos grandes grupos:
 * <ul>
 *   <li><b>Entradas de stock</b>: incrementan la cantidad disponible.</li>
 *   <li><b>Salidas de stock</b>: reducen la cantidad disponible.</li>
 * </ul>
 * </p>
 */
@Getter
public enum MovementType {
    // Entradas de stock
    PURCHASE("Compra a proveedor", true),
    RETURN("Devolución de cliente", true),
    ADJUSTMENT_POSITIVE("Ajuste positivo", true),
    MANUAL_ENTRY("Entrada manual", true),

    // Salidas de stock
    SALE("Venta a cliente", false),
    BREAKAGE("Rotura", false),
    LOSS("Pérdida por extravío", false),
    ADJUSTMENT_NEGATIVE("Ajuste negativo", false),
    MANUAL_EXIT("Salida manual", false);

    /**
     * Descripción legible del tipo de movimiento.
     */
    private final String description;

    /**
     * Indica si el movimiento es una entrada (true) o salida (false) de stock.
     */
    private final boolean isEntry;

    MovementType(String description, boolean isEntry) {
        this.description = description;
        this.isEntry = isEntry;
    }

}
