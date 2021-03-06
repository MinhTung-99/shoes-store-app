package com.shoes_store_app;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.shoes_store_app.model.Navigator;
import com.shoes_store_app.model.impl.NavigatorImpl;

public class BaseActivity extends AppCompatActivity {

    @Override
    public void onBackPressed() {
        if (getSupportFragmentManager().getBackStackEntryCount() == 1) {
            finish();
        } else {
            super.onBackPressed();
        }
    }
}
