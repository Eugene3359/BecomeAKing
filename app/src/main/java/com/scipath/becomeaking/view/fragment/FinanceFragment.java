package com.scipath.becomeaking.view.fragment;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import android.view.View;

import com.scipath.becomeaking.R;
import com.scipath.becomeaking.view.layout.CurrentCityLayout;
import com.scipath.becomeaking.view.layout.FinanceMenuElementLayout;


public class FinanceFragment extends BaseFragment {

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_finance;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        CurrentCityLayout currentCityLayout = view.findViewById(R.id.layout_current_city);
        currentCityLayout.bind(getViewLifecycleOwner());

        FinanceMenuElementLayout elementTrading = view.findViewById(R.id.element_trading);
        elementTrading.setButtonOnClickListener(v -> {
            requireActivity().getSupportFragmentManager().beginTransaction()
                    .replace(R.id.fragment_frame, new MarketFragment())
                    .addToBackStack(null)
                    .commit();
        });

        FinanceMenuElementLayout elementCaravans = view.findViewById(R.id.element_caravans);
        elementCaravans.setButtonOnClickListener(v -> {
            showInDevelopmentNotification();
        });

        FinanceMenuElementLayout elementBank = view.findViewById(R.id.element_bank);
        elementBank.setButtonOnClickListener(v -> {
            requireActivity().getSupportFragmentManager().beginTransaction()
                    .replace(R.id.fragment_frame, new BankFragment())
                    .addToBackStack(null)
                    .commit();
        });

        FinanceMenuElementLayout elementWorkshops = view.findViewById(R.id.element_workshops);
        elementWorkshops.setButtonOnClickListener(v -> {
            showInDevelopmentNotification();
        });

        FinanceMenuElementLayout elementGossips = view.findViewById(R.id.element_gossips);
        elementGossips.setButtonOnClickListener(v -> {
            showInDevelopmentNotification();
        });
    }
}