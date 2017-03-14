package me.ancale.supermarket.promotion;

public interface PromotionDatabase {

    void addPromotion(Promotion promotion);

    boolean hasPromotion(String promotionId);

    Promotion getPromotion(String promotionId);

    void clear();
}
