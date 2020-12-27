package com.example.nerdeyesem.viewmodel;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import com.example.nerdeyesem.model.AuthAppRepository;
import com.google.firebase.auth.FirebaseUser;

public class LoggedViewModel extends AndroidViewModel {

    private final AuthAppRepository authAppRepository;
    private final MutableLiveData<FirebaseUser> userMutableLiveData;
    private final MutableLiveData<Boolean> loggedOutMutableLiveData;

    public LoggedViewModel(Application application) {
        super(application);
        authAppRepository = new AuthAppRepository(application);
        userMutableLiveData = authAppRepository.getUserMutableLiveData();
        loggedOutMutableLiveData = authAppRepository.getLoggedOutMutableLiveData();
    }

    public MutableLiveData<FirebaseUser> getUserMutableLiveData() {
        return userMutableLiveData;
    }

    public MutableLiveData<Boolean> getLoggedOutMutableLiveData() {
        return loggedOutMutableLiveData;
    }

    public void logOut(){
        authAppRepository.logOut();
    }

}