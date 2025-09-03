package com.scipath.becomeaking.view.fragment;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
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
import com.scipath.becomeaking.contract.model.IStats;
import com.scipath.becomeaking.manager.AdManagerMock;
import com.scipath.becomeaking.contract.model.ILevel;
import com.scipath.becomeaking.model.Personage;
import com.scipath.becomeaking.model.enums.Stat;


public class PersonageStatsFragment extends Fragment {

    // Models variables
    private Personage personage;
    private ILevel ILevel;
    private IStats statBonuses;


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
        personage = BecomeAKing.getInstance().getPersonage();
        ILevel = personage.getLevel().clone();
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
                ILevel.getValue()));
        textViewExperience.setText(getActivity().getString(
                R.string.d_d,
                ILevel.getCurrentExperience(),
                ILevel.getNeededExperience()));
        textViewSkillPoints.setText(getActivity().getString(
                R.string.skill_points_d,
                ILevel.getAvailableSkillPoints()));

        // Stat bonuses
        int statBonusValue = statBonuses.get(Stat.HealthPerDay);
        imageViewHealthIncome.setBackgroundColor(ContextCompat.getColor(requireContext(),
                statBonusValue < 0 ? R.color.icon_red : R.color.icon_green));
        textViewHealthIncome.setText(Integer.toString(statBonusValue));

        statBonusValue = statBonuses.get(Stat.ReputationPerDay);
        imageViewReputationIncome.setBackgroundColor(ContextCompat.getColor(requireContext(),
                statBonusValue < 0 ? R.color.icon_red : R.color.icon_green));
        textViewReputationIncome.setText(Integer.toString(statBonusValue));

        statBonusValue = statBonuses.get(Stat.CoinsPerDay);
        imageViewMoneyIncome.setBackgroundColor(ContextCompat.getColor(requireContext(),
                statBonusValue < 0 ? R.color.icon_red : R.color.icon_green));
        textViewMoneyIncome.setText(Integer.toString(statBonusValue));

        // Strength
        textViewStrength.setText(Integer.toString(ILevel.getStrength()));
        ImageButton imageButtonIncreaseStrength = view.findViewById(R.id.increase_strength);
        imageButtonIncreaseStrength.setOnClickListener(v -> {
            if (ILevel.getAvailableSkillPoints() > 0) {
                ILevel.affectStrength(1);
                textViewStrength.setText(Integer.toString(ILevel.getStrength()));
                textViewSkillPoints.setText(getActivity().getString(
                        R.string.skill_points_d,
                        ILevel.getAvailableSkillPoints()));
            }
        });
        ImageButton imageButtonDecreaseStrength = view.findViewById(R.id.decrease_strength);
        imageButtonDecreaseStrength.setOnClickListener(v -> {
            if (ILevel.getStrength() > personage.getLevel().getStrength()) {
                ILevel.affectStrength(-1);
                textViewStrength.setText(Integer.toString(ILevel.getStrength()));
                textViewSkillPoints.setText(getActivity().getString(
                        R.string.skill_points_d,
                        ILevel.getAvailableSkillPoints()));
            }
        });

        // Luck
        textViewLuck.setText(Integer.toString(ILevel.getLuck()));
        ImageButton imageButtonIncreaseLuck = view.findViewById(R.id.increase_luck);
        imageButtonIncreaseLuck.setOnClickListener(v -> {
            if (ILevel.getAvailableSkillPoints() > 0) {
                ILevel.affectLuck(1);
                textViewLuck.setText(Integer.toString(ILevel.getLuck()));
                textViewSkillPoints.setText(getActivity().getString(
                        R.string.skill_points_d,
                        ILevel.getAvailableSkillPoints()));
            }
        });
        ImageButton imageButtonDecreaseLuck = view.findViewById(R.id.decrease_luck);
        imageButtonDecreaseLuck.setOnClickListener(v -> {
            if (ILevel.getLuck() > personage.getLevel().getLuck()) {
                ILevel.affectLuck(-1);
                textViewLuck.setText(Integer.toString(ILevel.getLuck()));
                textViewSkillPoints.setText(getActivity().getString(
                        R.string.skill_points_d,
                        ILevel.getAvailableSkillPoints()));
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
            personage.setLevel(ILevel);
            Fragment fragment = new PersonageFragment();
            getActivity().getSupportFragmentManager().beginTransaction()
                    .replace(R.id.fragment_frame, fragment)
                    .addToBackStack(null)
                    .commit();
        });

        LinearLayout layoutDropSkillPoints = view.findViewById(R.id.layout_drop_skill_points);
        layoutDropSkillPoints.setOnClickListener(v -> {
            ILevel.dropSkillPoints();
            textViewStrength.setText(Integer.toString(ILevel.getStrength()));
            textViewLuck.setText(Integer.toString(ILevel.getLuck()));
            textViewSkillPoints.setText(getActivity().getString(
                    R.string.skill_points_d,
                    ILevel.getAvailableSkillPoints()));
            AdManagerMock.showAd((AppCompatActivity) getActivity());
        });
    }
}