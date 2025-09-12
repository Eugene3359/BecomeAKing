package com.scipath.becomeaking.view.fragment;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.scipath.becomeaking.R;
import com.scipath.becomeaking.view.activity.MapActivity;


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

        // Buttons
        Button buttonCity = view.findViewById(R.id.button_city);
        buttonCity.setOnClickListener(v -> {
            showDialogue(
                    R.string.drakkenburg,
                    R.string.placeholder,
                    R.string.got_it,
                    null
            );
        });

        Button buttonMap = view.findViewById(R.id.button_map);
        buttonMap.setOnClickListener(v -> {
            Intent intent = new Intent(requireContext(), MapActivity.class);
            startActivity(intent);
        });
    }
}