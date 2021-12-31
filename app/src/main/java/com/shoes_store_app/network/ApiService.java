package com.shoes_store_app.network;

import com.shoes_store_app.network.request.UserRequest;
import com.shoes_store_app.network.request.UserUpdateRequest;
import com.shoes_store_app.network.response.TestResponse;
import com.shoes_store_app.network.response.UserPostResponse;
import com.shoes_store_app.network.response.UserResponse;

import java.util.List;

import io.reactivex.Single;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface ApiService {
    @GET("user")
    Single<List<UserResponse>> getUsers();

    @GET("user/id")
    Single<UserResponse> getUserById(@Query(value = "id") Integer id);

    @POST("user/add")
    Single<UserPostResponse> addUser (@Body UserRequest userRequest);

    @DELETE("user/delete/{id}")
    Single<UserPostResponse> deleteUser (@Path(value = "id") int id);

    @PUT("user/update/{email}")
    Single<UserPostResponse> updateUser (@Path(value = "email") String email, @Body UserUpdateRequest userUpdateRequest);
}
