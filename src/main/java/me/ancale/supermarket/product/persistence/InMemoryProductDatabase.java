package me.ancale.supermarket.product.persistence;

import me.ancale.supermarket.product.entity.Product;

import java.util.HashMap;
import java.util.Map;

public class InMemoryProductDatabase implements ProductDatabase {

    private final Map<String, Product> products = new HashMap<>();

    @Override
    public Product getProduct(String sku) {
        return products.get(sku);
    }

    @Override
    public void addProduct(Product product) {
        products.put(product.getSku(), product);
    }

    @Override
    public void deleteProduct(String sku) {
        products.remove(sku);
    }

    @Override
    public void clear() {
        products.clear();
    }
}
