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
import com.maxlab.darkmusic.databinding.FragmentSingersBinding;

import java.util.ArrayList;


public class SingersFragment extends Fragment {


    public SingersFragment() {
        // Required empty public constructor
    }

    FragmentSingersBinding binding;
    FirebaseFirestore database;
    RecyclerView recyclerView;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentSingersBinding.inflate(inflater, container, false);
        database = FirebaseFirestore.getInstance();
        recyclerView = binding.recyclerView;
        ArrayList<SingersModel> singersModels = new ArrayList<>();
        SingersAdapter adapter = new SingersAdapter(getContext(), singersModels);
        database.collection("singers").addSnapshotListener(new EventListener<QuerySnapshot>() {
            @Override
            public void onEvent(@Nullable QuerySnapshot value, @Nullable FirebaseFirestoreException error) {
                singersModels.clear();
                for (DocumentSnapshot snapshot : value.getDocuments()){
                    SingersModel model = snapshot.toObject(SingersModel.class);
                    model.setSingerId(snapshot.getId());
                    singersModels.add(model);
                }
                adapter.notifyDataSetChanged();
            }
        });

        recyclerView.setLayoutManager(new GridLayoutManager(getContext(), 2));
        recyclerView.setAdapter(adapter);
        return binding.getRoot();
    }
}