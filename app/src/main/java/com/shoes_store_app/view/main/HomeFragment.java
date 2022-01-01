package com.shoes_store_app.view.main;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.shoes_store_app.BaseFragment;
import com.shoes_store_app.adapter.ShoesAdapter;
import com.shoes_store_app.adapter.TypeAdapter;
import com.shoes_store_app.databinding.FragmentHomeBinding;
import com.shoes_store_app.model.Shoes;
import com.shoes_store_app.model.Type;
import com.shoes_store_app.network.response.ProductItemResponse;
import com.shoes_store_app.network.response.ProductResponse;
import com.shoes_store_app.view.activity.AdminActivity;
import com.shoes_store_app.view.activity.DetailActivity;
import com.shoes_store_app.view.activity.MainActivity;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class HomeFragment extends BaseFragment {

    private FragmentHomeBinding binding;
    private List<ProductResponse> types;
    private TypeAdapter typeAdapter;
    private List<ProductItemResponse> shoes;
    private ShoesAdapter shoesAdapter;
    private List<ProductItemResponse> shoesType;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentHomeBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        callApiGetProduct();
        types = new ArrayList<>();
        typeAdapter = new TypeAdapter(types);
        typeAdapter.setItemOnClick(id -> {
            shoesType.clear();
            for (ProductItemResponse item : shoes) {
                if (item.getProductId() == id) {
                    shoesType.add(item);
                }
            }
            shoesAdapter.update(shoesType);
        });
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false);
        binding.rvType.setLayoutManager(linearLayoutManager);
        binding.rvType.setAdapter(typeAdapter);

        shoes = new ArrayList<>();
        shoesAdapter = new ShoesAdapter(shoes);
        shoesAdapter.setItemOnClick(new ShoesAdapter.ItemOnClick() {
            @Override
            public void onItemSelected() {
                if (getActivity() instanceof MainActivity) {
                    startActivity(new Intent(getActivity(), DetailActivity.class));
                } else if (getActivity() instanceof AdminActivity) {
                    ((AdminActivity) getActivity()).getNavigator().push(new HomeUpdateFragment());
                }
            }

            @Override
            public void onItemLongSelected() {
                if (getActivity() instanceof AdminActivity) {
                    //DELETE ITEM
                    Toast.makeText(getContext(), "DELETE", Toast.LENGTH_SHORT).show();
                }
            }
        });
        binding.rvItem.setLayoutManager(new GridLayoutManager(getActivity(), 2));
        binding.rvItem.setAdapter(shoesAdapter);
    }

    @Override
    protected void onSuccessGetProduct(List<ProductResponse> productResponses) {
        typeAdapter.update(productResponses);
        types.clear();
        types.addAll(productResponses);
        callApiGetProductItem();
    }

    @Override
    protected void onSuccessGetProductItem(List<ProductItemResponse> productItemResponses) {
        for (int i = 0; i < productItemResponses.size(); i++) {
            for (ProductResponse type: types) {
                if (type.getProductId() == productItemResponses.get(i).getProductId()) {
                    productItemResponses.get(i).setProductName(type.getProductName());
                    productItemResponses.get(i).setUpdateTime(type.getUpdateTime());
                    productItemResponses.get(i).setPrice(type.getPrice());
                }
            }
        }

        shoes.addAll(productItemResponses);
        shoesType = new ArrayList<>();
        for (ProductItemResponse item : productItemResponses) {
            if (item.getProductId() == 1) {
                shoesType.add(item);
            }
        }

        shoesAdapter.update(shoesType);
    }
}
