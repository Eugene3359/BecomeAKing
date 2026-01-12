package com.scipath.becomeaking.view.dialogue;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.android.material.slider.Slider;
import com.scipath.becomeaking.R;
import com.scipath.becomeaking.contract.callback.DialogueCallback;
import com.scipath.becomeaking.view.view.CustomButton;

import java.util.ArrayList;


public class DialogueFragment extends DialogFragment {

    private int headerId;
    private LinearLayout layoutContainer;
    private LinearLayout inputLayout;
    private LinearLayout lastButtonContainer;
    private final ArrayList<DialogueItem> items;


    public static class Builder {
        private final DialogueFragment dialogue;

        public Builder() {
            dialogue = new DialogueFragment();
        }

        public Builder addHeader(int headerId) {
            dialogue.headerId = headerId;
            return this;
        }

        public Builder addMessage(int messageId) {
            dialogue.items.add(new DialogueItem.Message(messageId));
            return this;
        }

        public Builder addMessage(String message) {
            dialogue.items.add(new DialogueItem.Message(message));
            return this;
        }

        public Builder addButton(int textId, @Nullable DialogueCallback callback) {
            dialogue.items.add(new DialogueItem.Button(textId, callback));
            return this;
        }

        public Builder addInput(int maxValue) {
            if (maxValue > 0) {
                dialogue.items.add(new DialogueItem.Input(maxValue));
            }
            return this;
        }

        public DialogueFragment getDialogue() {
            return dialogue;
        }
    }

    public DialogueFragment(){
        super();
        items = new ArrayList<>();
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
        View view = inflater.inflate(R.layout.fragment_dialogue, container, false);

        // Header
        LinearLayout layoutHeader = view.findViewById(R.id.layout_header);
        TextView textHeader = view.findViewById(R.id.text_header);
        if (headerId != 0) {
            layoutHeader.setVisibility(View.VISIBLE);
            textHeader.setText(headerId);
        } else {
            layoutHeader.setVisibility(View.GONE);
        }

        // Content
        layoutContainer = view.findViewById(R.id.layout_container);
        for (DialogueItem item : items) {
            if (item instanceof DialogueItem.Message) {
                addMessage((DialogueItem.Message) item);
            }
            if (item instanceof DialogueItem.Button) {
                addButton((DialogueItem.Button) item);
            }
            if (item instanceof DialogueItem.Input) {
                addInput((DialogueItem.Input) item);
            }
        }

        return view;
    }

    private void addMessage(DialogueItem.Message messageItem) {
        TextView textView = (TextView) getLayoutInflater()
                .inflate(R.layout.dialogue_message, layoutContainer, false);
        if (messageItem.messageId != 0) {
            textView.setText(messageItem.messageId);
        } else if (messageItem.message != null) {
            textView.setText(messageItem.message);
        }
        layoutContainer.addView(textView);
    }

    private void addButton(DialogueItem.Button buttonItem) {
        if (lastButtonContainer == null) {
            lastButtonContainer = new LinearLayout(getContext());
            lastButtonContainer.setGravity(Gravity.CENTER);
            layoutContainer.addView(lastButtonContainer);
        }
        CustomButton button = (CustomButton) getLayoutInflater()
                .inflate(R.layout.dialogue_button, layoutContainer, false);
        if (buttonItem.textId != 0) button.setText(buttonItem.textId);
        button.setOnClickListener(v -> {
            if (buttonItem.callback != null) {
                buttonItem.callback.call(this);
            }
            dismiss();
        });
        lastButtonContainer.addView(button);
    }

    private void addInput(DialogueItem.Input inputItem) {
        if (inputLayout != null) return;
        inputLayout = (LinearLayout) getLayoutInflater()
                .inflate(R.layout.dialogue_input_layout, layoutContainer, false);

        Slider sliderAmount = inputLayout.findViewById(R.id.slider_amount);
        EditText inputAmount = inputLayout.findViewById(R.id.input_amount);
        sliderAmount.setValueFrom(0f);
        sliderAmount.setValueTo(inputItem.maxValue);
        sliderAmount.setStepSize(1f);

        final boolean[] isUpdating = {false};

        sliderAmount.addOnChangeListener(new Slider.OnChangeListener() {
            @Override
            public void onValueChange(@NonNull Slider slider, float value, boolean fromUser) {
                if (!fromUser || isUpdating[0]) return;
                isUpdating[0] = true;
                inputAmount.setText(String.valueOf((int) value));
                inputAmount.setSelection(inputAmount.getText().length());
                isUpdating[0] = false;
            }
        });

        inputAmount.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {}

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if (isUpdating[0]) return;

                if (charSequence == null || charSequence.length() == 0) {
                    sliderAmount.setValue(0f);
                    return;
                }

                if (charSequence.length() > 1 && charSequence.charAt(0) == '0') {
                    charSequence = charSequence.subSequence(1, charSequence.length());
                    inputAmount.setText(charSequence);
                    return;
                }

                try {
                    int value = Integer.parseInt(charSequence.toString());
                    if (value > inputItem.maxValue) {
                        inputAmount.setText(String.valueOf(inputItem.maxValue));
                    } else {
                        isUpdating[0] = true;
                        sliderAmount.setValue(value);
                        isUpdating[0] = false;
                    }
                } catch (NumberFormatException ignored) {}
            }

            @Override
            public void afterTextChanged(Editable editable) {}
        });

        layoutContainer.addView(inputLayout);
    }

    public int getInputValue() {
        if (inputLayout == null) return 0;
        EditText inputAmount = inputLayout.findViewById(R.id.input_amount);
        String input = inputAmount.getText().toString();
        if (input.isEmpty()) return 0;
        return Integer.parseInt(input);
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