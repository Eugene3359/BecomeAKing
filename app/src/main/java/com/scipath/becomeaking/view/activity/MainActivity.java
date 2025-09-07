package com.scipath.becomeaking.view.activity;

import android.content.Intent;
import android.graphics.ColorMatrix;
import android.graphics.ColorMatrixColorFilter;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.scipath.becomeaking.BecomeAKing;
import com.scipath.becomeaking.R;
import com.scipath.becomeaking.contract.model.ICategory;
import com.scipath.becomeaking.data.CategoriesList;
import com.scipath.becomeaking.model.GameState;
import com.scipath.becomeaking.model.Personage;
import com.scipath.becomeaking.model.enums.Sex;
import com.scipath.becomeaking.model.enums.Title;

import java.util.ArrayList;


public class MainActivity extends BaseActivity {

    @Override
    protected int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

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
            Intent intent = new Intent(MainActivity.this, LanguageSelectionActivity.class);
            startActivity(intent);
        });

        // Button test
        Button buttonTest = findViewById(R.id.button_test);
        if (BecomeAKing.getInstance().isTest()) buttonTest.setVisibility(View.VISIBLE);
        buttonTest.setOnClickListener(view -> {
            Personage personage = new Personage("Hero", Sex.Male, Title.Bandit);
            personage.setReputation(8000);
            personage.setMoney(100000);
            ArrayList<ICategory> categories = CategoriesList.getCategories(true);
            BecomeAKing.getInstance().setGameState(new GameState(personage, categories, 0));
            Intent intent = new Intent(MainActivity.this, GameActivity.class);
            startActivity(intent);
        });
    }
}