package com.shoes_store_app.network;

import com.shoes_store_app.network.response.TestResponse;

import io.reactivex.Single;
import retrofit2.http.GET;

public interface ApiService {
    @GET("entries")
    Single<TestResponse> testApi();
}
