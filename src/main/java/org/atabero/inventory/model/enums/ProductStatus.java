package org.atabero.inventory.model.enums;

import lombok.Getter;

@Getter
public enum ProductStatus {
    ACTIVE("Activo – Disponible para la venta y operaciones"),
    INACTIVE("Inactivo – No se muestra ni se puede vender"),
    DISCONTINUED("Descontinuado – Se puede vender pero no reponer"),
    BLOCKED("Bloqueado – Prohibido por razones sanitarias o legales"),
    UNAVAILABLE("No disponible – Proveedor no puede surtir temporalmente");

    private final String label;

    ProductStatus(String label) {
        this.label = label;
    }

    /**
     * Permite realizar entradas de stock (reposición)
     */
    public boolean allowsStockEntry() {
        return this == ACTIVE;
    }

    /**
     * Permite realizar salidas de stock (ventas, ajustes negativos, etc.)
     */
    public boolean allowsStockExit() {
        return this == ACTIVE || this == DISCONTINUED || this == UNAVAILABLE;
    }

    /**
     * Permite cualquier tipo de movimiento de stock (entrada o salida)
     */
    public boolean allowsStockMovement() {
        return allowsStockEntry() || allowsStockExit();
    }

    /**
     * Producto bloqueado de forma permanente (no operable)
     */
    public boolean isPermanentlyBlocked() {
        return this == BLOCKED;
    }
}
