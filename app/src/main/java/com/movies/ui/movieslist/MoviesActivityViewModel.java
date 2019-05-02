package com.movies.ui.movieslist;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.MutableLiveData;
import android.support.annotation.NonNull;
import android.util.Log;

import com.movies.app.AppController;
import com.movies.model.MoviesMasterResponse;
import com.movies.rest.ApiInterface;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.observers.DisposableSingleObserver;
import io.reactivex.schedulers.Schedulers;

import static com.movies.rest.ApiClient.Url.GET_POPULAR;
import static com.movies.rest.ApiClient.Url.GET_TOP_RATED;
import static com.movies.rest.ApiClient.Url.GET_TV_ARRIVING_TODAY;
import static com.movies.rest.ApiClient.Url.GET_TV_POPULAR;
import static com.movies.rest.ApiClient.Url.GET_TV_TOP_RATED;
import static com.movies.rest.ApiClient.Url.GET_UPCOMING;
import static com.movies.utils.Constants.MV_POPULAR;
import static com.movies.utils.Constants.MV_TOP_RATED;
import static com.movies.utils.Constants.MV_UPCOMING;
import static com.movies.utils.Constants.TV_POPULAR;
import static com.movies.utils.Constants.TV_TOP_RATED;

public class MoviesActivityViewModel extends AndroidViewModel {

    private static final String TAG = MoviesActivityViewModel.class.getSimpleName();

    MutableLiveData<Boolean> isError = new MutableLiveData<>();
    MutableLiveData<List<MoviesMasterResponse>> responseData = new MutableLiveData<>();
    private CompositeDisposable disposable = new CompositeDisposable();

    /**
     * initialise constructor
     **/
    public MoviesActivityViewModel(@NonNull Application application) {
        super(application);
    }

    /**
     * make server call to get data
     **/
    void getPopularMovies(String type, int page) {

        Log.d(TAG, "getPopularMovies Page -- > " + page);

        AppController appController = AppController.getInstance();
        ApiInterface mApiInterface = appController.getMoviewService();

        String apiCall = getCall(type);

//        disposable.add(mApiInterface.getPopularMoviesResponse(GET_POPULAR + page)
        disposable.add(mApiInterface.getPopularMoviesResponse(apiCall + page)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribeWith(new DisposableSingleObserver<MoviesMasterResponse>() {
                    @Override
                    public void onSuccess(MoviesMasterResponse response) {
                        /** post value here **/
//                        responseData.postValue(response);
                        List<MoviesMasterResponse> moviesResponseList = new ArrayList<>();

                        MoviesMasterResponse newRe = new MoviesMasterResponse();
                        newRe.setPage(response.getPage());
                        newRe.setLabel(type);
                        newRe.setResults(response.getResults());
                        moviesResponseList.add(newRe);
                        responseData.postValue(moviesResponseList);
                    }

                    @Override
                    public void onError(Throwable e) {
                        /** post error if any **/
                        isError.postValue(true);
                        e.printStackTrace();
                    }
                }));
    }

    /**
     * finally clear the disposable
     **/
    @Override
    protected void onCleared() {
        if (disposable != null)
            disposable.dispose();
        super.onCleared();
    }

    private String getCall(String apiType) {
        String t;
        if (MV_POPULAR.equalsIgnoreCase(apiType)) {
            t = GET_POPULAR;
        } else if (MV_TOP_RATED.endsWith(apiType)) {
            t = GET_TOP_RATED;
        } else if (MV_UPCOMING.endsWith(apiType)) {
            t = GET_UPCOMING;
        } else if (TV_POPULAR.endsWith(apiType)) {
            t = GET_TV_POPULAR;
        } else if (TV_TOP_RATED.endsWith(apiType)) {
            t = GET_TV_TOP_RATED;
        } else  {
            t = GET_TV_ARRIVING_TODAY;
        }
        return t;
    }
}
