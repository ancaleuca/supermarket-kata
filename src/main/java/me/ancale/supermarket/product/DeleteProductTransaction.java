package me.ancale.supermarket.product;

import me.ancale.supermarket.Transaction;

import static me.ancale.supermarket.Databases.productDatabase;

public class DeleteProductTransaction implements Transaction {

    private final String sku;

    public DeleteProductTransaction(String sku) {
        this.sku = sku;
    }

    @Override
    public void execute() {
        productDatabase().deleteProduct(sku);
    }
}
