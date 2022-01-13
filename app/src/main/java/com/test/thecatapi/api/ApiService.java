package com.test.thecatapi.api;

import com.test.thecatapi.ui.breed.BreedResponse;
import com.test.thecatapi.ui.category.CatResponse;
import com.test.thecatapi.ui.category.CategoryResponse;
import com.test.thecatapi.ui.favorite.FavoriteResponse;
import com.test.thecatapi.ui.upload.UploadImageResponse;

import java.util.List;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.Query;

public interface ApiService {
    @GET("breeds/search")
    Call<List<BreedResponse>> searchBreedByName(@Query("q") String query);

    @GET("images/search")
    Call<List<CatResponse>> searchCategory(@Query("category_ids") int ids, @Query("limit") int limit);

    @GET("categories")
    Call<List<CategoryResponse>> getAllCategory();

    @Headers("x-api-key: 3a8c3bce-cf69-47cd-8d65-d364aa742a54")
    @GET("favourites?sub_id=test")
    Call<List<FavoriteResponse>> getFavoritedImages();

    @Headers("x-api-key: 3a8c3bce-cf69-47cd-8d65-d364aa742a54")
    @Multipart
    @POST("images/upload")
    Call<UploadImageResponse> postImage(
            @Part MultipartBody.Part file,
            @Part ("sub_id") String subId);
}
