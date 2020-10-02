package com.mkmcmxci.breakingbad.util;

import android.content.Context;
import android.widget.ImageView;

import androidx.databinding.BindingAdapter;
import androidx.swiperefreshlayout.widget.CircularProgressDrawable;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.mkmcmxci.breakingbad.R;

public class ImageGetter {

    public static void loadImage(ImageView view, String url) {


        Glide.with(view.getContext())
                .setDefaultRequestOptions(new RequestOptions().circleCrop())
                .load(url)
                .into(view);


    }


    @BindingAdapter("android:imageURL")
    public static void loadImageUI(ImageView view, String url){

        loadImage(view,url);

    }
}
