package me.ancale.supermarket.promotion;

import org.joda.money.Money;

public class AddSingleBuyValuePromotionTransaction extends AddPromotionTransaction {

    private final Money value;

    protected AddSingleBuyValuePromotionTransaction(String id, String description, Money value) {
        super(id, description);
        this.value = value;
    }

    @Override
    protected Applicability makeApplicability() {
        return new SingleApplicability();
    }

    @Override
    protected Discountability makeDiscountability() {
        return new ValueDiscountability(value);
    }
}
