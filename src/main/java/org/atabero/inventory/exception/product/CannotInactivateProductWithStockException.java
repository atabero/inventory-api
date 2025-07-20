package org.atabero.inventory.exception.product;

public class CannotInactivateProductWithStockException extends RuntimeException {

    public CannotInactivateProductWithStockException(String message) {
        super(message);
    }
}