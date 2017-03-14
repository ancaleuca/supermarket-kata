package me.ancale.supermarket.basket;

import me.ancale.supermarket.product.entity.Product;
import me.ancale.supermarket.promotion.entity.Promotion;
import org.joda.money.CurrencyUnit;
import org.joda.money.Money;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class Basket {

    private final List<Product> products;

    public Basket() {
        products = new ArrayList<>();
    }

    public void addProduct(Product product) {
        products.add(product);
    }

    public PriceBreakDown calculatePrices() {
        Money total = calculateTotal(products);
        Money discount = calculateDiscountTotal();
        return new PriceBreakDown(total, total.minus(discount));
    }

    private Money calculateDiscountTotal() {
        List<Product> products = getPromotedProductsSortedByPromoThenPrice();
        Money totalDiscount = Money.zero(CurrencyUnit.GBP);
        for (int i = 0; i < products.size(); i++) {
            Product product = products.get(i);
            Promotion promotion = product.getPromotion().get();
            int applicabilitySetSize = promotion.getApplicability().applicabilitySetSize();
            String promoId = promotion.getId();
            List<Product> candidates = products.subList(i, i + applicabilitySetSize);
            if (candidates.stream().allMatch(p -> p.getPromotion().isPresent() && p.getPromotion().get().getId().equals(promoId))) {
                totalDiscount = totalDiscount.plus(
                        promotion.getDiscountability().discount(calculateTotal(candidates)));
                i = i + applicabilitySetSize - 1;
            }
        }
        return totalDiscount;
    }

    private Money calculateTotal(List<Product> products) {
        return products.stream().map(Product::getPrice).reduce(Money.zero(CurrencyUnit.GBP), Money::plus);
    }


    private List<Product> getPromotedProductsSortedByPromoThenPrice() {
        List<Product> promotedProducts = products.stream().filter(p -> p.getPromotion().isPresent()).collect(Collectors.toList());
        Comparator<Product> comparator = Comparator.comparing(p -> p.getPromotion().get().getId());
        comparator.thenComparing(Product::getPrice);
        promotedProducts.sort(comparator);
        return promotedProducts;
    }
}
