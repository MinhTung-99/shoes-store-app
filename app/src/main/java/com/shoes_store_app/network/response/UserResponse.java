package com.shoes_store_app.network.response;

import com.google.gson.annotations.SerializedName;

public class UserResponse {
    @SerializedName("id")
    private Integer id;
    @SerializedName("email")
    private String email;
    @SerializedName("roleId")
    private Integer roleId;
    @SerializedName("passWord")
    private String password;
    @SerializedName("fullName")
    private String fullName;
    @SerializedName("address")
    private String address;
    @SerializedName("phoneNumber")
    private String phoneNumber;
    @SerializedName("gender")
    private String gender;
    @SerializedName("status")
    private Integer status;
    @SerializedName("userName")
    private String userName;

    public Integer getId() {
        return id;
    }

    public String getEmail() {
        return email;
    }

    public Integer getRoleId() {
        return roleId;
    }

    public String getPassword() {
        return password;
    }

    public String getFullName() {
        return fullName;
    }

    public String getAddress() {
        return address;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getGender() {
        return gender;
    }

    public Integer getStatus() {
        return status;
    }

    public String getUserName() {
        return userName;
    }
}
