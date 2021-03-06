package me.ancale.supermarket.promotion;

import me.ancale.supermarket.TransactionTest;
import me.ancale.supermarket.promotion.entity.MultiApplicability;
import me.ancale.supermarket.promotion.entity.PercentDiscount;
import me.ancale.supermarket.promotion.entity.Promotion;
import me.ancale.supermarket.promotion.transaction.AddMultiBuyPercentPromotionTransaction;
import org.junit.Test;

import java.math.BigDecimal;

import static me.ancale.supermarket.Databases.promotionDatabase;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class AddMultiBuyPercentPromotionTransactionTest extends TransactionTest {

    @Test
    public void addPromotion() throws Exception {
        String id = "promoId";
        String description = "Buy 3 get 20% off";
        int setSize = 3;
        BigDecimal percent = new BigDecimal(20);

        AddMultiBuyPercentPromotionTransaction t = new AddMultiBuyPercentPromotionTransaction(id, description, setSize, percent);

        t.execute();

        Promotion promotion = promotionDatabase().getPromotion(id);
        assertThat(promotion.getId(), is(id));
        assertThat(promotion.getDescription(), is(description));
        assertThat(promotion.getDiscount() instanceof PercentDiscount, is(true));
        assertThat(((PercentDiscount)promotion.getDiscount()).getPercent(), is(percent));
        assertThat(promotion.getApplicability() instanceof MultiApplicability, is(true));
        assertThat(promotion.getApplicability().applicabilitySetSize(), is(setSize));
    }
}
