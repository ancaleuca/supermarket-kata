package me.ancale.supermarket.product;

import org.joda.money.CurrencyUnit;
import org.joda.money.Money;
import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;

import static me.ancale.supermarket.Databases.productDatabase;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class ChangeProductPriceTest {

    @Before
    public void before() throws Exception {
        productDatabase().clear();
    }

    @Test
    public void changeProductPrice() throws Exception {
        String sku = "sku1";
        String description = "coffee";
        Money price = Money.of(CurrencyUnit.GBP, BigDecimal.TEN);
        Money newPrice = Money.of(CurrencyUnit.GBP, BigDecimal.ONE);

        AddProductTransaction t1 = new AddProductTransaction(sku, description, price);
        t1.execute();

        ChangeProductTransaction t2 = new ChangeProductPriceTransaction(sku, newPrice);

        t2.execute();

        Product product = productDatabase().getProduct(sku);

        assertThat(product.getSku(), is(sku));
        assertThat(product.getDescription(), is(description));
        assertThat(product.getPrice(), is(newPrice));
    }

    @Test(expected = IllegalStateException.class)
    public void cannotChangePriceIfProductDoesNotExist() {
        ChangeProductTransaction t2 = new ChangeProductPriceTransaction("sku", Money.of(CurrencyUnit.GBP, BigDecimal.ONE));
        t2.execute();
    }
}
