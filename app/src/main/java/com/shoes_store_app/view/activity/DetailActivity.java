package com.shoes_store_app.view.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;

import com.shoes_store_app.R;
import com.shoes_store_app.databinding.ActivityDetailBinding;
import com.shoes_store_app.utils.Constant;
import com.shoes_store_app.view.main.HomeFragment;

public class DetailActivity extends AppCompatActivity {

    private ActivityDetailBinding binding;

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityDetailBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        int position = getIntent().getIntExtra(Constant.POSITION, -1);
        if (position != -1) {
            binding.txtName.setText(HomeFragment.getInstance().shoesType.get(position).getProductName());
            binding.txtPrice.setText(HomeFragment.getInstance().shoesType.get(position).getPrice() + "");
            binding.txtDate.setText(HomeFragment.getInstance().shoesType.get(position).getUpdateTime());
            binding.txtSize.setText("Size:" + HomeFragment.getInstance().shoesType.get(position).getSize());
            binding.txtNum.setText("Số lượng:" + HomeFragment.getInstance().shoesType.get(position).getNumItems());
            binding.txtSale.setText("Sale:" + HomeFragment.getInstance().shoesType.get(position).getSale());
            if (HomeFragment.getInstance().shoesType.get(position).getStatus() == 1) {
                binding.txtStatus.setText("Còn hàng");
            } else {
                binding.txtStatus.setText("Hết hàng");
            }
        }
    }
}