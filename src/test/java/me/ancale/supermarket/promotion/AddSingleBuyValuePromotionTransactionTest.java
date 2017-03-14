package me.ancale.supermarket.promotion;

import org.hamcrest.core.IsNull;
import org.joda.money.CurrencyUnit;
import org.joda.money.Money;
import org.junit.Test;

import java.math.BigDecimal;

import static me.ancale.supermarket.Databases.promotionDatabase;
import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsNull.notNullValue;
import static org.junit.Assert.assertThat;

public class AddSingleBuyValuePromotionTransactionTest {

    @Test
    public void addPromotion() throws Exception {
        String id = "promoid";
        String description = "Pay £2 less";
        Money value = Money.of(CurrencyUnit.GBP, new BigDecimal(2));
        AddSingleBuyValuePromotionTransaction t = new AddSingleBuyValuePromotionTransaction(
                id, description, value);

        t.execute();

        Promotion promotion = promotionDatabase().getPromotion(id);
        assertThat(promotion, notNullValue());
        assertThat(promotion.getDescription(), is(description));
        assertThat(promotion.getApplicability() instanceof SingleApplicability, is(true));
    }
}
