package com.scipath.becomeaking.view.activity;

import android.os.Bundle;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.core.content.ContextCompat;
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
    private TextView textViewHealth;
    private TextView textViewReputation;
    private TextView textViewMoney;
    private ImageButton buttonActive;
    private ImageButton buttonPersonage;
    private ImageButton buttonShop;
    private ImageButton buttonHousing;
    private ImageButton buttonJob;
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

        // Getting Personage
        personage = BecomeAKing.getInstance().getPersonage();

        // Views
        textViewHealth = findViewById(R.id.text_view_health);
        textViewReputation = findViewById(R.id.text_view_reputation);
        textViewMoney = findViewById(R.id.text_view_money);
        updateViews();

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

        buttonJob = findViewById(R.id.button_job);
        buttonJob.setOnClickListener(view -> {
            CategoriesFragment newFragment = new CategoriesFragment();
            Bundle args = new Bundle();
            args.putInt("fromIndex", 10);
            args.putInt("toIndex", 14);
            newFragment.setArguments(args);
            switchMenuButton(buttonJob, newFragment);
        });

        buttonFinance = findViewById(R.id.button_finance);
        buttonFinance.setOnClickListener(view -> {
            switchMenuButton(buttonFinance, new FinanceFragment());
            showDialogue(
                    R.string.notification,
                    R.string.in_development,
                    R.string.got_it,
                    null
            );
        });

        buttonBattle = findViewById(R.id.button_battle);
        buttonBattle.setOnClickListener(view -> {
            // TODO: Change a fragment after implementation
            switchMenuButton(buttonBattle, new Fragment());
            showDialogue(
                    R.string.notification,
                    R.string.in_development,
                    R.string.got_it,
                    null
            );
        });

        switchMenuButton(buttonPersonage, new PersonageFragment());

        // Next Day Button
        ImageButton imageButtonNextDay = findViewById(R.id.image_button_next_day);
        imageButtonNextDay.setOnClickListener(view -> {
            BecomeAKing.getInstance().nextDay();
            BecomeAKing.getInstance().saveGame();
            updateViews();
            if (fragment.getClass() == PersonageFragment.class) {
                ((PersonageFragment)fragment).updateViews();
            } else {
                switchMenuButton(buttonPersonage, new PersonageFragment());
            }
            BecomeAKing.getInstance().checkPersonageForNegativeValues(this);
        });

    }

    @Override
    protected void onResume() {
        super.onResume();
        updateViews();
        BecomeAKing.getInstance().checkPersonageForNegativeValues(this);
    }

    @Override
    protected void onDestroy() {
        BecomeAKing.getInstance().saveGame();
        BecomeAKing.getInstance().clearGameState();
        super.onDestroy();
    }

    public void updateViews() {
        textViewHealth.setText(String.valueOf(personage.getHealth()));
        textViewReputation.setText(String.valueOf(personage.getReputation()));
        textViewMoney.setText(String.valueOf(personage.getMoney()));
    }

    public void switchMenuButton(ImageButton pressedButton, Fragment newFragment) {
        if (buttonActive != pressedButton) {
            // Make all buttons brown
            buttonPersonage.setBackgroundColor(ContextCompat.getColor(GameActivity.this, R.color.game_menu_brown));
            buttonShop.setBackgroundColor(ContextCompat.getColor(GameActivity.this, R.color.game_menu_brown));
            buttonHousing.setBackgroundColor(ContextCompat.getColor(GameActivity.this, R.color.game_menu_brown));
            buttonJob.setBackgroundColor(ContextCompat.getColor(GameActivity.this, R.color.game_menu_brown));
            buttonFinance.setBackgroundColor(ContextCompat.getColor(GameActivity.this, R.color.game_menu_brown));
            buttonBattle.setBackgroundColor(ContextCompat.getColor(GameActivity.this, R.color.game_menu_brown));

            // Make active button green
            buttonActive = pressedButton;
            buttonActive.setBackgroundColor(ContextCompat.getColor(GameActivity.this, R.color.game_menu_button_green));

            setFragment(newFragment);
        } else {
            refreshFragmentData();
        }
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