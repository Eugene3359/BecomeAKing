package com.scipath.becomeaking.view.dialogue;

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
        TextView textCityName = view.findViewById(R.id.text_city_name);
        textCityName.setText(city.getNameId());
        TextView textCityDescription = view.findViewById(R.id.text_city_description);
        textCityDescription.setText(city.getDescriptionId());
        ImageView imageCity = view.findViewById(R.id.image_city);
        imageCity.setImageResource(city.getImageId());

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
