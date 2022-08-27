package com.maxlab.darkmusic;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

public class CategoryAdapter extends RecyclerView.Adapter<CategoryAdapter.CategoryViewHolder>{

    Context context;
    ArrayList<CategoryModel> categoryModels;
    RecyclerView recyclerView;
    public CategoryAdapter(Context context, ArrayList<CategoryModel> categoryModels) {

        this.context = context;
        this.categoryModels = categoryModels;

    }
    @NonNull
    @Override
    public CategoryViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.categoryview, null);
        return new CategoryViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CategoryViewHolder holder, int position) {

        CategoryModel categoryModel = categoryModels.get(position);
        Glide.with(context).load(categoryModel.getCategoryImage()).into(holder.categoryImage);
//        holder.categoryImage.setImageResource(categoryModel.getCategoryImage());
        holder.categoryTitle.setText(categoryModel.getCategoryName());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, SongActivity.class);
                intent.putExtra("catid", categoryModel.getCategoryId());
                intent.putExtra("catname", categoryModel.getCategoryName());
                context.startActivity(intent);

            }
        });
    }

    @Override
    public int getItemCount() {
        return categoryModels.size();
    }

    public class CategoryViewHolder extends RecyclerView.ViewHolder{

        ImageView categoryImage;
        TextView categoryTitle;
        public CategoryViewHolder(@NonNull View itemView) {
            super(itemView);

            categoryImage = itemView.findViewById(R.id.categoryImage);
            categoryTitle = itemView.findViewById(R.id.categoryTitle);
        }
    }
}
