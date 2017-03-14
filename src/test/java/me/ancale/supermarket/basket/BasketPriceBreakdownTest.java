package me.ancale.supermarket.basket;

import me.ancale.supermarket.product.entity.Product;
import me.ancale.supermarket.promotion.entity.MultiApplicability;
import me.ancale.supermarket.promotion.entity.PercentDiscountabiliy;
import me.ancale.supermarket.promotion.entity.Promotion;
import me.ancale.supermarket.promotion.entity.SingleApplicability;
import org.joda.money.CurrencyUnit;
import org.joda.money.Money;
import org.junit.Test;

import java.math.BigDecimal;

import static me.ancale.supermarket.product.entity.Product.builder;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class BasketPriceBreakdownTest {

    @Test
    public void calculatePriceBreakdownForEmptyBasket() throws Exception {
        Basket basket = new Basket();

        PriceBreakDown prices = basket.calculatePrices();

        assertThat(prices.getTotal(), is(Money.zero(CurrencyUnit.GBP)));

        assertThat(prices.getDiscountedTotal(), is(Money.zero(CurrencyUnit.GBP)));
    }

    @Test
    public void calculatePriceBreakdownForMatchingMixOfPromotions() {
        Basket basket = new Basket();
        Promotion promo1 = new Promotion("id1", "Buy 1 get 10% off",
                new SingleApplicability(), new PercentDiscountabiliy(BigDecimal.TEN));

        Promotion promo2 = new Promotion("id2", "Buy 2 get 20% off",
                new MultiApplicability(2), new PercentDiscountabiliy(new BigDecimal("20")));

        basket.addProduct(product("sku1", "coffee", Money.of(CurrencyUnit.GBP, BigDecimal.TEN), promo1));

        basket.addProduct(product("sku2", "tea", Money.of(CurrencyUnit.GBP, BigDecimal.TEN), promo2));

        basket.addProduct(product("sku3", "chocolate", Money.of(CurrencyUnit.GBP, BigDecimal.TEN), promo1));

        basket.addProduct(product("sku4", "milk", Money.of(CurrencyUnit.GBP, BigDecimal.TEN), promo2));


        PriceBreakDown priceBreakDown = basket.calculatePrices();

        assertThat(priceBreakDown.getTotal(), is(Money.of(CurrencyUnit.GBP, new BigDecimal("40"))));
        assertThat(priceBreakDown.getDiscountedTotal(), is(Money.of(CurrencyUnit.GBP, new BigDecimal("34"))));

    }

    public Product product(String sku, String description, Money price, Promotion promotion) {
        return builder().setSku(sku).setDescription(description)
                .setPrice(price)
                .setPromotion(promotion)
                .build();
    }

}
