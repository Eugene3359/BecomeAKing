package com.scipath.becomeaking.manager;

import androidx.appcompat.app.AppCompatActivity;

import com.scipath.becomeaking.R;
import com.scipath.becomeaking.view.fragment.DialogueFragment;


public class AdManagerMock {

    public static void showAd(AppCompatActivity activity) {
        DialogueFragment dialogueFragment = new DialogueFragment.Builder()
                .setHeader(R.string.notification)
                .setMessage(R.string.ad)
                .setButton1(R.string.got_it, null)
                .build();
        dialogueFragment.show(activity.getSupportFragmentManager(), "dialogue");
    }
}
