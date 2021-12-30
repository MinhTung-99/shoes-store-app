package com.shoes_store_app.network.response;

import com.google.gson.annotations.SerializedName;

public class TestResponse {

    @SerializedName("count")
    private Integer count;

    public Integer getCount() {
        return count;
    }
}
