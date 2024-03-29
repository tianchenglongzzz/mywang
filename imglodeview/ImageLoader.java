package com.meida.shaokaoshop.imglodeview;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.Priority;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.load.resource.gif.GifDrawable;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.RequestOptions;
import com.bumptech.glide.request.target.SimpleTarget;
import com.bumptech.glide.request.target.Target;
import com.bumptech.glide.request.transition.Transition;
import com.meida.shaokaoshop.R;
import com.previewlibrary.loader.IZoomMediaLoader;
import com.previewlibrary.loader.MySimpleTarget;

/**
 * 项目名称：ShaoKaoShop
 * 创建人：刘
 * 创建时间：2019-07-13 09:25
 * 功能描述：
 */
public class ImageLoader implements IZoomMediaLoader {


    @Override
    public void displayImage(@NonNull Fragment context, @NonNull String path, ImageView imageView, @NonNull final MySimpleTarget simpleTarget) {
        RequestOptions options = new RequestOptions();
        RequestOptions placeholder = options.error(R.mipmap.ic_launcher).placeholder(R.mipmap.ic_launcher);
        Glide.with(context).asBitmap().load(path).apply(placeholder).listener(new RequestListener<Bitmap>() {


                                                               @Override
                                                               public boolean onLoadFailed(@Nullable GlideException e, Object model, Target<Bitmap> target, boolean isFirstResource) {
                                                                   simpleTarget.onLoadFailed(null);
                                                                   return false;
                                                               }

                                                               @Override
                                                               public boolean onResourceReady(Bitmap resource, Object model, Target<Bitmap> target, DataSource dataSource, boolean isFirstResource) {
                                                                   simpleTarget.onResourceReady();
                                                                   return false;

                                                               }
                                                           }
        ).into(imageView);
    }

        @Override
        public void displayGifImage (@NonNull Fragment context, @NonNull String path, ImageView
        imageView, @NonNull final MySimpleTarget simpleTarget){
            RequestOptions options = new RequestOptions();
            RequestOptions placeholder = options.error(R.mipmap.ic_launcher).placeholder(R.mipmap.ic_launcher);
            Glide.with(context).asGif().load(path).apply(placeholder).listener(new RequestListener<GifDrawable>() {


                                                                                   @Override
                                                                                   public boolean onLoadFailed(@Nullable GlideException e, Object model, Target<GifDrawable> target, boolean isFirstResource) {
                                                                                      simpleTarget.onLoadFailed(null);
                                                                                       return false;
                                                                                   }

                                                                                   @Override
                                                                                   public boolean onResourceReady(GifDrawable resource, Object model, Target<GifDrawable> target, DataSource dataSource, boolean isFirstResource) {
                                                                                      simpleTarget.onResourceReady();
                                                                                       return false;
                                                                                   }
                                                                               }
            ).into(imageView);


        }

        @Override
        public void onStop (@NonNull Fragment context){
            Glide.with(context).onStop();
        }

        @Override
        public void clearMemory (@NonNull Context c){
            Glide.get(c).clearMemory();
        }
    }
