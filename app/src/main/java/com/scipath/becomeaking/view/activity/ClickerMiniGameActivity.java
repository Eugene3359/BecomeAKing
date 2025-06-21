package com.scipath.becomeaking.view.activity;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.fragment.app.Fragment;

import com.scipath.becomeaking.BecomeAKing;
import com.scipath.becomeaking.R;
import com.scipath.becomeaking.model.item.IItem;
import com.scipath.becomeaking.model.StatBonus;
import com.scipath.becomeaking.view.customview.CustomLinearLayout;
import com.scipath.becomeaking.view.fragment.DialogueFragment;

public class ClickerMiniGameActivity extends AppCompatActivity {

    // Variables
    private int moneyEarned = 0;

    // Views variables
    private Fragment fragment;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_clicker_minigame);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // Getting item
        IItem item = (IItem) getIntent().getSerializableExtra("item");
        int moneyPerClick = item.getStatBonuses().get(StatBonus.MoneyPerClick);

        // Views
        TextView textViewWork = findViewById(R.id.text_view_work);
        TextView textViewMoneyEarned = findViewById(R.id.text_view_money_earned);
        ImageView imageViewWork = findViewById(R.id.image_view_work);
        TextView textViewMoneyPerClick = findViewById(R.id.text_view_money_per_click);
        TextView textViewTimer = findViewById(R.id.text_view_timer);

        // Setting Views values
        textViewWork.setText(item.getNameId());
        textViewMoneyEarned.setText(Float.toString(moneyEarned));
        imageViewWork.setImageResource(item.getImageId());
        imageViewWork.setContentDescription(item.getName(this));
        textViewMoneyPerClick.setText(Integer.toString(moneyPerClick));
        textViewTimer.setText("10");

        // Setting Dialog
        DialogueFragment dialogueFragmentStart = DialogueFragment.newInstance(R.string.instruction, R.string.start);
        dialogueFragmentStart.setCallback(() ->
        {
            new CountDownTimer(10000, 1000) {
                public void onTick(long millisUntilFinished) {
                    // Update timer display on tick
                    textViewTimer.setText(Long.toString(millisUntilFinished / 1000));
                }

                public void onFinish() {
                    DialogueFragment dialogueFragmentResult = DialogueFragment.newInstance(R.string.exit, R.string.exit);
                    dialogueFragmentResult.show(getSupportFragmentManager(), "dialogue");
                    dialogueFragmentResult.setCallback(() -> {
                        BecomeAKing.getInstance().getPersonage().affectMoney(moneyEarned);
                        finish();
                    });
                }
            }.start();
        });
        dialogueFragmentStart.show(getSupportFragmentManager(), "dialogue");

        // Clicker
        CustomLinearLayout linearLayoutClick = findViewById(R.id.linear_layout_click);
        linearLayoutClick.setOnClickListener(view -> {
            moneyEarned += moneyPerClick;
            textViewMoneyEarned.setText(Float.toString(moneyEarned));
        });
    }
}