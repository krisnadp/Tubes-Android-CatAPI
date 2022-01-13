package com.test.thecatapi.ui.category;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.test.thecatapi.R;

import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;

public class CategoryAdapter extends RecyclerView.Adapter<CategoryAdapter.ViewHolder> {

    private OnItemClickCallback onItemClickCallback;
    private final ArrayList<CategoryResponse> listCategory;

    public CategoryAdapter(ArrayList<CategoryResponse> listCategory) {
        this.listCategory = listCategory;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_category_list, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, final int position) {
        viewHolder.tvCategoryName.setText(StringUtils.capitalize(listCategory.get(position).getName()));
        viewHolder.itemView.setOnClickListener(v -> onItemClickCallback.onItemClicked(listCategory.get(viewHolder.getAbsoluteAdapterPosition())));
    }

    @Override
    public int getItemCount() {
        return listCategory != null ? listCategory.size() : 0;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{
        private final TextView tvCategoryName;

        public ViewHolder(View view) {
            super(view);
            tvCategoryName = view.findViewById(R.id.tv_category_name);
        }
    }

    public void setOnItemClickCallback(OnItemClickCallback onItemClickCallback) {
        this.onItemClickCallback = onItemClickCallback;
    }

    public interface OnItemClickCallback {
        void onItemClicked(CategoryResponse data);
    }
}
