package com.shoes_store_app.network.request;

import com.google.gson.annotations.SerializedName;

public class UserUpdateRequest {
    @SerializedName("passWord")
    private String password;
    @SerializedName("fullName")
    private String fullName;
    @SerializedName("gender")
    private String gender;
    @SerializedName("address")
    private String address;

    public UserUpdateRequest(String password, String fullName, String gender, String address) {
        this.password = password;
        this.fullName = fullName;
        this.gender = gender;
        this.address = address;
    }
}
