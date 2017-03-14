package me.ancale.supermarket.promotion.persistence;

import me.ancale.supermarket.promotion.entity.Promotion;

public interface PromotionDatabase {

    void addPromotion(Promotion promotion);

    boolean hasPromotion(String promotionId);

    Promotion getPromotion(String promotionId);

    void clear();
}
