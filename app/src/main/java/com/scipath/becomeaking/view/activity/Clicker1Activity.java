package com.scipath.becomeaking.view.activity;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.widget.ImageView;
import android.widget.TextView;

import com.scipath.becomeaking.BecomeAKing;
import com.scipath.becomeaking.R;
import com.scipath.becomeaking.model.enums.Stat;
import com.scipath.becomeaking.model.item.Work;
import com.scipath.becomeaking.view.fragment.DialogueFragment;
import com.scipath.becomeaking.view.view.CustomLinearLayout;


public class Clicker1Activity extends BaseActivity {

    // Variables
    protected static final int WORK_DURATION_MS = 10_000;
    protected static final int TICK_INTERVAL_MS = 1000;
    protected int moneyEarned = 0;
    protected int moneyPerClick = 0;

    // Views
    protected TextView textViewWork = null;
    protected ImageView imageViewWork = null;
    protected TextView textViewMoneyEarned = null;
    protected TextView textViewMoneyPerClick = null;
    protected TextView textViewTimer = null;


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
        textViewWork = findViewById(R.id.text_view_work);
        imageViewWork = findViewById(R.id.image_view_work);
        textViewMoneyEarned = findViewById(R.id.text_view_money_earned);
        textViewMoneyPerClick = findViewById(R.id.text_view_money_per_click);
        textViewTimer = findViewById(R.id.text_view_timer);

        textViewWork.setText(work.getNameId());
        imageViewWork.setImageResource(work.getImageId());
        imageViewWork.setContentDescription(work.getName(this));
        textViewMoneyEarned.setText(String.valueOf(moneyEarned));
        textViewMoneyPerClick.setText(String.valueOf(moneyPerClick));
        textViewTimer.setText(R.string._10);
    }

    protected void onWorkStarted() {
        setClicker();
        DialogueFragment dialogueFragment = new DialogueFragment.Builder()
                .setHeader(R.string.notification)
                .setMessage(R.string.instruction1)
                .setButton1(R.string.start, this::startTimer)
                .build();
        showDialogue(dialogueFragment);
    }

    private void setClicker() {
        CustomLinearLayout linearLayoutClick = findViewById(R.id.container_clicker);
        linearLayoutClick.setOnClickListener(view -> {
            moneyEarned += moneyPerClick;
            textViewMoneyEarned.setText(String.valueOf(moneyEarned));
        });
    }

    protected void startTimer() {
        new CountDownTimer(WORK_DURATION_MS, TICK_INTERVAL_MS) {
            public void onTick(long millisUntilFinished) {
                textViewTimer.setText(String.valueOf(millisUntilFinished / 1000));
            }
            public void onFinish() {
                onWorkCompleted();
            }
        }.start();
    }

    protected void onWorkCompleted() {
        DialogueFragment dialogueFragment = new DialogueFragment.Builder()
                .setHeader(R.string.notification)
                .setMessage(getString(R.string.you_have_earned_d_coins, moneyEarned))
                .setButton1(R.string.exit, () -> {
                    BecomeAKing.getInstance().getPersonage().affectMoney(moneyEarned);
                    finish();
                })
                .build();
        showDialogue(dialogueFragment);
    }
}