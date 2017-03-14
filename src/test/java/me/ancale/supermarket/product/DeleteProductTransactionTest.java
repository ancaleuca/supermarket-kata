package me.ancale.supermarket.product;

import org.joda.money.CurrencyUnit;
import org.joda.money.Money;
import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;

import static me.ancale.supermarket.Databases.productDatabase;
import static org.hamcrest.core.IsNull.notNullValue;
import static org.hamcrest.core.IsNull.nullValue;
import static org.junit.Assert.assertThat;

public class DeleteProductTransactionTest {

    @Before
    public void before() {
        productDatabase().deleteAllProducts();
    }

    @Test
    public void deleteAnExistingProduct() {
        String sku = "sku1";
        AddProductTransaction t1 = new AddProductTransaction(
                sku, "coffee", Money.of(CurrencyUnit.GBP, BigDecimal.TEN));

        t1.execute();

        assertThat(productDatabase().getProduct(sku), notNullValue());

        DeleteProductTransaction t2 = new DeleteProductTransaction(sku);

        t2.execute();

        assertThat(productDatabase().getProduct(sku), nullValue());
    }

    @Test(expected = IllegalStateException.class)
    public void cannotDeleteAProductIfDoesNotExist() {
        DeleteProductTransaction t = new DeleteProductTransaction("sku1");
        t.execute();
    }
}
