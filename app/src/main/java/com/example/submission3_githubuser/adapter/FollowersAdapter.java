package com.example.submission3_githubuser.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.submission3_githubuser.R;
import com.example.submission3_githubuser.databinding.FollowerItemBinding;
import com.example.submission3_githubuser.model.ResponseFollowersModelItem;

import java.util.ArrayList;
import java.util.List;

public class FollowersAdapter extends RecyclerView.Adapter<FollowersAdapter.ListViewHolder>{
    protected List<ResponseFollowersModelItem> listFollowers;
    Context context;

    public FollowersAdapter(ArrayList<ResponseFollowersModelItem> listFollowers, Context context) {
        this.listFollowers = listFollowers;
        this.context = context;
    }


    @NonNull
    @Override
    public ListViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        FollowerItemBinding binding = FollowerItemBinding.inflate(LayoutInflater.from(viewGroup.getContext()), viewGroup, false);
        return new ListViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull ListViewHolder holder, int position) {
        ResponseFollowersModelItem followers = listFollowers.get(position);
        Glide.with(holder.itemView.getContext())
                .load(followers.getAvatarUrl())
                .circleCrop()
                .into(holder.binding.imgAvatarFollower);
        holder.binding.tvUsernameFollower.setText(followers.getLogin());
    }

    @Override
    public int getItemCount() {
        return listFollowers.size();

    }

    public class ListViewHolder extends RecyclerView.ViewHolder {
        FollowerItemBinding binding;
        public ListViewHolder(FollowerItemBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }
}
