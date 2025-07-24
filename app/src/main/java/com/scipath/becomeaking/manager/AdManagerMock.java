package com.scipath.becomeaking.manager;

import androidx.appcompat.app.AppCompatActivity;

import com.scipath.becomeaking.R;
import com.scipath.becomeaking.view.fragment.DialogueFragment;


public class AdManagerMock {

    public static void showAd(AppCompatActivity activity) {
        DialogueFragment dialogueFragment = DialogueFragment.newInstance(R.string.ad, R.string.ok);
        dialogueFragment.show(activity.getSupportFragmentManager(), "dialogue");
    }
}
