package org.atabero.inventory.exception.supplier;

public class SupplierAlreadyDeactivatedException extends RuntimeException {
    public SupplierAlreadyDeactivatedException(String message) {
        super(message);
    }

    public SupplierAlreadyDeactivatedException(Long id) {
        super("El proveedor con ID " + id + " ya está activada.");
    }
}
