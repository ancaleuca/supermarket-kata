package me.ancale.supermarket.promotion.transaction;

import me.ancale.supermarket.promotion.entity.Applicability;
import me.ancale.supermarket.promotion.entity.Discount;
import me.ancale.supermarket.promotion.entity.SingleApplicability;
import me.ancale.supermarket.promotion.entity.ValueDiscount;
import org.joda.money.Money;

public class AddSingleBuyValuePromotionTransaction extends AddPromotionTransaction {

    private final Money value;

    public AddSingleBuyValuePromotionTransaction(String id, String description, Money value) {
        super(id, description);
        this.value = value;
    }

    @Override
    protected Applicability makeApplicability() {
        return new SingleApplicability();
    }

    @Override
    protected Discount makeDiscount() {
        return new ValueDiscount(value);
    }
}
