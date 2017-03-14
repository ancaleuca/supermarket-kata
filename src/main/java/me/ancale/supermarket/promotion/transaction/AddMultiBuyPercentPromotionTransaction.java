package me.ancale.supermarket.promotion.transaction;

import me.ancale.supermarket.promotion.entity.Applicability;
import me.ancale.supermarket.promotion.entity.Discountability;
import me.ancale.supermarket.promotion.entity.MultiApplicability;
import me.ancale.supermarket.promotion.entity.PercentDiscountabiliy;

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
    protected Discountability makeDiscountability() {
        return new PercentDiscountabiliy(percent);
    }
}
