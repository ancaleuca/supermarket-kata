package me.ancale.supermarket.promotion;

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
