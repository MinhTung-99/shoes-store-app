package com.shoes_store_app.network.response;

import com.google.gson.annotations.SerializedName;

public class ProductResponse {

    @SerializedName("productId")
    private int productId;
    @SerializedName("productName")
    private String productName;
    @SerializedName("importPrice")
    private double importPrice;
    @SerializedName("price")
    private double price;
    @SerializedName("updaterId")
    private int updaterId;
    @SerializedName("updateTime")
    private String updateTime;
    @SerializedName("status")
    private int status;

    public int getProductId() {
        return productId;
    }

    public String getProductName() {
        return productName;
    }

    public double getImportPrice() {
        return importPrice;
    }

    public double getPrice() {
        return price;
    }

    public int getUpdaterId() {
        return updaterId;
    }

    public String getUpdateTime() {
        return updateTime;
    }

    public int getStatus() {
        return status;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public void setImportPrice(double importPrice) {
        this.importPrice = importPrice;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public void setUpdaterId(int updaterId) {
        this.updaterId = updaterId;
    }

    public void setUpdateTime(String updateTime) {
        this.updateTime = updateTime;
    }

    public void setStatus(int status) {
        this.status = status;
    }
}
