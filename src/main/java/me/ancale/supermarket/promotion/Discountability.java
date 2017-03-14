package me.ancale.supermarket.promotion;

import org.joda.money.Money;

public interface Discountability {

    Money discount(Money money);
}
