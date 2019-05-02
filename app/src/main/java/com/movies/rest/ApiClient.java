package com.movies.rest;


import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static com.movies.utils.Constants.MOVIE_API_KEY;


/**
 */

public class ApiClient {
    private static final String BASE_URL = "https://api.themoviedb.org/";
    private static Retrofit retrofit = null;

    public static Retrofit getClient() {

        HttpLoggingInterceptor logLevel = new HttpLoggingInterceptor();
        // set your desired log level
//        logging.setLevel(HttpLoggingInterceptor.Level.HEADERS);
        logLevel.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient.Builder httpClient = new OkHttpClient.Builder();
        // add your other interceptors …

        // add logging as last interceptor
        httpClient.retryOnConnectionFailure(true);
        httpClient.addInterceptor(logLevel);  // <-- this is the important line!

        httpClient.connectTimeout(120, TimeUnit.SECONDS);
        httpClient.readTimeout(120, TimeUnit.SECONDS);
        httpClient.writeTimeout(120, TimeUnit.SECONDS);
//        httpClient.connectionSpecs(Collections.singletonList(spec));

        Gson gson = new GsonBuilder()  // added recently, can be removed
                .setLenient()
                .create();

        if (retrofit == null) {
            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create(gson))
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .client(httpClient.build())
                    .build();
        }
        return retrofit;
    }

    public static final class Url {


        public static final String GET_POPULAR = BASE_URL + "3/movie/popular?api_key=" + MOVIE_API_KEY + "&language=en-US&page=";
        public static final String GET_TOP_RATED = BASE_URL + "3/movie/top_rated?api_key=" + MOVIE_API_KEY + "&language=en-US&page=";
//        public static final String GET_LATEST = BASE_URL + "3/movie/latest?api_key=" + MOVIE_API_KEY + "&language=en-US";
        public static final String GET_UPCOMING = BASE_URL + "3/movie/upcoming?api_key=" + MOVIE_API_KEY + "&language=en-US&page=";

        public static final String GET_TV_POPULAR = BASE_URL + "3/tv/popular?api_key=" + MOVIE_API_KEY + "&language=en-US&page=";
        public static final String GET_TV_TOP_RATED = BASE_URL + "3/tv/top_rated?api_key=" + MOVIE_API_KEY + "&language=en-US&page=";
//        public static final String GET_TV_LATEST = BASE_URL + "3/tv/latest?api_key=" + MOVIE_API_KEY + "&language=en-US&page=";
        public static final String GET_TV_ARRIVING_TODAY = BASE_URL + "3/tv/airing_today?api_key=" + MOVIE_API_KEY + "&language=en-US&page=";

//       public static final String GET_POPULAR = BASE_URL + "3/movie/popular?api_key=" + MOVIE_API_KEY + "&language=en-US&page=";

    }

}
