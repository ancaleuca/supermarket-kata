package me.ancale.supermarket.product.transaction;

import me.ancale.supermarket.Transaction;

public abstract class ChangeProductTransaction implements Transaction {

    private final String sku;

    public ChangeProductTransaction(String sku) {
        this.sku = sku;
    }

    @Override
    public void execute() {
        change(sku);
    }

    public abstract void change(String sku);
}
