package com.shoes_store_app.model.impl;

import androidx.fragment.app.Fragment;

import com.shoes_store_app.BaseActivity;
import com.shoes_store_app.model.Navigator;

public class NavigatorImpl implements Navigator {

    private BaseActivity activity;
    private int containerResId;


    public NavigatorImpl(BaseActivity activity, int containerResId) {
        this.activity = activity;
        this.containerResId = containerResId;
    }

    @Override
    public void push(Fragment fragment) {
        activity.getSupportFragmentManager()
                .beginTransaction()
                .replace(containerResId, fragment, "tag")
                .addToBackStack(null)
                .commit();

        this.fragments.add(fragment);
    }

    @Override
    public void pop() {

    }
}
