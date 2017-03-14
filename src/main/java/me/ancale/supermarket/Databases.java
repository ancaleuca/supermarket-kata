package me.ancale.supermarket;

import me.ancale.supermarket.product.persistence.InMemoryProductDatabase;
import me.ancale.supermarket.product.persistence.ProductDatabase;
import me.ancale.supermarket.promotion.persistence.InMemoryPromotionDatabase;
import me.ancale.supermarket.promotion.persistence.PromotionDatabase;

public class Databases {

    private static final ProductDatabase productDatabase = new InMemoryProductDatabase();
    private static final PromotionDatabase promotionDatabase = new InMemoryPromotionDatabase();

    public static ProductDatabase productDatabase() {
        return productDatabase;
    }

    public static PromotionDatabase promotionDatabase() {
        return promotionDatabase;
    }

    public static void clear() {
        productDatabase().clear();
        promotionDatabase().clear();
    }

}
