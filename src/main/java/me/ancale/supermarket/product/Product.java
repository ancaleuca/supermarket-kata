package me.ancale.supermarket.product;

import me.ancale.supermarket.promotion.Promotion;
import org.joda.money.Money;

import java.util.Optional;

import static com.google.common.base.Preconditions.checkNotNull;
import static com.google.common.base.Preconditions.checkState;
import static com.google.common.base.Strings.isNullOrEmpty;

public class Product {

    private final String sku;
    private final String description;
    private final Money price;
    private final Promotion promotion;

    private Product(Builder builder) {
        this.sku = builder.sku;
        this.description = builder.description;
        this.price = builder.price;
        this.promotion = builder.promotion;
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

    public Optional<Promotion> getPromotion() {
        return Optional.ofNullable(promotion);
    }

    public static Builder builder() {
        return new Builder();
    }

    public Builder toBuilder() {
        return new Builder(this.sku, this.description, this.price, this.promotion);
    }

    public static class Builder {
        private String sku;
        private String description;
        private Money price;
        private Promotion promotion;

        public Builder() {
        }

        public Builder(String sku, String description, Money price, Promotion promotion) {
            this.sku = sku;
            this.description = description;
            this.price = price;
            this.promotion = promotion;
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

        public Builder setPromotion(Promotion promotion) {
            this.promotion = promotion;
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
