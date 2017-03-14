package me.ancale.supermarket.product.transaction;

import me.ancale.supermarket.Transaction;
import me.ancale.supermarket.product.entity.Product;
import me.ancale.supermarket.promotion.entity.Promotion;

import static me.ancale.supermarket.Databases.productDatabase;
import static me.ancale.supermarket.Databases.promotionDatabase;

public class ChangeProductPromotionTransaction implements Transaction {

    private final String promotionId;
    private final String sku;

    public ChangeProductPromotionTransaction(String promotionId, String sku) {
        this.promotionId = promotionId;
        this.sku = sku;
    }

    @Override
    public void execute() {
        Promotion promotion = promotionDatabase().getPromotion(promotionId);
        Product product = productDatabase().getProduct(sku);

        productDatabase().addProduct(product.toBuilder().setPromotion(promotion).build());
    }
}
