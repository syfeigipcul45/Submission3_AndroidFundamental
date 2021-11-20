package com.example.submission3_githubuser.setting;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

public class ViewModelFactory extends ViewModelProvider.NewInstanceFactory {
    private final SettingPreferences pref;

    public ViewModelFactory(SettingPreferences pref) {
        this.pref = pref;
    }

    @SuppressWarnings("unchecked")
    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        if (modelClass.isAssignableFrom(MainViewModel.class)) {
            return (T) new MainViewModel(pref);
        }
        throw new IllegalArgumentException("Unknown ViewModel class: "+ modelClass.getName());
    }
}
