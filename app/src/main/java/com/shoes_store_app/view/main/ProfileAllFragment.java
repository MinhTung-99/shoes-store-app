package com.shoes_store_app.view.main;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.shoes_store_app.BaseFragment;
import com.shoes_store_app.adapter.UserAdapter;
import com.shoes_store_app.databinding.FragmentProfileAllBinding;
import com.shoes_store_app.databinding.FragmentProfileBinding;
import com.shoes_store_app.model.User;
import com.shoes_store_app.network.response.UserResponse;

import java.util.ArrayList;
import java.util.List;

public class ProfileAllFragment extends BaseFragment {

    private FragmentProfileAllBinding binding;
    private List<UserResponse> users;
    private UserAdapter userAdapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentProfileAllBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        users = new ArrayList<>();
        userAdapter = new UserAdapter(users);
        binding.rvUser.setAdapter(userAdapter);

        callApiGetUser();
    }

    @Override
    protected void onSuccessGetUser(List<UserResponse> userResponses) {
        userAdapter.update(userResponses);
    }
}
