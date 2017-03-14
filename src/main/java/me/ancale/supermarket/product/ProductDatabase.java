package me.ancale.supermarket.product;

public interface ProductDatabase {

    Product getProduct(String sku);

    void addProduct(Product product);
}
