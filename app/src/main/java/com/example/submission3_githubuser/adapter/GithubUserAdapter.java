package com.example.submission3_githubuser.adapter;

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
import com.example.submission3_githubuser.R;
import com.example.submission3_githubuser.databinding.ItemRowUserBinding;
import com.example.submission3_githubuser.form.DetailUserActivity;
import com.example.submission3_githubuser.model.ItemsItem;

import java.util.List;

public class GithubUserAdapter extends RecyclerView.Adapter<GithubUserAdapter.ListViewHolder> {
    protected List<ItemsItem> listUser;
    Context context;

    public GithubUserAdapter(List<ItemsItem> listUser, Context context) {
        this.listUser = listUser;
        this.context = context;
    }


    @NonNull
    @Override
    public ListViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        ItemRowUserBinding binding = ItemRowUserBinding.inflate(LayoutInflater.from(viewGroup.getContext()), viewGroup, false);
        return new ListViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull ListViewHolder holder, int position) {
        ItemsItem githubUser = listUser.get(position);
        Glide.with(holder.itemView.getContext())
                .load(githubUser.getAvatarUrl())
                .circleCrop()
                .into(holder.binding.imgItemAvatar);
        holder.binding.tvItemUsername.setText(githubUser.getLogin());

    }

    @Override
    public int getItemCount() {
        return listUser.size();

    }

    public void setListUser(List<ItemsItem> listUser) {
        this.listUser = listUser;
    }

    public class ListViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        ItemRowUserBinding binding;

        public ListViewHolder(ItemRowUserBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
            binding.getRoot().setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            int position = getAdapterPosition();
            if (position != RecyclerView.NO_POSITION) {
                ItemsItem detailUser = listUser.get(position);
                Intent intent = new Intent(context, DetailUserActivity.class);
                intent.putExtra("username", listUser.get(position).getLogin());

                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(intent);
                Toast.makeText(v.getContext(), detailUser.getLogin(), Toast.LENGTH_SHORT).show();
            }
        }
    }
}
