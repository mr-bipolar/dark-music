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
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;

public class SongsAdapter extends RecyclerView.Adapter<SongsAdapter.SongsViewHolder> {

    Context context;
    ArrayList<SongsModel> songsModels;

    public  SongsAdapter(Context context, ArrayList<SongsModel> songsModels){
        this.context = context;
        this.songsModels = songsModels;
    }

    @NonNull
    @Override
    public SongsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.songsview, null);

        return new SongsViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SongsViewHolder holder, int position) {

        SongsModel songsModel = songsModels.get(position);
        Glide.with(context).load(songsModel.getSongImage()).into(holder.songImage);
        holder.songName.setText(songsModel.getSongName());
        holder.songmovieName.setText(songsModel.getMovieName());
        holder.singersName.setText(songsModel.getSingerName());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent("custom-data");
                intent.putExtra("songid", songsModel.getSongId());
                LocalBroadcastManager.getInstance(context).sendBroadcast(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return songsModels.size();
    }

    public class SongsViewHolder extends  RecyclerView.ViewHolder{

        ImageView songImage;
        TextView songName;
        TextView songmovieName;
        TextView singersName;
        public SongsViewHolder(@NonNull View itemView) {
            super(itemView);

            songImage = itemView.findViewById(R.id.songImage);
            songName  = itemView.findViewById(R.id.songName);
            songmovieName = itemView.findViewById(R.id.songmovieName);
            singersName = itemView.findViewById(R.id.singersName);
        }
    }
}
