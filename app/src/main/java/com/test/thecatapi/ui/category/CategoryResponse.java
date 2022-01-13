package com.test.thecatapi.ui.category;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

public class CategoryResponse implements Parcelable {

	@SerializedName("name")
	private String name;

	@SerializedName("id")
	private int id;

	public String getName(){
		return name;
	}

	public int getId(){
		return id;
	}

	@Override
	public int describeContents() {
		return 0;
	}

	@Override
	public void writeToParcel(Parcel dest, int flags) {
		dest.writeString(this.name);
		dest.writeInt(this.id);
	}

	protected CategoryResponse(Parcel in) {
		name = in.readString();
		id = in.readInt();
	}

	public static final Creator<CategoryResponse> CREATOR = new Creator<CategoryResponse>() {
		@Override
		public CategoryResponse createFromParcel(Parcel in) {
			return new CategoryResponse(in);
		}

		@Override
		public CategoryResponse[] newArray(int size) {
			return new CategoryResponse[size];
		}
	};
}