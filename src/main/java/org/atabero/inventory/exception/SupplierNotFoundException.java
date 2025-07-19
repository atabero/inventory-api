package org.atabero.inventory.exception;

public class SupplierNotFoundException extends RuntimeException {
    public SupplierNotFoundException(String message) {
        super(message);
    }

    public SupplierNotFoundException(Long id) {
        super("No se encontró el proveedor con ID: " + id);
    }
}
