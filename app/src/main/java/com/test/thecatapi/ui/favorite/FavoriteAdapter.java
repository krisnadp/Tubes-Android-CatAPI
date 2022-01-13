package com.test.thecatapi.ui.favorite;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.test.thecatapi.R;

import java.util.ArrayList;

public class FavoriteAdapter extends RecyclerView.Adapter<FavoriteAdapter.ViewHolder> {

    private final ArrayList<FavoriteResponse> listFavorite;

    public FavoriteAdapter(ArrayList<FavoriteResponse> listFavorite) {
        this.listFavorite = listFavorite;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_favorite_list, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, final int position) {
        Glide.with(viewHolder.itemView)
                .load(listFavorite.get(position).getImage().getUrl())
                .centerCrop()
                .into(viewHolder.favoriteImage);
    }

    @Override
    public int getItemCount() {
        return listFavorite.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        private final ImageView favoriteImage;

        public ViewHolder(View view) {
            super(view);
            favoriteImage = view.findViewById(R.id.favorite_image);
        }
    }
}
