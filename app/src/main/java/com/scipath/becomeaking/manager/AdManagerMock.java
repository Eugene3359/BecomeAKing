package com.scipath.becomeaking.manager;

import androidx.appcompat.app.AppCompatActivity;

import com.scipath.becomeaking.R;
import com.scipath.becomeaking.view.fragment.DialogueFragment;


public class AdManagerMock {

    public static void showAd(AppCompatActivity activity) {
        DialogueFragment dialogueFragment = DialogueFragment.newInstance(R.string.notification, R.string.ad, R.string.got_it);
        dialogueFragment.show(activity.getSupportFragmentManager(), "dialogue");
    }
}
