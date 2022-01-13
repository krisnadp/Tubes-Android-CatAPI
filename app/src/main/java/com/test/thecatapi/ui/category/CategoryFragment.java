package com.test.thecatapi.ui.category;

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

import com.test.thecatapi.MainActivity;
import com.test.thecatapi.api.ApiConfig;
import com.test.thecatapi.databinding.FragmentCategoryBinding;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CategoryFragment extends Fragment {

    private FragmentCategoryBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentCategoryBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        LinearLayoutManager layoutManager = new GridLayoutManager(getContext(), 2);
        binding.rvCategory.setLayoutManager(layoutManager);
        getCategory();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    private void getCategory() {
        showLoading(true);
        Call<List<CategoryResponse>> client = ApiConfig.getApiService().getAllCategory();
        client.enqueue(new Callback<List<CategoryResponse>>() {
            @Override
            public void onResponse(@NonNull Call<List<CategoryResponse>> call, @NonNull Response<List<CategoryResponse>> response) {
                showLoading(false);
                if (response.isSuccessful()) {
                    if (response.body() != null) {
                        setCategoryData(response.body());
                    }
                }
            }
            @Override
            public void onFailure(@NonNull Call<List<CategoryResponse>> call, @NonNull Throwable t) {
                showLoading(false);
                Toast.makeText(getContext(), "Fail to retrieve result", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void setCategoryData(List<CategoryResponse> categoryData) {
        ArrayList<CategoryResponse> listCategory = new ArrayList<>(categoryData);
        CategoryAdapter adapter = new CategoryAdapter(listCategory);
        binding.rvCategory.setAdapter(adapter);
        adapter.setOnItemClickCallback(this::getCat);
    }

    public void getCat(CategoryResponse categoryResponse) {
        int id = categoryResponse.getId();
        Objects.requireNonNull(((MainActivity) requireActivity()).getSupportActionBar()).setTitle("Cat with " + categoryResponse.getName() + " category");
        showLoading(true);
        Call<List<CatResponse>> client = ApiConfig.getApiService().searchCategory(id, 12);
        client.enqueue(new Callback<List<CatResponse>>() {
            @Override
            public void onResponse(@NonNull Call<List<CatResponse>> call, @NonNull Response<List<CatResponse>> response) {
                showLoading(false);
                if (response.isSuccessful()) {
                    if (response.body() != null) {
                        setCatData(response.body());
                    }
                }
            }

            @Override
            public void onFailure(@NonNull Call<List<CatResponse>> call, @NonNull Throwable t) {
                showLoading(false);
                Toast.makeText(getContext(), "Fail to retrieve result", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void setCatData(List<CatResponse> catData) {
        ArrayList<CatResponse> listCat = new ArrayList<>(catData);
        CatAdapter adapter = new CatAdapter(listCat);
        binding.rvCategory.setAdapter(adapter);
    }

    private void showLoading(Boolean isLoading) {
        if (isLoading) {
            binding.progressBar.setVisibility(View.VISIBLE);
        } else {
            binding.progressBar.setVisibility(View.GONE);
        }
    }
}