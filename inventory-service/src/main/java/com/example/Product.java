package com.example;

public class Product {

    public Long id;
    public String name;
    public double price;
    public int quantity;

    public Product() {
    }

    public Product(Long id, String name, double price, int quantity) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.quantity = quantity;
    }
}
