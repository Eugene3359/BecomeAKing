package com.scipath.becomeaking.view.fragment;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelStoreOwner;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;

import com.scipath.becomeaking.BecomeAKing;
import com.scipath.becomeaking.R;
import com.scipath.becomeaking.view.activity.MapActivity;
import com.scipath.becomeaking.view.layout.CurrentCityLayout;
import com.scipath.becomeaking.view.layout.FinanceMenuElementLayout;
import com.scipath.becomeaking.viewmodel.CurrentCityViewModel;


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

        CurrentCityLayout currentCityLayout = view.findViewById(R.id.layout_current_city);
        CurrentCityViewModel currentCityViewModel =
                new ViewModelProvider(
                        BecomeAKing.getInstance(),
                        ViewModelProvider.AndroidViewModelFactory.getInstance(BecomeAKing.getInstance())
                ).get(CurrentCityViewModel.class);
        currentCityViewModel.getCity().observe(getViewLifecycleOwner(), city -> {
            currentCityLayout.setImageResource(city.getImageId());
            currentCityLayout.setText(city.getNameId(), requireContext());

            if (BecomeAKing.getInstance().isTraveling()) {
                currentCityLayout.setButtonCityOnClickListener(null);
            } else {
                DialogueFragment dialogue = new DialogueFragment.Builder()
                        .setHeader(city.getNameId())
                        .setMessage(city.getDescriptionId())
                        .setButton1(R.string.got_it, null).build();
                currentCityLayout.setButtonCityOnClickListener(v -> {
                    showDialogue(dialogue);
                });
            }
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