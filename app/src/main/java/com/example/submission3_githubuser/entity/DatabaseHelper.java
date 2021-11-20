package com.example.submission3_githubuser.entity;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper {

    public static String DATABASE_NAME = "favorite.db";

    private static final int DATABASE_VERSION = 1;

    private static final String SQL_CREATE_TABLE_USER = String.format("CREATE TABLE %s"
    +" (%s TEXT PRIMARY KEY," +
            " %s TEXT NULL,"+
            " %s TEXT NULL,"+
            " %s TEXT NULL,"+
            " %s TEXT NULL,"+
            " %s INTEGER NULL,"+
            " %s INTEGER NULL,"+
            " %s INTEGER NULL)",
            DatabaseContract.TABLE_NAME,
            DatabaseContract.FavoriteColumns.USERNAME,
            DatabaseContract.FavoriteColumns.AVATAR,
            DatabaseContract.FavoriteColumns.NAME,
            DatabaseContract.FavoriteColumns.COMPANY,
            DatabaseContract.FavoriteColumns.LOCATION,
            DatabaseContract.FavoriteColumns.REPOSITORY,
            DatabaseContract.FavoriteColumns.FOLLOWERS,
            DatabaseContract.FavoriteColumns.FOLLOWING);

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQL_CREATE_TABLE_USER);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS "+DatabaseContract.TABLE_NAME);
        onCreate(db);
    }
}
