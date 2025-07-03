package com.scipath.becomeaking.view.activity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.scipath.becomeaking.BecomeAKing;
import com.scipath.becomeaking.R;
import com.scipath.becomeaking.data.CategoriesList;
import com.scipath.becomeaking.model.GameState;
import com.scipath.becomeaking.model.Category;
import com.scipath.becomeaking.model.Personage;
import com.scipath.becomeaking.model.Sex;
import com.scipath.becomeaking.model.Title;

import java.util.ArrayList;


public class NamingPersonageActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_naming_personage);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

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
                ArrayList<Category> categories = CategoriesList.getCategories(sex == Sex.Male);
                categories.get(1).recalculateStats(); // To set cloth image
                BecomeAKing.getInstance().setGameState(new GameState(0, personage, categories));

                Intent intent = new Intent(NamingPersonageActivity.this, GameActivity.class);
                startActivity(intent);
            } else {
                Toast.makeText(NamingPersonageActivity.this, getString(R.string.enter_name),
                        Toast.LENGTH_SHORT).show();
            }
        });

        // Button back
        Button buttonBack = findViewById(R.id.button_back);
        buttonBack.setOnClickListener(view -> {
            finish();
        });
    }
}