package com.example.submission3_githubuser.form;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.datastore.preferences.core.Preferences;
import androidx.datastore.preferences.rxjava3.RxPreferenceDataStoreBuilder;
import androidx.datastore.rxjava3.RxDataStore;
import androidx.lifecycle.ViewModelProvider;

import com.example.submission3_githubuser.MainActivity;
import com.example.submission3_githubuser.setting.MainViewModel;
import com.example.submission3_githubuser.setting.SettingPreferences;
import com.example.submission3_githubuser.setting.ViewModelFactory;

public class SplashScreenActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        int waktu = 2000;
        super.onCreate(savedInstanceState);

        RxDataStore<Preferences> dataStore = new RxPreferenceDataStoreBuilder(this, "theme_setting").build();
        SettingPreferences pref = SettingPreferences.getInstance(dataStore);
        MainViewModel mainViewModel = new ViewModelProvider(this, new ViewModelFactory(pref)).get(MainViewModel.class);
        mainViewModel.getThemeSettings().observe(this, isDarkModeActive -> {
            if (isDarkModeActive) {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
            } else {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
            }
        });

        new Handler(Looper.getMainLooper()).postDelayed(()-> {
                Intent intent = new Intent(SplashScreenActivity.this, MainActivity.class);
                startActivity(intent);
                finish();
        },waktu);
    }
}
