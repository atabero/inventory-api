package org.atabero.inventory.exception.product;

public class ProductAlreadyDeactivatedException extends RuntimeException{

    public ProductAlreadyDeactivatedException(Long id) {
        super("La producto con ID " + id + " ya está desactivada.");
    }
    public ProductAlreadyDeactivatedException(String message) {
        super(message);
    }
}
