package org.atabero.inventory.exception.stockmovemen;

public class InsufficientStockException extends RuntimeException {
    public InsufficientStockException(String message) {
        super(message);
    }
}
