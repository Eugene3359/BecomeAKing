package com.scipath.becomeaking.view.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;

import com.scipath.becomeaking.R;
import com.scipath.becomeaking.view.dialogue.DialogueFragment;


public abstract class BaseFragment extends Fragment {

    protected abstract int getLayoutId();

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(getLayoutId(), container, false);
    }

    protected void showDialogue(DialogueFragment dialogue) {
        dialogue.show(getParentFragmentManager(), "dialogue");
    }

    protected void showNotification(int message) {
        DialogueFragment dialogueFragment = new DialogueFragment.Builder()
                .addHeader(R.string.notification)
                .addMessage(message)
                .addButton(R.string.got_it, null)
                .getDialogue();
        showDialogue(dialogueFragment);
    }

    protected void showNotification(String message) {
        DialogueFragment dialogueFragment = new DialogueFragment.Builder()
                .addHeader(R.string.notification)
                .addMessage(message)
                .addButton(R.string.got_it, null)
                .getDialogue();
        showDialogue(dialogueFragment);
    }

    protected void showInDevelopmentNotification() {
        showNotification(R.string.in_development);
    }
}
