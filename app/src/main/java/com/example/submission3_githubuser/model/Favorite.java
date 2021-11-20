package com.example.submission3_githubuser.model;

import android.os.Parcel;
import android.os.Parcelable;

public class Favorite implements Parcelable {

    private String avatar;
    private String username;
    private String name;
    private String company;
    private String location;

    public Favorite(String avatar, String username, String name, String company, String location) {
        this.avatar = avatar;
        this.username = username;
        this.name = name;
        this.company = company;
        this.location = location;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    protected Favorite(Parcel in) {
        avatar = in.readString();
        username = in.readString();
        name = in.readString();
        company = in.readString();
        location = in.readString();
    }

    public static final Creator<Favorite> CREATOR = new Creator<Favorite>() {
        @Override
        public Favorite createFromParcel(Parcel in) {
            return new Favorite(in);
        }

        @Override
        public Favorite[] newArray(int size) {
            return new Favorite[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(avatar);
        dest.writeString(username);
        dest.writeString(name);
        dest.writeString(company);
        dest.writeString(location);
    }
}
