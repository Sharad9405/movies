package com.movies.ui.movieslist.view;

import android.text.TextUtils;

import com.android.databinding.library.baseAdapters.BR;
import com.movies.databinding.CvMoviesItemsHorizontalColoredBinding;
import com.movies.generic.BaseViewHolder;
import com.movies.generic.OnRecyclerItemClickListener;
import com.movies.model.Results;

import static com.movies.utils.Constants.NA;


public class MoviesAdapterHorizontalColoredViewHolder extends BaseViewHolder<Results, OnRecyclerItemClickListener> {

    private CvMoviesItemsHorizontalColoredBinding mCvMoviesItemsHorizontalColoredBinding;

    public MoviesAdapterHorizontalColoredViewHolder(CvMoviesItemsHorizontalColoredBinding binding, OnRecyclerItemClickListener listener) {
        super(binding.getRoot(),listener);
        this.mCvMoviesItemsHorizontalColoredBinding = binding;
    }

    @Override
    public void onBind(Results item) {
        /** setting recycler view click and title with poster path  **/

        if (getListener() != null) {
            itemView.setOnClickListener(v -> getListener().onItemClick(getAdapterPosition()));
        }

        String title = NA;
        if (!TextUtils.isEmpty(item.getTitle())){
            title = item.getTitle();
        }else if (!TextUtils.isEmpty(item.getName())){
            title = item.getName();
        }

        mCvMoviesItemsHorizontalColoredBinding.setVariable(BR.title, title);
        mCvMoviesItemsHorizontalColoredBinding.setVariable(BR.imagePath, item.getPosterPath());

//        Log.e("movie name ", "movie name : " + item.getTitle());
        mCvMoviesItemsHorizontalColoredBinding.executePendingBindings();
    }
}
