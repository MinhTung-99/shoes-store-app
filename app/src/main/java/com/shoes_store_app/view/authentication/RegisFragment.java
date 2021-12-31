package com.shoes_store_app.view.authentication;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.shoes_store_app.BaseFragment;
import com.shoes_store_app.R;
import com.shoes_store_app.databinding.FragmentRegisBinding;
import com.shoes_store_app.model.Navigator;
import com.shoes_store_app.network.request.UserRequest;
import com.shoes_store_app.view.activity.AuthenticationActivity;

import java.util.Objects;

public class RegisFragment extends BaseFragment {

    private FragmentRegisBinding binding;
    private String[] genders;
    private String gender;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentRegisBinding.inflate(inflater, container, false);

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

    private void onItemSelectedGenders(int position) {
        gender = genders[position];
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        binding.btnRegis.setOnClickListener(v -> {
            UserRequest userRequest = new UserRequest(
                binding.edtUserName.getText().toString(),
                    binding.edtPassword.getText().toString(),
                    binding.edtFullName.getText().toString(),
                    gender,
                    binding.edtAddress.getText().toString(),
                    binding.edtPhoneNumber.getText().toString(),
                    binding.edtEmail.getText().toString(),
                    "user", "1"
            );
            callApiAddUser(userRequest);
        });
    }

    @Override
    protected void onSuccessAddUser() {
        ((AuthenticationActivity) requireActivity()).getNavigator().pop();
    }
}
