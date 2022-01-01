package com.shoes_store_app.network.request;

import com.google.gson.annotations.SerializedName;

public class ProductAddRequest {

    @SerializedName("productName")
    private String productName;
    @SerializedName("importPrice")
    private double importPrice;
    @SerializedName("price")
    private double price;
    @SerializedName("updaterId")
    private int updaterId;

    public ProductAddRequest(String productName, double importPrice, double price, int updaterId) {
        this.productName = productName;
        this.importPrice = importPrice;
        this.price = price;
        this.updaterId = updaterId;
    }
}
