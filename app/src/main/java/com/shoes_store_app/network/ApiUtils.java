package com.shoes_store_app.network;

public class ApiUtils {
    public static ApiService getApiService() {
        return ApiClient.getClient("https://22dd-14-176-11-122.ngrok.io").create(ApiService.class);
    }
}
