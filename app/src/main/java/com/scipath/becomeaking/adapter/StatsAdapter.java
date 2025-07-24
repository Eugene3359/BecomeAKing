package com.scipath.becomeaking.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import androidx.core.util.Pair;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.scipath.becomeaking.R;
import com.scipath.becomeaking.contract.model.IStats;
import com.scipath.becomeaking.model.enums.Stat;


public class StatsAdapter extends RecyclerView.Adapter<StatsAdapter.ViewHolder> {

    // Variables
    private IStats stats;
    private Context context;


    // Constructor
    public StatsAdapter(IStats stats, Context context) {
        this.stats = stats;
        this.context = context;
    }


    // ViewHolder subclass
    // Provide a reference to the type of views that you are using
    public static class ViewHolder extends RecyclerView.ViewHolder {

        private final LinearLayout layout;

        public ViewHolder(View view) {
            super(view);
            // Define click listener for the ViewHolder's View
            layout = view.findViewById(R.id.layout_stat);
        }

        public LinearLayout getLayout() {
            return layout;
        }

        public ImageView getStatImageView() {
            return layout.findViewById(R.id.image_view_stat);
        }

        public TextView getStatTextView() {
            return layout.findViewById(R.id.text_view_stat);
        }
    }


    // Create new views (invoked by the layout manager)
    @NonNull
    @Override
    public StatsAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        // Create a new view, which defines the UI of the list item
        View view = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.layout_stat, viewGroup, false);
        return new StatsAdapter.ViewHolder(view);
    }


    @Override
    public void onBindViewHolder(@NonNull StatsAdapter.ViewHolder viewHolder, @SuppressLint("RecyclerView") final int position) {
        // Get element from your dataset at this position and replace the
        // contents of the view with that element
        Pair<Stat, Integer> element = stats.getPair(position);
        Stat stat = element.first;
        int value = element.second;

        // Setting values to views
        viewHolder.getStatImageView().setImageResource(stat.getIconId());
        viewHolder.getStatImageView().setContentDescription(stat.getName(context));
        viewHolder.getStatTextView().setText(stat.getDescription(value, context));
    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return stats.size();
    }
}
