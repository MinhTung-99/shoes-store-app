package com.shoes_store_app.view.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.shoes_store_app.R;
import com.shoes_store_app.databinding.ActivityDetailBinding;

public class DetailActivity extends AppCompatActivity {

    private ActivityDetailBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityDetailBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
    }
}