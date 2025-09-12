package com.scipath.becomeaking.view.activity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.FrameLayout;

import com.scipath.becomeaking.R;
import com.scipath.becomeaking.view.layout.CityLayout;


public class MapActivity extends BaseActivity {

    // Variables
    private int mapHeight;
    private int mapWidth;

    // Views
    private FrameLayout mapContainer;


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

        mapContainer = findViewById(R.id.map_container);
        mapContainer.post(() -> {
            mapHeight = mapContainer.getHeight();
            mapWidth = mapContainer.getWidth();

            // Cities
            addCity(0.115f, 0.200f, R.string.grimshaven);
            addCity(0.160f, 0.360f, R.string.farendol);
            addCity(0.215f, 0.340f, R.string.thornford);
            addCity(0.305f, 0.100f, R.string.drakkenburg);
            addCity(0.315f, 0.645f, R.string.steinhart);
            addCity(0.370f, 0.415f, R.string.ravenholm);
            addCity(0.415f, 0.315f, R.string.valmir);
            addCity(0.575f, 0.295f, R.string.tenebris);
            addCity(0.655f, 0.120f, R.string.wolfengard);
            addCity(0.640f, 0.240f, R.string.kastervik);
            addCity(0.670f, 0.680f, R.string.morgenheim);
            addCity(0.815f, 0.290f, R.string.elmyria);
            addCity(0.795f, 0.395f, R.string.blackhollow);
            addCity(0.775f, 0.780f, R.string.albreston);
            addCity(0.835f, 0.690f, R.string.schaderveld);
            addCity(0.885f, 0.335f, R.string.winterholm);
        });

        // Buttons
        Button buttonBack = findViewById(R.id.button_back);
        buttonBack.setOnClickListener(view -> {
            finish();
        });
    }

    private void addCity(float x, float y, int cityNameId) {
        CityLayout city = new CityLayout(this);
        city.setName(cityNameId);

        FrameLayout.LayoutParams params = new FrameLayout.LayoutParams(
                FrameLayout.LayoutParams.WRAP_CONTENT,
                FrameLayout.LayoutParams.WRAP_CONTENT
        );
        params.topMargin = (int) (mapHeight * y);
        params.leftMargin = (int) (mapWidth * x);

        city.setLayoutParams(params);

        city.setOnClickListener(v -> {
            showDialogue(
                    R.string.notification,
                    R.string.in_development,
                    R.string.got_it,
                    null
            );
        });

        mapContainer.addView(city);
    }
}