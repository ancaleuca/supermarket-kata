package me.ancale.supermarket.promotion.entity;

import org.joda.money.Money;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class PercentDiscount implements Discount {

    private static final RoundingMode ROUNDING_MODE = RoundingMode.HALF_UP;

    private final BigDecimal percent;

    public PercentDiscount(BigDecimal percent) {
        this.percent = percent;
    }

    @Override
    public Money discount(Money money) {
        return money.multipliedBy(percent, ROUNDING_MODE).dividedBy(100, ROUNDING_MODE);
    }

    public BigDecimal getPercent() {
        return percent;
    }
}
