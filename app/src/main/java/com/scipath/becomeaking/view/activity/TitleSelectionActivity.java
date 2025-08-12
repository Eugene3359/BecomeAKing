package com.scipath.becomeaking.view.activity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.scipath.becomeaking.R;
import com.scipath.becomeaking.adapter.TitlesAdapter;
import com.scipath.becomeaking.model.enums.Title;
import com.scipath.becomeaking.contract.callback.ObjectCallback;
import com.scipath.becomeaking.view.fragment.DialogueFragment;


public class TitleSelectionActivity extends AppCompatActivity {

    // Models variables
    private Title currentTitle;

    // Adapters variables
    private TitlesAdapter titlesAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_title_selection);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // Getting titles set
        // TODO: make it algorithmically depending on players achievements
        Title[] titles = Title.values();

        // Views
        RecyclerView recyclerView = findViewById(R.id.titles_list);

        // Button click callback
        ObjectCallback objectCallback = new ObjectCallback() {
            @Override public void onClick(Object title)
            {
                if(!(title instanceof Title)) return;
                // Notify adapter about onButtonClick event
                recyclerView.post(new Runnable() {
                    @Override public void run()
                    {
                        titlesAdapter.notifyDataSetChanged();
                        currentTitle = (Title)title;
                    }
                });
            }
        };

        // Displaying titles as Buttons that works as RadioButtons using TitleAdapter
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        titlesAdapter = new TitlesAdapter(titles, objectCallback);
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
                DialogueFragment dialogueFragment = DialogueFragment.newInstance(R.string.chose_title, R.string.ok);
                dialogueFragment.show(getSupportFragmentManager(), "dialogue");
            }
        });

        // Button back
        Button buttonBack = findViewById(R.id.button_back);
        buttonBack.setOnClickListener(view -> {
            finish();
        });
    }
}