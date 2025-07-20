package org.atabero.inventory.exception;

public class CannotInactivateProductWithStockException extends RuntimeException {

    public CannotInactivateProductWithStockException(String message) {
        super(message);
    }
}