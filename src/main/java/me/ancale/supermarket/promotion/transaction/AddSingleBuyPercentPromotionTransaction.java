package me.ancale.supermarket.promotion.transaction;

import me.ancale.supermarket.promotion.entity.Applicability;
import me.ancale.supermarket.promotion.entity.Discountability;
import me.ancale.supermarket.promotion.entity.PercentDiscountabiliy;
import me.ancale.supermarket.promotion.entity.SingleApplicability;

import java.math.BigDecimal;

public class AddSingleBuyPercentPromotionTransaction extends AddPromotionTransaction {

    private final BigDecimal percent;

    public AddSingleBuyPercentPromotionTransaction(String id, String description, BigDecimal percent) {
        super(id, description);
        this.percent = percent;
    }

    @Override
    protected Applicability makeApplicability() {
        return new SingleApplicability();
    }

    @Override
    protected Discountability makeDiscountability() {
        return new PercentDiscountabiliy(percent);
    }
}
