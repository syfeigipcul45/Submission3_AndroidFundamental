package com.example.submission3_githubuser.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.submission3_githubuser.databinding.ItemRowFavoriteBinding;
import com.example.submission3_githubuser.entity.FavoriteHelper;
import com.example.submission3_githubuser.form.DetailFavoriteActivity;
import com.example.submission3_githubuser.form.ListFavoriteActivity;
import com.example.submission3_githubuser.model.Favorite;

import java.util.ArrayList;

import cn.pedant.SweetAlert.SweetAlertDialog;

public class FavoriteAdapter extends RecyclerView.Adapter<FavoriteAdapter.ListViewHolder> {

    private final ArrayList<Favorite> listFavorite;
    Context context;
    FavoriteHelper favoriteHelper ;

    public FavoriteAdapter(ArrayList<Favorite> listFavorite, Context context) {
        this.listFavorite = listFavorite;
        this.context = context;
    }

    public ArrayList<Favorite> getListFavorite(){
        return listFavorite;
    }

    public void setListFavorite(ArrayList<Favorite> listFavorite) {
        if (listFavorite.size() > 0){
            this.listFavorite.clear();
        }
        this.listFavorite.addAll(listFavorite);
    }

    @NonNull
    @Override
    public FavoriteAdapter.ListViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        ItemRowFavoriteBinding binding = ItemRowFavoriteBinding.inflate(LayoutInflater.from(viewGroup.getContext()), viewGroup, false);
        return new ListViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull FavoriteAdapter.ListViewHolder holder, @SuppressLint("RecyclerView") int position) {
        favoriteHelper = FavoriteHelper.getInstance(holder.itemView.getContext());
        favoriteHelper.open();
        Favorite favorite = listFavorite.get(position);
        String username = favorite.getUsername();
        Glide.with(holder.itemView.getContext())
                .load(favorite.getAvatar())
                .circleCrop()
                .into(holder.binding.imgFavAvatar);
        holder.binding.tvFavUsername.setText(favorite.getUsername());
        holder.binding.tvFavName.setText(favorite.getName());
        holder.binding.tvFavCompany.setText(favorite.getCompany());
        holder.binding.tvFavLocation.setText(favorite.getLocation());
        holder.binding.buttonFav.setOnClickListener(v -> new SweetAlertDialog(holder.itemView.getContext(), SweetAlertDialog.WARNING_TYPE)
                .setTitleText("Are you sure?")
                .setContentText("Won't be able to recover this file!")
                .setConfirmText("Yes,delete it!")
                .setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
                    @Override
                    public void onClick(SweetAlertDialog sDialog) {
                        favoriteHelper.delete(username);
                        sDialog
                                .setTitleText("Deleted!")
                                .setContentText("Your imaginary file has been deleted!")
                                .setConfirmText("OK")
                                .setConfirmClickListener(null)
                                .changeAlertType(SweetAlertDialog.SUCCESS_TYPE);
                        listFavorite.remove(position);
                        notifyItemRemoved(position);
                        notifyItemRangeChanged(position, listFavorite.size());
                    }
                })
                .show());

    }

    @Override
    public int getItemCount() {
        return listFavorite.size();
    }

    public class ListViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        ItemRowFavoriteBinding binding;

        public ListViewHolder(ItemRowFavoriteBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
            binding.getRoot().setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            int position = getAdapterPosition();
            if(position != RecyclerView.NO_POSITION){
                Favorite detailFav = listFavorite.get(position);
                Intent intent = new Intent(context, DetailFavoriteActivity.class);
                intent.putExtra("username", listFavorite.get(position).getUsername());
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(intent);
                Toast.makeText(v.getContext(), detailFav.getUsername(), Toast.LENGTH_SHORT).show();
            }
        }
    }
}
