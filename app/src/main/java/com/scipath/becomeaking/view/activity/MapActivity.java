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

import com.scipath.becomeaking.BecomeAKing;
import com.scipath.becomeaking.R;
import com.scipath.becomeaking.contract.model.ICity;
import com.scipath.becomeaking.contract.model.IRegion;
import com.scipath.becomeaking.data.CitiesList;
import com.scipath.becomeaking.data.RegionsList;
import com.scipath.becomeaking.view.fragment.DialogueFragment;
import com.scipath.becomeaking.view.view.MapRoutesView;
import com.scipath.becomeaking.view.layout.CarriageLayout;
import com.scipath.becomeaking.view.layout.CityLayout;
import com.scipath.becomeaking.view.layout.RegionLayout;

import java.util.ArrayList;
import java.util.Random;


public class MapActivity extends BaseActivity {

    // Variables
    private int mapHeight;
    private int mapWidth;

    // Models
    private ArrayList<ICity> cities;
    private ArrayList<IRegion> regions;
    private ICity currentCity;

    // Views
    private FrameLayout mapContainer;
    private MapRoutesView mapRoutesView;
    private ArrayList<LinearLayout> mapRegionsLayouts;
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
        showInDevelopmentNotification();

        cities = CitiesList.getCities();
        regions = RegionsList.getRegions();
        currentCity = BecomeAKing.getInstance().getCity();

        // Views
        mapContainer = findViewById(R.id.map_container);
        mapRoutesView = findViewById(R.id.map_routes);
        mapRegionsLayouts = new ArrayList<>();

        mapContainer.post(() -> {
            mapHeight = mapContainer.getHeight();
            mapWidth = mapContainer.getWidth();

            for (IRegion region : regions) {
                addRegion(region);
            }

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
            movePersonage(city);
        });
    }

    private void addRoutes(ICity city) {
        for (ICity route : city.getRoutes()) {
            mapRoutesView.addConnection(city.getX(), city.getY(), route.getX(), route.getY());
        }
    }

    private void addRegion(IRegion region) {
        RegionLayout regionLayout = new RegionLayout(this);
        regionLayout.setWidth((int) (mapWidth * region.getWidth()));
        regionLayout.setHeight((int) (mapHeight * region.getHeight()));
        regionLayout.setImageSrc(region.getDrawableId());
        addMarker(regionLayout, region.getX(), region.getY());
        regionLayout.setOnClickListener(v -> {
            showInDevelopmentNotification();
        });
        mapRegionsLayouts.add(regionLayout);
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

    private void movePersonage(ICity city) {
        if (currentCity == city) {
            DialogueFragment dialogueFragment = new DialogueFragment.Builder()
                    .setHeader(city.getNameId())
                    .setMessage(R.string.you_are_already_in_this_city)
                    .setButton1(R.string.got_it, null)
                    .build();
            showDialogue(dialogueFragment);
        } else if (currentCity.getRoutes().contains(city)) {
            DialogueFragment dialogueFragment = new DialogueFragment.Builder()
                    .setHeader(city.getNameId())
                    .setMessage(R.string.go_to_city_confirmation)
                    .setButton1(R.string.no, null)
                    .setButton2(R.string.yes, () -> {
                        BecomeAKing.getInstance().setCity(city);
                        currentCity = city;
                    })
                    .build();
            showDialogue(dialogueFragment);
        } else {
            DialogueFragment dialogueFragment = new DialogueFragment.Builder()
                    .setHeader(city.getNameId())
                    .setMessage(R.string.no_direct_connection)
                    .setButton1(R.string.got_it, null)
                    .build();
            showDialogue(dialogueFragment);
        }
    }

    private void switchMenuButton(ImageButton pressedButton) {
        if (buttonActive != pressedButton) {
            // Make all buttons brown
            buttonFinance.setBackgroundColor(ContextCompat.getColor(this, R.color.game_menu_element));
            buttonBattle.setBackgroundColor(ContextCompat.getColor(this, R.color.game_menu_element));

            // Make active button green
            buttonActive = pressedButton;
            buttonActive.setBackgroundColor(ContextCompat.getColor(this, R.color.game_menu_button_active));

            if (buttonActive == buttonFinance) {
                mapRoutesView.setVisibility(View.VISIBLE);
                for (LinearLayout regionLayout : mapRegionsLayouts) {
                    regionLayout.setVisibility(View.GONE);
                }
            } else {
                mapRoutesView.setVisibility(View.GONE);
                for (LinearLayout regionLayout : mapRegionsLayouts) {
                    regionLayout.setVisibility(View.VISIBLE);
                }
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