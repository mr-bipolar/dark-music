package com.maxlab.darkmusic;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.denzcoskun.imageslider.ImageSlider;
import com.denzcoskun.imageslider.models.SlideModel;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QuerySnapshot;
import com.maxlab.darkmusic.databinding.FragmentHomeBinding;

import java.util.ArrayList;
import java.util.List;


public class HomeFragment extends Fragment {


    public HomeFragment() {
        // Required empty public constructor
    }

  FragmentHomeBinding binding;
  FirebaseFirestore database;
  RecyclerView recyclerView;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        binding = FragmentHomeBinding.inflate(inflater, container, false);
        database = FirebaseFirestore.getInstance();
        ImageSlider imageSlider = binding.slider;
        List<SlideModel> slideModels = new ArrayList<>();

        database.collection("slider").addSnapshotListener(new EventListener<QuerySnapshot>() {
            @Override
            public void onEvent(@Nullable QuerySnapshot value, @Nullable FirebaseFirestoreException error) {

                for (DocumentSnapshot snapshot : value.getDocuments()){
                    SliderModel model = snapshot.toObject(SliderModel.class);
                    slideModels.add(new SlideModel(model.getSliderImage(),model.getSliderName()));
                    imageSlider.setImageList(slideModels,true);
                }

            }
        });

        recyclerView = binding.RecyclerView;
        ArrayList<CategoryModel> categoryModels = new ArrayList<>();
        CategoryAdapter adapter = new CategoryAdapter(getContext(), categoryModels);
        database.collection("category").addSnapshotListener(new EventListener<QuerySnapshot>() {
            @Override
            public void onEvent(@Nullable QuerySnapshot value, @Nullable FirebaseFirestoreException error) {
                categoryModels.clear();
                for (DocumentSnapshot snapshot : value.getDocuments()){
                    CategoryModel model = snapshot.toObject(CategoryModel.class);
                    model.setCategoryId(snapshot.getId());
                    categoryModels.add(model);
                }
                adapter.notifyDataSetChanged();
            }
        });

        recyclerView.setLayoutManager( new GridLayoutManager(getContext(), 2));
        recyclerView.setAdapter(adapter);

        return binding.getRoot();
    }
}