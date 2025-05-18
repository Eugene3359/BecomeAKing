package com.scipath.becomeaking.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.scipath.becomeaking.R;
import com.scipath.becomeaking.model.Title;
import com.scipath.becomeaking.view.customview.CustomRadioButton;

public class TitlesAdapter extends RecyclerView.Adapter<TitlesAdapter.ViewHolder> {

    // Variables
    private Context context;
    private Title[] localDataSet;
    private ObjectClickListener objectClickListener;
    private int selectedPosition = -1;


    // Constructor
    public TitlesAdapter(Title[] dataSet, ObjectClickListener objectClickListener, Context context) {
        localDataSet = dataSet;
        this.objectClickListener = objectClickListener;
        this.context = context;
    }


    // ViewHolder subclass
    // Provide a reference to the type of views that you are using
    public static class ViewHolder extends RecyclerView.ViewHolder {
        private final CustomRadioButton radioButton;

        public ViewHolder(View view) {
            super(view);
            // Define click listener for the ViewHolder's View
            radioButton = (CustomRadioButton) view.findViewById(R.id.title_radio_button);
        }

        public CustomRadioButton getRadioButton() {
            return radioButton;
        }
    }


    // Create new views (invoked by the layout manager)
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        // Create a new view, which defines the UI of the list item
        View view = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.layout_radio_button_title, viewGroup, false);

        return new ViewHolder(view);
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(ViewHolder viewHolder, @SuppressLint("RecyclerView") final int position) {
        // Get element from your dataset at this position and replace the
        // contents of the view with that element
        viewHolder.getRadioButton().setText(localDataSet[position].getName(context));

        // Checked selected radio button
        viewHolder.radioButton.setChecked(position
                == selectedPosition);

        viewHolder.radioButton.setOnCheckedChangeListener(
                (compoundButton, checked) -> {
                    if (checked) {
                        // Update selected position
                        selectedPosition = viewHolder.getAdapterPosition();
                        // Call listener
                        objectClickListener.onClick(localDataSet[position]);
                    }
                });
    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return localDataSet.length;
    }
}