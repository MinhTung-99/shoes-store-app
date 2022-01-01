package com.shoes_store_app.network.response;

import com.google.gson.annotations.SerializedName;

public class ProductItemResponse {
    @SerializedName("itemId")
    private int itemId;
    @SerializedName("productId")
    private int productId;
    @SerializedName("color")
    private String color;
    @SerializedName("size")
    private int size;
    @SerializedName("numItems")
    private int numItems;
    @SerializedName("sale")
    private int sale;
    @SerializedName("status")
    private int status;
    @SerializedName("img")
    private String img;

    private String updateTime;
    private double price;
    private String productName;


    public int getItemId() {
        return itemId;
    }

    public int getProductId() {
        return productId;
    }

    public String getColor() {
        return color;
    }

    public int getSize() {
        return size;
    }

    public int getNumItems() {
        return numItems;
    }

    public int getSale() {
        return sale;
    }

    public int getStatus() {
        return status;
    }

    public String getImg() {
        return img;
    }

    public String getUpdateTime() {
        return updateTime;
    }

    public double getPrice() {
        return price;
    }

    public String getProductName() {
        return productName;
    }

    public void setItemId(int itemId) {
        this.itemId = itemId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public void setNumItems(int numItems) {
        this.numItems = numItems;
    }

    public void setSale(int sale) {
        this.sale = sale;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public void setUpdateTime(String updateTime) {
        this.updateTime = updateTime;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }
}
