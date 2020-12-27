package com.example.nerdeyesem.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.nerdeyesem.model.AuthAppRepository;
import com.google.firebase.auth.FirebaseUser;

public class LogInViewModel extends AndroidViewModel {

    private final AuthAppRepository authAppRepository;
    private final MutableLiveData<FirebaseUser> userMutableLiveData;

    public LogInViewModel(@NonNull Application application) {
        super(application);
        authAppRepository = new AuthAppRepository(application);
        userMutableLiveData = authAppRepository.getUserMutableLiveData();
    }

    public void login(String email, String password) {
        authAppRepository.login(email, password);
    }

    public MutableLiveData<FirebaseUser> getUserMutableLiveData() {
        return userMutableLiveData;
    }
}