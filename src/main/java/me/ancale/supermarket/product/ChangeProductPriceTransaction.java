package me.ancale.supermarket.product;

import org.joda.money.Money;

import static com.google.common.base.Preconditions.checkNotNull;
import static me.ancale.supermarket.Databases.productDatabase;

public class ChangeProductPriceTransaction extends ChangeProductTransaction {

    private final Money price;

    public ChangeProductPriceTransaction(String sku, Money price) {
        super(sku);
        checkNotNull(price);
        this.price = price;
    }

    @Override
    public void change(String sku) {
        Product product = productDatabase().getProduct(sku);
        if (product == null) {
            throw new IllegalStateException(String.format("Product %s does not exist", sku));
        }
        productDatabase().addProduct(product.toBuilder().setPrice(price).build());
    }
}
