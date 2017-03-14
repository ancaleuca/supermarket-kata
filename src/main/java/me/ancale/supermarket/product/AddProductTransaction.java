package me.ancale.supermarket.product;

import me.ancale.supermarket.Databases;
import me.ancale.supermarket.Transaction;
import org.joda.money.Money;

public class AddProductTransaction implements Transaction {

    private final String sku;
    private final String description;
    private final Money price;

    public AddProductTransaction(String sku, String description, Money price) {
        this.sku = sku;
        this.description = description;
        this.price = price;
    }

    public void execute() {
        Product product = new Product(sku, description, price);
        Databases.productDatabase().addProduct(product);
    }
}
