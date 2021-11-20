package com.example.submission3_githubuser.adapter;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.example.submission3_githubuser.fragment.FollowerFragment;
import com.example.submission3_githubuser.fragment.FollowingFragment;

public class PageAdapter extends FragmentStateAdapter {
    public PageAdapter(AppCompatActivity activity) {
        super(activity);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        Fragment fragment = null;
        switch (position) {
            case 0:
                fragment = new FollowerFragment();
                break;
            case 1:
                fragment = new FollowingFragment();
                break;
        }
        return fragment;
    }

    @Override
    public int getItemCount() {
        return 2;
    }
}
