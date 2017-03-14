package me.ancale.supermarket.product;

import org.joda.money.Money;

public class Product {

    private final String sku;
    private final String description;
    private final Money price;

    public Product(String sku, String description, Money price) {
        this.sku = sku;
        this.description = description;
        this.price = price;
    }

    public String getSku() {
        return sku;
    }

    public String getDescription() {
        return description;
    }

    public Money getPrice() {
        return price;
    }
}
