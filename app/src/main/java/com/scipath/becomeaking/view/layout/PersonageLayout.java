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

    private ImageView imageViewHealthIncome;
    private TextView textViewHealthIncome;
    private ImageView imageViewReputationIncome;
    private TextView textViewReputationIncome;
    private ImageView imageViewMoneyIncome;
    private TextView textViewMoneyIncome;
    private ImageView imageViewPersonage;


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

        imageViewHealthIncome = findViewById(R.id.image_view_health_income);
        textViewHealthIncome = findViewById(R.id.text_view_health_income);
        imageViewReputationIncome = findViewById(R.id.image_view_reputation_income);
        textViewReputationIncome = findViewById(R.id.text_view_reputation_income);
        imageViewMoneyIncome = findViewById(R.id.image_view_money_income);
        textViewMoneyIncome = findViewById(R.id.text_view_money_income);

        imageViewPersonage = findViewById(R.id.image_view_personage);
    }

    public void updateAll() {
        updateStats();
        updateImage();
    }

    public void updateStats() {
        IStats statBonuses = BecomeAKing.getInstance().getCurrentStatBonuses();

        int statBonusValue = statBonuses.get(Stat.HealthPerDay);
        imageViewHealthIncome.setBackgroundColor(ContextCompat.getColor(getContext(),
                statBonusValue < 0 ? R.color.icon_red : R.color.icon_green));
        textViewHealthIncome.setText(String.valueOf(statBonusValue));

        statBonusValue = statBonuses.get(Stat.ReputationPerDay);
        imageViewReputationIncome.setBackgroundColor(ContextCompat.getColor(getContext(),
                statBonusValue < 0 ? R.color.icon_red : R.color.icon_green));
        textViewReputationIncome.setText(String.valueOf(statBonusValue));

        statBonusValue = statBonuses.get(Stat.CoinsPerDay);
        imageViewMoneyIncome.setBackgroundColor(ContextCompat.getColor(getContext(),
                statBonusValue < 0 ? R.color.icon_red : R.color.icon_green));
        textViewMoneyIncome.setText(String.valueOf(statBonusValue));
    }

    public void updateImage() {
        imageViewPersonage.setImageResource(BecomeAKing.getInstance()
                .getCategories().get(1).getImageId());
    }
}
