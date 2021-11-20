package com.example.submission3_githubuser.entity;

import android.provider.BaseColumns;

public class DatabaseContract {

    static String TABLE_NAME = "user";

    public static final class FavoriteColumns implements BaseColumns {
        public static String AVATAR = "avatar";
        public static String USERNAME = "username";
        public static String NAME = "name";
        public static String COMPANY = "company";
        public static String LOCATION = "location";
        public static String REPOSITORY = "repository";
        public static String FOLLOWERS = "followers";
        public static String FOLLOWING = "following";
    }
}
