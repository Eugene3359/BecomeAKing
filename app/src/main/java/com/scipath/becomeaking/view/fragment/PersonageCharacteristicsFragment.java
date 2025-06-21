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

import com.scipath.becomeaking.BecomeAKing;
import com.scipath.becomeaking.R;
import com.scipath.becomeaking.model.Personage;


public class PersonageCharacteristicsFragment extends Fragment {

    public static PersonageCharacteristicsFragment newInstance() {
        return new PersonageCharacteristicsFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_personage_characteristics, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        // Getting Personage from Application
        Personage personage = BecomeAKing.getInstance().getPersonage();

        // Views
        TextView textViewAge = view.findViewById(R.id.text_view_age);
        TextView textViewLevel = view.findViewById(R.id.text_view_level);
        TextView textViewExperience = view.findViewById(R.id.text_view_experience);
        TextView textViewSkillPoints = view.findViewById(R.id.text_view_skill_points);
        ImageView imageViewPersonageIcon = view.findViewById(R.id.image_view_personage_icon);

        // Setting Views values
        textViewAge.setText(getActivity().getString(R.string.age_d, personage.getAge()));
        textViewLevel.setText(getActivity().getString(
                R.string.level_d,
                personage.getLevel().getValue()));
        textViewExperience.setText(getActivity().getString(
                R.string.d_d,
                personage.getLevel().getCurrentExperience(),
                personage.getLevel().getNeededExperience()));
        imageViewPersonageIcon.setImageResource(
                BecomeAKing.getInstance().getCategories().get(1).getImageId());

        // Buttons
        Button buttonBack = view.findViewById(R.id.button_back);
        buttonBack.setOnClickListener(v -> {
            Fragment fragment = new PersonageFragment();
            getActivity().getSupportFragmentManager().beginTransaction()
                    .replace(R.id.fragment_frame, fragment)
                    .addToBackStack(null)
                    .commit();
        });

        Button buttonSave = view.findViewById(R.id.button_save);
        buttonSave.setOnClickListener(v -> {
            // TODO: Handler
        });

        LinearLayout layoutDropSkillPoints = view.findViewById(R.id.layout_drop_skill_points);
        layoutDropSkillPoints.setOnClickListener(v -> {
            // TODO: Handler
        });
    }
}