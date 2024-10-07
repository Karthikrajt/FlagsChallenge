package com.ajgroup.flagschallenge.helper;

import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.ImageViewTarget;
import com.bumptech.glide.request.target.Target;

public class SvgSoftwareLayerSetter<T> implements RequestListener<T> {
    @Override
    public boolean onLoadFailed(GlideException e, Object model, Target<T> target, boolean isFirstResource) {
        return false;
    }

    @Override
    public boolean onResourceReady(T resource, Object model, Target<T> target, DataSource dataSource, boolean isFirstResource) {
        if (target instanceof ImageViewTarget) {
            ImageView imageView = ((ImageViewTarget<?>) target).getView();
            imageView.setLayerType(View.LAYER_TYPE_SOFTWARE, null);
        }
        return false;
    }
}