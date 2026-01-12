package com.scipath.becomeaking.view.activity;

import android.os.Bundle;
import android.widget.FrameLayout;

import androidx.appcompat.content.res.AppCompatResources;

import com.scipath.becomeaking.R;
import com.scipath.becomeaking.view.dialogue.DialogueFragment;
import com.scipath.becomeaking.view.view.CustomButton;

import java.util.Random;


public class Clicker2Activity extends Clicker1Activity {

    // Variables
    private int currentNumber = 0;
    private final Random random = new Random();

    // Views
    private FrameLayout[] frames;
    private CustomButton[] buttons;
    private FrameLayout currentFrame = null;
    private CustomButton currentButton = null;


    @Override
    protected int getLayoutId() {
        return R.layout.activity_clicker2;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void onWorkStarted() {
        DialogueFragment dialogueFragment = new DialogueFragment.Builder()
                .addHeader(R.string.notification)
                .addMessage(R.string.instruction2)
                .addButton(R.string.start, d -> {
                    changeCurrentButton();
                    startTimer();
                })
                .getDialogue();
        showDialogue(dialogueFragment);
    }

    private void changeCurrentButton() {
        if (currentNumber == 0) {
            initButtons();
        } else {
            currentFrame.setBackground(null);
            currentButton.setOnClickListener(null);
        }

        currentNumber = random.nextInt(9) + 1;
        currentFrame = frames[currentNumber - 1];
        currentButton = buttons[currentNumber - 1];

        currentFrame.setBackground(AppCompatResources.getDrawable(this, R.drawable.shape_green_glow));
        currentButton.setOnClickListener(view -> {
            moneyEarned += moneyPerClick;
            textMoneyEarned.setText(String.valueOf(moneyEarned));
            changeCurrentButton();
        });
    }

    private void initButtons() {
        frames = new FrameLayout[] {
                findViewById(R.id.frame1),
                findViewById(R.id.frame2),
                findViewById(R.id.frame3),
                findViewById(R.id.frame4),
                findViewById(R.id.frame5),
                findViewById(R.id.frame6),
                findViewById(R.id.frame7),
                findViewById(R.id.frame8),
                findViewById(R.id.frame9)
        };

        buttons = new CustomButton[] {
                findViewById(R.id.button1),
                findViewById(R.id.button2),
                findViewById(R.id.button_3),
                findViewById(R.id.button_4),
                findViewById(R.id.button_5),
                findViewById(R.id.button_6),
                findViewById(R.id.button_7),
                findViewById(R.id.button_8),
                findViewById(R.id.button_9)
        };
    }
}