package com.test.thecatapi.ui.upload;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.test.thecatapi.api.ApiConfig;
import com.test.thecatapi.databinding.FragmentUploadBinding;

import org.json.JSONObject;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UploadFragment extends Fragment {

    private static final String SUB_ID = "test";

    private FragmentUploadBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentUploadBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        binding.imageUpload.setOnClickListener(v -> {
            binding.cardUpload.setVisibility(View.VISIBLE);
            binding.bg.setVisibility(View.VISIBLE);
        });

        binding.btnUpload.setOnClickListener(v -> uploadImage());

        binding.btnGallery.setOnClickListener(v-> getImageFromGallery());

        binding.btnCamera.setOnClickListener(v-> getImageFromCamera());
    }

    public void getImageFromGallery() {
        galleryResultLaucher.launch(new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI));
    }

    public void getImageFromCamera() {
        cameraResultLaucher.launch(new Intent(MediaStore.ACTION_IMAGE_CAPTURE));
    }

    ActivityResultLauncher<Intent> galleryResultLaucher = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(),
            new ActivityResultCallback<ActivityResult>() {
                @Override
                public void onActivityResult(ActivityResult result) {
                    binding.cardUpload.setVisibility(View.GONE);
                    binding.bg.setVisibility(View.GONE);
                    if (result != null && result.getResultCode() == Activity.RESULT_OK) {
                        Intent data = result.getData();
                        if (data != null) {
                            Uri uri = data.getData();
                            Bitmap bitmap;
                            try {
                                bitmap = MediaStore.Images.Media.getBitmap(requireContext().getContentResolver(), uri);
                                binding.imageUpload.setImageBitmap(bitmap);
                                binding.btnUpload.setVisibility(View.VISIBLE);
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        }
                    }
                }
            }
    );

    ActivityResultLauncher<Intent> cameraResultLaucher = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(),
            new ActivityResultCallback<ActivityResult>() {
                @Override
                public void onActivityResult(ActivityResult result) {
                    binding.cardUpload.setVisibility(View.GONE);
                    binding.bg.setVisibility(View.GONE);
                    if (result != null && result.getResultCode() == Activity.RESULT_OK) {
                        Intent data = result.getData();
                        if (data != null) {
                            Bitmap bitmap = (Bitmap) data.getExtras().get("data");
                            binding.imageUpload.setImageBitmap(bitmap);
                            binding.btnUpload.setVisibility(View.VISIBLE);
                        }
                    }
                }
            }
    );

    private void uploadImage() {
        File file = new File(requireContext().getCacheDir()+"/Image.jpg");
        BitmapDrawable drawable = (BitmapDrawable) binding.imageUpload.getDrawable();
        Bitmap bitmap = drawable.getBitmap();
        try {
            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, new FileOutputStream(file));
            postImage(file);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    private void postImage(File file) {
        MultipartBody.Part filePart = MultipartBody.Part.createFormData("file", file.getName(), RequestBody.create(file ,MediaType.parse("image/jpg")));
        showLoading(true);
        Call<UploadImageResponse> client = ApiConfig.getApiService().postImage(filePart, SUB_ID);
        client.enqueue(new Callback<UploadImageResponse>() {
            @Override
            public void onResponse(@NonNull Call<UploadImageResponse> call, @NonNull Response<UploadImageResponse> response) {
                showLoading(false);
                if (response.isSuccessful()) {
                    if (response.body() != null) {
                        int approved = response.body().getApproved();
                        if (String.valueOf(approved).equals(String.valueOf(1))) {
                            Toast.makeText(requireContext(), "Post Image Success", Toast.LENGTH_SHORT).show();
                        }
                    }
                } else {
                    try {
                        JSONObject jObjError;
                        if (response.errorBody() != null) {
                            jObjError = new JSONObject(response.errorBody().string());
                            Toast.makeText(getContext(), jObjError.getString("message"), Toast.LENGTH_SHORT).show();
                        }
                    } catch (Exception e) {
                        Toast.makeText(getContext(), e.getMessage(), Toast.LENGTH_LONG).show();
                    }
                }
            }

            @Override
            public void onFailure(@NonNull Call<UploadImageResponse> call, @NonNull Throwable t) {
                showLoading(false);
                Toast.makeText(requireContext(), t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void showLoading(Boolean isLoading) {
        if (isLoading) {
            binding.progressBar.setVisibility(View.VISIBLE);
        } else {
            binding.progressBar.setVisibility(View.GONE);
        }
    }
}