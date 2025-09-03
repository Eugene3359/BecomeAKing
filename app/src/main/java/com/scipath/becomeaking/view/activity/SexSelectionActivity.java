package com.scipath.becomeaking.view.activity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageButton;

import com.scipath.becomeaking.R;
import com.scipath.becomeaking.model.enums.Sex;


public class SexSelectionActivity extends BaseActivity {

    // Models
    private Sex currentSex;

    // Views
    ImageButton buttonMale;
    ImageButton buttonFemale;


    @Override
    protected int getLayoutId() {
        return R.layout.activity_sex_selection;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Variables initialization
        currentSex = Sex.Male;

        // Button male
        buttonMale = findViewById(R.id.button_male);
        buttonMale.setOnClickListener(view -> {
            buttonMale.setBackgroundColor(getColor(R.color.transparent_white));
            buttonFemale.setBackgroundColor(0x00000000);
            currentSex = Sex.Male;
        });

        // Button female
        buttonFemale = findViewById(R.id.button_female);
        buttonFemale.setOnClickListener(view -> {
            buttonMale.setBackgroundColor(0x00000000);
            buttonFemale.setBackgroundColor(getColor(R.color.transparent_white));
            currentSex = Sex.Female;
        });

        // Button continue
        Button buttonContinue = findViewById(R.id.button_continue);
        buttonContinue.setOnClickListener(view -> {
            Intent intent = new Intent(SexSelectionActivity.this, TitleSelectionActivity.class);
            intent.putExtra("sex", currentSex);
            startActivity(intent);
        });

        // Button back
        Button buttonBack = findViewById(R.id.button_back);
        buttonBack.setOnClickListener(view -> {
            finish();
        });
    }
}