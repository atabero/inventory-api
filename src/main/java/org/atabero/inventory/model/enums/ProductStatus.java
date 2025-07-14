package org.atabero.inventory.model.enums;

import lombok.Getter;

@Getter
public enum ProductStatus {
    ACTIVE("Active"),
    INACTIVE("Inactive"),
    DISCONTINUED("Discontinued");

    private final String label;

    ProductStatus(String label) {
        this.label = label;
    }

}
