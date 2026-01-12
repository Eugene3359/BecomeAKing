package com.scipath.becomeaking.view.activity;

import android.os.Bundle;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.scipath.becomeaking.BecomeAKing;
import com.scipath.becomeaking.R;
import com.scipath.becomeaking.model.Personage;
import com.scipath.becomeaking.view.fragment.CategoriesFragment;
import com.scipath.becomeaking.view.fragment.FinanceFragment;
import com.scipath.becomeaking.view.fragment.PersonageFragment;


public class GameActivity extends BaseActivity {

    // Models
    private Personage personage;

    // Views
    private TextView textHealth;
    private TextView textReputation;
    private TextView textMoney;
    private ImageButton buttonActive;
    private ImageButton buttonPersonage;
    private ImageButton buttonShop;
    private ImageButton buttonHousing;
    private ImageButton buttonWork;
    private ImageButton buttonFinance;
    private ImageButton buttonBattle;
    private Fragment fragment;


    @Override
    protected int getLayoutId() {
        return R.layout.activity_game;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        personage = BecomeAKing.getInstance().getPersonage();

        // Views
        textHealth = findViewById(R.id.text_health);
        textReputation = findViewById(R.id.text_reputation);
        textMoney = findViewById(R.id.text_money);
        updateAll();

        // Menu Buttons
        buttonPersonage = findViewById(R.id.button_personage);
        buttonPersonage.setOnClickListener(view -> {
            switchMenuButton(buttonPersonage, new PersonageFragment());
        });

        buttonShop = findViewById(R.id.button_shop);
        buttonShop.setOnClickListener(view -> {
            CategoriesFragment newFragment = new CategoriesFragment();
            Bundle args = new Bundle();
            args.putInt("fromIndex", 0);
            args.putInt("toIndex", 5);
            newFragment.setArguments(args);
            switchMenuButton(buttonShop, newFragment);
        });

        buttonHousing = findViewById(R.id.button_housing);
        buttonHousing.setOnClickListener(view -> {
            CategoriesFragment newFragment = new CategoriesFragment();
            Bundle args = new Bundle();
            args.putInt("fromIndex", 5);
            args.putInt("toIndex", 10);
            newFragment.setArguments(args);
            switchMenuButton(buttonHousing, newFragment);
        });

        buttonWork = findViewById(R.id.button_work);
        buttonWork.setOnClickListener(view -> {
            CategoriesFragment newFragment = new CategoriesFragment();
            Bundle args = new Bundle();
            args.putInt("fromIndex", 10);
            args.putInt("toIndex", 14);
            newFragment.setArguments(args);
            switchMenuButton(buttonWork, newFragment);
        });

        buttonFinance = findViewById(R.id.button_finance);
        buttonFinance.setOnClickListener(view -> {
            switchMenuButton(buttonFinance, new FinanceFragment());
        });

        buttonBattle = findViewById(R.id.button_battle);
        buttonBattle.setOnClickListener(view -> {
            // TODO: Change a fragment after implementation
            switchMenuButton(buttonBattle, new Fragment());
            showInDevelopmentNotification();
        });

        switchMenuButton(buttonPersonage, new PersonageFragment());

        // Next Day Button
        ImageButton imageButtonNextDay = findViewById(R.id.image_button_next_day);
        imageButtonNextDay.setOnClickListener(view -> {
            BecomeAKing.getInstance().nextDay();
            BecomeAKing.getInstance().saveGame();
            updateAll();
            if (fragment.getClass() == PersonageFragment.class) {
                ((PersonageFragment)fragment).updateViews();
            } else {
                switchMenuButton(buttonPersonage, new PersonageFragment());
            }
            BecomeAKing.getInstance().checkPersonageState(this);
        });

    }

    @Override
    protected void onResume() {
        super.onResume();
        updateAll();
        BecomeAKing.getInstance().checkPersonageState(this);
    }

    @Override
    protected void onDestroy() {
        BecomeAKing.getInstance().saveGame();
        BecomeAKing.getInstance().clearGameState();
        super.onDestroy();
    }


    public void updateHealth() {
        textHealth.setText(String.valueOf(personage.getHealth()));
    }

    public void updateReputation() {
        textReputation.setText(String.valueOf(personage.getReputation()));
    }

    public void updateMoney() {
        textMoney.setText(String.valueOf(personage.getMoney()));
    }

    public void updateAll() {
        updateHealth();
        updateReputation();
        updateMoney();
    }

    public void switchMenuButton(ImageButton pressedButton, Fragment newFragment) {
        if (buttonActive == pressedButton) {
            refreshFragmentData();
            return;
        }

        if (buttonActive != null) {
            buttonActive.setBackgroundColor(this.getColor(R.color.game_menu_element));
        }

        buttonActive = pressedButton;
        buttonActive.setBackgroundColor(this.getColor(R.color.game_menu_button_active));
        setFragment(newFragment);
    }


    public void setFragment(Fragment fragment) {
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.fragment_frame, fragment)
                .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
                .commit();
        this.fragment = fragment;
    }

    private void refreshFragmentData() {
        getSupportFragmentManager().beginTransaction()
                .detach(fragment) // Detach to refresh the view
                .commit();
        getSupportFragmentManager().beginTransaction()
                .attach(fragment) // Reattach to force a refresh
                .commit();
    }
}