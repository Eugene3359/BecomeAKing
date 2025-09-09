package com.scipath.becomeaking.view.activity;

import android.os.Bundle;
import android.widget.Button;

import com.scipath.becomeaking.R;

public class MapActivity extends BaseActivity {

    @Override
    protected int getLayoutId() {
        return R.layout.activity_map;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        showDialogue(
                R.string.notification,
                R.string.in_development,
                R.string.got_it,
                null
        );

        // Buttons
        Button buttonBack = findViewById(R.id.button_back);
        buttonBack.setOnClickListener(view -> {
            finish();
        });
    }
}