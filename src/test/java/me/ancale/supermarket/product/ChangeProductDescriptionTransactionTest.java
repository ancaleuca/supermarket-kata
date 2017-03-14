package me.ancale.supermarket.product;

import me.ancale.supermarket.TransactionTest;
import me.ancale.supermarket.product.entity.Product;
import me.ancale.supermarket.product.transaction.AddProductTransaction;
import me.ancale.supermarket.product.transaction.ChangeProductDescriptionTransaction;
import me.ancale.supermarket.product.transaction.ChangeProductTransaction;
import org.joda.money.CurrencyUnit;
import org.joda.money.Money;
import org.junit.Test;

import java.math.BigDecimal;

import static me.ancale.supermarket.Databases.productDatabase;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class ChangeProductDescriptionTransactionTest extends TransactionTest {

    @Test
    public void changeDescription() {
        String sku = "sku1";
        String description = "coffee";
        Money price = Money.of(CurrencyUnit.GBP, BigDecimal.TEN);
        String newDescription = "better coffee";

        AddProductTransaction t1 = new AddProductTransaction(sku, description, price);
        t1.execute();

        ChangeProductTransaction t2 = new ChangeProductDescriptionTransaction(sku, newDescription);

        t2.execute();

        Product product = productDatabase().getProduct(sku);

        assertThat(product.getSku(), is(sku));
        assertThat(product.getDescription(), is(newDescription));
        assertThat(product.getPrice(), is(price));
    }

    @Test(expected = IllegalStateException.class)
    public void cannotChangeProductDescriptionIfProductDoesNotExist() throws Exception {
        ChangeProductTransaction t2 = new ChangeProductDescriptionTransaction("sku", "description");
        t2.execute();
    }
}
