package com.scipath.becomeaking.view.fragment;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.scipath.becomeaking.BecomeAKing;
import com.scipath.becomeaking.R;
import com.scipath.becomeaking.model.Level;
import com.scipath.becomeaking.model.Personage;
import com.scipath.becomeaking.model.StatBonus;
import com.scipath.becomeaking.model.StatBonusesMap;


public class PersonageStatsFragment extends Fragment {

    // Models variables
    Personage personage;
    Level level;
    StatBonusesMap statBonuses;


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

        // Getting Personage from Application
        personage = BecomeAKing.getInstance().getPersonage();
        level = personage.getLevel().clone();
        statBonuses = BecomeAKing.getInstance().getCurrentStatBonuses();

        // Views
        ImageView imageViewPersonageIcon = view.findViewById(R.id.image_view_personage_icon);
        TextView textViewAge = view.findViewById(R.id.text_view_age);
        TextView textViewLevel = view.findViewById(R.id.text_view_level);
        TextView textViewExperience = view.findViewById(R.id.text_view_experience);
        TextView textViewSkillPoints = view.findViewById(R.id.text_view_skill_points);

        ImageView imageViewHealthIncome = view.findViewById(R.id.image_view_health_income);
        TextView textViewHealthIncome = view.findViewById(R.id.text_view_health_income);
        ImageView imageViewReputationIncome = view.findViewById(R.id.image_view_reputation_income);
        TextView textViewReputationIncome = view.findViewById(R.id.text_view_reputation_income);
        ImageView imageViewMoneyIncome = view.findViewById(R.id.image_view_money_income);
        TextView textViewMoneyIncome = view.findViewById(R.id.text_view_money_income);

        TextView textViewStrength = view.findViewById(R.id.strength);
        TextView textViewLuck = view.findViewById(R.id.luck);

        // Setting Views values
        imageViewPersonageIcon.setImageResource(
                BecomeAKing.getInstance().getCategories().get(1).getImageId());
        textViewAge.setText(getActivity().getString(
                R.string.age_d,
                personage.getAge()));
        textViewLevel.setText(getActivity().getString(
                R.string.level_d,
                level.getValue()));
        textViewExperience.setText(getActivity().getString(
                R.string.d_d,
                level.getCurrentExperience(),
                level.getNeededExperience()));
        textViewSkillPoints.setText(getActivity().getString(
                R.string.skill_points_d,
                level.getAvailableSkillPoints()));

        // Stat bonuses
        int statBonusValue = statBonuses.get(StatBonus.HealthPerDay);
        imageViewHealthIncome.setBackgroundColor(ContextCompat.getColor(requireContext(),
                statBonusValue < 0 ? R.color.icon_red : R.color.icon_green));
        textViewHealthIncome.setText(Integer.toString(statBonusValue));

        statBonusValue = statBonuses.get(StatBonus.ReputationPerDay);
        imageViewReputationIncome.setBackgroundColor(ContextCompat.getColor(requireContext(),
                statBonusValue < 0 ? R.color.icon_red : R.color.icon_green));
        textViewReputationIncome.setText(Integer.toString(statBonusValue));

        statBonusValue = statBonuses.get(StatBonus.CostPerDay);
        imageViewMoneyIncome.setBackgroundColor(ContextCompat.getColor(requireContext(),
                statBonusValue < 0 ? R.color.icon_red : R.color.icon_green));
        textViewMoneyIncome.setText(Integer.toString(statBonusValue));

        // Strength
        textViewStrength.setText(Integer.toString(level.getStrength()));
        ImageButton imageButtonIncreaseStrength = view.findViewById(R.id.increase_strength);
        imageButtonIncreaseStrength.setOnClickListener(v -> {
            if (level.getAvailableSkillPoints() > 0) {
                level.affectStrength(1);
                textViewStrength.setText(Integer.toString(level.getStrength()));
                textViewSkillPoints.setText(getActivity().getString(
                        R.string.skill_points_d,
                        level.getAvailableSkillPoints()));
            }
        });
        ImageButton imageButtonDecreaseStrength = view.findViewById(R.id.decrease_strength);
        imageButtonDecreaseStrength.setOnClickListener(v -> {
            if (level.getStrength() > personage.getLevel().getStrength()) {
                level.affectStrength(-1);
                textViewStrength.setText(Integer.toString(level.getStrength()));
                textViewSkillPoints.setText(getActivity().getString(
                        R.string.skill_points_d,
                        level.getAvailableSkillPoints()));
            }
        });

        // Luck
        textViewLuck.setText(Integer.toString(level.getLuck()));
        ImageButton imageButtonIncreaseLuck = view.findViewById(R.id.increase_luck);
        imageButtonIncreaseLuck.setOnClickListener(v -> {
            if (level.getAvailableSkillPoints() > 0) {
                level.affectLuck(1);
                textViewLuck.setText(Integer.toString(level.getLuck()));
                textViewSkillPoints.setText(getActivity().getString(
                        R.string.skill_points_d,
                        level.getAvailableSkillPoints()));
            }
        });
        ImageButton imageButtonDecreaseLuck = view.findViewById(R.id.decrease_luck);
        imageButtonDecreaseLuck.setOnClickListener(v -> {
            if (level.getLuck() > personage.getLevel().getLuck()) {
                level.affectLuck(-1);
                textViewLuck.setText(Integer.toString(level.getLuck()));
                textViewSkillPoints.setText(getActivity().getString(
                        R.string.skill_points_d,
                        level.getAvailableSkillPoints()));
            }
        });

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
            personage.setLevel(level);
            Fragment fragment = new PersonageFragment();
            getActivity().getSupportFragmentManager().beginTransaction()
                    .replace(R.id.fragment_frame, fragment)
                    .addToBackStack(null)
                    .commit();
        });

        LinearLayout layoutDropSkillPoints = view.findViewById(R.id.layout_drop_skill_points);
        layoutDropSkillPoints.setOnClickListener(v -> {
            // TODO: Handler
        });
    }
}