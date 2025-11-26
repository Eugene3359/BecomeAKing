package com.scipath.becomeaking.adapter;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.scipath.becomeaking.R;
import com.scipath.becomeaking.contract.callback.ObjectCallback;
import com.scipath.becomeaking.model.enums.Title;
import com.scipath.becomeaking.view.view.CustomRadioButton;


public class TitlesAdapter extends RecyclerView.Adapter<TitlesAdapter.ViewHolder> {

    // Variables
    private final Title[] dataSet;
    private final ObjectCallback objectCallback;
    private int selectedPosition = -1;


    // Constructor
    public TitlesAdapter(Title[] dataSet, ObjectCallback objectCallback) {
        this.dataSet = dataSet;
        this.objectCallback = objectCallback;
    }


    // ViewHolder subclass
    // Provide a reference to the type of views that you are using
    public static class ViewHolder extends RecyclerView.ViewHolder {
        private final CustomRadioButton radioButton;

        public ViewHolder(View view) {
            super(view);
            radioButton = view.findViewById(R.id.title_radio_button);
            radioButton.setBackgroundColor(view.getContext().getColor(R.color.button_blue));
            radioButton.setBackgroundColorSelected(view.getContext().getColor(R.color.radio_button_selected));
        }

        public CustomRadioButton getRadioButton() {
            return radioButton;
        }
    }


    // Create new views (invoked by the layout manager)
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        View view = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.layout_radio_button_title, viewGroup, false);

        return new ViewHolder(view);
    }


    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(ViewHolder viewHolder, @SuppressLint("RecyclerView") final int position) {
        // Get element from your dataset at this position and replace the
        // contents of the view with that element
        Title title = dataSet[position];

        // Setting values to views
        viewHolder.getRadioButton().setText(title.nameId);
        viewHolder.getRadioButton().setChecked(position == selectedPosition);
        viewHolder.getRadioButton().setOnCheckedChangeListener(
                (compoundButton, isChecked) -> {
                    if (isChecked) {
                        // Update selected position
                        selectedPosition = viewHolder.getAdapterPosition();
                        // Call listener
                        objectCallback.onClick(title);
                    }
                });
    }


    // Return the size of dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return dataSet.length;
    }
}