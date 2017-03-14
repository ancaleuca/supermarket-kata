package me.ancale.supermarket.product;

import me.ancale.supermarket.Transaction;
import org.joda.money.Money;

import static com.google.common.base.Preconditions.checkArgument;
import static com.google.common.base.Preconditions.checkNotNull;
import static com.google.common.base.Strings.isNullOrEmpty;
import static me.ancale.supermarket.Databases.productDatabase;

public class AddProductTransaction implements Transaction {

    private final String sku;
    private final String description;
    private final Money price;

    public AddProductTransaction(String sku, String description, Money price) {
        checkNotNull(isNullOrEmpty(sku));
        checkArgument(!isNullOrEmpty(description));
        checkNotNull(price);

        this.sku = sku;
        this.description = description;
        this.price = price;
    }

    public void execute() {
        validateProduct();
        Product product = Product.builder().setSku(sku).setDescription(description).setPrice(price).build();
        productDatabase().addProduct(product);
    }

    private void validateProduct() {
        if (productDatabase().getProduct(sku) != null) {
            throw new IllegalStateException(String.format("Product %s already exists", sku));
        }
    }
}
