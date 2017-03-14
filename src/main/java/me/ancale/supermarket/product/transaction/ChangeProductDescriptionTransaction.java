package me.ancale.supermarket.product.transaction;

import me.ancale.supermarket.product.entity.Product;

import static com.google.common.base.Preconditions.checkArgument;
import static com.google.common.base.Strings.isNullOrEmpty;
import static me.ancale.supermarket.Databases.productDatabase;

public class ChangeProductDescriptionTransaction extends ChangeProductTransaction {

    private final String description;

    public ChangeProductDescriptionTransaction(String sku, String description) {
        super(sku);
        checkArgument(!isNullOrEmpty(description));
        this.description = description;
    }

    @Override
    public void change(String sku) {
        Product product = productDatabase().getProduct(sku);
        if (product == null) {
            throw new IllegalStateException(String.format("Product %s does not exist", sku));
        }
        productDatabase().addProduct(product.toBuilder().setDescription(description).build());
    }
}
