package com.shoes_store_app;

import android.app.Activity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

public class CustomProgressDialogFragment extends DialogFragment {

    private static CustomProgressDialogFragment customProgressDialogFragment = new CustomProgressDialogFragment();
    private static boolean isShowing = false;

    public static void show (BaseActivity activity) {
        if (!isShowing) {
            isShowing = true;
            customProgressDialogFragment.show(activity.getSupportFragmentManager(), "");
        }
    }

    public static void hide () {
        isShowing = false;
        customProgressDialogFragment.dismiss();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.custom_dialog_fragment, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        setupDialog();
    }

    private void setupDialog() {
        if (getDialog() != null) {
            getDialog().getWindow().setBackgroundDrawableResource(android.R.color.transparent);
            getDialog().setCancelable(false);
            getDialog().setCanceledOnTouchOutside(false);
        }
    }
}
