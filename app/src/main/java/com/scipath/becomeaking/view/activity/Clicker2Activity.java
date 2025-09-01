package com.scipath.becomeaking.view.activity;

import android.os.Bundle;
import android.widget.FrameLayout;

import androidx.appcompat.content.res.AppCompatResources;

import com.scipath.becomeaking.R;
import com.scipath.becomeaking.view.customview.CustomButton;

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
    protected void onCreate(Bundle savedInstanceState) {
        layoutId = R.layout.activity_clicker2;
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void onWorkStarted() {
        showDialogue(
                R.string.notification,
                R.string.instruction2,
                R.string.start,
                () -> {
                    changeCurrentButton();
                    startTimer();
                }
        );
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
            textViewMoneyEarned.setText(String.valueOf(moneyEarned));
            changeCurrentButton();
        });
    }

    private void initButtons() {
        frames = new FrameLayout[] {
                findViewById(R.id.frame_1),
                findViewById(R.id.frame_2),
                findViewById(R.id.frame_3),
                findViewById(R.id.frame_4),
                findViewById(R.id.frame_5),
                findViewById(R.id.frame_6),
                findViewById(R.id.frame_7),
                findViewById(R.id.frame_8),
                findViewById(R.id.frame_9)
        };

        buttons = new CustomButton[] {
                findViewById(R.id.button_1),
                findViewById(R.id.button_2),
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