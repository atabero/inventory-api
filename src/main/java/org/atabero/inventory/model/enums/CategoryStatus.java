package org.atabero.inventory.model.enums;

import lombok.Getter;

/**
 * Enum que representa los posibles estados de una categoría.
 * <p>
 * - ACTIVE: Categoría activa y disponible para uso.
 * - INACTIVE: Categoría inactiva o deshabilitada.
 * </p>
 */
@Getter
public enum CategoryStatus {
    ACTIVE("Active"),
    INACTIVE("Inactive");

    /**
     * Etiqueta legible asociada al estado.
     */
    private final String label;

    CategoryStatus(String label) {
        this.label = label;
    }
}
