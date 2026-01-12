package com.scipath.becomeaking.view.fragment;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.view.View;
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


public class PersonageStatsFragment extends BaseFragment {

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_personage_stats;
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

        TextView textAge = view.findViewById(R.id.text_age);
        TextView textLevel = view.findViewById(R.id.text_level);
        TextView textExperience = view.findViewById(R.id.text_experience);
        TextView textSkillPoints = view.findViewById(R.id.text_skill_points);

        // Setting Views values
        textAge.setText(requireActivity().getString(
                R.string.age_d,
                personage.getAge()));
        textLevel.setText(requireActivity().getString(
                R.string.level_d,
                level.getValue()));
        textExperience.setText(requireActivity().getString(
                R.string.d_d,
                level.getCurrentExperience(),
                level.getNeededExperience()));
        textSkillPoints.setText(requireActivity().getString(
                R.string.skill_points_d,
                level.getAvailableSkillPoints()));

        // Strength
        TextView textStrength = view.findViewById(R.id.strength);
        textStrength.setText(String.valueOf(level.getStrength()));
        ImageButton imageButtonIncreaseStrength = view.findViewById(R.id.increase_strength);
        imageButtonIncreaseStrength.setOnClickListener(v -> {
            if (level.getAvailableSkillPoints() > 0) {
                level.affectStrength(1);
                textStrength.setText(String.valueOf(level.getStrength()));
                textSkillPoints.setText(requireActivity().getString(
                        R.string.skill_points_d,
                        level.getAvailableSkillPoints()));
            }
        });
        ImageButton imageButtonDecreaseStrength = view.findViewById(R.id.decrease_strength);
        imageButtonDecreaseStrength.setOnClickListener(v -> {
            if (level.getStrength() > personage.getLevel().getStrength()) {
                level.affectStrength(-1);
                textStrength.setText(String.valueOf(level.getStrength()));
                textSkillPoints.setText(requireActivity().getString(
                        R.string.skill_points_d,
                        level.getAvailableSkillPoints()));
            }
        });

        // Luck
        TextView textLuck = view.findViewById(R.id.luck);
        textLuck.setText(String.valueOf(level.getLuck()));
        ImageButton imageButtonIncreaseLuck = view.findViewById(R.id.increase_luck);
        imageButtonIncreaseLuck.setOnClickListener(v -> {
            if (level.getAvailableSkillPoints() > 0) {
                level.affectLuck(1);
                textLuck.setText(String.valueOf(level.getLuck()));
                textSkillPoints.setText(requireActivity().getString(
                        R.string.skill_points_d,
                        level.getAvailableSkillPoints()));
            }
        });
        ImageButton imageButtonDecreaseLuck = view.findViewById(R.id.decrease_luck);
        imageButtonDecreaseLuck.setOnClickListener(v -> {
            if (level.getLuck() > personage.getLevel().getLuck()) {
                level.affectLuck(-1);
                textLuck.setText(String.valueOf(level.getLuck()));
                textSkillPoints.setText(requireActivity().getString(
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
            textStrength.setText(String.valueOf(level.getStrength()));
            textLuck.setText(String.valueOf(level.getLuck()));
            textSkillPoints.setText(requireActivity().getString(
                    R.string.skill_points_d,
                    level.getAvailableSkillPoints()));
            AdManagerMock.showAd((AppCompatActivity) requireActivity());
        });
    }
}