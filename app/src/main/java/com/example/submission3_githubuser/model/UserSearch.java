package com.example.submission3_githubuser.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class UserSearch{

	@SerializedName("items")
	private List<ItemsItem> items;

	public List<ItemsItem> getItems(){
		return items;
	}
}