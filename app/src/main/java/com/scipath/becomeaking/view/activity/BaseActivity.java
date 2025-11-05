package com.scipath.becomeaking.view.activity;

import android.content.Context;
import android.os.Bundle;
import android.view.View;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.scipath.becomeaking.R;
import com.scipath.becomeaking.manager.LocaleManager;
import com.scipath.becomeaking.view.fragment.DialogueFragment;


public abstract class BaseActivity extends AppCompatActivity {

    protected abstract int getLayoutId();

    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(LocaleManager.wrap(newBase));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(getLayoutId());
        View root = findViewById(R.id.main);
        if (root != null) {
            ViewCompat.setOnApplyWindowInsetsListener(root, (v, insets) -> {
                Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
                v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
                return insets;
            });
        }
    }

    public void showDialogue(DialogueFragment dialogue) {
        dialogue.show(getSupportFragmentManager(), "dialogue");
    }

    public void showNotification(int message) {
        DialogueFragment dialogueFragment = new DialogueFragment.Builder()
                .setHeader(R.string.notification)
                .setMessage(message)
                .setButton1(R.string.got_it, null)
                .build();
        showDialogue(dialogueFragment);
    }

    public void showNotification(String message) {
        DialogueFragment dialogueFragment = new DialogueFragment.Builder()
                .setHeader(R.string.notification)
                .setMessage(message)
                .setButton1(R.string.got_it, null)
                .build();
        showDialogue(dialogueFragment);
    }

    protected void showInDevelopmentNotification() {
        showNotification(R.string.in_development);
    }
}
