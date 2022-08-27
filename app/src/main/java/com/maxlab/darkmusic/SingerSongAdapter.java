package com.maxlab.darkmusic;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

public class SingerSongAdapter extends RecyclerView.Adapter<SingerSongAdapter.SingerViewHolder> {

    Context context;
    ArrayList<SingerSongModel> singerSongModels;
    public SingerSongAdapter(Context context, ArrayList<SingerSongModel> singerSongModels){
        this.context = context;
        this.singerSongModels = singerSongModels;

    }

    @NonNull
    @Override
    public SingerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.singerview, null);
        return new SingerViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SingerViewHolder holder, int position) {

        SingerSongModel singerSongModel = singerSongModels.get(position);
        Glide.with(context).load(singerSongModel.getSongImage()).into(holder.singerSongImage);
        holder.singerSongName.setText(singerSongModel.getSongName());
        holder.singerSongMovie.setText(singerSongModel.getMovieName());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent("custom-data");
                intent.putExtra("songid", singerSongModel.getSongId());
                LocalBroadcastManager.getInstance(context).sendBroadcast(intent);

            }
        });

    }

    @Override
    public int getItemCount() {
        return singerSongModels.size();
    }


    public class SingerViewHolder  extends RecyclerView.ViewHolder{
        ImageView singerSongImage;
        TextView singerSongName, singerSongMovie;
        public SingerViewHolder(@NonNull View itemView) {
            super(itemView);
            singerSongImage = itemView.findViewById(R.id.singerSongImage);
            singerSongName  = itemView.findViewById(R.id.singerSongName);
            singerSongMovie = itemView.findViewById(R.id.singerSongMovie);
        }
    }
}
