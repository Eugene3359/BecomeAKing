package com.scipath.becomeaking.view.activity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.scipath.becomeaking.R;
import com.scipath.becomeaking.adapter.LocalesAdapter;
import com.scipath.becomeaking.manager.LocaleManager;

import java.util.Locale;


public class LanguageSelectionActivity extends BaseActivity {

    // Variables
    Locale currentLocale;

    // Adapters
    private LocalesAdapter localesAdapter;


    @Override
    protected int getLayoutId() {
        return R.layout.activity_language_selection;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Getting locales set
        Locale[] locales = LocaleManager.getLocales();

        // Views
        RecyclerView recyclerView = findViewById(R.id.languages_list);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        // Displaying locales as RadioButtons using LocalesAdapter
        localesAdapter = new LocalesAdapter(locales, locale -> {
            if (locale instanceof Locale) {
                // Notify adapter about onButtonClick event
                recyclerView.post(() -> {
                    localesAdapter.notifyDataSetChanged();
                    currentLocale = (Locale)locale;
                });
            }
        });
        recyclerView.setAdapter(localesAdapter);

        // Button continue
        Button buttonChange = findViewById(R.id.button_change);
        buttonChange.setOnClickListener(view -> {
            if(currentLocale != null) {
                LocaleManager.setLocale(currentLocale.getLanguage(), this);

                Intent intent = new Intent(this, MainActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
                finishAffinity();
            }
        });

        // Button back
        Button buttonBack = findViewById(R.id.button_back);
        buttonBack.setOnClickListener(view -> {
            finish();
        });
    }
}