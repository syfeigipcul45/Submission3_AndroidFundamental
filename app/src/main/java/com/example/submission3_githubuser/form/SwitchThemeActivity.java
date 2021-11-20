package com.example.submission3_githubuser.form;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.datastore.preferences.core.Preferences;
import androidx.datastore.preferences.rxjava3.RxPreferenceDataStoreBuilder;
import androidx.datastore.rxjava3.RxDataStore;
import androidx.lifecycle.ViewModelProvider;

import com.example.submission3_githubuser.databinding.SettingPreferencesBinding;
import com.example.submission3_githubuser.setting.MainViewModel;
import com.example.submission3_githubuser.setting.SettingPreferences;
import com.example.submission3_githubuser.setting.ViewModelFactory;

public class SwitchThemeActivity extends AppCompatActivity {
    SettingPreferencesBinding binding;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = SettingPreferencesBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        if (getSupportActionBar() != null) {
            getSupportActionBar().setTitle("Pengaturan");
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }

        RxDataStore<Preferences> dataStore = new RxPreferenceDataStoreBuilder(this, "theme_setting").build();
        SettingPreferences pref = SettingPreferences.getInstance(dataStore);

        MainViewModel mainViewModel = new ViewModelProvider(this, new ViewModelFactory(pref)).get(MainViewModel.class);
        mainViewModel.getThemeSettings().observe(this, isDarkModeActive -> {
            if (isDarkModeActive) {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
                binding.switchTheme.setChecked(true);
            } else {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
                binding.switchTheme.setChecked(false);
            }
        });

        binding.switchTheme.setOnCheckedChangeListener((buttonView, isChecked) ->
                mainViewModel.saveThemeSetting(isChecked));

    }
}
