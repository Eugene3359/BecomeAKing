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

import com.scipath.becomeaking.BecomeAKing;
import com.scipath.becomeaking.R;
import com.scipath.becomeaking.contract.model.ICategory;
import com.scipath.becomeaking.contract.model.IStats;
import com.scipath.becomeaking.manager.AdManagerMock;
import com.scipath.becomeaking.model.Personage;
import com.scipath.becomeaking.model.enums.Stat;
import com.scipath.becomeaking.view.activity.GameActivity;

import java.util.ArrayList;


public class PersonageFragment extends BaseFragment {

    // Models
    private Personage personage;
    private ArrayList<ICategory> categories;

    // Views
    private ImageView imageViewHealthIncome;
    private TextView textViewHealthIncome;
    private ImageView imageViewReputationIncome;
    private TextView textViewReputationIncome;
    private ImageView imageViewMoneyIncome;
    private TextView textViewMoneyIncome;
    private TextView textViewReputation;
    private TextView textViewDay;


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

        // Getting personage and categories
        personage = BecomeAKing.getInstance().getPersonage();
        categories = BecomeAKing.getInstance().getCategories();

        // Views
        TextView textViewName = view.findViewById(R.id.text_view_name);
        TextView textViewTitle = view.findViewById(R.id.text_view_title);
        ImageView imageViewPersonageIcon = view.findViewById(R.id.image_view_personage_icon);

        imageViewHealthIncome = view.findViewById(R.id.image_view_health_income);
        textViewHealthIncome = view.findViewById(R.id.text_view_health_income);
        imageViewReputationIncome = view.findViewById(R.id.image_view_reputation_income);
        textViewReputationIncome = view.findViewById(R.id.text_view_reputation_income);
        imageViewMoneyIncome = view.findViewById(R.id.image_view_money_income);
        textViewMoneyIncome = view.findViewById(R.id.text_view_money_income);

        textViewDay = view.findViewById(R.id.text_view_day);
        textViewReputation = view.findViewById(R.id.text_view_reputation2);

        TextView textViewNutrition = view.findViewById(R.id.text_view_nutrition);
        TextView textViewClothes = view.findViewById(R.id.text_view_clothes);
        TextView textViewHousing = view.findViewById(R.id.text_view_housing);
        TextView textViewHorse = view.findViewById(R.id.text_view_horse);
        TextView textViewMaxHealth = view.findViewById(R.id.text_view_max_health);
        TextView textViewMight = view.findViewById(R.id.text_view_might);

        // Setting Views values
        textViewName.setText(personage.getName());
        textViewTitle.setText(personage.getTitle().getNameId());
        imageViewPersonageIcon.setImageResource(categories.get(1).getImageId());

        updateViews();

        textViewNutrition.setText(categories.get(0).getBestItem().getNameId());
        textViewClothes.setText(categories.get(1).getBestItem().getNameId());
        if (categories.get(5).getBestItem() != null) {
            textViewHousing.setText(categories.get(5).getBestItem().getNameId());
        }
        if (categories.get(9).getBestItem() != null) {
            textViewHorse.setText(categories.get(9).getBestItem().getNameId());
        }
        textViewMaxHealth.setText(String.valueOf(personage.getMaxHealth()));
        textViewMight.setText(String.valueOf(personage.getMight()));

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
            showDialogue(R.string.notification, R.string.in_development, R.string.got_it, null);
        });

        Button buttonFriends = view.findViewById(R.id.button_friends);
        buttonFriends.setOnClickListener(v -> {
            // TODO: Handler
            showDialogue(R.string.notification, R.string.in_development, R.string.got_it, null);
        });

        Button buttonCaravans = view.findViewById(R.id.button_caravans);
        buttonCaravans.setOnClickListener(v -> {
            // TODO: Handler
            showDialogue(R.string.notification, R.string.in_development, R.string.got_it, null);
        });

        Button buttonSetting = view.findViewById(R.id.button_setting);
        buttonSetting.setOnClickListener(v -> {
            // TODO: Handler
            showDialogue(R.string.notification, R.string.in_development, R.string.got_it, null);
        });

        Button buttonDeveloper = view.findViewById(R.id.button_developer);
        buttonDeveloper.setOnClickListener(v -> {
            // TODO: Handler
            showDialogue(R.string.notification, R.string.in_development, R.string.got_it, null);
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
        // Daily stats
        IStats stats = BecomeAKing.getInstance().getCurrentStatBonuses();
        int statBonusValue = stats.get(Stat.HealthPerDay);
        imageViewHealthIncome.setBackgroundColor(ContextCompat.getColor(requireContext(),
                statBonusValue < 0 ? R.color.icon_red : R.color.icon_green));
        textViewHealthIncome.setText(String.valueOf(statBonusValue));

        statBonusValue = stats.get(Stat.ReputationPerDay);
        imageViewReputationIncome.setBackgroundColor(ContextCompat.getColor(requireContext(),
                statBonusValue < 0 ? R.color.icon_red : R.color.icon_green));
        textViewReputationIncome.setText(String.valueOf(statBonusValue));

        statBonusValue = stats.get(Stat.CoinsPerDay);
        imageViewMoneyIncome.setBackgroundColor(ContextCompat.getColor(requireContext(),
                statBonusValue < 0 ? R.color.icon_red : R.color.icon_green));
        textViewMoneyIncome.setText(String.valueOf(statBonusValue));

        // Day number and personage reputation
        textViewDay.setText(String.valueOf(BecomeAKing.getInstance().getDay()));
        textViewReputation.setText(String.valueOf(personage.getReputation()));
    }
}