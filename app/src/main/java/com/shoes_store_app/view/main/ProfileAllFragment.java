package com.shoes_store_app.view.main;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
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
    private List<UserResponse> userSearch;
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

        userSearch = new ArrayList<>();
        users = new ArrayList<>();
        userAdapter = new UserAdapter(users);
        userAdapter.setItemOnClick(id -> {
            callApiDeleteUser(id);
        });
        binding.rvUser.setAdapter(userAdapter);

        callApiGetUser();

        binding.edtSearch.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                filter(s.toString());
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }

    private void filter (String text) {
        userSearch.clear();
        if (text.isEmpty()) {
            userAdapter.update(users);
        } else {
            for (UserResponse user: users) {
                if (user.getEmail().toLowerCase().contains(text.toLowerCase())) {
                    userSearch.add(user);
                }
            }

            userAdapter.update(userSearch);
        }
    }

    @Override
    protected void onSuccessGetUser(List<UserResponse> userResponses) {
        userAdapter.update(userResponses);
        users.clear();
        users.addAll(userResponses);
    }

    @Override
    protected void onSuccessDeleteUser() {
        callApiGetUser();
    }
}
