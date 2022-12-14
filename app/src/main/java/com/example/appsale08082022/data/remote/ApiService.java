package com.example.appsale08082022.data.remote;

import com.example.appsale08082022.data.model.AppResource;
import com.example.appsale08082022.data.remote.dto.UserDTO;

import java.util.HashMap;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

/**
 * Created by tichhv on 11/7/2022.
 **/
public interface ApiService {
    @POST("/user/sign-in")
    Call<AppResource<UserDTO>> signIn(@Body HashMap<String, Object> body);

    @POST("/user/sign-up")
    Call<AppResource<UserDTO>> register(@Body HashMap<String, Object> body);
}
