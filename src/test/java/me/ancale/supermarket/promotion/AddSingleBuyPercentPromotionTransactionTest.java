package me.ancale.supermarket.promotion;

import me.ancale.supermarket.TransactionTest;
import me.ancale.supermarket.promotion.entity.PercentDiscount;
import me.ancale.supermarket.promotion.entity.Promotion;
import me.ancale.supermarket.promotion.entity.SingleApplicability;
import me.ancale.supermarket.promotion.transaction.AddSingleBuyPercentPromotionTransaction;
import org.junit.Test;

import java.math.BigDecimal;

import static me.ancale.supermarket.Databases.promotionDatabase;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class AddSingleBuyPercentPromotionTransactionTest extends TransactionTest {

    @Test
    public void addPromotion() throws Exception {
        String id = "promoId";
        String description = "Buy one pay 10% less";
        BigDecimal percent = new BigDecimal(10);

        AddSingleBuyPercentPromotionTransaction t = new AddSingleBuyPercentPromotionTransaction(id, description, percent);
        t.execute();

        Promotion promotion = promotionDatabase().getPromotion(id);
        assertThat(promotion.getId(), is(id));
        assertThat(promotion.getDescription(), is(description));
        assertThat(promotion.getApplicability() instanceof SingleApplicability, is(true));
        assertThat(promotion.getDiscount() instanceof PercentDiscount, is(true));
        assertThat(((PercentDiscount)promotion.getDiscount()).getPercent(), is(percent));
    }
}
