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
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

public class SingersAdapter extends RecyclerView.Adapter<SingersAdapter.SingersViewHolder> {

    Context context;
    ArrayList<SingersModel> singersModels;
    public  SingersAdapter(Context context, ArrayList<SingersModel> singersModels){
         this.context = context;
         this.singersModels = singersModels;
    }

    @NonNull
    @Override
    public SingersViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.singersview, null);
        return new SingersViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SingersViewHolder holder, int position) {
        SingersModel singersModel =  singersModels.get(position);
        Glide.with(context).load(singersModel.getSingerImage()).into(holder.singerImage);
        holder.singerName.setText(singersModel.getSingerName());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, SingerActivity.class);
                intent.putExtra("singer_id", singersModel.getSingerId());
                intent.putExtra("singer_name", singersModel.getSingerName());
                context.startActivity(intent);


            }
        });

    }

    @Override
    public int getItemCount() {
        return singersModels.size();
    }

    public class SingersViewHolder extends RecyclerView.ViewHolder{

        ImageView singerImage;
        TextView singerName;
        public SingersViewHolder(@NonNull View itemView) {
            super(itemView);

            singerName = itemView.findViewById(R.id.singerName);
            singerImage = itemView.findViewById(R.id.singerImage);
        }
    }
}
