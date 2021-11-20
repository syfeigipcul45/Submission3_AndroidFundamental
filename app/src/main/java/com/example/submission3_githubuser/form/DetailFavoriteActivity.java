package com.example.submission3_githubuser.form;

import android.database.Cursor;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.example.submission3_githubuser.R;
import com.example.submission3_githubuser.databinding.FavoriteDetailBinding;
import com.example.submission3_githubuser.entity.DatabaseContract;
import com.example.submission3_githubuser.entity.FavoriteHelper;

import java.util.ArrayList;

public class DetailFavoriteActivity extends AppCompatActivity {
    private FavoriteDetailBinding binding;
    private FavoriteHelper favoriteHelper;
    String avatar, user, name, company, location;
    int repos, numFollowers, numFollowing;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = FavoriteDetailBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        favoriteHelper = FavoriteHelper.getInstance(getApplicationContext());
        favoriteHelper.open();

        String username = getIntent().getExtras().getString("username");
        Cursor dataCursor = favoriteHelper.selectByUsername(username);
        binding.progressBarFav.setVisibility(View.VISIBLE);
        if(dataCursor.moveToFirst()){
            binding.progressBarFav.setVisibility(View.GONE);
            avatar = dataCursor.getString(dataCursor.getColumnIndexOrThrow(DatabaseContract.FavoriteColumns.AVATAR));
            user = dataCursor.getString(dataCursor.getColumnIndexOrThrow(DatabaseContract.FavoriteColumns.USERNAME));
            name = dataCursor.getString(dataCursor.getColumnIndexOrThrow(DatabaseContract.FavoriteColumns.NAME));
            company = dataCursor.getString(dataCursor.getColumnIndexOrThrow(DatabaseContract.FavoriteColumns.COMPANY));
            location = dataCursor.getString(dataCursor.getColumnIndexOrThrow(DatabaseContract.FavoriteColumns.LOCATION));
            repos = dataCursor.getInt(dataCursor.getColumnIndexOrThrow(DatabaseContract.FavoriteColumns.REPOSITORY));
            numFollowers = dataCursor.getInt(dataCursor.getColumnIndexOrThrow(DatabaseContract.FavoriteColumns.FOLLOWERS));
            numFollowing = dataCursor.getInt(dataCursor.getColumnIndexOrThrow(DatabaseContract.FavoriteColumns.FOLLOWING));

            Glide.with(DetailFavoriteActivity.this)
                    .load(avatar)
                    .circleCrop()
                    .into(binding.imgAvatarFav);
            binding.tvUsernameFav.setText(user);
            binding.tvNameFav.setText(name);
            binding.tvCompanyFav.setText(company);
            binding.tvLocationFav.setText(location);
            binding.tvRepositoryFav.setText(repos + " " + getString(R.string.repository));
            binding.tvFollowersFav.setText(String.valueOf(numFollowers));
            binding.tvFollowingFav.setText(String.valueOf(numFollowing));
        }

        if (getSupportActionBar() != null) {
            getSupportActionBar().setTitle("Detail User Favorite");
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
