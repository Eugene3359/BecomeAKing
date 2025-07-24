package com.scipath.becomeaking.view.activity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageButton;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.scipath.becomeaking.R;
import com.scipath.becomeaking.model.enums.Sex;


public class SexSelectionActivity extends AppCompatActivity {

    // Models variables
    private Sex currentSex;

    // Views variables
    ImageButton buttonMale;
    ImageButton buttonFemale;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_sex_selection);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

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

        Button buttonBack = findViewById(R.id.button_back);
        buttonBack.setOnClickListener(view -> {
            finish();
        });
    }
}