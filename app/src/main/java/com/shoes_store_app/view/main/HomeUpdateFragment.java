package com.shoes_store_app.view.main;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.shoes_store_app.BaseFragment;
import com.shoes_store_app.databinding.FragmentHomeUpdateBinding;
import com.shoes_store_app.network.request.ProductAddItemRequest;
import com.shoes_store_app.network.response.ProductItemResponse;
import com.shoes_store_app.view.activity.AdminActivity;

public class HomeUpdateFragment extends BaseFragment {

    private int position;
    private FragmentHomeUpdateBinding binding;
    private HomeFragment.HomeCallback homeCallback;

    public HomeUpdateFragment(int position, HomeFragment.HomeCallback homeCallback) {
        this.position = position;
        this.homeCallback = homeCallback;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentHomeUpdateBinding.inflate(inflater, container, false);

        binding.btnUpdate.setOnClickListener(v -> {
            ProductAddItemRequest productAddItemRequest = new ProductAddItemRequest(
                    HomeFragment.getInstance().shoesType.get(position).getItemId(),
                    binding.txtProductName.getText().toString(),
                    binding.edtColor.getText().toString(),
                    binding.edtSize.getText().toString(),
                    binding.edtNumItems.getText().toString(),
                    binding.edtSale.getText().toString()
            );
            callApiUpdateProductItem(productAddItemRequest);
        });

        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        callApiGetProductItemById(HomeFragment.getInstance().shoesType.get(position).getItemId());
    }

    @Override
    protected void onSuccessGetProductItemById(ProductItemResponse productResponses) {
        binding.txtProductName.setText(HomeFragment.getInstance().shoesType.get(position).getProductName());
        binding.edtColor.setText(productResponses.getColor());
        binding.edtNumItems.setText(String.valueOf(productResponses.getNumItems()));
        binding.edtSize.setText(String.valueOf(productResponses.getSize()));
        binding.edtSale.setText(String.valueOf(productResponses.getSale()));
    }

    @Override
    protected void onSuccessUpdateProductItem() {
        homeCallback.callback();
        ((AdminActivity) requireActivity()).getNavigator().pop();
    }
}
