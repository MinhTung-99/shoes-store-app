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

import java.util.ArrayList;
import java.util.List;

public class ProfileAllFragment extends BaseFragment {

    private FragmentProfileAllBinding binding;
    private List<User> users;
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
        users.add(new User("ABC", "acb@gmail.com"));
        users.add(new User("TYU", "tyu@gmail.com"));
        userAdapter = new UserAdapter(users);
        binding.rvUser.setAdapter(userAdapter);
    }
}
