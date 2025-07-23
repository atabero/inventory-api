package org.atabero.inventory.model.enums;

import lombok.Getter;

/**
 * Enum que representa los posibles estados de un proveedor.
 */
@Getter
public enum SupplierStatus {

    /**
     * Proveedor activo y operativo.
     */
    ACTIVE("Active"),

    /**
     * Proveedor inactivo o deshabilitado.
     */
    INACTIVE("Inactive");

    private final String label;

    SupplierStatus(String label) {
        this.label = label;
    }
}
