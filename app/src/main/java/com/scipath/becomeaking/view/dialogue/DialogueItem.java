package com.scipath.becomeaking.view.dialogue;

import com.scipath.becomeaking.contract.callback.DialogueCallback;


public abstract class DialogueItem {

    public static class Message extends DialogueItem {
        final int messageId;
        final String message;

        public Message(int messageId) {
            this.messageId = messageId;
            this.message = null;
        }

        public Message(String message) {
            this.messageId = 0;
            this.message = message;
        }
    }


    public static class Button extends DialogueItem {
        final int textId;
        final DialogueCallback callback;

        public Button(int textId, DialogueCallback callback) {
            this.textId = textId;
            this.callback = callback;
        }
    }


    public static class Input extends DialogueItem {
        final int maxValue;

        public Input(int maxValue) {
            this.maxValue = maxValue;
        }
    }
}
