package com.scipath.becomeaking.view.activity;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.widget.ImageView;
import android.widget.TextView;

import com.scipath.becomeaking.BecomeAKing;
import com.scipath.becomeaking.R;
import com.scipath.becomeaking.model.enums.Stat;
import com.scipath.becomeaking.model.item.Work;
import com.scipath.becomeaking.view.dialogue.DialogueFragment;
import com.scipath.becomeaking.view.view.CustomLinearLayout;


public class Clicker1Activity extends BaseActivity {

    // Variables
    protected static final int WORK_DURATION_MS = 10_000;
    protected static final int TICK_INTERVAL_MS = 1000;
    protected int moneyEarned = 0;
    protected int moneyPerClick = 0;

    // Views
    protected TextView textWork = null;
    protected ImageView imageWork = null;
    protected TextView textMoneyEarned = null;
    protected TextView textMoneyPerClick = null;
    protected TextView textTimer = null;


    @Override
    protected int getLayoutId() {
        return R.layout.activity_clicker1;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Getting work
        Work work = (Work) getIntent().getSerializableExtra("work");
        moneyPerClick = work.getStats().get(Stat.MoneyPerClick);

        setViews(work);
        onWorkStarted();
    }

    protected void setViews(Work work) {
        textWork = findViewById(R.id.text_work);
        imageWork = findViewById(R.id.image_work);
        textMoneyEarned = findViewById(R.id.text_money_earned);
        textMoneyPerClick = findViewById(R.id.text_money_per_click);
        textTimer = findViewById(R.id.text_timer);

        textWork.setText(work.getNameId());
        imageWork.setImageResource(work.getImageId());
        imageWork.setContentDescription(work.getName(this));
        textMoneyEarned.setText(String.valueOf(moneyEarned));
        textMoneyPerClick.setText(String.valueOf(moneyPerClick));
        textTimer.setText(R.string._10);
    }

    protected void onWorkStarted() {
        setClicker();
        DialogueFragment dialogueFragment = new DialogueFragment.Builder()
                .addHeader(R.string.notification)
                .addMessage(R.string.instruction1)
                .addButton(R.string.start, d -> startTimer())
                .getDialogue();
        showDialogue(dialogueFragment);
    }

    private void setClicker() {
        CustomLinearLayout linearLayoutClick = findViewById(R.id.container_clicker);
        linearLayoutClick.setOnClickListener(view -> {
            moneyEarned += moneyPerClick;
            textMoneyEarned.setText(String.valueOf(moneyEarned));
        });
    }

    protected void startTimer() {
        new CountDownTimer(WORK_DURATION_MS, TICK_INTERVAL_MS) {
            public void onTick(long millisUntilFinished) {
                textTimer.setText(String.valueOf(millisUntilFinished / 1000));
            }
            public void onFinish() {
                onWorkCompleted();
            }
        }.start();
    }

    protected void onWorkCompleted() {
        DialogueFragment dialogueFragment = new DialogueFragment.Builder()
                .addHeader(R.string.notification)
                .addMessage(getString(R.string.you_have_earned_d_coins, moneyEarned))
                .addButton(R.string.exit, d -> {
                    BecomeAKing.getInstance().getPersonage().affectMoney(moneyEarned);
                    finish();
                })
                .getDialogue();
        showDialogue(dialogueFragment);
    }
}