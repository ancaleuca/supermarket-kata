package me.ancale.supermarket.promotion;

import org.joda.money.Money;

public class ValueDiscountability implements Discountability {

    private final Money value;

    public ValueDiscountability(Money value) {
        this.value = value;
    }

    @Override
    public Money discounted(Money money) {
        return money.minus(value);
    }

    public Money getValue() {
        return value;
    }
}
