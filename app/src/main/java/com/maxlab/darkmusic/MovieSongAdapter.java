package com.maxlab.darkmusic;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

public class MovieSongAdapter extends RecyclerView.Adapter<MovieSongAdapter.MovieSongViewHolder> {

    Context context;
    ArrayList<MovieSongModel> movieSongModels;
    public  MovieSongAdapter(Context context, ArrayList<MovieSongModel> movieSongModels){
        this.context = context;
        this.movieSongModels = movieSongModels;
    }

    @NonNull
    @Override
    public MovieSongViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.moviesongview, null);
        return new MovieSongViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MovieSongViewHolder holder, int position) {
       MovieSongModel movieSongModel = movieSongModels.get(position);
        Glide.with(context).load(movieSongModel.getSongImage()).into(holder.movieSongImage);
        holder.movieSongName.setText(movieSongModel.getSongName());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent("custom-data");
                intent.putExtra("songid", movieSongModel.getSongId());
                LocalBroadcastManager.getInstance(context).sendBroadcast(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return movieSongModels.size();
    }

    public class MovieSongViewHolder extends RecyclerView.ViewHolder{
        ImageView movieSongImage;
        TextView movieSongName;
        public MovieSongViewHolder(@NonNull View itemView) {
            super(itemView);
            movieSongImage = itemView.findViewById(R.id.movieSongImage);
            movieSongName  = itemView.findViewById(R.id.movieSongName);
        }
    }
}
