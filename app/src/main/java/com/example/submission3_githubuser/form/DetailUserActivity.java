package com.example.submission3_githubuser.form;

import android.content.ContentValues;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.StringRes;
import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.example.submission3_githubuser.R;
import com.example.submission3_githubuser.Service.ApiConfig;
import com.example.submission3_githubuser.adapter.PageAdapter;
import com.example.submission3_githubuser.databinding.UserDetailBinding;
import com.example.submission3_githubuser.entity.DatabaseContract;
import com.example.submission3_githubuser.entity.FavoriteHelper;
import com.example.submission3_githubuser.model.DetailUserModel;
import com.example.submission3_githubuser.model.Favorite;
import com.google.android.material.tabs.TabLayoutMediator;

import cn.pedant.SweetAlert.SweetAlertDialog;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DetailUserActivity extends AppCompatActivity {
    private UserDetailBinding binding;
    private Favorite favorite;
    private FavoriteHelper favoriteHelper;

    String avatar, user, name, company, location;
    int repos, numFollowers, numFollowing;

    @StringRes
    private final int[] TAB_TITLES = new int[]{
            R.string.tab_follower,
            R.string.tab_following
    };

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = UserDetailBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        favoriteHelper = FavoriteHelper.getInstance(getApplicationContext());
        favoriteHelper.open();

        String username = getIntent().getExtras().getString("username");
        Call<DetailUserModel> client = ApiConfig.getApiEndPointService().getDetailUser(username);
        client.enqueue(new Callback<DetailUserModel>() {
            @Override
            public void onResponse(Call<DetailUserModel> call, Response<DetailUserModel> response) {
                binding.progressBarDetail.setVisibility(View.GONE);
                if (response.isSuccessful()) {
                    if (response.body() != null) {
                        avatar = response.body().getAvatarUrl();
                        user = response.body().getLogin();
                        name = response.body().getName();
                        company = response.body().getCompany();
                        location = response.body().getLocation();
                        repos = response.body().getPublicRepos();
                        numFollowers = response.body().getFollowers();
                        numFollowing = response.body().getFollowing();

                        Glide.with(DetailUserActivity.this)
                                .load(avatar)
                                .circleCrop()
                                .into(binding.imgAvatarDetail);
                        binding.tvUsernameDetail.setText(user);
                        binding.tvNameDetail.setText(name);
                        binding.tvCompanyDetail.setText(company);
                        binding.tvLocationDetail.setText(location);
                        binding.tvRepositoryDetail.setText(repos + " " + getString(R.string.repository));
                        binding.tvFollowersDetail.setText(String.valueOf(numFollowers));
                        binding.tvFollowingDetail.setText(String.valueOf(numFollowing));


                    }
                } else {
                    if (response.body() != null) {
                        Toast.makeText(DetailUserActivity.this, "Gagal", Toast.LENGTH_SHORT).show();
                    }
                }
            }

            @Override
            public void onFailure(Call<DetailUserModel> call, Throwable t) {
                binding.progressBarDetail.setVisibility(View.GONE);
                Toast.makeText(DetailUserActivity.this, "Gagal", Toast.LENGTH_SHORT).show();
            }
        });

        PageAdapter pageAdapter = new PageAdapter(this);
        binding.viewPager.setAdapter(pageAdapter);
        new TabLayoutMediator(binding.tabs, binding.viewPager,
                (tab, position) -> tab.setText(getResources()
                        .getString(TAB_TITLES[position]))
        ).attach();

        if (getSupportActionBar() != null) {
            getSupportActionBar().setTitle("Detail User");
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setElevation(0);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.option_detail_menu, menu);
        for (int i = 0; i < menu.size(); i++) {
            menu.getItem(i).setShowAsAction(MenuItem.SHOW_AS_ACTION_ALWAYS);
        }
        return super.onCreateOptionsMenu(menu);
    }

    private void insertDB() {
        ContentValues values = new ContentValues();
        values.put(DatabaseContract.FavoriteColumns.USERNAME, user);
        values.put(DatabaseContract.FavoriteColumns.AVATAR, avatar);
        values.put(DatabaseContract.FavoriteColumns.NAME, name);
        values.put(DatabaseContract.FavoriteColumns.COMPANY, company);
        values.put(DatabaseContract.FavoriteColumns.LOCATION, location);
        values.put(DatabaseContract.FavoriteColumns.REPOSITORY, repos);
        values.put(DatabaseContract.FavoriteColumns.FOLLOWERS, numFollowers);
        values.put(DatabaseContract.FavoriteColumns.FOLLOWING, numFollowing);
        favoriteHelper.insert(values);
        new SweetAlertDialog(this, SweetAlertDialog.SUCCESS_TYPE)
                .setTitleText("Berhasil")
                .setContentText("Data Berhasil Tersimpan")
                .show();
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.add_daftar:
                insertDB();
                return true;
            case R.id.list_favorite:
                Intent favIntent = new Intent(DetailUserActivity.this, ListFavoriteActivity.class);
                startActivity(favIntent);
                return true;
            case android.R.id.home:
                onBackPressed();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
