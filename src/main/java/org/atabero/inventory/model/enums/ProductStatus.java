package org.atabero.inventory.model.enums;

import lombok.Getter;

/**
 * Enum que representa los posibles estados de un producto dentro del inventario.
 * Cada estado incluye una etiqueta descriptiva y reglas sobre los movimientos de stock permitidos.
 */
@Getter
public enum ProductStatus {

    /**
     * Producto activo y disponible para venta y operaciones normales.
     */
    ACTIVE("Activo – Disponible para la venta y operaciones"),

    /**
     * Producto inactivo, no visible ni disponible para venta.
     */
    INACTIVE("Inactivo – No se muestra ni se puede vender"),

    /**
     * Producto descontinuado, puede venderse pero no reponerse.
     */
    DISCONTINUED("Descontinuado – Se puede vender pero no reponer"),

    /**
     * Producto bloqueado por razones sanitarias o legales, no operable.
     */
    BLOCKED("Bloqueado – Prohibido por razones sanitarias o legales"),

    /**
     * Producto temporalmente no disponible, proveedor no puede surtirlo.
     */
    UNAVAILABLE("No disponible – Proveedor no puede surtir temporalmente");

    private final String label;

    ProductStatus(String label) {
        this.label = label;
    }

    /**
     * Indica si el producto permite entradas de stock (reposición).
     *
     * @return true si el estado permite entradas de stock; false en caso contrario.
     */
    public boolean allowsStockEntry() {
        return this == ACTIVE;
    }

    /**
     * Indica si el producto permite salidas de stock (ventas, ajustes negativos, etc.).
     *
     * @return true si el estado permite salidas de stock; false en caso contrario.
     */
    public boolean allowsStockExit() {
        return this == ACTIVE || this == DISCONTINUED || this == UNAVAILABLE;
    }

    /**
     * Indica si el producto permite cualquier tipo de movimiento de stock (entrada o salida).
     *
     * @return true si se permiten movimientos de stock; false en caso contrario.
     */
    public boolean allowsStockMovement() {
        return allowsStockEntry() || allowsStockExit();
    }

    /**
     * Indica si el producto está bloqueado de forma permanente (no operable).
     *
     * @return true si el producto está bloqueado; false en caso contrario.
     */
    public boolean isPermanentlyBlocked() {
        return this == BLOCKED;
    }
}
