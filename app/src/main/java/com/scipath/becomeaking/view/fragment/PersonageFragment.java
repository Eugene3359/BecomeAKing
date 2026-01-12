package com.scipath.becomeaking.view.fragment;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.scipath.becomeaking.BecomeAKing;
import com.scipath.becomeaking.R;
import com.scipath.becomeaking.contract.model.ICategory;
import com.scipath.becomeaking.manager.AdManagerMock;
import com.scipath.becomeaking.model.Personage;
import com.scipath.becomeaking.view.activity.GameActivity;
import com.scipath.becomeaking.view.layout.PersonageLayout;

import java.util.ArrayList;


public class PersonageFragment extends BaseFragment {

    // Models
    private Personage personage;

    // Views
    private PersonageLayout personageLayout;
    private TextView textReputation;
    private TextView textDay;


    @Override
    protected int getLayoutId() {
        return R.layout.fragment_personage;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        // Getting personage and categories
        personage = BecomeAKing.getInstance().getPersonage();
        ArrayList<ICategory> categories = BecomeAKing.getInstance().getCategories();

        // Views
        personageLayout = view.findViewById(R.id.layout_personage);
        TextView textName = view.findViewById(R.id.text_name);
        TextView textTitle = view.findViewById(R.id.text_title);
        textDay = view.findViewById(R.id.text_day);
        textReputation = view.findViewById(R.id.text_reputation2);

        TextView textNutrition = view.findViewById(R.id.text_nutrition);
        TextView textClothes = view.findViewById(R.id.text_clothes);
        TextView textHousing = view.findViewById(R.id.text_housing);
        TextView textHorse = view.findViewById(R.id.text_horse);
        TextView textMaxHealth = view.findViewById(R.id.text_max_health);
        TextView textMight = view.findViewById(R.id.text_might);

        // Setting Views values
        personageLayout.updateImage();
        textName.setText(personage.getName());
        textTitle.setText(personage.getTitle().nameId);
        updateViews();

        if (categories.get(0).getBestItem() != null) {
            textNutrition.setText(categories.get(0).getBestItem().getNameId());
        }
        if (categories.get(1).getBestItem() != null) {
            textClothes.setText(categories.get(1).getBestItem().getNameId());
        }
        if (categories.get(5).getBestItem() != null) {
            textHousing.setText(categories.get(5).getBestItem().getNameId());
        }
        if (categories.get(9).getBestItem() != null) {
            textHorse.setText(categories.get(9).getBestItem().getNameId());
        }
        textMaxHealth.setText(String.valueOf(personage.getMaxHealth()));
        textMight.setText(String.valueOf(personage.getMight()));

        // Buttons
        Button buttonLevel = view.findViewById(R.id.button_level);
        buttonLevel.setOnClickListener(v -> {
            Fragment fragment = new PersonageStatsFragment();
            requireActivity().getSupportFragmentManager().beginTransaction()
                    .replace(R.id.fragment_frame, fragment)
                    .addToBackStack(null)
                    .commit();
        });

        Button buttonFamily = view.findViewById(R.id.button_family);
        buttonFamily.setOnClickListener(v -> {
            // TODO: Handler
            showInDevelopmentNotification();
        });

        Button buttonFriends = view.findViewById(R.id.button_friends);
        buttonFriends.setOnClickListener(v -> {
            // TODO: Handler
            showInDevelopmentNotification();
        });

        Button buttonCaravans = view.findViewById(R.id.button_caravans);
        buttonCaravans.setOnClickListener(v -> {
            // TODO: Handler
            showInDevelopmentNotification();
        });

        Button buttonSetting = view.findViewById(R.id.button_setting);
        buttonSetting.setOnClickListener(v -> {
            // TODO: Handler
            showInDevelopmentNotification();
        });

        Button buttonDeveloper = view.findViewById(R.id.button_developer);
        buttonDeveloper.setOnClickListener(v -> {
            // TODO: Handler
            showInDevelopmentNotification();
        });

        Button buttonExit = view.findViewById(R.id.button_exit);
        buttonExit.setOnClickListener(v -> {
            requireActivity().finish();
        });

        // Ad
        LinearLayout layoutHealthForAd = view.findViewById(R.id.layout_health_for_ad);
        layoutHealthForAd.setOnClickListener(v -> {
            personage.affectHealth(300);
            ((GameActivity)requireActivity()).updateHealth();
            AdManagerMock.showAd((AppCompatActivity) requireActivity());
        });

        LinearLayout layoutReputationForAd = view.findViewById(R.id.layout_reputation_for_ad);
        layoutReputationForAd.setOnClickListener(v -> {
            personage.affectReputation(500);
            ((GameActivity)requireActivity()).updateReputation();
            textReputation.setText(String.valueOf(personage.getReputation()));
            AdManagerMock.showAd((AppCompatActivity) requireActivity());
        });

        LinearLayout layoutMoneyForAd = view.findViewById(R.id.layout_money_for_ad);
        layoutMoneyForAd.setOnClickListener(v -> {
            personage.affectMoney(1000);
            ((GameActivity)requireActivity()).updateMoney();
            AdManagerMock.showAd((AppCompatActivity) requireActivity());
        });
    }

    public void updateViews() {
        // Daily stats
        personageLayout.updateStats();

        // Day number and personage reputation
        textDay.setText(String.valueOf(BecomeAKing.getInstance().getDay()));
        textReputation.setText(String.valueOf(personage.getReputation()));
    }
}