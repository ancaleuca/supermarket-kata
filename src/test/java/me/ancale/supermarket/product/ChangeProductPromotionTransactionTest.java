package me.ancale.supermarket.product;

import me.ancale.supermarket.promotion.AddSingleBuyPercentPromotionTransaction;
import me.ancale.supermarket.promotion.Promotion;
import org.joda.money.CurrencyUnit;
import org.joda.money.Money;
import org.junit.Test;

import java.math.BigDecimal;

import static me.ancale.supermarket.Databases.productDatabase;
import static me.ancale.supermarket.Databases.promotionDatabase;
import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsNull.notNullValue;
import static org.junit.Assert.assertThat;

public class ChangeProductPromotionTransactionTest {

    @Test
    public void addNewPromotionOnProduct() throws Exception {
        String promoId = "promoId";
        String sku = "sku";

        AddSingleBuyPercentPromotionTransaction t1 = new AddSingleBuyPercentPromotionTransaction(promoId, "d", BigDecimal.TEN);
        t1.execute();
        Promotion promotion = promotionDatabase().getPromotion(promoId);
        assertThat(promotion, notNullValue());

        AddProductTransaction t2 = new AddProductTransaction(sku, "d", Money.of(CurrencyUnit.GBP, BigDecimal.TEN));
        t2.execute();
        Product product = productDatabase().getProduct(sku);
        assertThat(product.getPromotion().isPresent(), is(false));

        ChangeProductPromotionTransaction t3 = new ChangeProductPromotionTransaction(promoId, sku);
        t3.execute();

        product = productDatabase().getProduct(sku);
        assertThat(product.getPromotion().isPresent(), is(true));
        assertThat(product.getPromotion().get(), is(promotion));
    }
}
