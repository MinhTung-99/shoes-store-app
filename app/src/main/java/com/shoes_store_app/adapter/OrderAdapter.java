package com.shoes_store_app.adapter;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.shoes_store_app.databinding.ItemOrderBinding;
import com.shoes_store_app.network.response.OrderResponse;

import java.util.List;

public class OrderAdapter extends RecyclerView.Adapter<OrderAdapter.OrderViewHolder> {

    private List<OrderResponse> orderResponses;

    public OrderAdapter(List<OrderResponse> orderResponses) {
        this.orderResponses = orderResponses;
    }

    @NonNull
    @Override
    public OrderViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemOrderBinding binding = ItemOrderBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        OrderViewHolder viewHolder = new OrderViewHolder(binding);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull OrderViewHolder holder, int position) {
        holder.binding.txtDate.setText(orderResponses.get(position).getCreateTime());
    }

    @Override
    public int getItemCount() {
        return orderResponses.size();
    }

    @SuppressLint("NotifyDataSetChanged")
    public void update (List<OrderResponse> orderResponses) {
        this.orderResponses = orderResponses;
        notifyDataSetChanged();
    }

    static class OrderViewHolder extends RecyclerView.ViewHolder {
        final ItemOrderBinding binding;
        public OrderViewHolder(@NonNull ItemOrderBinding itemView) {
            super(itemView.getRoot());
            this.binding = itemView;
        }
    }
}
