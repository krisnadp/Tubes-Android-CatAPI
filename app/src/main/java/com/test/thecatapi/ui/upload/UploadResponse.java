package com.test.thecatapi.ui.upload;

import com.google.gson.annotations.SerializedName;

public class UploadResponse{

	@SerializedName("level")
	private String level;

	@SerializedName("message")
	private String message;

	@SerializedName("status")
	private int status;

	@SerializedName("approved")
	private int approved;

	@SerializedName("original_filename")
	private String originalFilename;

	@SerializedName("sub_id")
	private String subId;

	@SerializedName("pending")
	private int pending;

	@SerializedName("width")
	private int width;

	@SerializedName("id")
	private String id;

	@SerializedName("url")
	private String url;

	@SerializedName("height")
	private int height;

	public String getLevel(){
		return level;
	}

	public String getMessage(){
		return message;
	}

	public int getStatus(){
		return status;
	}

	public int getApproved(){
		return approved;
	}

	public String getOriginalFilename(){
		return originalFilename;
	}

	public String getSubId(){
		return subId;
	}

	public int getPending(){
		return pending;
	}

	public int getWidth(){
		return width;
	}

	public String getId(){
		return id;
	}

	public String getUrl(){
		return url;
	}

	public int getHeight(){
		return height;
	}
}