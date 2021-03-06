package me.ancale.supermarket.promotion.persistence;

import me.ancale.supermarket.promotion.entity.Promotion;

import java.util.HashMap;
import java.util.Map;

public class InMemoryPromotionDatabase implements PromotionDatabase {

    private final Map<String, Promotion> promotions = new HashMap<>();

    @Override
    public void addPromotion(Promotion promotion) {
        promotions.put(promotion.getId(), promotion);
    }

    @Override
    public boolean hasPromotion(String promotionId) {
        return promotions.containsKey(promotionId);
    }

    @Override
    public Promotion getPromotion(String promotionId) {
        return promotions.get(promotionId);
    }

    @Override
    public void clear() {
        promotions.clear();
    }
}
