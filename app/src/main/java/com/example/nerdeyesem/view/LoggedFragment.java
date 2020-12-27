package com.example.nerdeyesem.view;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;

import com.example.nerdeyesem.R;
import com.example.nerdeyesem.viewmodel.LoggedViewModel;


public class LoggedFragment extends Fragment {

    private LoggedViewModel mViewModel;

    private TextView userTextView;
    private Button logOutButton;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mViewModel = new ViewModelProvider(this).get(LoggedViewModel.class);

        mViewModel.getUserMutableLiveData().observe(this, firebaseUser -> {
            if (firebaseUser != null) {
                userTextView.setText(String.format("%s%s", getString(R.string.loggedInUserName),
                        firebaseUser.getEmail()));
            } else {
                logOutButton.setEnabled(false);
            }
        });

        mViewModel.getLoggedOutMutableLiveData().observe(this, aBoolean -> {
            if (aBoolean) {
                Toast.makeText(requireContext(), "User Logged Out", Toast.LENGTH_SHORT).show();
                Navigation.findNavController(requireView()).navigate(R.id.action_loggedFragment_to_logInFragment);
            }
        });
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.logged_fragment, container, false);

        initialise(view);

        logOutButton.setOnClickListener(v -> mViewModel.logOut());

        return view;
    }

    private void initialise(View view) {
        userTextView = view.findViewById(R.id.logged_in_user_TextView);
        logOutButton = view.findViewById(R.id.button_log_out);
    }


}