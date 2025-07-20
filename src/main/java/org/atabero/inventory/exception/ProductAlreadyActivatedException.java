package org.atabero.inventory.exception;

public class ProductAlreadyActivatedException extends RuntimeException {
    public ProductAlreadyActivatedException(String message) {
        super(message);
    }

    public ProductAlreadyActivatedException(Long id) {
        super("El producto con ID " + id + " ya está desactivada.");
    }
}
