package com.shoes_store_app.utils;

import android.os.Handler;

public class DelayUtil {

    private static final DelayUtil instance = new DelayUtil();
    public static DelayUtil getInstance () {
        return instance;
    }

    public void delay (int millisecond, DelayCallback delayCallback) {
        new Handler().postDelayed(() -> {
            delayCallback.callback();
        }, millisecond);
    }

    public interface DelayCallback {
        void callback ();
    }
}
