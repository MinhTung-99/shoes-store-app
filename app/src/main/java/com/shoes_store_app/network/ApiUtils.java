package com.shoes_store_app.network;

public class ApiUtils {
    public static ApiService getApiService() {
        return ApiClient.getClient("https://9cda-171-229-223-66.ngrok.io").create(ApiService.class);
    }
}
