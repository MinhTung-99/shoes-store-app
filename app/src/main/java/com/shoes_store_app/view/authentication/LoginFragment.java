package com.shoes_store_app.view.authentication;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.shoes_store_app.BaseFragment;
import com.shoes_store_app.databinding.FragmentLoginBinding;
import com.shoes_store_app.network.response.UserResponse;
import com.shoes_store_app.view.activity.AdminActivity;
import com.shoes_store_app.view.activity.AuthenticationActivity;
import com.shoes_store_app.view.activity.MainActivity;

import java.util.List;
import java.util.Objects;

public class LoginFragment extends BaseFragment {

    private FragmentLoginBinding binding;
    public Integer userId;
    private static LoginFragment loginFragment;
    public static LoginFragment getInstance() {
        return loginFragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentLoginBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        loginFragment = this;

        binding.txtSignUp.setOnClickListener(v -> ((AuthenticationActivity) requireActivity()).getNavigator().push(new RegisFragment()));

        binding.btnSignUp.setOnClickListener(v -> callApiGetUser());
    }

    @Override
    protected void onSuccessGetUser(List<UserResponse> userResponse) {
        boolean isLogin = false;
        for (UserResponse user : userResponse) {
            if (user.getEmail().equals(binding.edtEmail.getText().toString())
                    && user.getPassword().equals(binding.edtPassword.getText().toString())
                    && user.getStatus() == 1) {
                isLogin = true;

                userId = user.getId();
                Intent intent;
                if (user.getRoleId() == 3) {
                    intent = new Intent(getActivity(), AdminActivity.class);
                } else {
                    intent = new Intent(getActivity(), MainActivity.class);
                }
                startActivity(intent);
                requireActivity().finish();

                break;
            }
        }
        if (!isLogin) {
            Toast.makeText(getContext(), "Tài khoảng hoặc mật khẩu không đúng", Toast.LENGTH_SHORT).show();
        }
    }
}
