package com.example.submission3_githubuser;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.example.submission3_githubuser.Service.ApiConfig;
import com.example.submission3_githubuser.adapter.GithubUserAdapter;
import com.example.submission3_githubuser.databinding.ActivityMainBinding;
import com.example.submission3_githubuser.form.ListFavoriteActivity;
import com.example.submission3_githubuser.form.SwitchThemeActivity;
import com.example.submission3_githubuser.model.ItemsItem;
import com.example.submission3_githubuser.model.UserSearch;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;
    GithubUserAdapter githubUserAdapter;
    List<ItemsItem> list = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.rvGithubUser.setHasFixedSize(true);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setTitle("Github User");
        }
        showRecyclerList();


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.option_menu, menu);
        SearchManager searchManager = (SearchManager) getSystemService(Context.SEARCH_SERVICE);
        if (searchManager != null) {
            SearchView searchView = (SearchView) (menu.findItem(R.id.search)).getActionView();
            searchView.setSearchableInfo(searchManager.getSearchableInfo(getComponentName()));
            searchView.setQueryHint(getResources().getString(R.string.search_hint));
            searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
                @Override
                public boolean onQueryTextSubmit(String username) {
                    return true;
                }

                @Override
                public boolean onQueryTextChange(String username) {
                    getUserSearch(username);
                    return true;
                }
            });
        }
        return true;
    }

    public void getUserSearch(String username) {
        try {
            binding.progressBar.setVisibility(View.VISIBLE);
            Call<UserSearch> client = ApiConfig.getApiEndPointService().getUserSearch(username);
            client.enqueue(new Callback<UserSearch>() {
                @Override
                public void onResponse(Call<UserSearch> call, Response<UserSearch> response) {
                    binding.progressBar.setVisibility(View.GONE);
                    if (response.isSuccessful()) {
                        if (response.body() != null) {
                            githubUserAdapter.setListUser(response.body().getItems());
                            githubUserAdapter.notifyDataSetChanged();
                        }
                    } else {
                        if (response.body() != null) {
                            Toast.makeText(MainActivity.this, "Gagal", Toast.LENGTH_SHORT).show();
                        }
                    }
                }

                @Override
                public void onFailure(Call<UserSearch> call, Throwable t) {
                    binding.progressBar.setVisibility(View.GONE);
                    Toast.makeText(MainActivity.this, "Gagal", Toast.LENGTH_SHORT).show();
                }
            });
        } catch (Exception exception) {
            Toast.makeText(MainActivity.this, "Gagal", Toast.LENGTH_SHORT).show();
        }
    }

    private void showRecyclerList() {
        githubUserAdapter = new GithubUserAdapter(list, this);
        binding.rvGithubUser.setAdapter(githubUserAdapter);
        binding.rvGithubUser.setLayoutManager(new LinearLayoutManager(this));
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.setting:
                Intent intent = new Intent(MainActivity.this, SwitchThemeActivity.class);
                startActivity(intent);
                return true;
            case R.id.fav_list:
                Intent favIntent = new Intent(MainActivity.this, ListFavoriteActivity.class);
                startActivity(favIntent);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}