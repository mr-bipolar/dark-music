package com.maxlab.darkmusic;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

public class MoviesAdapter extends RecyclerView.Adapter<MoviesAdapter.MoviesViewHolder> {
    Context context;
    ArrayList<MoviesModel> moviesModels;

    public MoviesAdapter(Context context, ArrayList<MoviesModel> moviesModels){
        this.context = context;
        this.moviesModels = moviesModels;
    }

    @NonNull
    @Override
    public MoviesViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.movieview, null);
        return new MoviesViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MoviesViewHolder holder, int position) {
       MoviesModel moviesModel = moviesModels.get(position);
        Glide.with(context).load(moviesModel.getMovieImage()).into(holder.movieImage);
        holder.movieName.setText(moviesModel.getMovieName());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, MovieSongActivity.class);
                intent.putExtra("movieid", moviesModel.getMovieId());
                intent.putExtra("moviename", moviesModel.getMovieName());
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return moviesModels.size();
    }

    public class MoviesViewHolder extends RecyclerView.ViewHolder {

        ImageView movieImage;
        TextView  movieName;
        public MoviesViewHolder(@NonNull View itemView) {
            super(itemView);
            movieName = itemView.findViewById(R.id.movieName);
            movieImage = itemView.findViewById(R.id.movieImage);
        }
    }
}
