package com.movies.ui.movieslist.view;

import android.text.TextUtils;

import com.android.databinding.library.baseAdapters.BR;
import com.movies.databinding.CvMoviesItemsHorizontalNormalBinding;
import com.movies.generic.BaseViewHolder;
import com.movies.generic.OnRecyclerItemClickListener;
import com.movies.model.Results;

import static com.movies.utils.Constants.NA;


public class MoviesAdapterHorizontalNormalViewHolder extends BaseViewHolder<Results, OnRecyclerItemClickListener> {

    private CvMoviesItemsHorizontalNormalBinding mCvMoviesItemsHorizontalNormalBinding;

    public MoviesAdapterHorizontalNormalViewHolder(CvMoviesItemsHorizontalNormalBinding binding, OnRecyclerItemClickListener listener) {
        super(binding.getRoot(),listener);
        this.mCvMoviesItemsHorizontalNormalBinding = binding;
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

        mCvMoviesItemsHorizontalNormalBinding.setVariable(BR.title, title);
        mCvMoviesItemsHorizontalNormalBinding.setVariable(BR.imagePath, item.getPosterPath());

//        Log.e("movie name ", "movie name : " + item.getTitle());
        mCvMoviesItemsHorizontalNormalBinding.executePendingBindings();
    }
}
