package com.scipath.becomeaking.view.activity;

import android.content.Context;

import androidx.appcompat.app.AppCompatActivity;

import com.scipath.becomeaking.manager.LocaleManager;
import com.scipath.becomeaking.view.fragment.DialogueFragment;


public class BaseActivity extends AppCompatActivity {
    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(LocaleManager.wrap(newBase));
    }

    protected void showDialogue(int headerId, int messageId, int buttonTextId, Runnable callback) {
        DialogueFragment dialogueFragmentResult = DialogueFragment.newInstance(
                headerId,
                messageId,
                buttonTextId);
        dialogueFragmentResult.show(getSupportFragmentManager(), "dialogue");
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
