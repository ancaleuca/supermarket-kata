package me.ancale.supermarket.product;

import com.google.common.base.Strings;
import org.joda.money.Money;

import static com.google.common.base.Preconditions.checkArgument;
import static com.google.common.base.Preconditions.checkNotNull;
import static com.google.common.base.Preconditions.checkState;
import static com.google.common.base.Strings.isNullOrEmpty;

public class Product {

    private final String sku;
    private final String description;
    private final Money price;

    private Product(Builder builder) {
        this.sku = builder.sku;
        this.description = builder.description;
        this.price = builder.price;
    }

    public String getSku() {
        return sku;
    }

    public String getDescription() {
        return description;
    }

    public Money getPrice() {
        return price;
    }

    public static Builder builder() {
        return new Builder();
    }

    public Builder toBuilder() {
        return new Builder(this.sku, this.description, this.price);
    }

    public static class Builder {
        private String sku;
        private String description;
        private Money price;

        public Builder() {

        }

        public Builder(String sku, String description, Money price) {
            this.sku = sku;
            this.description = description;
            this.price = price;
        }

        public Builder setSku(String sku) {
            this.sku = sku;
            return this;
        }

        public Builder setDescription(String description) {
            this.description = description;
            return this;
        }

        public Builder setPrice(Money price) {
            this.price = price;
            return this;
        }

        public Product build() {
            checkState(!isNullOrEmpty(sku));
            checkState(!isNullOrEmpty(description));
            checkNotNull(price);
            return new Product(this);
        }
    }
}
