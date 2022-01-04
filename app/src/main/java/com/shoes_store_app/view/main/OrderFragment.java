package com.shoes_store_app.view.main;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.shoes_store_app.BaseFragment;
import com.shoes_store_app.adapter.OrderAdapter;
import com.shoes_store_app.databinding.FragmentOrderBinding;
import com.shoes_store_app.network.response.OrderResponse;

import java.util.ArrayList;
import java.util.List;

public class OrderFragment extends BaseFragment {

    private FragmentOrderBinding binding;
    private OrderAdapter adapter;
    private List<OrderResponse> orderResponses;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentOrderBinding.inflate(inflater, container, false);

        binding.btnOk.setOnClickListener(v -> callApiGetOrdersOk());

        binding.btnConfirm.setOnClickListener(v -> {

        });

        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        callApiGetOrdersOk();

        orderResponses = new ArrayList<>();
        adapter = new OrderAdapter(orderResponses);
        binding.rvOrder.setLayoutManager(new GridLayoutManager(getActivity(), 2));
        binding.rvOrder.setAdapter(adapter);
    }

    @Override
    protected void onSuccessGetOrderOK(List<OrderResponse> orderResponses) {
        adapter.update(orderResponses);
    }
}
