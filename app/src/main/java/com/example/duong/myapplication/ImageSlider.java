package com.example.duong.myapplication;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.support.v7.widget.AppCompatImageView;
import android.view.View;
import android.view.ViewGroup;
import android.graphics.Bitmap;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.bumptech.glide.request.target.SimpleTarget;
import com.bumptech.glide.request.transition.Transition;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
public class ImageSlider extends PagerAdapter {

    private String[] urls;

    public ImageSlider(String[] urls) {

        this.urls = urls;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        final Context context = container.getContext();
        final ImageView imageView = new ImageView(context);
        container.addView(imageView);
        // Load ảnh vào ImageView bằng Glide
        RequestOptions options = new RequestOptions();
        options.centerCrop();
//
        Glide.with(context).asBitmap().load(urls[position])
                .apply(RequestOptions.diskCacheStrategyOf(DiskCacheStrategy.AUTOMATIC)
                        .override(820, 280)
                        .centerCrop()
                )
                .into(imageView);
        // Return
        return imageView;
    }

    @Override
    public int getCount() {
        return urls.length;
    }

    @Override
    public boolean isViewFromObject(View view, Object obj) {
        return view == obj;
    }


    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View) object);
    }
}