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
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.scipath.becomeaking.BecomeAKing;
import com.scipath.becomeaking.R;
import com.scipath.becomeaking.manager.AdManagerMock;
import com.scipath.becomeaking.model.Category;
import com.scipath.becomeaking.model.GameState;
import com.scipath.becomeaking.model.Personage;
import com.scipath.becomeaking.model.StatBonus;
import com.scipath.becomeaking.model.StatBonusesMap;
import com.scipath.becomeaking.view.activity.GameActivity;

import java.util.ArrayList;


public class PersonageFragment extends Fragment {

    // Models variables
    Personage personage;
    ArrayList<Category> categories;
    StatBonusesMap statBonuses;

    // Views variables
    TextView textViewReputation;
    TextView textViewDay;


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

        // Getting Personage, Categories and StatBonuses from Application
        personage = BecomeAKing.getInstance().getPersonage();
        categories = BecomeAKing.getInstance().getCategories();
        statBonuses = BecomeAKing.getInstance().getCurrentStatBonuses();

        // Views
        TextView textViewName = view.findViewById(R.id.text_view_name);
        TextView textViewTitle = view.findViewById(R.id.text_view_title);
        ImageView imageViewPersonageIcon = view.findViewById(R.id.image_view_personage_icon);

        textViewDay = view.findViewById(R.id.text_view_day);
        textViewReputation = view.findViewById(R.id.text_view_reputation2);

        ImageView imageViewHealthIncome = view.findViewById(R.id.image_view_health_income);
        TextView textViewHealthIncome = view.findViewById(R.id.text_view_health_income);
        ImageView imageViewReputationIncome = view.findViewById(R.id.image_view_reputation_income);
        TextView textViewReputationIncome = view.findViewById(R.id.text_view_reputation_income);
        ImageView imageViewMoneyIncome = view.findViewById(R.id.image_view_money_income);
        TextView textViewMoneyIncome = view.findViewById(R.id.text_view_money_income);

        TextView textViewNutrition = view.findViewById(R.id.text_view_nutrition);
        TextView textViewClothes = view.findViewById(R.id.text_view_clothes);
        TextView textViewHousing = view.findViewById(R.id.text_view_housing);
        TextView textViewHorse = view.findViewById(R.id.text_view_horse);
        TextView textViewMaxHealth = view.findViewById(R.id.text_view_max_health);
        TextView textViewMight = view.findViewById(R.id.text_view_might);

        // Setting Views values
        textViewName.setText(personage.getName());
        textViewTitle.setText(personage.getTitle().getName(getActivity()));
        imageViewPersonageIcon.setImageResource(BecomeAKing.getInstance().getCategories().get(1).getImageId());

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

        updateViews();

        textViewNutrition.setText(categories.get(0).getLastBoughtItem().getNameId());
        textViewClothes.setText(categories.get(1).getLastBoughtItem().getNameId());
        if (categories.get(5).getLastBoughtItem() != null) {
            textViewHousing.setText(categories.get(5).getLastBoughtItem().getNameId());
        }
        if (categories.get(9).getLastBoughtItem() != null) {
            textViewHorse.setText(categories.get(9).getLastBoughtItem().getNameId());
        }
        textViewMaxHealth.setText(Integer.toString(personage.getMaxHealth()));
        textViewMight.setText(Integer.toString(personage.getMight()));

        // Buttons
        Button buttonLevel = view.findViewById(R.id.button_level);
        buttonLevel.setOnClickListener(v -> {
            Fragment fragment = new PersonageStatsFragment();
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
            getActivity().finish();
        });

        // Ad
        LinearLayout layoutHealthForAd = view.findViewById(R.id.layout_health_for_ad);
        layoutHealthForAd.setOnClickListener(v -> {
            personage.affectHealth(300);
            ((GameActivity)getActivity()).updateViews();
            AdManagerMock.showAd((AppCompatActivity) getActivity());
        });

        LinearLayout layoutReputationForAd = view.findViewById(R.id.layout_reputation_for_ad);
        layoutReputationForAd.setOnClickListener(v -> {
            personage.affectReputation(500);
            ((GameActivity)getActivity()).updateViews();
            textViewReputation.setText(Integer.toString(personage.getReputation()));
            AdManagerMock.showAd((AppCompatActivity) getActivity());
        });

        LinearLayout layoutMoneyForAd = view.findViewById(R.id.layout_money_for_ad);
        layoutMoneyForAd.setOnClickListener(v -> {
            personage.affectMoney(1000);
            ((GameActivity)getActivity()).updateViews();
            AdManagerMock.showAd((AppCompatActivity) getActivity());
        });
    }

    public void updateViews() {
        textViewDay.setText(Integer.toString(BecomeAKing.getInstance().getDay()));
        textViewReputation.setText(Integer.toString(personage.getReputation()));
    }
}