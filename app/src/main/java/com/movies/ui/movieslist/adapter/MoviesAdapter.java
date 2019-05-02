package com.movies.ui.movieslist.adapter;

import android.databinding.DataBindingUtil;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.Toast;

import com.android.databinding.library.baseAdapters.BR;
import com.movies.R;
import com.movies.databinding.CvMoviesItemsBinding;
import com.movies.generic.OnRecyclerItemClickListener;
import com.movies.model.MoviesMasterResponse;
import com.movies.model.Results;
import com.movies.ui.movieslist.MoviesActivity;

import java.util.List;

public class MoviesAdapter extends RecyclerView.Adapter<MoviesAdapter.MoviesAdapterViewHolder> {

    private MoviesActivity mMoviesActivity;
    private List<MoviesMasterResponse> mItemsList;

    public MoviesAdapter(MoviesActivity activity, List<MoviesMasterResponse> mMovieResults) {

        this.mMoviesActivity = activity;
        this.mItemsList = mMovieResults;
    }


    @NonNull
    @Override
    public MoviesAdapterViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {

        /** get the movies item layout **/
        CvMoviesItemsBinding binding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), R.layout.cv_movies_items, parent, false);

        return new MoviesAdapterViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull MoviesAdapterViewHolder vh, int pos) {

        vh.bindUser(mItemsList.get(pos));

    }

    @Override
    public long getItemId(int position) {
        return super.getItemId(position);
    }

    @Override
    public int getItemViewType(int position) {
        return position;
    }

    @Override
    public int getItemCount() {
        return mItemsList == null ? 0 : mItemsList.size();
    }

    public void setUserList(List<MoviesMasterResponse> userList) {
        this.mItemsList = userList;
        notifyDataSetChanged();
    }


    class MoviesAdapterViewHolder extends RecyclerView.ViewHolder implements OnRecyclerItemClickListener {

        CvMoviesItemsBinding mCvMovieItemsBinding;
        MultipleViewTypesAdapter adapter;

        MoviesAdapterViewHolder(CvMoviesItemsBinding binding) {
            super(binding.getRoot());
            this.mCvMovieItemsBinding = binding;
        }

        void bindUser(MoviesMasterResponse response) {

            /** set the movies title **/
            mCvMovieItemsBinding.setVariable(BR.mainResponse, response);

            /** Creating dynamic horizontal list **/
            LinearLayoutManager layoutManager = new LinearLayoutManager(itemView.getContext(), LinearLayoutManager.HORIZONTAL, false);
            adapter = new MultipleViewTypesAdapter(itemView.getContext(), this);
            mCvMovieItemsBinding.rvHoriList.setLayoutManager(layoutManager);
            mCvMovieItemsBinding.rvHoriList.setAdapter(adapter);
            adapter.setItems(mItemsList.get(getAdapterPosition()).getResults());


            mCvMovieItemsBinding.executePendingBindings();

/*
            mCvHomeVerticalItemsBinding.rvHoriList.addOnScrollListener(new EndlessLinearRvScrollListener(layoutManager) {
                @Override
                public void onLoadMore(int current_page) {

                    if (-1 == getAdapterPosition() || "1".equalsIgnoreCase(String.valueOf(current_page))) {
                        return;
                    }
//                    ((MoviesActivity) itemView.getContext()).getMovieServerCall(current_page);
                }
            });
            */
        }

        @Override
        public void onItemClick(int position) {
            /** Recycler view onClick callback method **/
            MultipleViewTypesAdapter mMultipleViewTypesAdapter = (MultipleViewTypesAdapter) (mCvMovieItemsBinding.rvHoriList.getAdapter());
            // get the User entity, associated with the clicked item.
            final Results clickedUser = mMultipleViewTypesAdapter.getItem(position);
            // now you are free to do whatever you want with it.
            // We just show a Toast message

            String strItem = "";
            if (null != clickedUser.getName()) {
                strItem = clickedUser.getName();
            } else if (null != clickedUser.getTitle()) {
                strItem = clickedUser.getTitle();
            }
            Toast.makeText(itemView.getContext(), strItem + "", Toast.LENGTH_SHORT).show();
        }
    }
}
