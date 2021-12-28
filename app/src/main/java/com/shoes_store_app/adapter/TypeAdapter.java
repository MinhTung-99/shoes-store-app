package com.shoes_store_app.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.shoes_store_app.databinding.ItemTypeBinding;
import com.shoes_store_app.model.Type;

import java.util.List;

public class TypeAdapter extends RecyclerView.Adapter<TypeAdapter.TypeViewHolder> {

    private List<Type> types;
    private ItemOnClick itemOnClick;

    public void setItemOnClick(ItemOnClick itemOnClick) {
        this.itemOnClick = itemOnClick;
    }

    public TypeAdapter(List<Type> types) {
        this.types = types;
    }

    @NonNull
    @Override
    public TypeViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemTypeBinding binding = ItemTypeBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        TypeViewHolder viewHolder = new TypeViewHolder(binding);
        binding.btnType.setOnClickListener(v -> {
           itemOnClick.onSelectedType(types.get(viewHolder.getAdapterPosition()).getTypeId());
        });
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull TypeViewHolder holder, int position) {
        holder.binding.btnType.setText(types.get(position).getTypeName());
    }

    @Override
    public int getItemCount() {
        return types.size();
    }

    class TypeViewHolder extends RecyclerView.ViewHolder {

        ItemTypeBinding binding;

        public TypeViewHolder(@NonNull ItemTypeBinding itemView) {
            super(itemView.getRoot());
            this.binding = itemView;
        }
    }

    public interface ItemOnClick {
        void onSelectedType (int id);
    }
}
