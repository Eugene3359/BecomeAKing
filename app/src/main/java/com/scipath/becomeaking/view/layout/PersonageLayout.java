package com.scipath.becomeaking.view.layout;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.core.content.ContextCompat;

import com.scipath.becomeaking.BecomeAKing;
import com.scipath.becomeaking.R;
import com.scipath.becomeaking.contract.model.IStats;
import com.scipath.becomeaking.model.enums.Stat;


public class PersonageLayout extends LinearLayout {

    private ImageView imageHealthIncome;
    private TextView textHealthIncome;
    private ImageView imageReputationIncome;
    private TextView textReputationIncome;
    private ImageView imageMoneyIncome;
    private TextView textMoneyIncome;
    private ImageView imagePersonage;


    public PersonageLayout(Context context) {
        super(context);
        init(context);
    }

    public PersonageLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public PersonageLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    private void init(Context context) {
        inflate(context, R.layout.layout_personage, this);

        imageHealthIncome = findViewById(R.id.image_health_income);
        textHealthIncome = findViewById(R.id.text_health_income);
        imageReputationIncome = findViewById(R.id.image_reputation_income);
        textReputationIncome = findViewById(R.id.text_reputation_income);
        imageMoneyIncome = findViewById(R.id.image_money_income);
        textMoneyIncome = findViewById(R.id.text_money_income);

        imagePersonage = findViewById(R.id.image_personage);
    }

    public void updateAll() {
        updateStats();
        updateImage();
    }

    public void updateStats() {
        IStats statBonuses = BecomeAKing.getInstance().getCurrentStatBonuses();

        int statBonusValue = statBonuses.get(Stat.HealthPerDay);
        imageHealthIncome.setBackgroundColor(ContextCompat.getColor(getContext(),
                statBonusValue < 0 ? R.color.icon_red : R.color.icon_green));
        textHealthIncome.setText(String.valueOf(statBonusValue));

        statBonusValue = statBonuses.get(Stat.ReputationPerDay);
        imageReputationIncome.setBackgroundColor(ContextCompat.getColor(getContext(),
                statBonusValue < 0 ? R.color.icon_red : R.color.icon_green));
        textReputationIncome.setText(String.valueOf(statBonusValue));

        statBonusValue = statBonuses.get(Stat.CoinsPerDay);
        imageMoneyIncome.setBackgroundColor(ContextCompat.getColor(getContext(),
                statBonusValue < 0 ? R.color.icon_red : R.color.icon_green));
        textMoneyIncome.setText(String.valueOf(statBonusValue));
    }

    public void updateImage() {
        imagePersonage.setImageResource(BecomeAKing.getInstance()
                .getCategories().get(1).getImageId());
    }
}
