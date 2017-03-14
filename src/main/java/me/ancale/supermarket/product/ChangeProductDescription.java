package me.ancale.supermarket.product;

import static com.google.common.base.Preconditions.checkArgument;
import static com.google.common.base.Strings.isNullOrEmpty;
import static me.ancale.supermarket.Databases.productDatabase;

public class ChangeProductDescription extends ChangeProductTransaction {

    private final String description;

    public ChangeProductDescription(String sku, String description) {
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
