package com.example.submission3_githubuser.entity;

import android.database.Cursor;

import com.example.submission3_githubuser.model.Favorite;

import java.util.ArrayList;

public class MappingHelper {

    public  static ArrayList<Favorite> mapCursorToArrayList(Cursor favoriteCursor){
        ArrayList<Favorite> favoriteList = new ArrayList<>();

        while (favoriteCursor.moveToNext()){
            String avatar = favoriteCursor.getString(favoriteCursor.getColumnIndexOrThrow(DatabaseContract.FavoriteColumns.AVATAR));
            String username = favoriteCursor.getString(favoriteCursor.getColumnIndexOrThrow(DatabaseContract.FavoriteColumns.USERNAME));
            String name = favoriteCursor.getString(favoriteCursor.getColumnIndexOrThrow(DatabaseContract.FavoriteColumns.NAME));
            String company = favoriteCursor.getString(favoriteCursor.getColumnIndexOrThrow(DatabaseContract.FavoriteColumns.COMPANY));
            String location = favoriteCursor.getString(favoriteCursor.getColumnIndexOrThrow(DatabaseContract.FavoriteColumns.LOCATION));
            favoriteList.add(new Favorite(avatar,username,name,company,location));
        }
        return favoriteList;
    }

    public void mapCursorTofirst(Cursor favoriteCursor){
        if (favoriteCursor.moveToFirst()){
            String avatar = favoriteCursor.getString(favoriteCursor.getColumnIndexOrThrow(DatabaseContract.FavoriteColumns.AVATAR));
            String username = favoriteCursor.getString(favoriteCursor.getColumnIndexOrThrow(DatabaseContract.FavoriteColumns.USERNAME));
            String name = favoriteCursor.getString(favoriteCursor.getColumnIndexOrThrow(DatabaseContract.FavoriteColumns.NAME));
            String company = favoriteCursor.getString(favoriteCursor.getColumnIndexOrThrow(DatabaseContract.FavoriteColumns.COMPANY));
            String location = favoriteCursor.getString(favoriteCursor.getColumnIndexOrThrow(DatabaseContract.FavoriteColumns.LOCATION));
        }
    }
}
