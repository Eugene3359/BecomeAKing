package com.scipath.becomeaking.view.activity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import com.scipath.becomeaking.BecomeAKing;
import com.scipath.becomeaking.R;
import com.scipath.becomeaking.contract.model.ICategory;
import com.scipath.becomeaking.data.CategoriesList;
import com.scipath.becomeaking.model.GameState;
import com.scipath.becomeaking.model.Personage;
import com.scipath.becomeaking.model.enums.Sex;
import com.scipath.becomeaking.model.enums.Title;

import java.util.ArrayList;


public class NamingPersonageActivity extends BaseActivity {

    @Override
    protected int getLayoutId() {
        return R.layout.activity_naming_personage;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Views initialization
        EditText editTextNameInput = findViewById(R.id.edit_text_name_input);

        // Button start
        Button buttonStart = findViewById(R.id.button_start);
        buttonStart.setOnClickListener(view -> {
            String personageName = editTextNameInput.getText().toString();

            if (!personageName.isEmpty()) {
                Intent thisIntent = getIntent();
                Sex sex = (Sex)thisIntent.getSerializableExtra("sex");
                Title title = (Title)thisIntent.getSerializableExtra("title");
                Personage personage = new Personage(personageName, sex, title);
                ArrayList<ICategory> categories = CategoriesList.getCategories(sex == Sex.Male);
                BecomeAKing.getInstance().setGameState(new GameState(personage, categories, 0));

                Intent intent = new Intent(NamingPersonageActivity.this, GameActivity.class);
                startActivity(intent);
            } else {
                showDialogue(R.string.notification, R.string.enter_a_name, R.string.got_it, null);
            }
        });

        // Button back
        Button buttonBack = findViewById(R.id.button_back);
        buttonBack.setOnClickListener(view -> {
            finish();
        });
    }
}