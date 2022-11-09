package com.example.appsale08082022.data.remote;
import android.content.Context;

import com.example.appsale08082022.common.AppConstant;
import com.example.appsale08082022.data.remote.ApiService;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import android.content.Context;

import retrofit2.Retrofit;

/**
 * Created by tichhv on 11/7/2022.
 **/


public class RetrofitClient {
    private static RetrofitClient instance = null;
    private Retrofit retrofit;
    private ApiService apiService;

    private RetrofitClient(Context context) {
        retrofit = createRetrofit(context);
        apiService = retrofit.create(ApiService.class);
    }

    public static RetrofitClient getInstance(Context context) {
        if (instance == null) {
            instance = new RetrofitClient(context);
        }
        return instance;
    }

    private Retrofit createRetrofit(Context context) {
        HttpLoggingInterceptor logRequest = new HttpLoggingInterceptor();
        logRequest.setLevel(HttpLoggingInterceptor.Level.BODY);

        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .readTimeout(30, TimeUnit.SECONDS)
                .writeTimeout(30, TimeUnit.SECONDS)
                .connectTimeout(30, TimeUnit.SECONDS)
                .addInterceptor(logRequest)
                .build();

        Gson gson = new GsonBuilder().create();

        return new Retrofit.Builder()
                .baseUrl(AppConstant.BASE_URL)
                .client(okHttpClient)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();
    }

    public ApiService getApiService() {
        if (apiService != null) {
            return apiService;
        } else {
            return retrofit.create(ApiService.class);
        }
    }
}
