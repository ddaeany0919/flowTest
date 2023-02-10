package com.example.flowtest;

import android.content.Intent;
import android.net.Uri;
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

        if (movie.getImage() !=null && !movie.getImage().isEmpty()){
        Picasso.get().load(movie.getImage()).into(holder.postImageView);
        }
        String title = movie.getTitle().replaceAll("<b>","").replaceAll("</b>","");
        holder.titleTextView.setText("제목 : " + title);
        holder.pubDateTextView.setText("출시 :" + movie.getPubDate());
        holder.userRatingTextView.setText("평점 : " + movie.getUserRating());

        holder.postImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://search.naver.com/search.naver?where=nexearch&sm=top_hty&fbm=0&ie=utf8&query="+title));
                v.getContext().startActivity(intent); //해당 링크로 주소 이동
            }
        });
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