package com.example.appsale08082022.presentation.viewmodel;

import android.content.Context;
import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.appsale08082022.data.model.AppResource;
import com.example.appsale08082022.data.model.User;
import com.example.appsale08082022.data.remote.dto.UserDTO;
import com.example.appsale08082022.data.repository.AuthenticationRepository;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by tichhv on 11/9/2022.
 **/
public class SignInViewModel extends ViewModel {
    private MutableLiveData<AppResource<User>> userResource = new MutableLiveData<>();
    private AuthenticationRepository repository;

    public SignInViewModel(Context context) {
        repository = new AuthenticationRepository(context);
    }

    public LiveData<AppResource<User>> getUserResource() {
        return userResource;
    }

    public void signIn(String email, String password){
        userResource.setValue(new AppResource.Loading(null));
        Call<AppResource<UserDTO>> callSignIn = repository.signIn(email, password);
        callSignIn.enqueue(new Callback<AppResource<UserDTO>>() {
            @Override
            public void onResponse(Call<AppResource<UserDTO>> call, Response<AppResource<UserDTO>> response) {
                if (response.isSuccessful()) {
                    AppResource<UserDTO> userDTOAppResource = response.body();
                    UserDTO userDTO = userDTOAppResource.data;
                    User user = new User(userDTO.getEmail(), userDTO.getName(), userDTO.getPhone(), userDTO.getToken());
                    userResource.setValue(new AppResource.Success<>(user));
                } else {
                    Log.d("BBB", response.errorBody().toString());
                }
            }

            @Override
            public void onFailure(Call<AppResource<UserDTO>> call, Throwable t) {
                userResource.setValue(new AppResource.Error<>(t.getMessage()));
            }
        });
    }
}
