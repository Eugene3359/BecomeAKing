package com.scipath.becomeaking.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.scipath.becomeaking.R;
import com.scipath.becomeaking.contract.model.ICategory;
import com.scipath.becomeaking.contract.model.IStats;
import com.scipath.becomeaking.view.fragment.ItemsFragment;

import java.util.ArrayList;


public class CategoriesAdapter extends RecyclerView.Adapter<CategoriesAdapter.ViewHolder> {

    // Variables
    private Context context;
    private ArrayList<ICategory> categories;


    // Constructor
    public CategoriesAdapter(ArrayList<ICategory> categories, Context context) {
        this.categories = categories;
        this.context = context;
    }


    // ViewHolder subclass
    // Provide a reference to the type of views that you are using
    public static class ViewHolder extends RecyclerView.ViewHolder {
        private final LinearLayout layout;

        public ViewHolder(View view) {
            super(view);
            // Define click listener for the ViewHolder's View
            layout = view.findViewById(R.id.category_layout);
        }

        public LinearLayout getLayout() {
            return layout;
        }

        public TextView getCategoryNameView() {
            return layout.findViewById(R.id.text_view_category);
        }

        public ImageView getCategoryImageView() {
            return layout.findViewById(R.id.image_view_category);
        }

        public RecyclerView getCategoryStatsView() {
            return layout.findViewById(R.id.stats_list);
        }
    }


    // Create new views (invoked by the layout manager)
    @NonNull
    @Override
    public CategoriesAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        // Create a new view, which defines the UI of the list item
        View view = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.layout_category, viewGroup, false);

        return new CategoriesAdapter.ViewHolder(view);
    }


    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(@NonNull CategoriesAdapter.ViewHolder viewHolder, @SuppressLint("RecyclerView") final int position) {
        // Get element from your dataset at this position and replace the
        // contents of the view with that element
        ICategory ICategory = categories.get(position);
        IStats stats = categories.get(position).getStats();

        // Setting values to views
        viewHolder.getCategoryNameView().setText(ICategory.getName(context));
        if (ICategory.getImageId()!= 0)
            viewHolder.getCategoryImageView().setImageResource(ICategory.getImageId());
        viewHolder.getCategoryImageView().setContentDescription(ICategory.getName(context));
        viewHolder.getCategoryStatsView().setLayoutManager(new LinearLayoutManager(context));
        viewHolder.getCategoryStatsView().setAdapter(new StatsAdapter(stats, context));

        viewHolder.getLayout().setOnClickListener(view -> {
            if (!categories.get(position).getItems().isEmpty()) {
                Fragment fragment = new ItemsFragment();
                Bundle bundle = new Bundle();
                bundle.putInt("categoryId", categories.get(position).getId());
                fragment.setArguments(bundle);
                ((FragmentActivity) context).getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.fragment_frame, fragment)
                        .addToBackStack(null)
                        .commit();
            }
        });
    }


    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return categories.size();
    }
}
