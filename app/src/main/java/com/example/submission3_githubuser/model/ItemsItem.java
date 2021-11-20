package com.example.submission3_githubuser.model;

import com.google.gson.annotations.SerializedName;

public class ItemsItem{

	@SerializedName("login")
	private String login;

	@SerializedName("avatar_url")
	private String avatarUrl;

	@SerializedName("id")
	private int id;

	public String getLogin(){
		return login;
	}

	public String getAvatarUrl(){
		return avatarUrl;
	}

	public int getId(){
		return id;
	}
}