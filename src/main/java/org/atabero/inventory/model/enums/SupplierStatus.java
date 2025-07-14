package org.atabero.inventory.model.enums;


import lombok.Getter;

@Getter
public enum SupplierStatus {
    ACTIVE("Active"),
    INACTIVE("Inactive");

    private final String label;

    SupplierStatus(String label) {
        this.label = label;
    }


}
