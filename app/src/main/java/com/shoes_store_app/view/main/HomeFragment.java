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
import com.shoes_store_app.view.activity.AdminActivity;
import com.shoes_store_app.view.activity.DetailActivity;
import com.shoes_store_app.view.activity.MainActivity;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class HomeFragment extends BaseFragment {

    private FragmentHomeBinding binding;
    private List<Type> types;
    private TypeAdapter typeAdapter;
    private List<Shoes> shoes;
    private ShoesAdapter shoesAdapter;
    private List<Shoes> shoesType;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentHomeBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        types = new ArrayList<>();
        types.add(new Type(1, "Giày thể thao"));
        types.add(new Type(2, "Giày da"));
        types.add(new Type(3, "Giày lười da"));
        types.add(new Type(4, "Giày Sneaker"));

        typeAdapter = new TypeAdapter(types);
        typeAdapter.setItemOnClick(id -> {
            shoesType.clear();
            for (Shoes shoes : shoes) {
                if (shoes.getTypeId() == id) {
                    shoesType.add(shoes);
                }
            }
            shoesAdapter.update(shoesType);
        });
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false);
        binding.rvType.setLayoutManager(linearLayoutManager);
        binding.rvType.setAdapter(typeAdapter);

        shoes = new ArrayList<>();
        shoes.add(new Shoes(1, "Giày thể thao", "399000 VND", "10/10/2021"));
        shoes.add(new Shoes(2, "Giày da", "499000 VND", "10/10/2021"));
        shoes.add(new Shoes(1, "Giày lười", "599000 VND", "10/10/2021"));
        shoesType = new ArrayList<>();
        for (Shoes shoes : shoes) {
            if (shoes.getTypeId() == 1) {
                shoesType.add(shoes);
            }
        }
        shoesAdapter = new ShoesAdapter(shoesType);
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
}
