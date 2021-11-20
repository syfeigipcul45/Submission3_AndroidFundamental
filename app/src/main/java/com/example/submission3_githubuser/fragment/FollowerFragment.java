package com.example.submission3_githubuser.fragment;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.example.submission3_githubuser.R;
import com.example.submission3_githubuser.Service.ApiConfig;
import com.example.submission3_githubuser.adapter.FollowersAdapter;
import com.example.submission3_githubuser.databinding.FragmentFollowerBinding;
import com.example.submission3_githubuser.form.DetailUserActivity;
import com.example.submission3_githubuser.model.ResponseFollowersModelItem;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FollowerFragment extends Fragment {

    private FragmentFollowerBinding binding;
    FollowersAdapter followersAdapter;
    ArrayList<ResponseFollowersModelItem> list = new ArrayList<>();

    public FollowerFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentFollowerBinding.inflate(inflater, container, false);
        View view = binding.getRoot();
        return view;
    }

    @Override
    public void onViewCreated(@NonNull final View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        DetailUserActivity detailUser = (DetailUserActivity) getActivity();
        String username = detailUser.getIntent().getExtras().getString("username");

        binding.rvFollower.setLayoutManager(new LinearLayoutManager(view.getContext()));
        binding.progressBarFollower.setVisibility(View.VISIBLE);
        Call<List<ResponseFollowersModelItem>> client = ApiConfig.getApiEndPointService().getFollowers(username);
        client.enqueue(new Callback<List<ResponseFollowersModelItem>>() {
            @Override
            public void onResponse(Call<List<ResponseFollowersModelItem>> call, Response<List<ResponseFollowersModelItem>> response) {
                binding.progressBarFollower.setVisibility(View.GONE);
                if (response.isSuccessful()) {
                    if (response.body() != null) {
                        list.addAll(response.body());
                        followersAdapter = new FollowersAdapter(list, getContext());
                        binding.rvFollower.setAdapter(followersAdapter);
                    }
                } else {
                    if (response.body() != null) {
                        Toast.makeText(getContext(), "Gagal", Toast.LENGTH_SHORT).show();
                    }
                }
            }

            @Override
            public void onFailure(Call<List<ResponseFollowersModelItem>> call, Throwable t) {
                binding.progressBarFollower.setVisibility(View.GONE);
                Log.d("TAG RESULT", "onFailure: " +t.getMessage());
                Toast.makeText(getContext(), "Request Failure"+t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}