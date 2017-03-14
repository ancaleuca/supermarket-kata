package me.ancale.supermarket;

import me.ancale.supermarket.product.InMemoryProductDatabase;
import me.ancale.supermarket.product.ProductDatabase;
import me.ancale.supermarket.promotion.InMemoryPromotionDatabase;
import me.ancale.supermarket.promotion.PromotionDatabase;

public class Databases {

    private static final ProductDatabase productDatabase = new InMemoryProductDatabase();
    private static final PromotionDatabase promotionDatabase = new InMemoryPromotionDatabase();

    public static ProductDatabase productDatabase() {
        return productDatabase;
    }

    public static PromotionDatabase promotionDatabase() {
        return promotionDatabase;
    }

}
