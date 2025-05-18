package com.scipath.becomeaking.view.fragment;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.scipath.becomeaking.BecomeAKing;
import com.scipath.becomeaking.R;
import com.scipath.becomeaking.model.Personage;
import com.scipath.becomeaking.view.activity.GameActivity;
import com.scipath.becomeaking.view.activity.TitleSelectionActivity;

public class PersonageFragment extends Fragment {

    public static PersonageFragment newInstance() {
        return new PersonageFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_personage, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        // Getting Personage from Application
        Personage personage = BecomeAKing.getInstance().getCurrentPersonage();

        // Views
        TextView textViewName = view.findViewById(R.id.text_view_name);
        TextView textViewTitle = view.findViewById(R.id.text_view_title);
        TextView textViewReputation = view.findViewById(R.id.text_view_reputation2);
        ImageView imageViewPersonageIcon = view.findViewById(R.id.image_view_personage_icon);
        TextView textViewNutrition = view.findViewById(R.id.text_view_nutrition);
        TextView textViewClothes = view.findViewById(R.id.text_view_clothes);
        TextView textViewHousing = view.findViewById(R.id.text_view_housing);
        TextView textViewHorse = view.findViewById(R.id.text_view_horse);

        // Setting Text values
        textViewName.setText(personage.getName());
        textViewTitle.setText(personage.getTitle().getName(getActivity()));
        textViewReputation.setText(Integer.toString(personage.getReputation()));
        imageViewPersonageIcon.setImageResource(BecomeAKing.getInstance().getCurrentCategories().get(1).getImageId());
        // TODO: Set possessions values

        // Buttons
        Button buttonLevel = view.findViewById(R.id.button_level);
        buttonLevel.setOnClickListener(v -> {
            Fragment fragment = new PersonageCharacteristicsFragment();
            getActivity().getSupportFragmentManager().beginTransaction()
                    .replace(R.id.fragment_frame, fragment)
                    .addToBackStack(null)
                    .commit();
        });

        Button buttonFamily = view.findViewById(R.id.button_family);
        buttonFamily.setOnClickListener(v -> {
            // TODO: Handler
        });

        Button buttonFriends = view.findViewById(R.id.button_friends);
        buttonFriends.setOnClickListener(v -> {
            // TODO: Handler
        });

        Button buttonCaravans = view.findViewById(R.id.button_caravans);
        buttonCaravans.setOnClickListener(v -> {
            // TODO: Handler
        });

        Button buttonSetting = view.findViewById(R.id.button_setting);
        buttonSetting.setOnClickListener(v -> {
            // TODO: Handler
        });

        Button buttonDeveloper = view.findViewById(R.id.button_developer);
        buttonDeveloper.setOnClickListener(v -> {
            // TODO: Handler
        });

        Button buttonExit = view.findViewById(R.id.button_exit);
        buttonExit.setOnClickListener(v -> {
            // TODO: Handler
            getActivity().finish();
        });

        // Ad
        LinearLayout layoutHealthForAd = view.findViewById(R.id.layout_health_for_ad);
        layoutHealthForAd.setOnClickListener(v -> {
            // TODO: Handler
            Toast.makeText(view.getContext(), "Now you should see an ad",
                    Toast.LENGTH_SHORT).show();
        });

        LinearLayout layoutReputationForAd = view.findViewById(R.id.layout_reputation_for_ad);
        layoutReputationForAd.setOnClickListener(v -> {
            // TODO: Handler
            Toast.makeText(view.getContext(), "Now you should see an ad",
                    Toast.LENGTH_SHORT).show();
        });

        LinearLayout layoutMoneyForAd = view.findViewById(R.id.layout_money_for_ad);
        layoutMoneyForAd.setOnClickListener(v -> {
            // TODO: Handler
            Toast.makeText(view.getContext(), "Now you should see an ad",
                    Toast.LENGTH_SHORT).show();
        });
    }
}