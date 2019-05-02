package com.movies.custombinding;

import android.content.Context;
import android.databinding.BindingAdapter;
import android.widget.ImageView;

import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions;
import com.bumptech.glide.request.RequestOptions;
import com.movies.R;
import com.movies.utils.GlideApp;

import static com.movies.utils.Constants.BASE_IMAGE_URL;


public class GlideBindingAdapters {


    /** This for image source of integer type **/
    @BindingAdapter("imageUrl")
    public static void setImageUrl(ImageView view, int url){

        Context context = view.getContext();

        GlideApp
                .with(context)
                .load(url)
                .transition(DrawableTransitionOptions.withCrossFade())
                .error(R.drawable.ic_launcher_background)
                .apply(RequestOptions.overrideOf(200,200))
                .diskCacheStrategy(DiskCacheStrategy.AUTOMATIC)
//                .apply(RequestOptions.circleCropTransform())
                .into(view);
    }

    /** This for image source of string type **/
    @BindingAdapter("imageUrl")
    public static void setImageUrl(ImageView imageView, String url) {
//        Glide.with(imageView.getContext()).load(BASE_IMAGE_URL + url).into(imageView);

        GlideApp
                .with(imageView.getContext())
                .load(BASE_IMAGE_URL + url)
                .transition(DrawableTransitionOptions.withCrossFade())
                .error(R.drawable.ic_launcher_background)
                .apply(RequestOptions.overrideOf(200,200))
                .diskCacheStrategy(DiskCacheStrategy.AUTOMATIC)
//                .apply(RequestOptions.circleCropTransform())
                .into(imageView);

    }
}
