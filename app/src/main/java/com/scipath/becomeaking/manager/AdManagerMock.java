package com.scipath.becomeaking.manager;

import androidx.appcompat.app.AppCompatActivity;

import com.scipath.becomeaking.R;
import com.scipath.becomeaking.view.dialogue.DialogueFragment;


public class AdManagerMock {

    public static void showAd(AppCompatActivity activity) {
        DialogueFragment dialogueFragment = new DialogueFragment.Builder()
                .addHeader(R.string.notification)
                .addMessage(R.string.ad)
                .addButton(R.string.got_it, null)
                .getDialogue();
        dialogueFragment.show(activity.getSupportFragmentManager(), "dialogue");
    }
}
