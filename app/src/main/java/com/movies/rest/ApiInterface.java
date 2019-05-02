package com.movies.rest;
import com.movies.model.MoviesMasterResponse;

import io.reactivex.Single;
import retrofit2.http.GET;
import retrofit2.http.Url;


public interface ApiInterface {

    @GET
    Single<MoviesMasterResponse> getPopularMoviesResponse(@Url String url);
}
