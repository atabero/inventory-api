package org.atabero.inventory.util;

import lombok.Getter;

@Getter
public class StockInfo {
    private final Integer previousQuantity;
    private final Integer newQuantity;

    public StockInfo(int previousQuantity, int newQuantity) {
        this.previousQuantity = previousQuantity;
        this.newQuantity = newQuantity;
    }

}