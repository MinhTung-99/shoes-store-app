package com.shoes_store_app.network;

import com.shoes_store_app.network.response.TestResponse;
import com.shoes_store_app.network.response.UserResponse;

import java.util.List;

import io.reactivex.Single;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ApiService {
    @GET("user")
    Single<List<UserResponse>> getUsers();

    @GET("user/id")
    Single<UserResponse> getUserById(@Query(value = "id") Integer id);
}
