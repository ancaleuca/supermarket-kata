package me.ancale.supermarket.promotion.transaction;

import me.ancale.supermarket.promotion.entity.Applicability;
import me.ancale.supermarket.promotion.entity.Discount;
import me.ancale.supermarket.promotion.entity.MultiApplicability;
import me.ancale.supermarket.promotion.entity.PercentDiscount;

import java.math.BigDecimal;

public class AddMultiBuyPercentPromotionTransaction extends AddPromotionTransaction {

    private final int setSize;
    private final BigDecimal percent;

    public AddMultiBuyPercentPromotionTransaction(String id, String description, int setSize, BigDecimal percent) {
        super(id, description);
        this.setSize = setSize;
        this.percent = percent;
    }

    @Override
    protected Applicability makeApplicability() {
        return new MultiApplicability(setSize);
    }

    @Override
    protected Discount makeDiscount() {
        return new PercentDiscount(percent);
    }
}
