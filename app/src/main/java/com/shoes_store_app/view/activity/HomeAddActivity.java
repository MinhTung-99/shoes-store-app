package com.shoes_store_app.view.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.shoes_store_app.BaseActivity;
import com.shoes_store_app.R;
import com.shoes_store_app.model.Navigator;
import com.shoes_store_app.model.impl.NavigatorImpl;
import com.shoes_store_app.view.main.HomeAddFragment;
import com.shoes_store_app.view.main.HomeFragment;

public class HomeAddActivity extends BaseActivity {

    private final Navigator navigator = new NavigatorImpl(this, R.id.fragment_add);

    public Navigator getNavigator() {
        return navigator;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_add);

        navigator.push(new HomeAddFragment());
    }
}