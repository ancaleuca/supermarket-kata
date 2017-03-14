package me.ancale.supermarket.product;

import me.ancale.supermarket.Databases;
import org.joda.money.CurrencyUnit;
import org.joda.money.Money;
import org.junit.Test;

import java.math.BigDecimal;

import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsNull.notNullValue;
import static org.hamcrest.core.IsNull.nullValue;
import static org.junit.Assert.assertThat;

public class AddProductTransactionTest {

    @Test
    public void addANewProduct() {
        AddProductTransaction t = new AddProductTransaction(
                "sku1", "coffee", Money.of(CurrencyUnit.GBP, BigDecimal.TEN));

        Product product = Databases.productDatabase().getProduct("sku1");
        assertThat(product, nullValue());

        t.execute();

        product = Databases.productDatabase().getProduct("sku1");

        assertThat(product, notNullValue());
        assertThat(product.getSku(), is("sku1"));
        assertThat(product.getDescription(), is("coffee"));
        assertThat(product.getPrice(), is(Money.of(CurrencyUnit.GBP, BigDecimal.TEN)));
    }
}
