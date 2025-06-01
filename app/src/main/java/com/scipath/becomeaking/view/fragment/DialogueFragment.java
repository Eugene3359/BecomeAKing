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
import com.scipath.becomeaking.adapter.Callback;


public class DialogueFragment extends DialogFragment {

    private int messageId;
    private int buttonTextId;
    private Callback callback;

    public static DialogueFragment newInstance(int messageId, int buttonTextId) {
        DialogueFragment fragment = new DialogueFragment();
        Bundle args = new Bundle();
        args.putInt("messageId", messageId);
        args.putInt("buttonTextId", buttonTextId);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            messageId = getArguments().getInt("messageId");
            buttonTextId = getArguments().getInt("buttonTextId");
        }
        setCancelable(false);
    }

    public void setCallback(Callback callback) {
        this.callback = callback;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_dialogue, container, false);

        // Message TextView
        TextView textViewMessage = view.findViewById(R.id.text_view_message);
        textViewMessage.setText(messageId);

        // Button
        Button button = view.findViewById(R.id.button_end_dialogue);
        button.setText(buttonTextId);
        button.setOnClickListener(v -> {
            if (callback != null) {
                callback.call();
            }
            dismiss();
        });

        return view;
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