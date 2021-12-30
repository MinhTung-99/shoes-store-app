package com.shoes_store_app.network;

public class ApiUtils {
    public static ApiService getApiService() {
        return ApiClient.getClient("https://api.publicapis.org/").create(ApiService.class);
    }
}
