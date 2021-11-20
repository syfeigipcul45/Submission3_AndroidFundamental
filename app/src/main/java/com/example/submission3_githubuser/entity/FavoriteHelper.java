package com.example.submission3_githubuser.entity;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class FavoriteHelper {
    private static final String DATABASE_TABLE = "user";
    private static DatabaseHelper databaseHelper;
    private static FavoriteHelper INSTANCE;
    private static SQLiteDatabase database;

    public FavoriteHelper(Context context) {
        databaseHelper = new DatabaseHelper(context);
    }

    public static FavoriteHelper getInstance(Context context) {
        if (INSTANCE == null) {
            synchronized (SQLiteOpenHelper.class) {
                if (INSTANCE == null) {
                    INSTANCE = new FavoriteHelper(context);
                }
            }
        }
        return INSTANCE;
    }

    public void open() throws SQLException {
        database = databaseHelper.getWritableDatabase();
    }

    public void close() {
        databaseHelper.close();

        if (database.isOpen()) {
            database.close();
        }
    }

    public Cursor queryAll() {
        return database.rawQuery("SELECT * FROM " + DATABASE_TABLE, null);
    }

    public long insert(ContentValues values) {
        return database.insert(DATABASE_TABLE, null, values);
    }

    public int delete(String username) {
        return database.delete(DATABASE_TABLE, "username=?", new String[]{username});
    }

    public Cursor selectByUsername(String user) {
        return database.query(
                DATABASE_TABLE, null,
                DatabaseContract.FavoriteColumns.USERNAME+" =?",
                 new String[]{user},
                null,
                null,
                null,
                null);
    }

}
