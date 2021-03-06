package me.ancale.supermarket.promotion;

import me.ancale.supermarket.TransactionTest;
import me.ancale.supermarket.promotion.entity.Promotion;
import me.ancale.supermarket.promotion.entity.SingleApplicability;
import me.ancale.supermarket.promotion.entity.ValueDiscount;
import me.ancale.supermarket.promotion.transaction.AddSingleBuyValuePromotionTransaction;
import org.joda.money.CurrencyUnit;
import org.joda.money.Money;
import org.junit.Test;

import java.math.BigDecimal;

import static me.ancale.supermarket.Databases.promotionDatabase;
import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsNull.notNullValue;
import static org.junit.Assert.assertThat;

public class AddSingleBuyValuePromotionTransactionTest extends TransactionTest {

    @Test
    public void addPromotion() throws Exception {
        String id = "promoid";
        String description = "Buy one pay £2 less";
        Money value = Money.of(CurrencyUnit.GBP, new BigDecimal(2));
        AddSingleBuyValuePromotionTransaction t = new AddSingleBuyValuePromotionTransaction(
                id, description, value);

        t.execute();

        Promotion promotion = promotionDatabase().getPromotion(id);
        assertThat(promotion, notNullValue());
        assertThat(promotion.getDescription(), is(description));
        assertThat(promotion.getApplicability() instanceof SingleApplicability, is(true));
        assertThat(promotion.getDiscount() instanceof ValueDiscount, is(true));
        assertThat(((ValueDiscount)promotion.getDiscount()).getValue(), is(value));
    }
}
