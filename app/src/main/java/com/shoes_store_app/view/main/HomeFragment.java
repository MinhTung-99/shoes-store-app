package com.shoes_store_app.view.main;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.shoes_store_app.BaseFragment;
import com.shoes_store_app.adapter.TypeAdapter;
import com.shoes_store_app.databinding.FragmentHomeBinding;
import com.shoes_store_app.model.Type;

import java.util.ArrayList;
import java.util.List;

public class HomeFragment extends BaseFragment {

    private FragmentHomeBinding binding;
    private List<Type> types;
    private TypeAdapter typeAdapter;

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
            Toast.makeText(getContext(), id + "==", Toast.LENGTH_SHORT).show();
        });
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false);
        binding.rvType.setLayoutManager(linearLayoutManager);
        binding.rvType.setAdapter(typeAdapter);

    }
}
