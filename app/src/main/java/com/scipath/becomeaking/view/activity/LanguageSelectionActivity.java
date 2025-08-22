package com.scipath.becomeaking.view.activity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.scipath.becomeaking.R;
import com.scipath.becomeaking.adapter.LocalesAdapter;
import com.scipath.becomeaking.contract.callback.ObjectCallback;
import com.scipath.becomeaking.manager.LocaleManager;

import java.util.Locale;


public class LanguageSelectionActivity extends BaseActivity {

    // Models variables
    Locale currentLocale;

    // Adapters variables
    private LocalesAdapter localesAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_language_selection);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // Getting locales set
        Locale[] locales = LocaleManager.getLocales();

        // Views
        RecyclerView recyclerView = findViewById(R.id.languages_list);

        // Button click callback
        ObjectCallback objectCallback = locale -> {
            if(!(locale instanceof Locale)) return;
            // Notify adapter about onButtonClick event
            recyclerView.post(new Runnable() {
                @Override public void run()
                {
                    localesAdapter.notifyDataSetChanged();
                    currentLocale = (Locale)locale;
                }
            });
        };


        // Displaying locales as RadioButtons using LocalesAdapter
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        localesAdapter = new LocalesAdapter(locales, objectCallback);
        recyclerView.setAdapter(localesAdapter);

        // Button continue
        Button buttonChange = findViewById(R.id.button_change);
        buttonChange.setOnClickListener(view -> {
            if(currentLocale != null) {
                LocaleManager.setLocale(currentLocale.getLanguage(), getApplicationContext());

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