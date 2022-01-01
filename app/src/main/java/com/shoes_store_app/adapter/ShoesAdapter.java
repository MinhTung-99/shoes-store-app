package com.shoes_store_app.adapter;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.shoes_store_app.databinding.ItemShoesBinding;
import com.shoes_store_app.model.Shoes;
import com.shoes_store_app.network.response.ProductItemResponse;

import java.util.List;

public class ShoesAdapter extends RecyclerView.Adapter<ShoesAdapter.ShoesViewHolder> {

    private List<ProductItemResponse> shoes;
    private ItemOnClick itemOnClick;

    public void setItemOnClick(ItemOnClick itemOnClick) {
        this.itemOnClick = itemOnClick;
    }

    public ShoesAdapter(List<ProductItemResponse> shoes) {
        this.shoes = shoes;
    }

    @NonNull
    @Override
    public ShoesViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemShoesBinding binding = ItemShoesBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        ShoesViewHolder viewHolder = new ShoesViewHolder(binding);
        binding.getRoot().setOnClickListener(v -> itemOnClick.onItemSelected(viewHolder.getAdapterPosition()));
        binding.getRoot().setOnLongClickListener(v -> {
            itemOnClick.onItemLongSelected();
            return false;
        });
        return viewHolder;
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull ShoesViewHolder holder, int position) {
        holder.binding.txtSize.setText("Size:" + shoes.get(position).getSize());
        holder.binding.txtName.setText(shoes.get(position).getProductName());
        holder.binding.txtColor.setText(shoes.get(position).getColor());
        holder.binding.txtQuality.setText("Số lượng:" + shoes.get(position).getNumItems());
        holder.binding.txtSale.setText("Khuyến mại:" + shoes.get(position).getSale());
    }

    @Override
    public int getItemCount() {
        return shoes.size();
    }

    @SuppressLint("NotifyDataSetChanged")
    public void update (List<ProductItemResponse> shoes) {
        this.shoes = shoes;
        notifyDataSetChanged();
    }

    static class ShoesViewHolder extends RecyclerView.ViewHolder {
        ItemShoesBinding binding;

        public ShoesViewHolder(@NonNull ItemShoesBinding itemView) {
            super(itemView.getRoot());
            this.binding = itemView;
        }
    }

    public interface ItemOnClick {
        void onItemSelected (int position);
        void onItemLongSelected ();
    }
}
