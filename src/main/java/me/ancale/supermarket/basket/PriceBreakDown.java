package me.ancale.supermarket.basket;

import org.joda.money.Money;

public class PriceBreakDown {

    private final Money total;
    private final Money discountedTotal;

    public PriceBreakDown(Money total, Money discountedTotal) {
        this.total = total;
        this.discountedTotal = discountedTotal;
    }

    public Money getTotal() {
        return total;
    }

    public Money getDiscountedTotal() {
        return discountedTotal;
    }
}
