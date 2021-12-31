package com.shoes_store_app.network.request;

import com.google.gson.annotations.SerializedName;

public class UserRequest {
    @SerializedName("userName")
    private String userName;
    @SerializedName("passWord")
    private String password;
    @SerializedName("fullName")
    private String fullName;
    @SerializedName("gender")
    private String gender;
    @SerializedName("address")
    private String address;
    @SerializedName("phoneNumber")
    private String phoneNumber;
    @SerializedName("email")
    private String email;
    @SerializedName("role")
    private String role;
    @SerializedName("status")
    private String status;

    public UserRequest(String userName, String password, String fullName, String gender, String address, String phoneNumber, String email, String role, String status) {
        this.userName = userName;
        this.password = password;
        this.fullName = fullName;
        this.gender = gender;
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.role = role;
        this.status = status;
    }
}
