package com.example.demo1;

public class User
{
    private String product;
    private String price;
    private Integer quantity;

    public User(String productTXT, String priceTXT,Integer quantityTXT){
        product = productTXT;
        price = priceTXT;
        quantity = quantityTXT;
    }

    public String getProduct() {
        return product;
    }

    public String getPrice() {
        return price;
    }

    public Integer getQuantity() {return quantity; }
}
