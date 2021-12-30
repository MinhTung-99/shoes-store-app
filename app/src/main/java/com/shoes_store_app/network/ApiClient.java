package com.shoes_store_app.network;

import java.util.Arrays;
import java.util.concurrent.TimeUnit;

import okhttp3.ConnectionSpec;
import okhttp3.Dispatcher;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiClient {

    private static Retrofit retrofit = null;

    public static Retrofit getClient(String baseUrl) {
        if (retrofit == null) {
            HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
            Dispatcher dispatcher = new Dispatcher();
            dispatcher.setMaxRequests(1);
            interceptor.setLevel(HttpLoggingInterceptor.Level.HEADERS);
            /*if (BuildConfig.DEBUG) {
                interceptor.setLevel(HttpLoggingInterceptor.Level.HEADERS);
            }*/
            OkHttpClient client = new OkHttpClient
                    .Builder()
                    .addInterceptor(interceptor)
                  //  .authenticator(new RSAuthenticator())
                    .addInterceptor(chain -> {
                        Request.Builder newRequest = chain.request().newBuilder();

                        newRequest.addHeader("Content-Type", "application/json;charset=UTF-8");
                       // newRequest.addHeader("User-Agent", getUserAgent());
                        return chain.proceed(newRequest.build());
                    })
                    .readTimeout(120, TimeUnit.SECONDS)
                    .dispatcher(dispatcher)
                    .connectTimeout(120, TimeUnit.SECONDS)
                    .connectionSpecs(Arrays.asList(ConnectionSpec.COMPATIBLE_TLS, ConnectionSpec.CLEARTEXT))
                    .build();

            retrofit = new Retrofit.Builder()
                    .baseUrl(baseUrl)
                    .addConverterFactory(GsonConverterFactory.create())
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .client(client)
                    .build();
        }
        return retrofit;
    }
}
