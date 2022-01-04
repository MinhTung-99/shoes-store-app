package com.shoes_store_app.network.response;

import com.google.gson.annotations.SerializedName;

public class OrderResponse {
    @SerializedName("idUser")
    private int idUser;
    @SerializedName("itemId")
    private int itemId;
    @SerializedName("purchaseQuantity")
    private int purchaseQuantity;
    @SerializedName("process")
    private int process;
    @SerializedName("createTime")
    private String createTime;
    @SerializedName("money")
    private String money;

    public int getIdUser() {
        return idUser;
    }

    public int getItemId() {
        return itemId;
    }

    public int getPurchaseQuantity() {
        return purchaseQuantity;
    }

    public int getProcess() {
        return process;
    }

    public String getCreateTime() {
        return createTime;
    }

    public String getMoney() {
        return money;
    }
}
