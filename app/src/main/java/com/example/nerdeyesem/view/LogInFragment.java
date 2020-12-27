package com.example.nerdeyesem.view;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;

import com.example.nerdeyesem.R;
import com.example.nerdeyesem.viewmodel.LogInViewModel;
import com.google.android.material.button.MaterialButton;

public class LogInFragment extends Fragment {

    private EditText emailEditText;
    private EditText passwordEditText;
    private MaterialButton loginButton;
    private TextView navToRegister;

    private LogInViewModel mViewModel;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(LogInViewModel.class);
        mViewModel.getUserMutableLiveData().observe(this, firebaseUser -> {
            if (firebaseUser != null) {
                Navigation.findNavController(requireView()).
                        navigate(R.id.action_logInFragment_to_loggedFragment);
            }
        });
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.log_in_fragment, container, false);

        initialise(view);
        buttonClicked();

        return view;
    }

    private void buttonClicked() {
        loginButton.setOnClickListener(v -> {
            String email = emailEditText.getText().toString();
            String password = passwordEditText.getText().toString();
            if (email.length() > 0 && password.length() > 0) {
                mViewModel.login(email, password);
            } else {
                Toast.makeText(getContext(),
                        "Please enter your Email or Password",
                        Toast.LENGTH_SHORT).show();
            }
        });
        navToRegister.setOnClickListener(v -> Navigation.findNavController(requireView()).
                navigate(R.id.action_logInFragment_to_registerFragment));
    }

    private void initialise(View view) {
        emailEditText = view.findViewById(R.id.input_email_login);
        passwordEditText = view.findViewById(R.id.input_password_password);
        loginButton = view.findViewById(R.id.btn_login);
        navToRegister = view.findViewById(R.id.link_register);
    }


}