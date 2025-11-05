package com.scipath.becomeaking.view.fragment;

import androidx.fragment.app.Fragment;

import com.scipath.becomeaking.R;


public class BaseFragment extends Fragment {

    protected void showDialogue(DialogueFragment dialogue) {
        dialogue.show(getParentFragmentManager(), "dialogue");
    }

    protected void showNotification(int message) {
        DialogueFragment dialogueFragment = new DialogueFragment.Builder()
                .setHeader(R.string.notification)
                .setMessage(message)
                .setButton1(R.string.got_it, null)
                .build();
        showDialogue(dialogueFragment);
    }

    protected void showNotification(String message) {
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
