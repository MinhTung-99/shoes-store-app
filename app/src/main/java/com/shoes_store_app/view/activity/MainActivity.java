package com.shoes_store_app.view.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.MenuItem;

import com.google.android.material.navigation.NavigationBarView;
import com.shoes_store_app.BaseActivity;
import com.shoes_store_app.R;
import com.shoes_store_app.databinding.ActivityMainBinding;
import com.shoes_store_app.model.Navigator;
import com.shoes_store_app.model.impl.NavigatorImpl;
import com.shoes_store_app.view.main.HomeFragment;
import com.shoes_store_app.view.main.ProfileFragment;

public class MainActivity extends BaseActivity {

    private ActivityMainBinding binding;
    private final Navigator navigator = new NavigatorImpl(this, R.id.fragment_main);
    private final HomeFragment homeFragment = new HomeFragment();
    private final ProfileFragment profileFragment = new ProfileFragment();

    @SuppressLint("NonConstantResourceId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        navigator.replace(homeFragment);

        binding.bottomNav.setOnItemSelectedListener(item -> {
            switch (item.getItemId()) {
                case R.id.menu_home:
                    navigator.replace(homeFragment);
                    break;
                case R.id.menu_person:
                    navigator.replace(profileFragment);
                    break;
            }

            return false;
        });
    }

    @Override
    public void onBackPressed() {
        if (getSupportFragmentManager().getBackStackEntryCount() == 1) {
            finish();
        } else {
            super.onBackPressed();
        }
    }
}