package com.scipath.becomeaking.view.activity;

import android.os.Bundle;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.scipath.becomeaking.BecomeAKing;
import com.scipath.becomeaking.R;
import com.scipath.becomeaking.model.Personage;
import com.scipath.becomeaking.view.fragment.CategoriesFragment;
import com.scipath.becomeaking.view.fragment.PersonageFragment;


public class GameActivity extends AppCompatActivity {

    // Models variables
    private Personage personage;

    // Views variables
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
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_game);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // Getting Personage from Application
        personage = BecomeAKing.getInstance().getPersonage();

        // Views
        textViewHealth = findViewById(R.id.text_view_health);
        textViewReputation = findViewById(R.id.text_view_reputation);
        textViewMoney = findViewById(R.id.text_view_money);

        updateViews();

        // Setting Fragment
        fragment = new PersonageFragment();
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.fragment_frame, fragment)
                .addToBackStack(null).commit();

        // Buttons
        buttonActive = findViewById(R.id.button_personage);

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
            args.putInt("toIndex", 13);
            newFragment.setArguments(args);
            switchMenuButton(buttonJob, newFragment);
        });

        buttonFinance = findViewById(R.id.button_finance);
        buttonFinance.setOnClickListener(view -> {
            // TODO: ClickListener
        });

        buttonBattle = findViewById(R.id.button_battle);
        buttonBattle.setOnClickListener(view -> {
            // TODO: ClickListener
        });

        ImageButton imageButtonNextDay = findViewById(R.id.image_button_next_day);
        imageButtonNextDay.setOnClickListener(view -> {
            BecomeAKing.getInstance().nextDay();
            BecomeAKing.getInstance().saveGame();
            updateViews();
            if(fragment.getClass() == PersonageFragment.class) {
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

    public void switchMenuButton(ImageButton pressedButton, Fragment newFragment) {
        if (buttonActive != pressedButton) {
            // Make all buttons brown
            buttonPersonage.setBackgroundColor(ContextCompat.getColor(GameActivity.this, R.color.game_menu_brown));
            buttonShop.setBackgroundColor(ContextCompat.getColor(GameActivity.this, R.color.game_menu_brown));
            buttonHousing.setBackgroundColor(ContextCompat.getColor(GameActivity.this, R.color.game_menu_brown));
            buttonJob.setBackgroundColor(ContextCompat.getColor(GameActivity.this, R.color.game_menu_brown));

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
                .attach(fragment) // Reattach to force a refresh
                .commit();
    }

    public void updateViews() {
        textViewHealth.setText(Integer.toString(personage.getHealth()));
        textViewReputation.setText(Integer.toString(personage.getReputation()));
        textViewMoney.setText(Integer.toString(personage.getMoney()));
    }
}