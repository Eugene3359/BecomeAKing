package com.scipath.becomeaking.view.activity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.scipath.becomeaking.R;
import com.scipath.becomeaking.adapter.TitlesAdapter;
import com.scipath.becomeaking.model.enums.Title;


public class TitleSelectionActivity extends BaseActivity {

    // Models
    private Title currentTitle;

    // Adapters
    private TitlesAdapter titlesAdapter;


    @Override
    protected int getLayoutId() {
        return R.layout.activity_title_selection;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Getting titles set
        Title[] titles = Title.values();

        // Views
        RecyclerView recyclerView = findViewById(R.id.titles_list);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        // Displaying titles as RadioButtons using TitleAdapter
        titlesAdapter = new TitlesAdapter(titles, title -> {
            if(title instanceof Title) {
                // Notify adapter about onButtonClick event
                recyclerView.post(() -> {
                    titlesAdapter.notifyDataSetChanged();
                    currentTitle = (Title)title;
                });
            }
        });
        recyclerView.setAdapter(titlesAdapter);

        // Button continue
        Button buttonContinue = findViewById(R.id.button_continue);
        buttonContinue.setOnClickListener(view -> {
            if(currentTitle != null) {
                Intent intent = new Intent(TitleSelectionActivity.this, NamingPersonageActivity.class);
                Intent thisIntent = getIntent();
                intent.putExtra("sex", thisIntent.getSerializableExtra("sex"));
                intent.putExtra("title", currentTitle);
                startActivity(intent);
            } else {
                showDialogue(R.string.notification, R.string.chose_title, R.string.got_it, null);
            }
        });

        // Button back
        Button buttonBack = findViewById(R.id.button_back);
        buttonBack.setOnClickListener(view -> {
            finish();
        });
    }
}