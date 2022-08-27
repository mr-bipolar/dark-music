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
import com.maxlab.darkmusic.argmusicplayer.Models.ArgAudio;
import com.maxlab.darkmusic.argmusicplayer.PlayerViews.ArgPlayerSmallView;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QuerySnapshot;
import com.maxlab.darkmusic.databinding.ActivitySongBinding;


import java.util.ArrayList;

public class SongActivity extends AppCompatActivity {
    ActivitySongBinding binding;
    FirebaseFirestore database;
    RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivitySongBinding.inflate(getLayoutInflater());
        database = FirebaseFirestore.getInstance();
        recyclerView = binding.recyclerview;
        final String catId = getIntent().getStringExtra("catid");
        final String catname = getIntent().getStringExtra("catname");
        setContentView(binding.getRoot());
        binding.catName.setText(catname);


        binding.backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });


        ArrayList<SongsModel> songsModels = new ArrayList<>();
        SongsAdapter adapter = new SongsAdapter(this, songsModels);


        database.collection("category").document(catId).collection("songs").addSnapshotListener(new EventListener<QuerySnapshot>() {
            @Override
            public void onEvent(@Nullable QuerySnapshot value, @Nullable FirebaseFirestoreException error) {
                songsModels.clear();
                for (DocumentSnapshot snapshot : value.getDocuments()){
                    SongsModel model = snapshot.toObject(SongsModel.class);
                    model.setSongId(snapshot.getId());
                    songsModels.add(model);
                }
                 adapter.notifyDataSetChanged();
            }

        });

        BroadcastReceiver mMessageReceiver = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                String songid = intent.getStringExtra("songid");
                database.collection("category").document(catId)
                        .collection("songs")
                        .document(songid).get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {

            @Override
            public void onSuccess(DocumentSnapshot documentSnapshot) {

                if (documentSnapshot.exists()){
                    final String playerSongName = documentSnapshot.getString("songName");
                    final String playerSingerName = documentSnapshot.getString("singerName");
                    final String Url = documentSnapshot.getString("songUrl");

                    ArgAudio audio = ArgAudio.createFromURL(playerSingerName, playerSongName, Url);

                    ArgPlayerSmallView argMusicPlayer = (ArgPlayerSmallView) findViewById(R.id.argmusicplayer);
                    argMusicPlayer.play(audio);
                    argMusicPlayer.disableNextPrevButtons();
                    argMusicPlayer.enableNotification(SongActivity.this);


                }else{
                    Toast.makeText(SongActivity.this, "Data Loading Faild, Check your network", Toast.LENGTH_SHORT).show();

                }
            }

         });
   }};
         LocalBroadcastManager.getInstance(this).registerReceiver(mMessageReceiver, new IntentFilter("custom-data"));
         recyclerView.setLayoutManager(new GridLayoutManager(this,1));
         recyclerView.setAdapter(adapter);


    }
}