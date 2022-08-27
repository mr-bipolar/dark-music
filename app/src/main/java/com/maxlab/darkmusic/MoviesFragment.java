package com.maxlab.darkmusic;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QuerySnapshot;
import com.maxlab.darkmusic.databinding.FragmentMoviesBinding;

import java.util.ArrayList;


public class MoviesFragment extends Fragment {


    public MoviesFragment() {
        // Required empty public constructor
    }

    FragmentMoviesBinding binding;
    FirebaseFirestore database;
    RecyclerView recyclerView;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentMoviesBinding.inflate(inflater, container,false);
        database = FirebaseFirestore.getInstance();
        recyclerView = binding.recyclerview;
        ArrayList<MoviesModel> moviesModels = new ArrayList<>();
        MoviesAdapter adapter = new MoviesAdapter(getContext() , moviesModels);

        database.collection("movies").addSnapshotListener(new EventListener<QuerySnapshot>() {
            @Override
            public void onEvent(@Nullable QuerySnapshot value, @Nullable FirebaseFirestoreException error) {
                moviesModels.clear();
                for (DocumentSnapshot snapshot : value.getDocuments()){
                    MoviesModel model = snapshot.toObject(MoviesModel.class);
                    model.setMovieId(snapshot.getId());
                    moviesModels.add(model);
                }
                adapter.notifyDataSetChanged();
            }
        });

        recyclerView.setLayoutManager(new GridLayoutManager(getContext(), 2));
        recyclerView.setAdapter(adapter);
        return binding.getRoot();
    }
}