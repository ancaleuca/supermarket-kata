package me.ancale.supermarket.product;

import me.ancale.supermarket.SupermarketTransactionTest;
import org.joda.money.CurrencyUnit;
import org.joda.money.Money;
import org.junit.Test;

import java.math.BigDecimal;

import static me.ancale.supermarket.Databases.productDatabase;
import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsNull.notNullValue;
import static org.hamcrest.core.IsNull.nullValue;
import static org.junit.Assert.assertThat;

public class AddProductTransactionTest extends SupermarketTransactionTest {

    @Test
    public void addANewProduct() {
        AddProductTransaction t = new AddProductTransaction(
                "sku1", "coffee", Money.of(CurrencyUnit.GBP, BigDecimal.TEN));

        Product product = productDatabase().getProduct("sku1");
        assertThat(product, nullValue());

        t.execute();

        product = productDatabase().getProduct("sku1");

        assertThat(product, notNullValue());
        assertThat(product.getSku(), is("sku1"));
        assertThat(product.getDescription(), is("coffee"));
        assertThat(product.getPrice(), is(Money.of(CurrencyUnit.GBP, BigDecimal.TEN)));
    }

    @Test(expected = IllegalStateException.class)
    public void cannotAddProductIfAlreadyExists() {
        AddProductTransaction t1 = new AddProductTransaction(
                "sku1", "coffee", Money.of(CurrencyUnit.GBP, BigDecimal.TEN));
        t1.execute();

        AddProductTransaction t2 = new AddProductTransaction(
                "sku1", "duplicate", Money.of(CurrencyUnit.GBP, BigDecimal.TEN));

        t2.execute();
    }
}
