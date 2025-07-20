package org.atabero.inventory.exception.category;

public class CategoryAlreadyDeactivatedException extends RuntimeException {

    public CategoryAlreadyDeactivatedException(Long id) {
        super("La categoría con ID " + id + " ya está desactivada.");
    }
    public CategoryAlreadyDeactivatedException(String message) {
        super(message);
    }
}
