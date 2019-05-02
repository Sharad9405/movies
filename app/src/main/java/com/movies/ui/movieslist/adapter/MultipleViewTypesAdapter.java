
package com.movies.ui.movieslist.adapter;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.movies.R;
import com.movies.databinding.CvMoviesItemsHorizontalColoredBinding;
import com.movies.databinding.CvMoviesItemsHorizontalNormalBinding;
import com.movies.generic.BaseViewHolder;
import com.movies.generic.GenericRecyclerViewAdapter;
import com.movies.generic.OnRecyclerItemClickListener;
import com.movies.model.Results;
import com.movies.ui.movieslist.view.MoviesAdapterHorizontalColoredViewHolder;
import com.movies.ui.movieslist.view.MoviesAdapterHorizontalNormalViewHolder;


public class MultipleViewTypesAdapter extends GenericRecyclerViewAdapter<Results, OnRecyclerItemClickListener, BaseViewHolder<Results, OnRecyclerItemClickListener>> {

    private static final int VIEW_TYPE_NORMAL = R.layout.cv_movies_items_horizontal_normal;
    private static final int VIEW_TYPE_COLORED = R.layout.cv_movies_items_horizontal_colored;
    private LayoutInflater mInflater;

    public MultipleViewTypesAdapter(Context context, OnRecyclerItemClickListener listener) {
        super(context, listener);
        this.mInflater = LayoutInflater.from(context);
    }

    /**
     * Create here appropriate ViewHolder for each view type
     *
     * @param parent   The ViewGroup into which the new View will be added after it is bound to an adapter position.
     * @param viewType The view type of the new View. It corresponds to layout resource id {@link android.support.annotation.LayoutRes} for convenience
     * @return ViewHolder which corresponds to needed view type
     */
    @Override
    public BaseViewHolder<Results, OnRecyclerItemClickListener> onCreateViewHolder(ViewGroup parent, int viewType) {

        CvMoviesItemsHorizontalNormalBinding normalBinding = DataBindingUtil
                .inflate(LayoutInflater.from(parent.getContext()), VIEW_TYPE_NORMAL, parent, false);

        CvMoviesItemsHorizontalColoredBinding coloredBinding = DataBindingUtil
                .inflate(LayoutInflater.from(parent.getContext()), VIEW_TYPE_COLORED, parent, false);

        switch (viewType) {
            case VIEW_TYPE_NORMAL:
                return new MoviesAdapterHorizontalNormalViewHolder(normalBinding, getListener());
            case VIEW_TYPE_COLORED:
                return new MoviesAdapterHorizontalColoredViewHolder(coloredBinding, getListener());
            default:
                throw new IllegalArgumentException("Unexpected view type " + viewType);
        }
    }

    /**
     * Implement here returning various view types upon your requirements
     *
     * @param position item position
     * @return appropriate view type
     */
    @Override
    public int getItemViewType(int position) {
        return (position % 2 == 0) ? VIEW_TYPE_NORMAL : VIEW_TYPE_COLORED;
//        return VIEW_TYPE_COLORED;
//        return position;
    }

    @Override
    public long getItemId(int position) {
        return super.getItemId(position);
    }

}
