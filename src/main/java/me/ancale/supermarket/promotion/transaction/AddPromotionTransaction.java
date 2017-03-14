package me.ancale.supermarket.promotion.transaction;

import me.ancale.supermarket.Transaction;
import me.ancale.supermarket.promotion.entity.Applicability;
import me.ancale.supermarket.promotion.entity.Discount;
import me.ancale.supermarket.promotion.entity.Promotion;

import static me.ancale.supermarket.Databases.promotionDatabase;

public abstract class AddPromotionTransaction implements Transaction {

    private final String id;
    private final String description;

    protected AddPromotionTransaction(String id, String description) {
        this.id = id;
        this.description = description;
    }

    @Override
    public void execute() {
        if (promotionDatabase().hasPromotion(id)) {
            throw new IllegalStateException(String.format("Promotion %s already exists", id));
        }
        Applicability applicability = makeApplicability();
        Discount discount = makeDiscount();
        promotionDatabase().addPromotion(new Promotion(id, description, applicability, discount));
    }

    protected abstract Applicability makeApplicability();

    protected abstract Discount makeDiscount();
}
