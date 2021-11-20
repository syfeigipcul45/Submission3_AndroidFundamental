package com.example.submission3_githubuser.form;

import android.database.Cursor;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.submission3_githubuser.R;
import com.example.submission3_githubuser.adapter.FavoriteAdapter;
import com.example.submission3_githubuser.databinding.FavoriteLayoutBinding;
import com.example.submission3_githubuser.entity.FavoriteHelper;
import com.example.submission3_githubuser.entity.MappingHelper;
import com.example.submission3_githubuser.model.Favorite;
import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;

public class ListFavoriteActivity extends AppCompatActivity{
    FavoriteLayoutBinding binding;
    FavoriteAdapter favoriteAdapter;
    ArrayList<Favorite> list = new ArrayList<>();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        binding = FavoriteLayoutBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        showRecyclerList();
        retrieve();

        if (getSupportActionBar() != null) {
            getSupportActionBar().setTitle("List Favorite");
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }
    }

    private void showSnackbarMessage(String message) {
        Snackbar.make(binding.rvFavoriteUser, message, Snackbar.LENGTH_SHORT).show();
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void retrieve(){
        ArrayList<Favorite> favorites;
        list.clear();

        FavoriteHelper favoriteHelper = FavoriteHelper.getInstance(getApplicationContext());
        favoriteHelper.open();
        Cursor dataCursor = favoriteHelper.queryAll();
        favorites = MappingHelper.mapCursorToArrayList(dataCursor);
        if (favorites.size() > 0) {
            binding.progressBarFavorite.setVisibility(View.GONE);
            favoriteAdapter.setListFavorite(favorites);
            favoriteAdapter.getListFavorite();
            showSnackbarMessage(String.valueOf(favorites.size()));
        } else {
            favoriteAdapter.setListFavorite(new ArrayList<>());
            showSnackbarMessage("Data tidak ada");
        }

    }

    private void showRecyclerList() {
        binding.rvFavoriteUser.setLayoutManager(new LinearLayoutManager(this));
        favoriteAdapter = new FavoriteAdapter(list, this);
        binding.rvFavoriteUser.setAdapter(favoriteAdapter);

    }
}
