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
import com.example.submission3_githubuser.databinding.FollowingItemBinding;
import com.example.submission3_githubuser.model.ResponseFollowingModelItem;

import java.util.ArrayList;
import java.util.List;

public class FollowingAdapter extends RecyclerView.Adapter<FollowingAdapter.ListViewHolder> {
    protected List<ResponseFollowingModelItem> listFollowing;
    Context context;

    public FollowingAdapter(ArrayList<ResponseFollowingModelItem> listFollowing, Context context) {
        this.listFollowing = listFollowing;
        this.context = context;
    }


    @NonNull
    @Override
    public ListViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        FollowingItemBinding binding = FollowingItemBinding.inflate(LayoutInflater.from(viewGroup.getContext()), viewGroup, false);
        return new ListViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull ListViewHolder holder, int position) {
        ResponseFollowingModelItem following = listFollowing.get(position);
        Glide.with(holder.itemView.getContext())
                .load(following.getAvatarUrl())
                .circleCrop()
                .into(holder.binding.imgAvatarFollowing);
        holder.binding.tvUsernameFollowing.setText(following.getLogin());
    }

    @Override
    public int getItemCount() {
        return listFollowing.size();

    }

    public class ListViewHolder extends RecyclerView.ViewHolder {
        FollowingItemBinding binding;
        public ListViewHolder(FollowingItemBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }
}
