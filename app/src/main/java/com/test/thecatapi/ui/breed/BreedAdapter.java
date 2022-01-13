package com.test.thecatapi.ui.breed;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.test.thecatapi.R;

import java.util.ArrayList;

public class BreedAdapter extends RecyclerView.Adapter<BreedAdapter.ViewHolder> {

    private final ArrayList<BreedResponse> listBreed;

    public BreedAdapter(ArrayList<BreedResponse> listBreed) {
        this.listBreed = listBreed;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_breed_list, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, final int position) {
        Glide.with(viewHolder.itemView)
                .load("https://cdn2.thecatapi.com/images/"+ listBreed.get(position).getReferenceImageId() +".jpg")
                .centerCrop()
                .into(viewHolder.breedImage);
        viewHolder.tvBreedName.setText(listBreed.get(position).getName());
        viewHolder.tvBreedDesc.setText(listBreed.get(position).getTemperament());
    }

    @Override
    public int getItemCount() {
        return listBreed.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        private final TextView tvBreedName;
        private final TextView tvBreedDesc;
        private final ImageView breedImage;

        public ViewHolder(View view) {
            super(view);
            tvBreedName = view.findViewById(R.id.tv_breed_name);
            tvBreedDesc = view.findViewById(R.id.tv_breed_desc);
            breedImage = view.findViewById(R.id.breed_image);
        }
    }
}
