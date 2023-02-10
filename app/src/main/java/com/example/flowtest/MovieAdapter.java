package com.example.flowtest;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.List;

public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.MovieViewHolder> {

    private List<Movie> mMovieList;

    public MovieAdapter(List<Movie> movieList) {
        mMovieList = movieList;
    }

    @NonNull
    @Override
    public MovieViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.movie_item, parent, false);
        return new MovieViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MovieViewHolder holder, int position) {
        Movie movie = mMovieList.get(position);

        if (!movie.getImage().isEmpty()) {
            Picasso.get().load(movie.getImage()).into(holder.postImageView);
        }//잘 모르겠지만 url[ 경우면 에러가 뜬다고 한다. url유효성을 검사한다.

        holder.titleTextView.setText("제목 : "+movie.getTitle());
        holder.pubDateTextView.setText("출시 :"+movie.getPubDate());
        holder.userRatingTextView.setText("평점 : "+movie.getUserRating());
    }

    @Override
    public int getItemCount() {
        return mMovieList.size();
    }

    public void setMovieList(List<Movie> movieList) {
        mMovieList = movieList;
        notifyDataSetChanged();
    }

    class MovieViewHolder extends RecyclerView.ViewHolder {
        private ImageView postImageView;
        private TextView titleTextView;
        private TextView pubDateTextView;
        private TextView userRatingTextView;

        public MovieViewHolder(@NonNull View itemView) {
            super(itemView);
            postImageView = itemView.findViewById(R.id.poster_image_view);
            titleTextView = itemView.findViewById(R.id.title_text_view);
            pubDateTextView = itemView.findViewById(R.id.pubDate_text_view);
            userRatingTextView = itemView.findViewById(R.id.userRating_text_view);
        }
    }
}