package me.ancale.supermarket.promotion.entity;

import org.joda.money.Money;

public class ValueDiscount implements Discount {

    private final Money value;

    public ValueDiscount(Money value) {
        this.value = value;
    }

    @Override
    public Money discount(Money money) {
        return value;
    }

    public Money getValue() {
        return value;
    }
}
