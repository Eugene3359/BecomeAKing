package com.scipath.becomeaking.view.activity;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.content.res.AppCompatResources;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.scipath.becomeaking.BecomeAKing;
import com.scipath.becomeaking.R;
import com.scipath.becomeaking.model.StatBonus;
import com.scipath.becomeaking.model.item.IItem;
import com.scipath.becomeaking.view.customview.CustomButton;
import com.scipath.becomeaking.view.fragment.DialogueFragment;

import java.util.Random;

public class Clicker2Activity extends AppCompatActivity {

    // Variables
    private int moneyEarned = 0;
    private int moneyPerClick = 0;
    private int currentNumber = 0;
    private TextView textViewMoneyEarned = null;
    private FrameLayout currentFrame = null;
    private CustomButton currentButton = null;
    private final Random random = new Random();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_clicker2);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // Getting item
        IItem item = (IItem) getIntent().getSerializableExtra("item");
        moneyPerClick = item.getStatBonuses().get(StatBonus.MoneyPerClick);

        // Views
        TextView textViewWork = findViewById(R.id.text_view_work);
        textViewMoneyEarned = findViewById(R.id.text_view_money_earned);
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
        DialogueFragment dialogueFragmentStart = DialogueFragment.newInstance(R.string.instruction2, R.string.start);
        dialogueFragmentStart.setCallback(() ->
        {
            changeCurrentButton();

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
    }

    public void changeCurrentButton() {
        if (currentNumber != 0) {
            currentFrame.setBackground(null);
            currentButton.setOnClickListener(null);
        }

        currentNumber = random.nextInt(9) + 1;

        switch (currentNumber) {
            case 1:
                currentFrame = findViewById(R.id.frame_1);
                currentButton = findViewById(R.id.button_1);
                break;
            case 2:
                currentFrame = findViewById(R.id.frame_2);
                currentButton = findViewById(R.id.button_2);
                break;
            case 3:
                currentFrame = findViewById(R.id.frame_3);
                currentButton = findViewById(R.id.button_3);
                break;
            case 4:
                currentFrame = findViewById(R.id.frame_4);
                currentButton = findViewById(R.id.button_4);
                break;
            case 5:
                currentFrame = findViewById(R.id.frame_5);
                currentButton = findViewById(R.id.button_5);
                break;
            case 6:
                currentFrame = findViewById(R.id.frame_6);
                currentButton = findViewById(R.id.button_6);
                break;
            case 7:
                currentFrame = findViewById(R.id.frame_7);
                currentButton = findViewById(R.id.button_7);
                break;
            case 8:
                currentFrame = findViewById(R.id.frame_8);
                currentButton = findViewById(R.id.button_8);
                break;
            case 9:
                currentFrame = findViewById(R.id.frame_9);
                currentButton = findViewById(R.id.button_9);
                break;
        }

        currentFrame.setBackground(AppCompatResources.getDrawable(this, R.drawable.shape_green_glow));
        currentButton.setOnClickListener(view -> {
            moneyEarned += moneyPerClick;
            textViewMoneyEarned.setText(Float.toString(moneyEarned));

            changeCurrentButton();
        });
    }
}