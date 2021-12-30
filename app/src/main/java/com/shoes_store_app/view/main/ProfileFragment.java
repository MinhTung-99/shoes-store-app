package com.shoes_store_app.view.main;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.shoes_store_app.BaseFragment;
import com.shoes_store_app.databinding.FragmentProfileBinding;
import com.shoes_store_app.network.response.UserResponse;
import com.shoes_store_app.view.activity.MainActivity;

import java.util.List;

public class ProfileFragment extends BaseFragment {

    private FragmentProfileBinding binding;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentProfileBinding.inflate(inflater, container, false);

        binding.imgUpdate.setOnClickListener(v -> ((MainActivity) getActivity()).getNavigator().replace(new ProfileUpdateFragment()));

        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        callApiGetUserById(1);
    }

    @Override
    protected void onSuccessGetUserById(UserResponse userResponse) {
        binding.txtEmail.setText(userResponse.getEmail());
        binding.txtAddress.setText(userResponse.getAddress());
        binding.txtPhoneNumber.setText(userResponse.getPhoneNumber());
        binding.txtGender.setText(userResponse.getGender());
    }
}
