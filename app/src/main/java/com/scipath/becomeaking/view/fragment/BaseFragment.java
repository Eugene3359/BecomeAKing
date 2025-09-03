package com.scipath.becomeaking.view.fragment;

import androidx.fragment.app.Fragment;

public class BaseFragment extends Fragment {

    protected void showDialogue(int headerId, int messageId, int buttonTextId, Runnable callback) {
        DialogueFragment dialogueFragment = DialogueFragment.newInstance(
                headerId,
                messageId,
                buttonTextId);
        dialogueFragment.show(getParentFragmentManager(), "dialogue");
        if (callback != null)
            dialogueFragment.setCallback(callback::run);
    }

    protected void showDialogue(int headerId, String message, int buttonTextId, Runnable callback) {
        DialogueFragment dialogueFragmentResult = DialogueFragment.newInstance(
                headerId,
                message,
                buttonTextId);
        dialogueFragmentResult.show(getParentFragmentManager(), "dialogue");
        dialogueFragmentResult.setCallback(callback::run);
    }
}
