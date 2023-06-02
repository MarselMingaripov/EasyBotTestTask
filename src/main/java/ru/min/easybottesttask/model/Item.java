package ru.min.easybottesttask.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
public abstract class Item {

    private Long serialNumber;
    private String producer;
    private int price;
    private int stockCount;

    public Item(Long serialNumber, String producer, int price, int stockCount) {
        this.serialNumber = serialNumber;
        this.producer = producer;
        this.price = price;
        this.stockCount = stockCount;
    }

    public Item() {
    }
}
