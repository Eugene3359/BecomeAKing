package com.scipath.becomeaking;

import android.app.Application;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;

import com.scipath.becomeaking.model.Category;
import com.scipath.becomeaking.model.Personage;
import com.scipath.becomeaking.model.StatBonus;
import com.scipath.becomeaking.model.StatBonusesMap;
import com.scipath.becomeaking.view.fragment.DialogueFragment;

import java.util.ArrayList;


public class BecomeAKing extends Application {

    // Fields
    private static BecomeAKing instance;
    private boolean isDebug = true;
    private int day;
    private Personage currentPersonage;
    private ArrayList<Category> currentCategories;


    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
        day = 0;
    }

    public static BecomeAKing getInstance() {
        return instance;
    }


    // Accessors
    public boolean isDebug() {
        return isDebug;
    }

    public int getDay() {
        return day;
    }

    public Personage getCurrentPersonage() {
        return currentPersonage;
    }

    public ArrayList<Category> getCurrentCategories() {
        return currentCategories;
    }

    public ArrayList<Category> getCurrentCategoriesSublist(int fromIndex, int toIndex) {
        return new ArrayList<>(currentCategories.subList(fromIndex, toIndex));
    }

    public StatBonusesMap getCurrentStatBonuses () {
        StatBonusesMap statBonuses = new StatBonusesMap();
        for (Category category : currentCategories) {
            category.recalculateStats();
            StatBonusesMap categoryStatBonuses = category.getStatBonuses();
            for (StatBonus statBonusKey : categoryStatBonuses.getStatBonusesMap().keySet()) {
                statBonuses.addStatBonus(statBonusKey,
                        statBonuses.getStatBonusValue(statBonusKey)
                                + categoryStatBonuses.getStatBonusValue(statBonusKey));
            }
        }
        return statBonuses;
    }


    // Mutators
    public void setCurrentPersonage(Personage currentPersonage) {
        this.currentPersonage = currentPersonage;
    }

    public void setCurrentCategories (ArrayList<Category> categories) {
        currentCategories = categories;
    }


    // Methods
    public void nextDay() {
        day++;
        StatBonusesMap statBonuses = getCurrentStatBonuses();
        currentPersonage.affectHealth(statBonuses.getStatBonusValue(StatBonus.HealthPerDay));
        currentPersonage.affectReputation(statBonuses.getStatBonusValue(StatBonus.ReputationPerDay));
        currentPersonage.affectMoney(statBonuses.getStatBonusValue(StatBonus.CostPerDay));
    }

    public void checkPersonageForNegativeValues(AppCompatActivity activity) {

        if (currentPersonage.getHealth() <= 0) gameOver(0, activity);
        if (currentPersonage.getReputation() < 0) gameOver(1, activity);
        if (currentPersonage.getMoney() < 0) gameOver(2, activity);
    }

    public void gameOver(int code, AppCompatActivity activity) {
        // Forming message
        String message;
        switch (code) {
            case 0:
                message = getApplicationContext().getString(R.string.negative_health);
                break;
            case 1:
                message = getApplicationContext().getString(R.string.negative_reputation);
                break;
            case 2:
                message = getApplicationContext().getString(R.string.negative_money);
                break;
            default:
                message = "";
        }
        message += " " + getApplicationContext().getString(R.string.game_over);

        // Showing dialogue
        DialogueFragment dialogueFragmentStart = DialogueFragment.newInstance(message, R.string.ok);
        dialogueFragmentStart.show(activity.getSupportFragmentManager(), "dialogue");
        dialogueFragmentStart.setCallback(() ->
        {
            activity.finish();
        });
    }
}
