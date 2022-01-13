package com.test.thecatapi.ui.category;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class CatResponse {

	@SerializedName("width")
	private int width;

	@SerializedName("categories")
	private List<CategoryResponse> categories;

	@SerializedName("id")
	private String id;

	@SerializedName("url")
	private String url;

	@SerializedName("breeds")
	private List<Object> breeds;

	@SerializedName("height")
	private int height;

	public int getWidth(){
		return width;
	}

	public List<CategoryResponse> getCategories(){
		return categories;
	}

	public String getId(){
		return id;
	}

	public String getUrl(){
		return url;
	}

	public List<Object> getBreeds(){
		return breeds;
	}

	public int getHeight(){
		return height;
	}
}