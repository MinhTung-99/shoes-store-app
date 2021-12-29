package com.shoes_store_app.view.authentication;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.shoes_store_app.BaseFragment;
import com.shoes_store_app.databinding.FragmentLoginBinding;
import com.shoes_store_app.view.activity.AdminActivity;
import com.shoes_store_app.view.activity.AuthenticationActivity;
import com.shoes_store_app.view.activity.MainActivity;

import java.util.Objects;

public class LoginFragment extends BaseFragment {

    private FragmentLoginBinding binding;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentLoginBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        binding.txtSignUp.setOnClickListener(v -> ((AuthenticationActivity) requireActivity()).getNavigator().push(new RegisFragment()));

        binding.btnSignUp.setOnClickListener(v -> {
            Intent intent = new Intent(getActivity(), AdminActivity.class);
            startActivity(intent);
            requireActivity().finish();
        });
    }
}
