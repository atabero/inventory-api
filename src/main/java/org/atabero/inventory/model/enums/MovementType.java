package org.atabero.inventory.model.enums;

import lombok.Getter;

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

    private final String description;
    private final boolean isEntry;

    MovementType(String description, boolean isEntry) {
        this.description = description;
        this.isEntry = isEntry;
    }

}
