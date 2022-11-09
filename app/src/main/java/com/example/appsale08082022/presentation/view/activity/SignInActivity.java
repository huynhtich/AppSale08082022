package com.example.appsale08082022.presentation.view.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.appsale08082022.R;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.example.appsale08082022.R;
import com.example.appsale08082022.data.model.AppResource;
import com.example.appsale08082022.data.model.User;
import com.example.appsale08082022.presentation.viewmodel.SignInViewModel;

public class SignInActivity extends AppCompatActivity {

    SignInViewModel signInViewModel;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);

        signInViewModel = new ViewModelProvider(this, new ViewModelProvider.Factory() {
            @NonNull
            @Override
            public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
                return (T) new SignInViewModel(SignInActivity.this);
            }
        }).get(SignInViewModel.class);

        observer();
        event();
    }

    private void observer() {
        signInViewModel.getUserResource().observe(this, new Observer<AppResource<User>>() {
            @Override
            public void onChanged(AppResource<User> userAppResource) {
                switch (userAppResource.status) {
                    case ERROR:
                        Log.d("BBB",  userAppResource.message);
                        break;
                    case LOADING:
                        break;
                    case SUCCESS:
                        Log.d("BBB", userAppResource.data.toString());
                        break;
                }
            }
        });
    }

    private void event() {
        signInViewModel.signIn("demo1@gmail.com", "12345678");
    }
}