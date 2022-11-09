package com.example.appsale08082022.data.repository;

import android.content.Context;

import com.example.appsale08082022.data.model.AppResource;
import com.example.appsale08082022.data.remote.ApiService;
import com.example.appsale08082022.data.remote.RetrofitClient;
import com.example.appsale08082022.data.remote.dto.UserDTO;

import java.util.HashMap;

import retrofit2.Call;

/**
 * Created by tichhv on 11/9/2022.
 **/
public class AuthenticationRepository {
    private ApiService apiService;

    public AuthenticationRepository(Context context) {
        apiService = RetrofitClient.getInstance(context).getApiService();
    }

    public Call<AppResource<UserDTO>> signIn(String email, String password) {
        HashMap<String, Object> body = new HashMap<>();
        body.put("email", email);
        body.put("password", password);
        return apiService.signIn(body);
    }
}
