package org.atabero.inventory.exception;

public class SupplierAlreadyActivatedException extends RuntimeException {

    public SupplierAlreadyActivatedException(String message) {
        super(message);
    }

    public SupplierAlreadyActivatedException(Long id) {
        super("El proveedor con ID " + id + " ya está activada.");
    }
}
