package me.ancale.supermarket.promotion.entity;

import org.joda.money.Money;

public interface Discountability {

    Money discount(Money money);
}
