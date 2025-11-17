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
import androidx.lifecycle.ViewModelProvider;

import com.scipath.becomeaking.BecomeAKing;
import com.scipath.becomeaking.R;
import com.scipath.becomeaking.contract.callback.Callback;
import com.scipath.becomeaking.contract.model.ICity;
import com.scipath.becomeaking.contract.model.IRegion;
import com.scipath.becomeaking.data.CitiesList;
import com.scipath.becomeaking.data.RegionsList;
import com.scipath.becomeaking.view.fragment.DialogueFragment;
import com.scipath.becomeaking.view.fragment.TravelDialogueFragment;
import com.scipath.becomeaking.view.layout.PersonageMarkerLayout;
import com.scipath.becomeaking.view.view.MapRoutesView;
import com.scipath.becomeaking.view.layout.CarriageMarkerLayout;
import com.scipath.becomeaking.view.layout.CityMarkerLayout;
import com.scipath.becomeaking.view.layout.RegionLayout;
import com.scipath.becomeaking.viewmodel.CurrentCityViewModel;

import java.util.ArrayList;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;


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

    // ViewModels
    private CurrentCityViewModel currentCityViewModel;

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
        currentCityViewModel = new ViewModelProvider(
            BecomeAKing.getInstance(),
            ViewModelProvider.AndroidViewModelFactory.getInstance(BecomeAKing.getInstance()
        )).get(CurrentCityViewModel.class);
        currentCityViewModel.getCity().observe(this, city -> {
            currentCity = currentCityViewModel.getCity().getValue();
        });

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


    private void addMarker(LinearLayout marker, float x, float y) {
        marker.measure(
                View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED),
                View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED)
        );

        FrameLayout.LayoutParams params = new FrameLayout.LayoutParams(
                FrameLayout.LayoutParams.WRAP_CONTENT,
                FrameLayout.LayoutParams.WRAP_CONTENT
        );
        params.topMargin = (int) ((mapHeight * y) - (0.5 * marker.getMeasuredHeight()));
        params.leftMargin = (int) ((mapWidth * x) - (0.5 * marker.getMeasuredWidth()));

        marker.setLayoutParams(params);

        mapContainer.addView(marker);
    }

    private void moveMarker(LinearLayout marker, float x, float y) {
        FrameLayout.LayoutParams params = (FrameLayout.LayoutParams) marker.getLayoutParams();
        params.topMargin = (int) ((mapHeight * y) - (0.5 * marker.getMeasuredHeight()));
        params.leftMargin = (int) ((mapWidth * x) - (0.5 * marker.getMeasuredWidth()));
        marker.setLayoutParams(params);
    }

    private void addMovingMarker(LinearLayout marker, Pair<Float, Float> src, Pair<Float, Float> dest) {
        addMarker(marker, src.first, src.second);

        new CountDownTimer(20000, 25) {
            float x = src.first;
            float y = src.second;

            public void onTick(long millisUntilFinished) {
                float progress = 1f - (millisUntilFinished / 20000f);
                x = src.first + (dest.first - src.first) * progress;
                y = src.second + (dest.second - src.second) * progress;
                moveMarker(marker, x, y);
            }

            public void onFinish() {
                mapContainer.postDelayed(() -> mapContainer.removeView(marker), 250);
            }
        }.start();
    }

    private void addCity(ICity city) {
        CityMarkerLayout cityMarker = new CityMarkerLayout(this);
        cityMarker.setName(city.getNameId());
        addMarker(cityMarker, city.getX(), city.getY());
        cityMarker.setOnClickListener(v -> {
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

                    CarriageMarkerLayout marker = new CarriageMarkerLayout(context);
                    marker.setDirection(
                            city1.getX() > city2.getX() ?
                            CarriageMarkerLayout.Direction.Left :
                            CarriageMarkerLayout.Direction.Right
                    );

                    addMovingMarker(
                            marker,
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
        if (BecomeAKing.getInstance().isTraveling()) {
            showNotification(R.string.you_are_already_on_your_way);
            return;
        }

        if (currentCity == city) {
            showNotification(R.string.you_are_already_in_this_city);
        } else if (!currentCity.getRoutes().contains(city)) {
            showNotification(R.string.no_direct_connection);
        } else {
            Callback startTravel = () -> {
                BecomeAKing.getInstance().isTraveling(true);

                Timer timer = new Timer();
                timer.schedule(new TimerTask() {
                    @Override
                    public void run() {
                        currentCityViewModel.setCity(city);
                        BecomeAKing.getInstance().isTraveling(false);
                    }
                }, 20000);

                addMovingMarker(
                        new PersonageMarkerLayout(this),
                        currentCity.getCoordinates(),
                        city.getCoordinates()
                );
            };

            TravelDialogueFragment travelDialogueFragment = new TravelDialogueFragment.Builder()
                    .setCity(city)
                    .setCallback(startTravel)
                    .build();
            showDialogue(travelDialogueFragment);
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