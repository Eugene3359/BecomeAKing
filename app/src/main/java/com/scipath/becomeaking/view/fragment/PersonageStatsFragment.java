package com.scipath.becomeaking.view.fragment;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.scipath.becomeaking.BecomeAKing;
import com.scipath.becomeaking.R;
import com.scipath.becomeaking.manager.AdManagerMock;
import com.scipath.becomeaking.contract.model.ILevel;
import com.scipath.becomeaking.model.Personage;
import com.scipath.becomeaking.view.layout.PersonageLayout;


public class PersonageStatsFragment extends Fragment {

    public static PersonageStatsFragment newInstance() {
        return new PersonageStatsFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_personage_stats, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        // Getting personage
        Personage personage = BecomeAKing.getInstance().getPersonage();
        ILevel level = personage.getLevel().clone();

        // Views
        PersonageLayout personageLayout = view.findViewById(R.id.layout_personage);
        personageLayout.updateAll();

        TextView textViewAge = view.findViewById(R.id.text_view_age);
        TextView textViewLevel = view.findViewById(R.id.text_view_level);
        TextView textViewExperience = view.findViewById(R.id.text_view_experience);
        TextView textViewSkillPoints = view.findViewById(R.id.text_view_skill_points);

        // Setting Views values
        textViewAge.setText(requireActivity().getString(
                R.string.age_d,
                personage.getAge()));
        textViewLevel.setText(requireActivity().getString(
                R.string.level_d,
                level.getValue()));
        textViewExperience.setText(requireActivity().getString(
                R.string.d_d,
                level.getCurrentExperience(),
                level.getNeededExperience()));
        textViewSkillPoints.setText(requireActivity().getString(
                R.string.skill_points_d,
                level.getAvailableSkillPoints()));

        // Strength
        TextView textViewStrength = view.findViewById(R.id.strength);
        textViewStrength.setText(String.valueOf(level.getStrength()));
        ImageButton imageButtonIncreaseStrength = view.findViewById(R.id.increase_strength);
        imageButtonIncreaseStrength.setOnClickListener(v -> {
            if (level.getAvailableSkillPoints() > 0) {
                level.affectStrength(1);
                textViewStrength.setText(String.valueOf(level.getStrength()));
                textViewSkillPoints.setText(requireActivity().getString(
                        R.string.skill_points_d,
                        level.getAvailableSkillPoints()));
            }
        });
        ImageButton imageButtonDecreaseStrength = view.findViewById(R.id.decrease_strength);
        imageButtonDecreaseStrength.setOnClickListener(v -> {
            if (level.getStrength() > personage.getLevel().getStrength()) {
                level.affectStrength(-1);
                textViewStrength.setText(String.valueOf(level.getStrength()));
                textViewSkillPoints.setText(requireActivity().getString(
                        R.string.skill_points_d,
                        level.getAvailableSkillPoints()));
            }
        });

        // Luck
        TextView textViewLuck = view.findViewById(R.id.luck);
        textViewLuck.setText(String.valueOf(level.getLuck()));
        ImageButton imageButtonIncreaseLuck = view.findViewById(R.id.increase_luck);
        imageButtonIncreaseLuck.setOnClickListener(v -> {
            if (level.getAvailableSkillPoints() > 0) {
                level.affectLuck(1);
                textViewLuck.setText(String.valueOf(level.getLuck()));
                textViewSkillPoints.setText(requireActivity().getString(
                        R.string.skill_points_d,
                        level.getAvailableSkillPoints()));
            }
        });
        ImageButton imageButtonDecreaseLuck = view.findViewById(R.id.decrease_luck);
        imageButtonDecreaseLuck.setOnClickListener(v -> {
            if (level.getLuck() > personage.getLevel().getLuck()) {
                level.affectLuck(-1);
                textViewLuck.setText(String.valueOf(level.getLuck()));
                textViewSkillPoints.setText(requireActivity().getString(
                        R.string.skill_points_d,
                        level.getAvailableSkillPoints()));
            }
        });

        // Buttons
        Button buttonBack = view.findViewById(R.id.button_back);
        buttonBack.setOnClickListener(v -> {
            Fragment fragment = new PersonageFragment();
            requireActivity().getSupportFragmentManager().beginTransaction()
                    .replace(R.id.fragment_frame, fragment)
                    .addToBackStack(null)
                    .commit();
        });

        Button buttonSave = view.findViewById(R.id.button_save);
        buttonSave.setOnClickListener(v -> {
            personage.setLevel(level);
            Fragment fragment = new PersonageFragment();
            requireActivity().getSupportFragmentManager().beginTransaction()
                    .replace(R.id.fragment_frame, fragment)
                    .addToBackStack(null)
                    .commit();
        });

        LinearLayout layoutDropSkillPoints = view.findViewById(R.id.layout_drop_skill_points);
        layoutDropSkillPoints.setOnClickListener(v -> {
            level.dropSkillPoints();
            textViewStrength.setText(String.valueOf(level.getStrength()));
            textViewLuck.setText(String.valueOf(level.getLuck()));
            textViewSkillPoints.setText(requireActivity().getString(
                    R.string.skill_points_d,
                    level.getAvailableSkillPoints()));
            AdManagerMock.showAd((AppCompatActivity) requireActivity());
        });
    }
}