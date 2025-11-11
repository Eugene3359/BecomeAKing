package com.scipath.becomeaking.view.fragment;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.scipath.becomeaking.R;
import com.scipath.becomeaking.contract.callback.Callback;


public class DialogueFragment extends DialogFragment {

    private int headerId;
    private int messageId;
    private String message;
    private int button1TextId;
    private int button2TextId;
    private Callback callback1;
    private Callback callback2;


    public static class Builder {
        private int headerId;
        private int messageId;
        private String message;
        private int button1TextId;
        private int button2TextId;
        private Callback callback1;
        private Callback callback2;

        public Builder setHeader(int headerId) {
            this.headerId = headerId;
            return this;
        }

        public Builder setMessage(int messageId) {
            this.messageId = messageId;
            return this;
        }

        public Builder setMessage(String message) {
            this.message = message;
            return this;
        }

        public Builder setButton1(int textId, @Nullable Callback callback) {
            this.button1TextId = textId;
            this.callback1 = callback;
            return this;
        }

        public Builder setButton2(int textId, @Nullable Callback callback) {
            this.button2TextId = textId;
            this.callback2 = callback;
            return this;
        }

        public DialogueFragment build() {
            DialogueFragment dialogue = new DialogueFragment();
            dialogue.headerId = headerId;
            dialogue.messageId = messageId;
            dialogue.message = message;
            dialogue.button1TextId = button1TextId;
            dialogue.button2TextId = button2TextId;
            dialogue.callback1 = callback1;
            dialogue.callback2 = callback2;
            return dialogue;
        }
    }


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setCancelable(false);
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_dialogue2, container, false);

        // Header
        TextView textViewHeader = view.findViewById(R.id.text_view_header);
        if (headerId != 0) {
            textViewHeader.setText(headerId);
        } else {
            view.findViewById(R.id.layout_header).setVisibility(View.GONE);
        }

        // Message
        TextView textViewMessage = view.findViewById(R.id.text_view_message);
        if(messageId != 0) {
            textViewMessage.setText(messageId);
        } else if (message != null) {
            textViewMessage.setText(message);
        } else {
            textViewMessage.setVisibility(View.GONE);
        }

        // Buttons
        setupButton(view.findViewById(R.id.button_1), button1TextId, callback1);
        setupButton(view.findViewById(R.id.button_2), button2TextId, callback2);

        return view;
    }

    private void setupButton(Button button, int textId, @Nullable Callback callback) {
        if (button == null) return;
        if (textId != 0) {
            button.setText(textId);
            button.setOnClickListener(v -> {
                if (callback != null) callback.call();
                dismiss();
            });
        } else {
            button.setVisibility(View.GONE);
        }
    }

    @Override
    public void onStart() {
        super.onStart();
        if (getDialog() != null && getDialog().getWindow() != null) {
            getDialog().getWindow().setLayout(
                    ViewGroup.LayoutParams.MATCH_PARENT,
                    ViewGroup.LayoutParams.WRAP_CONTENT
            );
            getDialog().getWindow().setBackgroundDrawableResource(android.R.color.transparent);
        }
    }
}