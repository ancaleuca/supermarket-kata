package me.ancale.supermarket.product.persistence;

import me.ancale.supermarket.product.entity.Product;

public interface ProductDatabase {

    Product getProduct(String sku);

    void addProduct(Product product);

    void deleteProduct(String sku);

    void clear();
}
