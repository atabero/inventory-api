package org.atabero.inventory.exception.productstatuschangelog;

import java.util.UUID;

public class ProductStatusChangeLogNotFoundException extends RuntimeException {

    public ProductStatusChangeLogNotFoundException(UUID id) {
        super("No se encontró el producto con ID: " + id);
    }

    public ProductStatusChangeLogNotFoundException(String message) {
        super(message);
    }
}
