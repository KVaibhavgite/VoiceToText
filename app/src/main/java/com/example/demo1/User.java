package com.example.demo1;

public class User
{
    private String product;
    private String price;
    //private String quantity;

    public User(String productTXT, String priceTXT){
        product = productTXT;
        price = priceTXT;
        //quantity = quantityTXT;
    }

    public String getProduct() {
        return product;
    }

    public String getPrice() {
        return price;
    }

    /*public String getQuantity() {
        return quantity;
    }*/
}
