package com.example.nerdeyesem.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.nerdeyesem.model.AuthAppRepository;
import com.google.firebase.auth.FirebaseUser;

public class RegisterViewModel extends AndroidViewModel {

    private final AuthAppRepository authAppRepository;
    private final MutableLiveData<FirebaseUser> userMutableLiveData;

    public RegisterViewModel(@NonNull Application application) {
        super(application);

        authAppRepository = new AuthAppRepository(application);
        userMutableLiveData = authAppRepository.getUserMutableLiveData();
    }

    public void register(String name, String email, String password){
        authAppRepository.register(name, email, password);
    }

    public MutableLiveData<FirebaseUser> getUserMutableLiveData() {
        return userMutableLiveData;
    }

}