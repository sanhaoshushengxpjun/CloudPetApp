package com.example.pet.my_class;

import android.content.Context;
import android.widget.ImageView;

import com.youth.banner.loader.ImageLoader;


public class ImageLoadBanner extends ImageLoader {
    @Override
    public void displayImage(Context context, Object path, ImageView imageView) {
        imageView.setImageResource(Integer.parseInt(path.toString()));
    }
}
