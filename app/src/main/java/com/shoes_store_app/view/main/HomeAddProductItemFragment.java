package com.shoes_store_app.view.main;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.shoes_store_app.BaseFragment;
import com.shoes_store_app.databinding.FragmentHomeAddProductItemBinding;
import com.shoes_store_app.network.request.ProductAddItemRequest;

public class HomeAddProductItemFragment extends BaseFragment {

    private FragmentHomeAddProductItemBinding binding;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentHomeAddProductItemBinding.inflate(inflater, container, false);

        binding.btnAddProduct.setOnClickListener(v -> {
            ProductAddItemRequest productAddItemRequest = new ProductAddItemRequest(
                    binding.edtProductName.getText().toString().replace(";", ""),
                    binding.edtColor.getText().toString().replace(";", ""),
                    binding.edtSize.getText().toString().replace(";", ""),
                    binding.edtNumItems.getText().toString().replace(";", ""),
                    binding.edtSale.getText().toString().replace(";", "")
            );

            callApiAddProductItem(productAddItemRequest);
        });

        return binding.getRoot();
    }

    @Override
    protected void onSuccessAddProductItem() {
        requireActivity().finish();
    }
}
