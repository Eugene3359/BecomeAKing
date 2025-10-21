package com.scipath.becomeaking.view.activity;

import android.content.Context;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.LinearLayout;

import androidx.core.content.ContextCompat;
import androidx.core.util.Pair;

import com.scipath.becomeaking.R;
import com.scipath.becomeaking.contract.model.ICity;
import com.scipath.becomeaking.data.CitiesList;
import com.scipath.becomeaking.view.customview.MapRoutesView;
import com.scipath.becomeaking.view.layout.CarriageLayout;
import com.scipath.becomeaking.view.layout.CityLayout;

import java.util.ArrayList;
import java.util.Random;


public class MapActivity extends BaseActivity {

    // Variables
    private int mapHeight;
    private int mapWidth;

    // Models
    private ArrayList<ICity> cities;

    // Views
    private FrameLayout mapContainer;
    private MapRoutesView mapRoutesView;
    private ImageButton buttonActive;
    private ImageButton buttonFinance;
    private ImageButton buttonBattle;

    // Timer
    private CountDownTimer carriageTimer;


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
                addRoutes(city);
            }
        });

        // Buttons
        Button buttonBack = findViewById(R.id.button_back);
        buttonBack.setOnClickListener(view -> {
            finish();
        });

        buttonFinance = findViewById(R.id.button_finance);
        buttonFinance.setOnClickListener(view -> {
            switchMenuButton(buttonFinance);
        });

        buttonBattle = findViewById(R.id.button_battle);
        buttonBattle.setOnClickListener(view -> {
            switchMenuButton(buttonBattle);
        });

        switchMenuButton(buttonFinance);
        addCarriages(this);
    }

    private void addMarker(LinearLayout layout, float x, float y) {
        layout.measure(
                View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED),
                View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED)
        );

        FrameLayout.LayoutParams params = new FrameLayout.LayoutParams(
                FrameLayout.LayoutParams.WRAP_CONTENT,
                FrameLayout.LayoutParams.WRAP_CONTENT
        );
        params.topMargin = (int) ((mapHeight * y) - (0.5 * layout.getMeasuredHeight()));
        params.leftMargin = (int) ((mapWidth * x) - (0.5 * layout.getMeasuredWidth()));

        layout.setLayoutParams(params);

        mapContainer.addView(layout);
    }

    private void moveMarker(LinearLayout layout, float x, float y) {
        FrameLayout.LayoutParams params = (FrameLayout.LayoutParams) layout.getLayoutParams();
        params.topMargin = (int) ((mapHeight * y) - (0.5 * layout.getMeasuredHeight()));
        params.leftMargin = (int) ((mapWidth * x) - (0.5 * layout.getMeasuredWidth()));
        layout.setLayoutParams(params);
    }

    private void addMovingMarker(LinearLayout layout, Pair<Float, Float> src, Pair<Float, Float> dest) {
        addMarker(layout, src.first, src.second);

        new CountDownTimer(20000, 25) {
            int tickCount = 0;
            float x = src.first;
            float y = src.second;

            public void onTick(long millisUntilFinished) {
                float progress = 1f - (millisUntilFinished / 20000f);
                x = src.first + (dest.first - src.first) * progress;
                y = src.second + (dest.second - src.second) * progress;
                moveMarker(layout, x, y);
                tickCount++;
            }

            public void onFinish() {
                mapContainer.postDelayed(() -> mapContainer.removeView(layout), 250);
            }
        }.start();
    }

    private void addCity(ICity city) {
        CityLayout cityLayout = new CityLayout(this);
        cityLayout.setName(city.getNameId());
        addMarker(cityLayout, city.getX(), city.getY());
        cityLayout.setOnClickListener(v -> {
            showDialogue(
                    city.getNameId(),
                    R.string.in_development,
                    R.string.got_it,
                    null
            );
        });
    }

    private void addRoutes(ICity city) {
        for (ICity route : city.getRoutes()) {
            mapRoutesView.addConnection(city.getX(), city.getY(), route.getX(), route.getY());
        }
    }

    private void addCarriages(Context context) {
        carriageTimer = new CountDownTimer(60000, 1000) {
            Random random = new Random();

            public void onTick(long millisUntilFinished) {
                if (random.nextInt(12) % 10 == 0) {
                    ICity city1 = cities.get(random.nextInt(cities.size()));
                    ICity city2 = (ICity) city1.getRoutes().toArray()[random.nextInt(city1.getRoutes().size())];

                    CarriageLayout layout = new CarriageLayout(context);
                    layout.setDirection(
                            city1.getX() > city2.getX() ?
                            CarriageLayout.Direction.Left :
                            CarriageLayout.Direction.Right
                    );

                    addMovingMarker(
                            layout,
                            city1.getCoordinates(),
                            city2.getCoordinates()
                    );
                }
            }

            public void onFinish() {
                addCarriages(context);
            }
        }.start();
    }

    public void switchMenuButton(ImageButton pressedButton) {
        if (buttonActive != pressedButton) {
            // Make all buttons brown
            buttonFinance.setBackgroundColor(ContextCompat.getColor(this, R.color.game_menu_element));
            buttonBattle.setBackgroundColor(ContextCompat.getColor(this, R.color.game_menu_element));

            // Make active button green
            buttonActive = pressedButton;
            buttonActive.setBackgroundColor(ContextCompat.getColor(this, R.color.game_menu_button_active));

            if (buttonActive == buttonFinance) {
                mapRoutesView.setVisibility(View.VISIBLE);
            } else {
                mapRoutesView.setVisibility(View.GONE);
            }
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (carriageTimer != null) {
            carriageTimer.cancel();
            carriageTimer = null;
        }
    }
}