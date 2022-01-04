package com.shoes_store_app.view.main;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.shoes_store_app.BaseFragment;
import com.shoes_store_app.R;
import com.shoes_store_app.databinding.FragmentProfileBinding;
import com.shoes_store_app.databinding.FragmentProfileUpdateBinding;
import com.shoes_store_app.network.request.UserUpdateRequest;
import com.shoes_store_app.network.response.UserResponse;
import com.shoes_store_app.view.activity.MainActivity;
import com.shoes_store_app.view.authentication.LoginFragment;

public class ProfileUpdateFragment extends BaseFragment {

    private FragmentProfileUpdateBinding binding;
    private String[] genders;
    private String gender;
    private ProfileFragment.ProfileCallBack profileCallBack;

    public ProfileUpdateFragment(ProfileFragment.ProfileCallBack profileCallBack) {
        this.profileCallBack = profileCallBack;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentProfileUpdateBinding.inflate(inflater, container, false);

        genders = getResources().getStringArray(R.array.genders);
        ArrayAdapter<String> adapter = new ArrayAdapter<>(getActivity(),
                android.R.layout.simple_spinner_item,
                genders);

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        binding.spinnerGender.setAdapter(adapter);

        binding.spinnerGender.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                onItemSelectedGenders(position);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        callApiGetUserById(LoginFragment.getInstance().userId);

        binding.btnUpdate.setOnClickListener(v -> {
            UserUpdateRequest userUpdateRequest = new UserUpdateRequest(
                    LoginFragment.getInstance().password.replace(";", ""),
                    binding.edtFullName.getText().toString().replace(";", ""),
                    gender, binding.edtAddress.getText().toString().replace(";", "")
            );
            callApiUpdateUser(LoginFragment.getInstance().email, userUpdateRequest);
        });
    }

    @Override
    protected void onSuccessUpdateUser() {
        ((MainActivity) requireActivity()).getNavigator().pop();
        profileCallBack.callback();
    }

    private void onItemSelectedGenders(int position) {
        gender = genders[position];
    }

    @Override
    protected void onSuccessGetUserById(UserResponse userResponse) {
        binding.edtFullName.setText(userResponse.getFullName());
        binding.edtAddress.setText(userResponse.getAddress());
        if (userResponse.getGender().equals(genders[0])) {
            binding.spinnerGender.setSelection(0);
        } else {
            binding.spinnerGender.setSelection(1);
        }
    }
}
