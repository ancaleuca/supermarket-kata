package me.ancale.supermarket;

import me.ancale.supermarket.product.InMemoryProductDatabase;
import me.ancale.supermarket.product.ProductDatabase;

public class Databases {

    private static final ProductDatabase productDatabase = new InMemoryProductDatabase();

    public static ProductDatabase productDatabase() {
        return productDatabase;
    }

}
