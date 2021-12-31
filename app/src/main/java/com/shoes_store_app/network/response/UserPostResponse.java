package com.shoes_store_app.network.response;

import com.google.gson.annotations.SerializedName;

public class UserPostResponse {
    @SerializedName("code")
    private String code;

    public String getCode() {
        return code;
    }
}
