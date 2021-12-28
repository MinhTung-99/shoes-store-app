package com.shoes_store_app.model;

import androidx.fragment.app.Fragment;

import java.util.ArrayList;
import java.util.List;

public interface Navigator {
    List<Fragment> fragments = new ArrayList<>();

    void push(Fragment fragment);

    void pop();
}
