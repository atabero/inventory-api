package org.atabero.inventory.exception;

public class CategoryNotFoundException extends RuntimeException {
    public CategoryNotFoundException(Long id) {
        super("No se encontró la categoría con ID: " + id);
    }

    public CategoryNotFoundException(String message) {
        super(message);
    }
}
