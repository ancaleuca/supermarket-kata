package me.ancale.supermarket.product;

import me.ancale.supermarket.TransactionTest;
import me.ancale.supermarket.product.entity.Product;
import me.ancale.supermarket.product.transaction.AddProductTransaction;
import me.ancale.supermarket.product.transaction.ChangeProductPriceTransaction;
import me.ancale.supermarket.product.transaction.ChangeProductTransaction;
import org.joda.money.CurrencyUnit;
import org.joda.money.Money;
import org.junit.Test;

import java.math.BigDecimal;

import static me.ancale.supermarket.Databases.productDatabase;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class ChangeProductPriceTransactionTest extends TransactionTest {

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
