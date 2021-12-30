package com.shoes_store_app.view.activity;

import androidx.annotation.NonNull;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.shoes_store_app.BaseActivity;
import com.shoes_store_app.CustomProgressDialogFragment;
import com.shoes_store_app.R;
import com.shoes_store_app.databinding.ActivityMainBinding;
import com.shoes_store_app.model.Navigator;
import com.shoes_store_app.model.impl.NavigatorImpl;
import com.shoes_store_app.network.ApiUtils;
import com.shoes_store_app.network.response.TestResponse;
import com.shoes_store_app.network.response.UserResponse;
import com.shoes_store_app.view.main.HomeFragment;
import com.shoes_store_app.view.main.ProfileFragment;

import java.util.List;

import io.reactivex.SingleObserver;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class MainActivity extends BaseActivity {

    private ActivityMainBinding binding;
    private final Navigator navigator = new NavigatorImpl(this, R.id.fragment_main);
    private final HomeFragment homeFragment = new HomeFragment();
    private final ProfileFragment profileFragment = new ProfileFragment();

    public Navigator getNavigator() {
        return navigator;
    }

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