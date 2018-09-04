package com.example.fergie.application;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.fergie.application.models.PostsModel;

import java.util.ArrayList;

public class PostsAdapter extends RecyclerView.Adapter<PostsAdapter.MyViewHolder>
{

    private ArrayList<PostsModel> postsList;
    private Context context;


    public PostsAdapter(ArrayList<PostsModel> postsList, Context context)
    {
        this.postsList = postsList;
        this.context = context;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType)
    {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_item, parent, false);
        MyViewHolder myViewHolder = new MyViewHolder(view);

        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position)
    {
        holder.name.setText(postsList.get(position).getName());
        holder.message.setText(postsList.get(position).getMessage());

        Glide.with(context)
                .load(postsList.get(position).getImg())
                .into(holder.image);
    }

    @Override
    public int getItemCount()
    {
        return postsList.size();
    }


    class MyViewHolder extends RecyclerView.ViewHolder
    {
        TextView name, message;
        ImageView image;

        public MyViewHolder(View itemView)
        {
            super(itemView);
            name = itemView.findViewById(R.id.name_id);
            message = itemView.findViewById(R.id.message_id);
            image = itemView.findViewById(R.id.image_id);
        }
    }
}
