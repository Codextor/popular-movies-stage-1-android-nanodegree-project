package com.example.android.popularmoviesstage1;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

public class PosterAdapter extends RecyclerView.Adapter<PosterAdapter.PosterAdapterViewHolder> {

    private String[] mMovieData;

    public PosterAdapter() {
    }

    public class PosterAdapterViewHolder extends RecyclerView.ViewHolder {
        public final ImageView mPosterImageView;

        public PosterAdapterViewHolder(View view) {
            super(view);
            mPosterImageView = view.findViewById(R.id.iv_movie_poster);
        }
    }

    @Override
    public PosterAdapterViewHolder onCreateViewHolder( ViewGroup viewGroup, int i) {
        Context context = viewGroup.getContext();
        int layoutIdForListItem = R.layout.poster_list_item;
        LayoutInflater inflater = LayoutInflater.from(context);
        boolean shouldAttachToParentImmediately = false;

        View view = inflater.inflate(layoutIdForListItem, viewGroup, shouldAttachToParentImmediately);
        return new PosterAdapterViewHolder(view);
    }

    @Override
    public void onBindViewHolder(PosterAdapterViewHolder posterAdapterViewHolder, int position) {
        String posterForThisMovie = mMovieData[position];
        Picasso.get().load(posterForThisMovie).into(posterAdapterViewHolder.mPosterImageView);
    }

    @Override
    public int getItemCount() {
        if(null == mMovieData) return 0;
        return mMovieData.length;
    }

    public void setMovieData(String[] movieData) {
        mMovieData = movieData;
        notifyDataSetChanged();
    }
}
