package com.test.thecatapi.ui.favorite;

import com.google.gson.annotations.SerializedName;

public class FavoriteResponse {

	@SerializedName("image")
	private Image image;

	@SerializedName("user_id")
	private String userId;

	@SerializedName("sub_id")
	private String subId;

	@SerializedName("created_at")
	private String createdAt;

	@SerializedName("id")
	private int id;

	@SerializedName("image_id")
	private String imageId;

	public Image getImage(){
		return image;
	}

	public String getUserId(){
		return userId;
	}

	public String getSubId(){
		return subId;
	}

	public String getCreatedAt(){
		return createdAt;
	}

	public int getId(){
		return id;
	}

	public String getImageId(){
		return imageId;
	}
}

class Image{

	@SerializedName("id")
	private String id;

	@SerializedName("url")
	private String url;

	public String getId(){
		return id;
	}

	public String getUrl(){
		return url;
	}
}