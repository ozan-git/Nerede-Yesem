package com.example.nerdeyesem.view;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;

import com.example.nerdeyesem.R;
import com.example.nerdeyesem.viewmodel.RegisterViewModel;

public class RegisterFragment extends Fragment {

    private EditText nameEditText;
    private EditText emailEditText;
    private EditText passwordEditText;
    private Button registerButton;
    private TextView navToLoginText;

    private RegisterViewModel mViewModel;

    public static RegisterFragment newInstance() {
        return new RegisterFragment();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mViewModel = new ViewModelProvider(this).get(RegisterViewModel.class);

        mViewModel.getUserMutableLiveData().observe(this, firebaseUser -> {
            if (firebaseUser != null) {
                Navigation.findNavController(requireView())
                        .navigate(R.id.action_registerFragment_to_loggedFragment);
            }
        });
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.register_fragment, container, false);

        initialise(view);
        buttonClicked();

        return view;
    }

    private void buttonClicked() {
        registerButton.setOnClickListener(v -> {
            String email = emailEditText.getText().toString();
            String password = passwordEditText.getText().toString();
            String name = nameEditText.getText().toString();
            if ((email.length() > 0) && (password.length() > 0) && (name.length() > 0)) {
                mViewModel.register(name, email, password);
            } else {
                Toast.makeText(requireContext(), "Enter your email and password",
                        Toast.LENGTH_SHORT).show();
            }
        });

        navToLoginText.setOnClickListener(v -> Navigation.findNavController(requireView())
                .navigate(R.id.action_registerFragment_to_logInFragment));
    }

    private void initialise(View view) {
        nameEditText = view.findViewById(R.id.input_name);
        emailEditText = view.findViewById(R.id.input_email);
        passwordEditText = view.findViewById(R.id.input_password);
        registerButton = view.findViewById(R.id.btn_sign_up);
        navToLoginText = view.findViewById(R.id.link_login);
    }

}
