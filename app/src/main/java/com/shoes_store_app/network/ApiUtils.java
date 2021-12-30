package com.shoes_store_app.network;

public class ApiUtils {
    public static ApiService getApiService() {
        return ApiClient.getClient("http://192.168.100.6:8090/").create(ApiService.class);
    }
}
