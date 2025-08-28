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
    private int buttonTextId;
    private Callback callback;


    public static DialogueFragment newInstance(int headerId, int messageId, int buttonTextId) {
        DialogueFragment fragment = new DialogueFragment();
        Bundle args = new Bundle();
        args.putInt("headerId", headerId);
        args.putInt("messageId", messageId);
        args.putInt("buttonTextId", buttonTextId);
        fragment.setArguments(args);
        return fragment;
    }

    public static DialogueFragment newInstance(int headerId, String message, int buttonTextId) {
        DialogueFragment fragment = new DialogueFragment();
        Bundle args = new Bundle();
        args.putInt("headerId", headerId);
        args.putString("message", message);
        args.putInt("buttonTextId", buttonTextId);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            headerId = getArguments().getInt("headerId");
            messageId = getArguments().getInt("messageId");
            message = getArguments().getString("message");
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