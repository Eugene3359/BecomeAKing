package com.scipath.becomeaking.view.fragment;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.scipath.becomeaking.R;
import com.scipath.becomeaking.view.layout.CurrentCityLayout;
import com.scipath.becomeaking.view.layout.FinanceMenuElementLayout;


public class FinanceFragment extends BaseFragment {

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