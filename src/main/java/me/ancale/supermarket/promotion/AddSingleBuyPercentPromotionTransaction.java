package me.ancale.supermarket.promotion;

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
