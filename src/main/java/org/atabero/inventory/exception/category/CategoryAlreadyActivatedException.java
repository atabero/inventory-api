package org.atabero.inventory.exception.category;

public class CategoryAlreadyActivatedException extends RuntimeException {
    public CategoryAlreadyActivatedException(String message) {
        super(message);
    }

    public CategoryAlreadyActivatedException(Long id) {
        super("La categoría con ID " + id + " ya está desactivada.");
    }
}
