package org.atabero.inventory.exception.stockmovemen;

public class StockReplenishmentNotAllowedException extends RuntimeException {
    public StockReplenishmentNotAllowedException(String message) {
        super(message);
    }
}