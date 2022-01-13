package com.test.thecatapi.ui.breed;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.SearchView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.test.thecatapi.api.ApiConfig;
import com.test.thecatapi.databinding.FragmentBreedBinding;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class BreedFragment extends Fragment {

    private FragmentBreedBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentBreedBinding.inflate(inflater, container, false);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());

        binding.searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                searchBreedByName(query);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });

        binding.rvBreed.setLayoutManager(layoutManager);
        return binding.getRoot();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    public void searchBreedByName(String query) {
        showLoading(true);
        Call<List<BreedResponse>> client = ApiConfig.getApiService().searchBreedByName(query);
        client.enqueue(new Callback<List<BreedResponse>>() {
            @Override
            public void onResponse(@NonNull Call<List<BreedResponse>> call, @NonNull Response<List<BreedResponse>> response) {
                showLoading(false);
                if (response.isSuccessful()) {
                    if (response.body() != null) {
                        setBreedData(response.body());
                    }
                }
            }

            @Override
            public void onFailure(@NonNull Call<List<BreedResponse>> call, @NonNull Throwable t) {
                showLoading(false);
                Toast.makeText(getContext(), "Fail to retrieve result", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void setBreedData(List<BreedResponse> breedData) {
        ArrayList<BreedResponse> listBreed = new ArrayList<>(breedData);
        BreedAdapter adapter = new BreedAdapter(listBreed);
        binding.rvBreed.setAdapter(adapter);
    }

    private void showLoading(Boolean isLoading) {
        if (isLoading) {
            binding.progressBar.setVisibility(View.VISIBLE);
        } else {
            binding.progressBar.setVisibility(View.GONE);
        }
    }
}