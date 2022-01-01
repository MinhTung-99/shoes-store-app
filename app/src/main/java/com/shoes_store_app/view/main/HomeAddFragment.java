package com.shoes_store_app.view.main;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.shoes_store_app.BaseFragment;
import com.shoes_store_app.databinding.FragmentHomeAddBinding;
import com.shoes_store_app.view.activity.HomeAddActivity;

import java.util.Objects;

public class HomeAddFragment extends BaseFragment {

    private FragmentHomeAddBinding binding;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentHomeAddBinding.inflate(inflater, container, false);

        binding.btnProduct.setOnClickListener(v -> ((HomeAddActivity) requireActivity()).getNavigator().push(new HomeAddProductFragment()));

        binding.btnItem.setOnClickListener(v -> ((HomeAddActivity) requireActivity()).getNavigator().push(new HomeAddProductItemFragment()));

        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }
}
