package com.shoes_store_app.utils;

import android.graphics.BitmapFactory;
import android.util.Base64;
import android.widget.ImageView;

public class ImageConvert {

    private static ImageConvert instance = new ImageConvert();
    public static ImageConvert getInstance() {
        return instance;
    }

    public void convertBase64ToBitmap (String base64, ImageView imageView) {
        byte[] imageAsBytes = Base64.decode(base64, Base64.DEFAULT);
        imageView.setImageBitmap(
                BitmapFactory.decodeByteArray(imageAsBytes, 0, imageAsBytes.length)
        );
    }
}
