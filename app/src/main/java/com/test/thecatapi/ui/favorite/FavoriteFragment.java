package com.test.thecatapi.ui.favorite;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.test.thecatapi.api.ApiConfig;
import com.test.thecatapi.databinding.FragmentFavoriteBinding;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FavoriteFragment extends Fragment {

    private FragmentFavoriteBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        binding =  FragmentFavoriteBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        LinearLayoutManager layoutManager = new GridLayoutManager(getContext(), 2);
        binding.rvFavorite.setLayoutManager(layoutManager);
        getFavorite();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    private void getFavorite() {
        showLoading(true);
        Call<List<FavoriteResponse>> client = ApiConfig.getApiService().getFavoritedImages();
        client.enqueue(new Callback<List<FavoriteResponse>>() {
            @Override
            public void onResponse(@NonNull Call<List<FavoriteResponse>> call, @NonNull Response<List<FavoriteResponse>> response) {
                showLoading(false);
                if (response.isSuccessful()) {
                    if (response.body() != null) {
                        setFavoriteData(response.body());
                    }
                }
            }

            @Override
            public void onFailure(@NonNull Call<List<FavoriteResponse>> call, @NonNull Throwable t) {
                showLoading(false);
                Toast.makeText(getContext(), "Fail to retrieve result", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void setFavoriteData(List<FavoriteResponse> categoryData) {
        ArrayList<FavoriteResponse> listFavorite = new ArrayList<>(categoryData);
        FavoriteAdapter adapter = new FavoriteAdapter(listFavorite);
        binding.rvFavorite.setAdapter(adapter);
    }

    private void showLoading(Boolean isLoading) {
        if (isLoading) {
            binding.progressBar.setVisibility(View.VISIBLE);
        } else {
            binding.progressBar.setVisibility(View.GONE);
        }
    }
}