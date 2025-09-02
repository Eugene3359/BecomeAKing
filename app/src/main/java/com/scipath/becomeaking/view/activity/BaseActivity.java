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

    protected void showDialogue(int headerId, int messageId, int buttonTextId, Runnable callback) {
        DialogueFragment dialogueFragmentResult = DialogueFragment.newInstance(
                headerId,
                messageId,
                buttonTextId);
        dialogueFragmentResult.show(getSupportFragmentManager(), "dialogue");
        if (callback != null)
            dialogueFragmentResult.setCallback(callback::run);
    }

    protected void showDialogue(int headerId, String message, int buttonTextId, Runnable callback) {
        DialogueFragment dialogueFragmentResult = DialogueFragment.newInstance(
                headerId,
                message,
                buttonTextId);
        dialogueFragmentResult.show(getSupportFragmentManager(), "dialogue");
        dialogueFragmentResult.setCallback(callback::run);
    }
}
