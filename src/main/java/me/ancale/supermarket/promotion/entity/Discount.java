package me.ancale.supermarket.promotion.entity;

import org.joda.money.Money;

public interface Discount {

    Money discount(Money money);
}
