package com.scipath.becomeaking.view.activity;

import android.content.Intent;
import android.graphics.ColorMatrix;
import android.graphics.ColorMatrixColorFilter;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.scipath.becomeaking.BecomeAKing;
import com.scipath.becomeaking.R;
import com.scipath.becomeaking.data.CategoriesMock;
import com.scipath.becomeaking.data.GameState;
import com.scipath.becomeaking.model.Category;
import com.scipath.becomeaking.model.Personage;
import com.scipath.becomeaking.model.Sex;
import com.scipath.becomeaking.model.Title;

import java.util.ArrayList;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // Making knights image monochrome
        ImageView imageview = findViewById(R.id.image_view_knights);
        ColorMatrix matrix = new ColorMatrix();
        matrix.setSaturation(0);
        ColorMatrixColorFilter filter = new ColorMatrixColorFilter(matrix);
        imageview.setColorFilter(filter);

        // Button new game
        Button buttonNewGame = findViewById(R.id.button_new_game);
        buttonNewGame.setOnClickListener(view -> {
            Intent intent = new Intent(MainActivity.this, SexSelectionActivity.class);
            startActivity(intent);
        });

        // Button load
        Button buttonLoad = findViewById(R.id.button_load);
        buttonLoad.setOnClickListener(view -> {
            BecomeAKing.getInstance().loadGame(this);
            if (BecomeAKing.getInstance().isLoaded()) {
                Intent intent = new Intent(MainActivity.this, GameActivity.class);
                startActivity(intent);
            }
        });

        // Button language
        Button buttonLanguage = findViewById(R.id.button_language);
        buttonLanguage.setOnClickListener(view -> {
            // TODO: move to new LanguageActivity
        });

        // Button test
        Button buttonTest = findViewById(R.id.button_test);
        if (BecomeAKing.getInstance().isTest()) buttonTest.setVisibility(View.VISIBLE);
        buttonTest.setOnClickListener(view -> {
            Personage personage = new Personage("Hero", Sex.Male, Title.Bandit);
            personage.setMoney(100000);
            ArrayList<Category> categories = CategoriesMock.getCategories(true);
            categories.get(1).recalculateStats(); // To set cloth image
            BecomeAKing.getInstance().setGameState(new GameState(0, personage, categories));
            Intent intent = new Intent(MainActivity.this, GameActivity.class);
            startActivity(intent);
        });
    }
}