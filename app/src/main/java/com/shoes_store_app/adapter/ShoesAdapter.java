package com.shoes_store_app.adapter;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.graphics.BitmapFactory;
import android.util.Base64;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.shoes_store_app.R;
import com.shoes_store_app.databinding.ItemShoesBinding;
import com.shoes_store_app.network.response.ProductItemResponse;
import com.shoes_store_app.utils.ImageConvert;
import com.shoes_store_app.view.activity.AdminActivity;
import com.shoes_store_app.view.activity.MainActivity;

import java.util.List;

public class ShoesAdapter extends RecyclerView.Adapter<ShoesAdapter.ShoesViewHolder> {

    private List<ProductItemResponse> shoes;
    private ItemOnClick itemOnClick;
    private Activity activity;

    public void setItemOnClick(ItemOnClick itemOnClick) {
        this.itemOnClick = itemOnClick;
    }

    public ShoesAdapter(List<ProductItemResponse> shoes, Activity activity) {
        this.shoes = shoes;
        this.activity = activity;
    }

    @NonNull
    @Override
    public ShoesViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemShoesBinding binding = ItemShoesBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        ShoesViewHolder viewHolder = new ShoesViewHolder(binding);
        binding.getRoot().setOnClickListener(v -> itemOnClick.onItemSelected(viewHolder.getAdapterPosition()));
        binding.imgDelete.setOnClickListener(v -> itemOnClick.onItemDeleteSelected(viewHolder.getAdapterPosition()));
        return viewHolder;
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull ShoesViewHolder holder, int position) {

        if (activity instanceof MainActivity) {
            holder.binding.imgDelete.setVisibility(View.GONE);
        } else if (activity instanceof AdminActivity) {
            holder.binding.imgDelete.setVisibility(View.VISIBLE);
        }

        if (shoes.get(position).getImg() != null) {
            ImageConvert.getInstance().convertBase64ToBitmap(shoes.get(position).getImg(), holder.binding.imgShoes);
        } else {
            holder.binding.imgShoes.setImageResource(R.drawable.ic_shoes);
        }

        holder.binding.txtSize.setText("Size:" + shoes.get(position).getSize());
        holder.binding.txtName.setText(shoes.get(position).getProductName());
        holder.binding.txtColor.setText(shoes.get(position).getColor());
        holder.binding.txtQuality.setText("S??? l?????ng:" + shoes.get(position).getNumItems());
        holder.binding.txtSale.setText("Khuy???n m???i:" + shoes.get(position).getSale());
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
        void onItemDeleteSelected(int position);
    }
}
