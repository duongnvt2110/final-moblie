package com.example.duong.myapplication;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.support.v7.widget.AppCompatImageView;
import android.view.View;
import android.view.ViewGroup;

import com.bumptech.glide.Glide;

public class ImageSlider extends PagerAdapter {

    private String[] urls;

    public ImageSlider(String[] urls) {

        this.urls = urls;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        final Context context = container.getContext();
        final AppCompatImageView imageView = new AppCompatImageView(context);
        container.addView(imageView);
        // Load ảnh vào ImageView bằng Glide
        Glide.with(context).load(urls[position]).into(imageView);
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