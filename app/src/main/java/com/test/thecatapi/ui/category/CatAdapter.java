package com.test.thecatapi.ui.category;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.test.thecatapi.R;

import java.util.ArrayList;

public class CatAdapter extends RecyclerView.Adapter<CatAdapter.ViewHolder> {

    private final ArrayList<CatResponse> listCat;

    public CatAdapter(ArrayList<CatResponse> listCat) {
        this.listCat = listCat;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_favorite_list, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Glide.with(holder.itemView)
                .load(listCat.get(position).getUrl())
                .centerCrop()
                .into(holder.catImage);
    }

    @Override
    public int getItemCount() {
        return listCat.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        private final ImageView catImage;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            catImage = itemView.findViewById(R.id.favorite_image);
        }
    }
}
