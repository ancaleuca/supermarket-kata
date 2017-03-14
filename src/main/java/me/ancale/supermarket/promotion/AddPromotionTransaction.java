package me.ancale.supermarket.promotion;

import me.ancale.supermarket.Transaction;

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
        Discountability discountability = makeDiscountability();
        promotionDatabase().addPromotion(new Promotion(id, description, applicability, discountability));
    }

    protected abstract Applicability makeApplicability();

    protected abstract Discountability makeDiscountability();
}
