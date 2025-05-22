package com.scipath.becomeaking.view.activity;

import android.os.Bundle;
import android.widget.ImageButton;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.ViewModelProvider;

import com.scipath.becomeaking.BecomeAKing;
import com.scipath.becomeaking.R;
import com.scipath.becomeaking.databinding.ActivityGameBinding;
import com.scipath.becomeaking.model.Personage;
import com.scipath.becomeaking.view.fragment.CategoriesFragment;
import com.scipath.becomeaking.view.fragment.PersonageFragment;
import com.scipath.becomeaking.viewmodel.PersonageViewModel;


public class GameActivity extends AppCompatActivity {

    // Models variables
    private Personage personage;

    // Views variables
    private ImageButton buttonActive;
    private ImageButton buttonPersonage;
    private ImageButton buttonShop;
    private ImageButton buttonHouse;
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
        personage = BecomeAKing.getInstance().getCurrentPersonage();

        // Binding
        ActivityGameBinding binding = ActivityGameBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        // ViewModels
        PersonageViewModel personageViewModel = new ViewModelProvider(this).get(PersonageViewModel.class);
        personageViewModel.setPersonage(personage);
        personageViewModel.getPersonage().observe(this, personage -> {
            binding.textViewHealth.setText(Integer.toString(personage.getHealth()));
            binding.textViewReputation.setText(Integer.toString(personage.getReputation()));
            binding.textViewMoney.setText(Integer.toString(personage.getMoney()));
        });

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

        buttonHouse = findViewById(R.id.button_housing);
        buttonHouse.setOnClickListener(view -> {
            CategoriesFragment newFragment = new CategoriesFragment();
            Bundle args = new Bundle();
            args.putInt("fromIndex", 5);
            args.putInt("toIndex", 10);
            newFragment.setArguments(args);
            switchMenuButton(buttonHouse, newFragment);
        });

        buttonJob = findViewById(R.id.button_job);
        buttonJob.setOnClickListener(view -> {
            // TODO: ClickListener
        });

        buttonFinance = findViewById(R.id.button_finance);
        buttonFinance.setOnClickListener(view -> {
            // TODO: ClickListener
        });

        buttonBattle = findViewById(R.id.button_battle);
        buttonBattle.setOnClickListener(view -> {
            // TODO: ClickListener
        });
    }


    public void switchMenuButton(ImageButton pressedButton, Fragment newFragment) {
        if (buttonActive != pressedButton) {
            // Make all buttons brown
            buttonPersonage.setBackgroundColor(ContextCompat.getColor(GameActivity.this, R.color.game_menu_brown));
            buttonShop.setBackgroundColor(ContextCompat.getColor(GameActivity.this, R.color.game_menu_brown));
            buttonHouse.setBackgroundColor(ContextCompat.getColor(GameActivity.this, R.color.game_menu_brown));

            // Make active button green
            buttonActive = pressedButton;
            buttonActive.setBackgroundColor(ContextCompat.getColor(GameActivity.this, R.color.game_menu_button_green));

            fragment = newFragment;
            setFragment();
        } else {
            refreshFragmentData();
        }
    }

    public void setFragment() {
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.fragment_frame, fragment)
                .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
                .commit();
    }

    private void refreshFragmentData() {
        getSupportFragmentManager().beginTransaction()
                .detach(fragment) // Detach to refresh the view
                .attach(fragment) // Reattach to force a refresh
                .commit();
    }
}