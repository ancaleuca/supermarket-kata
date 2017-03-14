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
        Product existing = productDatabase().getProduct(sku);
        Product changed = new Product(existing.getSku(), description, existing.getPrice());
        productDatabase().addProduct(changed);
    }
}
