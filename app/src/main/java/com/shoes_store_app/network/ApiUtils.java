package com.shoes_store_app.network;

public class ApiUtils {
    public static ApiService getApiService() {
        return ApiClient.getClient("https://91a6-14-176-11-122.ngrok.io").create(ApiService.class);
    }
}
