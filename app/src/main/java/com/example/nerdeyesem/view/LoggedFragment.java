package com.example.nerdeyesem.view;

import android.Manifest;
import android.app.ActivityManager;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;

import com.example.nerdeyesem.R;
import com.example.nerdeyesem.service.LocationService;
import com.example.nerdeyesem.support.CommonUtils;
import com.example.nerdeyesem.viewmodel.LoggedViewModel;

public class LoggedFragment extends Fragment {

    private static final int REQUEST_CODE_LOCATION_PERMISSION = 1;

    private LoggedViewModel mViewModel;
    //private TextView userTextView;
    private Button logOutButton;
    private Button getLocationStartButton;
    private Button getLocationStopButton;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mViewModel = new ViewModelProvider(this).get(LoggedViewModel.class);

        mViewModel.getUserMutableLiveData().observe(this, firebaseUser -> {
            if (firebaseUser == null) {
                logOutButton.setEnabled(false);
            }
            /*else {
                // userTextView.setText(String.format("%s%s", getString(R.string.loggedInUserName),
                //  firebaseUser.getEmail()));
            }*/
        });

        mViewModel.getLoggedOutMutableLiveData().observe(this, aBoolean -> {
            if (aBoolean) {
                Toast.makeText(requireContext(), "User Logged Out", Toast.LENGTH_SHORT).show();
                Navigation.findNavController(requireView()).navigate(R.id.action_loggedFragment_to_logInFragment);
            }
        });
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.logged_fragment, container, false);

        initialise(view);

        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view,
                              @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        isButtonsClicked();
    }

    private void isButtonsClicked() {
        logOutButton.setOnClickListener(this::onClick);
        getLocationStartButton.setOnClickListener(v -> {
            if (ActivityCompat.checkSelfPermission(requireContext(), Manifest.permission.ACCESS_FINE_LOCATION)
                    != PackageManager.PERMISSION_GRANTED &&
                    ActivityCompat.checkSelfPermission(requireContext(),
                            Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(requireActivity(), new String[]{Manifest.permission.ACCESS_FINE_LOCATION},
                        REQUEST_CODE_LOCATION_PERMISSION);
            } else {
                startLocationService();
            }
        });
        getLocationStopButton.setOnClickListener(v -> stopLocationService());
    }

    private void initialise(View view) {
        //userTextView = view.findViewById(R.id.logged_in_user_TextView);
        logOutButton = view.findViewById(R.id.button_log_out);
        getLocationStopButton = view.findViewById(R.id.buttonStop);
        getLocationStartButton = view.findViewById(R.id.buttonStart);
    }

    private boolean isLocationServiceRunning() {
        ActivityManager activityManager = (ActivityManager) requireActivity()
                .getSystemService(Context.ACTIVITY_SERVICE);
        if (activityManager != null) {
            for (ActivityManager.RunningServiceInfo service :
                    activityManager.getRunningServices(Integer.MAX_VALUE)) {
                if (LocationService.class.getName().equals(service.service.getClassName())) {
                    if (service.foreground) {
                        return true;
                    }
                }
            }
            return false;
        }
        return false;
    }

    private void startLocationService() {
        if (!isLocationServiceRunning()) {
            Intent intent = new Intent(requireActivity().getApplicationContext(), LocationService.class);
            intent.setAction(CommonUtils.ACTION_START_LOCATION_SERVICE);
            requireActivity().startService(intent);
            Toast.makeText(requireContext(), "Location service started", Toast.LENGTH_SHORT).show();
        }
    }

    private void stopLocationService() {
        if (isLocationServiceRunning()) {
            Intent intent = new Intent(requireActivity().getApplicationContext(), LocationService.class);
            intent.setAction(CommonUtils.ACTION_STOP_LOCATION_SERVICE);
            requireActivity().startService(intent);
            Toast.makeText(requireContext(), "Location service stopped", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           @NonNull String[] permissions,
                                           @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == REQUEST_CODE_LOCATION_PERMISSION && grantResults.length > 0) {
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                startLocationService();
            } else {
                Toast.makeText(requireContext(), "Permission denied!", Toast.LENGTH_SHORT).show();
            }
        }
    }

    private void onClick(View v) {
        mViewModel.logOut();
        stopLocationService();
    }
}