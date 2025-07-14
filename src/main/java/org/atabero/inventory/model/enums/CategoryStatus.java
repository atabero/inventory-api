package org.atabero.inventory.model.enums;

import lombok.Getter;

@Getter
public enum CategoryStatus {
    ACTIVE("Active"),
    INACTIVE("Inactive");

    private final String label;

    CategoryStatus(String label) {
        this.label = label;
    }

}
