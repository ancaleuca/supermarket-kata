package me.ancale.supermarket.product;

import me.ancale.supermarket.Transaction;

import static com.google.common.base.Preconditions.checkArgument;
import static com.google.common.base.Strings.isNullOrEmpty;
import static me.ancale.supermarket.Databases.productDatabase;

public class DeleteProductTransaction implements Transaction {

    private final String sku;

    public DeleteProductTransaction(String sku) {
        checkArgument(!isNullOrEmpty(sku));
        this.sku = sku;
    }

    @Override
    public void execute() {
        if (productDatabase().getProduct(sku) == null) {
            throw new IllegalStateException(String.format("Product %s does not exist", sku));
        }
        productDatabase().deleteProduct(sku);
    }
}
