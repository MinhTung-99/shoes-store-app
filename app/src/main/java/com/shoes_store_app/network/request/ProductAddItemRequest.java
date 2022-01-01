package com.shoes_store_app.network.request;

import com.google.gson.annotations.SerializedName;

public class ProductAddItemRequest {
    @SerializedName("productName")
    private String productName;
    @SerializedName("colorName")
    private String colorName;
    @SerializedName("size")
    private String size;
    @SerializedName("numItems")
    private String numItems;
    @SerializedName("sale")
    private String sale;

    public ProductAddItemRequest(String productName, String colorName, String size, String numItems, String sale) {
        this.productName = productName;
        this.colorName = colorName;
        this.size = size;
        this.numItems = numItems;
        this.sale = sale;
    }
}
