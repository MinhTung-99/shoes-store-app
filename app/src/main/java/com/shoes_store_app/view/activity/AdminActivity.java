package com.shoes_store_app.view.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;

import com.shoes_store_app.BaseActivity;
import com.shoes_store_app.R;
import com.shoes_store_app.databinding.ActivityAdminBinding;
import com.shoes_store_app.model.Navigator;
import com.shoes_store_app.model.impl.NavigatorImpl;
import com.shoes_store_app.view.main.HomeFragment;
import com.shoes_store_app.view.main.ProfileAllFragment;
import com.shoes_store_app.view.main.ProfileFragment;

public class AdminActivity extends BaseActivity {

    private ActivityAdminBinding binding;

    private final Navigator navigator = new NavigatorImpl(this, R.id.fragment_admin);
    private final HomeFragment homeFragment = new HomeFragment();
    private final ProfileAllFragment profileAllFragment = new ProfileAllFragment();

    public Navigator getNavigator() {
        return navigator;
    }

    @SuppressLint("NonConstantResourceId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityAdminBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        navigator.replace(homeFragment);

        binding.bottomNav.setOnItemSelectedListener(item -> {
            switch (item.getItemId()) {
                case R.id.menu_home:
                    navigator.replace(homeFragment);
                    break;
                case R.id.menu_person:
                    navigator.replace(profileAllFragment);
                    break;
            }

            return false;
        });
    }
}