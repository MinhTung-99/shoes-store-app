package com.shoes_store_app.view.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.shoes_store_app.BaseActivity;
import com.shoes_store_app.R;
import com.shoes_store_app.model.Navigator;
import com.shoes_store_app.model.impl.NavigatorImpl;
import com.shoes_store_app.view.authentication.LoginFragment;

public class AuthenticationActivity extends BaseActivity {

    private Navigator navigator = new NavigatorImpl(this, R.id.fragment);

    public Navigator getNavigator() {
        return navigator;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_authentication);

        navigator.push(new LoginFragment());
    }

    @Override
    public void onBackPressed() {
        if (navigator.fragments.size() == 1) {
            finish();
        } else {
            super.onBackPressed();
        }

        navigator.fragments.remove(navigator.fragments.size() - 1);
    }
}