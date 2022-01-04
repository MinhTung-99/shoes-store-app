package com.shoes_store_app.view.main;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.shoes_store_app.BaseFragment;
import com.shoes_store_app.databinding.FragmentHomeAddBinding;
import com.shoes_store_app.databinding.FragmentHomeAddProductBinding;
import com.shoes_store_app.network.request.ProductAddRequest;
import com.shoes_store_app.view.activity.HomeAddActivity;
import com.shoes_store_app.view.authentication.LoginFragment;

public class HomeAddProductFragment extends BaseFragment {

    private FragmentHomeAddProductBinding binding;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentHomeAddProductBinding.inflate(inflater, container, false);

        binding.btnAddProduct.setOnClickListener(v -> {
            ProductAddRequest productAddRequest = new ProductAddRequest(
                    binding.edtProductName.getText().toString().replace(";", ""),
                    Integer.parseInt(binding.edtImportPrice.getText().toString()),
                    Integer.parseInt(binding.edtPrice.getText().toString()),
                    LoginFragment.getInstance().userId
            );
            callApiAddProduct(productAddRequest);
        });

        return binding.getRoot();
    }

    @Override
    protected void onSuccessAddProduct() {
        requireActivity().finish();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);


    }
}
