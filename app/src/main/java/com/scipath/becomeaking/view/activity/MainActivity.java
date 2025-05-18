package com.scipath.becomeaking.view.activity;

import android.content.Intent;
import android.graphics.ColorMatrix;
import android.graphics.ColorMatrixColorFilter;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.scipath.becomeaking.R;


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
            // TODO: move to new LoadActivity
        });

        // Button language
        Button buttonLanguage = findViewById(R.id.button_language);
        buttonLanguage.setOnClickListener(view -> {
            // TODO: move to new LanguageActivity
        });
    }
}