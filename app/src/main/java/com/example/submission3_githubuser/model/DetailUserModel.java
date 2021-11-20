package com.example.submission3_githubuser.model;

import com.google.gson.annotations.SerializedName;

public class DetailUserModel {

    @SerializedName("repos_url")
    private String reposUrl;

    @SerializedName("login")
    private String login;

    @SerializedName("company")
    private String company;

    @SerializedName("id")
    private int id;

    @SerializedName("public_repos")
    private int publicRepos;

    @SerializedName("email")
    private Object email;

    @SerializedName("followers")
    private int followers;

    @SerializedName("avatar_url")
    private String avatarUrl;

    @SerializedName("following")
    private int following;

    @SerializedName("name")
    private String name;

    @SerializedName("location")
    private String location;

    public String getLogin() {
        return login;
    }

    public String getCompany() {
        return company;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public int getPublicRepos() {
        return publicRepos;
    }

    public int getFollowers() {
        return followers;
    }

    public String getAvatarUrl() {
        return avatarUrl;
    }

    public int getFollowing() {
        return following;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public String getLocation() {
        return location;
    }
}