package me.ancale.supermarket.promotion.entity;

import com.google.common.base.Objects;

public class Promotion {

    private final String id;

    private final String description;

    private final Applicability applicability;

    private final Discount discount;

    public Promotion(String id, String description,
                        Applicability applicability,
                        Discount discount) {
        this.id = id;
        this.description = description;
        this.applicability = applicability;
        this.discount = discount;
    }

    public String getId() {
        return id;
    }

    public String getDescription() {
        return description;
    }

    public Applicability getApplicability() {
        return applicability;
    }

    public Discount getDiscount() {
        return discount;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Promotion promotion = (Promotion) o;
        return Objects.equal(id, promotion.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }
}
