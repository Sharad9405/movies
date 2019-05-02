package com.movies.utils;

import android.content.Context;

import com.bumptech.glide.GlideBuilder;
import com.bumptech.glide.annotation.GlideModule;
import com.bumptech.glide.load.engine.cache.LruResourceCache;
import com.bumptech.glide.load.engine.cache.MemorySizeCalculator;
import com.bumptech.glide.module.AppGlideModule;


/**
 */

//https://bumptech.github.io/glide/doc/getting-started.html

@GlideModule
public final class MyAppGlideModule extends AppGlideModule {


//    The API is generated in the same package as the AppGlideModule and is named GlideApp by default.
// Applications can use the API by starting all loads with GlideApp.with() instead of Glide.with():

        public void applyOptions(Context context, GlideBuilder builder) {
            MemorySizeCalculator calculator = new MemorySizeCalculator.Builder(context)
                    .setMemoryCacheScreens(2)
                    .build();
            builder.setMemoryCache(new LruResourceCache(calculator.getMemoryCacheSize()));
        }



}
