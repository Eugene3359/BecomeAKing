package com.scipath.becomeaking.view.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;

import com.scipath.becomeaking.R;
import com.scipath.becomeaking.contract.model.ICity;
import com.scipath.becomeaking.data.CitiesList;
import com.scipath.becomeaking.view.customview.MapRoutesView;
import com.scipath.becomeaking.view.layout.CityLayout;

import java.util.ArrayList;


public class MapActivity extends BaseActivity {

    // Variables
    private int mapHeight;
    private int mapWidth;

    // Models
    private ArrayList<ICity> cities;

    // Views
    private FrameLayout mapContainer;
    private MapRoutesView mapRoutesView;


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

        cities = CitiesList.getCities();

        // Views
        mapContainer = findViewById(R.id.map_container);
        mapRoutesView = findViewById(R.id.map_routes);

        mapContainer.post(() -> {
            mapHeight = mapContainer.getHeight();
            mapWidth = mapContainer.getWidth();


            for (ICity city : cities) {
                addCity(city);
                addRoute(city);
            }
        });

        // Buttons
        Button buttonBack = findViewById(R.id.button_back);
        buttonBack.setOnClickListener(view -> {
            finish();
        });
    }

    private void addCity(ICity city) {
        CityLayout cityLayout = new CityLayout(this);
        cityLayout.setName(city.getNameId());

        FrameLayout.LayoutParams params = new FrameLayout.LayoutParams(
                FrameLayout.LayoutParams.WRAP_CONTENT,
                FrameLayout.LayoutParams.WRAP_CONTENT
        );

        cityLayout.measure(
                View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED),
                View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED)
        );

        params.topMargin = (int) ((mapHeight * city.getY()) - (0.5 * cityLayout.getMeasuredHeight()));
        params.leftMargin = (int) ((mapWidth * city.getX()) - (0.5 * cityLayout.getMeasuredWidth()));

        cityLayout.setLayoutParams(params);

        cityLayout.setOnClickListener(v -> {
            showDialogue(
                    R.string.notification,
                    R.string.in_development,
                    R.string.got_it,
                    null
            );
        });

        mapContainer.addView(cityLayout);
    }

    private void addRoute(ICity city) {
        for (ICity route : city.getRoutes()) {
            mapRoutesView.addConnection(city.getX(), city.getY(), route.getX(), route.getY());
        }
    }
}