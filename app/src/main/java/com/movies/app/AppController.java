package com.movies.app;

import android.app.Application;


import com.movies.rest.ApiClient;
import com.movies.rest.ApiInterface;

import io.reactivex.Scheduler;
import io.reactivex.schedulers.Schedulers;

public class AppController extends Application {

    private static AppController mInstance;
    private Scheduler scheduler;
    private ApiInterface mApiInterface;

    public static synchronized AppController getInstance() {
        return mInstance;
    }

    public AppController() {
        mInstance = this;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        mInstance = this;
//        SharedPreferencesManager.createSharedPreferences(this);
    }

    public ApiInterface getMoviewService() {
        if (mApiInterface == null) {
            mApiInterface = ApiClient.getClient().create(ApiInterface.class);
        }
        return mApiInterface;
    }

    public Scheduler subscribeScheduler() {
        if (scheduler == null) {
            scheduler = Schedulers.io();
        }

        return scheduler;
    }


}
