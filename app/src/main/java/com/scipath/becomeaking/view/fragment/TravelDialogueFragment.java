package com.scipath.becomeaking.view.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import com.scipath.becomeaking.R;
import com.scipath.becomeaking.contract.callback.Callback;
import com.scipath.becomeaking.contract.model.ICity;


public class TravelDialogueFragment extends DialogFragment {

    private ICity city;
    private Callback callback;


    public static class Builder {
        private ICity city;
        private Callback callback;

        public TravelDialogueFragment.Builder setCity(ICity city) {
            this.city = city;
            return this;
        }

        public TravelDialogueFragment.Builder setCallback(@Nullable Callback callback) {
            this.callback = callback;
            return this;
        }

        public TravelDialogueFragment build() {
            TravelDialogueFragment dialogue = new TravelDialogueFragment();
            dialogue.city = city;
            dialogue.callback = callback;
            return dialogue;
        }
    }


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setCancelable(false);
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_travel_dialogue, container, false);
        if (city == null) return view;

        // Views
        TextView textViewCityName = view.findViewById(R.id.text_view_city_name);
        textViewCityName.setText(city.getNameId());
        TextView textViewCityDescription = view.findViewById(R.id.text_view_city_description);
        textViewCityDescription.setText(city.getDescriptionId());
        ImageView imageViewCity = view.findViewById(R.id.image_view_city);
        imageViewCity.setImageResource(city.getImageId());

        // Buttons
        Button buttonBack = view.findViewById(R.id.button_back);
        buttonBack.setOnClickListener(v -> {
            dismiss();
        });

        Button buttonTravel = view.findViewById(R.id.button_travel);
        buttonTravel.setOnClickListener(v -> {
            if (callback != null) callback.call();
            dismiss();
        });

        return view;
    }

    @Override
    public void onStart() {
        super.onStart();
        if (getDialog() != null && getDialog().getWindow() != null) {
            getDialog().getWindow().setLayout(
                    ViewGroup.LayoutParams.MATCH_PARENT,
                    ViewGroup.LayoutParams.WRAP_CONTENT
            );
            getDialog().getWindow().setBackgroundDrawableResource(android.R.color.transparent);
        }
    }
}
