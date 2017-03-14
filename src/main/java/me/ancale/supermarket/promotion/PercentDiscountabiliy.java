package me.ancale.supermarket.promotion;

import org.joda.money.Money;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class PercentDiscountabiliy implements Discountability {

    private static final RoundingMode ROUNDING_MODE = RoundingMode.HALF_UP;

    private final BigDecimal percent;

    public PercentDiscountabiliy(BigDecimal percent) {
        this.percent = percent;
    }

    @Override
    public Money discount(Money money) {
        Money discount = money.multipliedBy(percent, ROUNDING_MODE).dividedBy(100, ROUNDING_MODE);
        return money.minus(discount);
    }

    public BigDecimal getPercent() {
        return percent;
    }
}
