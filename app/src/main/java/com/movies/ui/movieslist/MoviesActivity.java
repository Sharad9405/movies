package com.movies.ui.movieslist;

import android.arch.lifecycle.ViewModelProviders;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;


import com.movies.R;
import com.movies.databinding.ActivityMoviesBinding;
import com.movies.model.MoviesMasterResponse;
import com.movies.ui.movieslist.adapter.MoviesAdapter;

import java.util.ArrayList;
import java.util.List;

import static com.movies.utils.Constants.MV_POPULAR;
import static com.movies.utils.Constants.MV_TOP_RATED;
import static com.movies.utils.Constants.MV_UPCOMING;
import static com.movies.utils.Constants.TV_ARRIVING_TODAY;
import static com.movies.utils.Constants.TV_LATEST;
import static com.movies.utils.Constants.TV_POPULAR;
import static com.movies.utils.Constants.TV_TOP_RATED;


public class MoviesActivity extends AppCompatActivity {
    private static final String TAG = MoviesActivity.class.getSimpleName();

    private ActivityMoviesBinding mActivityMoviesBinding;
    private MoviesActivityViewModel mActivityMVVMViewModel;
    private List<MoviesMasterResponse> mMoviesMasterResponse;
    private MoviesAdapter mMovieAdapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        /** bind activity  **/
        mActivityMoviesBinding = DataBindingUtil.setContentView(this, R.layout.activity_movies);
        /** get the viewModel reference  **/
        mActivityMVVMViewModel = ViewModelProviders.of(this).get(MoviesActivityViewModel.class);

        initUi();
        initObservers();


        makeBaseCall();

//        getMovieServerCall("popular",1);
    }

    private void makeBaseCall() {
        showProgress();

        for (int i = 0; i < 7; i++) {
            if (0 == i) {
                getMovieServerCall(MV_POPULAR, 1);
            }
            if (1 == i) {
                getMovieServerCall(MV_TOP_RATED, 1);
            }
            if (2 == i) {
                getMovieServerCall(MV_UPCOMING, 1);
            }
            if (3 == i) {
                getMovieServerCall(TV_POPULAR, 1);
            }
            if (4 == i) {
                getMovieServerCall(TV_TOP_RATED, 1);
            }
            if (5 == i) {
                getMovieServerCall(TV_LATEST, 1);
            }
            if (6 == i) {
                getMovieServerCall(TV_ARRIVING_TODAY, 1);
                hideProgress();
            }
        }
    }

    /**
     * initialise ui
     **/
    private void initUi() {
        mMoviesMasterResponse = new ArrayList<>();
        mMovieAdapter = new MoviesAdapter(this, mMoviesMasterResponse);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
//        StaggeredGridLayoutManager layoutManager = new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL);
        mActivityMoviesBinding.rvMovieList.setLayoutManager(mLayoutManager);
        mActivityMoviesBinding.rvMovieList.setItemAnimator(new DefaultItemAnimator());
        mActivityMoviesBinding.rvMovieList.setAdapter(mMovieAdapter);
        mMovieAdapter.notifyDataSetChanged();

        mActivityMoviesBinding.setLabel("Movies");
        mActivityMoviesBinding.setHeader("Movies");

/*
        mActivityMoviesBinding.rvMovieList.addOnScrollListener(new EndlessRecyclerViewScrollListener(layoutManager) {
            @Override
            public void onLoadMore(int page, int totalItemsCount, RecyclerView view) {
                getMovieServerCall(page);
            }
        });
        */


    }

    /**
     * initialise observer here, this is observed by *MoviesActivityViewModel.class*
     **/
    private void initObservers() {

        /** if error **/
        mActivityMVVMViewModel.isError.observe(this, mBoolean -> {
            hideProgress();

            if (null != mBoolean && mBoolean) {
                mActivityMoviesBinding.rvMovieList.setVisibility(View.GONE);
            } else {
                mActivityMoviesBinding.rvMovieList.setVisibility(View.VISIBLE);
            }
        });
        /** if result **/
        mActivityMVVMViewModel.responseData.observe(this, results -> {
            hideProgress();
            updateUi(results);

        });
    }

    /**
     * update the ui
     **/
    private void updateUi(List<MoviesMasterResponse> responses) {
        if (null != responses && responses.size() > 0) {

//            if (mMoviesMasterResponse != null && mMoviesMasterResponse.size() > 0) {
//                newR.addAll(mMovieResults.get(0).getResults());
//            }

            mMoviesMasterResponse.addAll(responses);
            mMovieAdapter.notifyDataSetChanged();
//          mMovieAdapter.notifyItemRangeChanged(mMovieResults.size() - results.size() - 1, mMovieResults.size() - 1);
        }
    }

    /**
     * get server call
     **/
    public void getMovieServerCall(String type, int page) {




        Log.d(TAG, "getMovieServerCall page --> " + page);
        showProgress();
        mActivityMVVMViewModel.getPopularMovies(type, page);
    }

    private void showProgress() {
        mActivityMoviesBinding.progressCircular.setVisibility(View.VISIBLE);
    }

    private void hideProgress() {
        mActivityMoviesBinding.progressCircular.postDelayed(new Runnable() {
            @Override
            public void run() {
                mActivityMoviesBinding.progressCircular.setVisibility(View.GONE);
            }
        }, 500);
    }
}
