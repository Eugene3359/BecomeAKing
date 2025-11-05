package com.scipath.becomeaking.view.fragment;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;

import com.scipath.becomeaking.BecomeAKing;
import com.scipath.becomeaking.R;
import com.scipath.becomeaking.contract.model.ICity;
import com.scipath.becomeaking.data.CitiesList;
import com.scipath.becomeaking.view.activity.MapActivity;
import com.scipath.becomeaking.view.layout.CurrentCityLayout;
import com.scipath.becomeaking.view.layout.FinanceMenuElementLayout;


public class FinanceFragment extends BaseFragment {

    public static FinanceFragment newInstance() {
        return new FinanceFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_finance, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        ICity city = BecomeAKing.getInstance().getCity();

        CurrentCityLayout currentCityLayout = view.findViewById(R.id.layout_current_city);
        ImageView imageViewCity = currentCityLayout.findViewById(R.id.image_view);
        // TODO: change image src
        imageViewCity.setContentDescription(getContext().getString(city.getNameId()));

        // Buttons
        Button buttonCity = currentCityLayout.findViewById(R.id.button_city);
        buttonCity.setText(city.getNameId());
        buttonCity.setOnClickListener(v -> {
            showInDevelopmentNotification();
        });

        Button buttonMap = view.findViewById(R.id.button_map);
        buttonMap.setOnClickListener(v -> {
            Intent intent = new Intent(requireContext(), MapActivity.class);
            startActivity(intent);
        });

        FinanceMenuElementLayout elementTrading = view.findViewById(R.id.element_trading);
        elementTrading.setButtonOnClickListener(v -> {
            showInDevelopmentNotification();
        });

        FinanceMenuElementLayout elementCaravans = view.findViewById(R.id.element_caravans);
        elementCaravans.setButtonOnClickListener(v -> {
            showInDevelopmentNotification();
        });

        FinanceMenuElementLayout elementGossips = view.findViewById(R.id.element_gossips);
        elementGossips.setButtonOnClickListener(v -> {
            showInDevelopmentNotification();
        });

        FinanceMenuElementLayout elementBank = view.findViewById(R.id.element_bank);
        elementBank.setButtonOnClickListener(v -> {
            showInDevelopmentNotification();
        });

        FinanceMenuElementLayout elementWorkshops = view.findViewById(R.id.element_workshops);
        elementWorkshops.setButtonOnClickListener(v -> {
            showInDevelopmentNotification();
        });
    }
}