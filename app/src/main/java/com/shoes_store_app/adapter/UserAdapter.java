package com.shoes_store_app.adapter;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.shoes_store_app.databinding.ItemUserBinding;
import com.shoes_store_app.model.User;
import com.shoes_store_app.network.response.UserResponse;

import java.util.List;

public class UserAdapter extends RecyclerView.Adapter<UserAdapter.UserViewHolder> {

    private List<UserResponse> users;
    private ItemOnClick itemOnClick;

    public void setItemOnClick(ItemOnClick itemOnClick) {
        this.itemOnClick = itemOnClick;
    }

    public UserAdapter(List<UserResponse> users) {
        this.users = users;
    }

    @NonNull
    @Override
    public UserViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemUserBinding binding = ItemUserBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        UserViewHolder viewHolder = new UserViewHolder(binding);
        binding.imgRemove.setOnClickListener(v -> {
            itemOnClick.onSelectedRemove(users.get(viewHolder.getAdapterPosition()).getId());
        });
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull UserViewHolder holder, int position) {
        holder.binding.txtEmail.setText(users.get(position).getEmail());
    }

    @Override
    public int getItemCount() {
        return users.size();
    }

    @SuppressLint("NotifyDataSetChanged")
    public void update (List<UserResponse> users) {
        this.users = users;
        notifyDataSetChanged();
    }

    static class UserViewHolder extends RecyclerView.ViewHolder {
        ItemUserBinding binding;
        public UserViewHolder(@NonNull ItemUserBinding itemView) {
            super(itemView.getRoot());

            this.binding = itemView;
        }
    }

    public interface ItemOnClick {
        void onSelectedRemove(int id);
    }
}
