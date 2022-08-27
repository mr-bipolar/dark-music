package com.maxlab.darkmusic;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QuerySnapshot;
import com.maxlab.darkmusic.argmusicplayer.Models.ArgAudio;
import com.maxlab.darkmusic.argmusicplayer.PlayerViews.ArgPlayerSmallView;
import com.maxlab.darkmusic.databinding.ActivityMovieSongBinding;

import java.util.ArrayList;

public class MovieSongActivity extends AppCompatActivity {
    ActivityMovieSongBinding binding;
    FirebaseFirestore database;
    RecyclerView recyclerView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMovieSongBinding.inflate(getLayoutInflater());
        database = FirebaseFirestore.getInstance();
        recyclerView = binding.recyclerView;
        final String movieid = getIntent().getStringExtra("movieid");
        final String moviename = getIntent().getStringExtra("moviename");
        binding.movieSName.setText(moviename);
        setContentView(binding.getRoot());

        binding.moviesongBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });

        ArrayList<MovieSongModel> movieSongModels = new ArrayList<>();
        MovieSongAdapter adapter = new MovieSongAdapter(this, movieSongModels);

        database.collection("movies").document(movieid).collection("songs").addSnapshotListener(new EventListener<QuerySnapshot>() {
            @Override
            public void onEvent(@Nullable QuerySnapshot value, @Nullable FirebaseFirestoreException error) {
                movieSongModels.clear();
                for (DocumentSnapshot snapshot : value.getDocuments()){
                   MovieSongModel model = snapshot.toObject(MovieSongModel.class);
                    model.setSongId(snapshot.getId());
                    movieSongModels.add(model);
                }
                adapter.notifyDataSetChanged();
            }

        });

        BroadcastReceiver mMessageReceiver = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                String songid = intent.getStringExtra("songid");
                database.collection("movies").document(movieid)
                        .collection("songs")
                        .document(songid).get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {

                    @Override
                    public void onSuccess(DocumentSnapshot documentSnapshot) {

                        if (documentSnapshot.exists()){
                            final String playerSongName = documentSnapshot.getString("songName");
                            final String playerMovieName = documentSnapshot.getString("singerName");
                            final String Url = documentSnapshot.getString("songUrl");
                            ArgAudio audio = ArgAudio.createFromURL(playerMovieName, playerSongName, Url);
                            ArgPlayerSmallView argMusicPlayer = (ArgPlayerSmallView) findViewById(R.id.argmusicplayer);
                            argMusicPlayer.play(audio);
                            argMusicPlayer.disableNextPrevButtons();
                            argMusicPlayer.enableNotification(MovieSongActivity.this);
                        }else{
                            Toast.makeText(MovieSongActivity.this, "Data Loading Faild, Check your network", Toast.LENGTH_SHORT).show();

                        }
                    }

                });
            }};
        LocalBroadcastManager.getInstance(this).registerReceiver(mMessageReceiver, new IntentFilter("custom-data"));


        recyclerView.setLayoutManager(new GridLayoutManager(this,2));
        recyclerView.setAdapter(adapter);
    }
}