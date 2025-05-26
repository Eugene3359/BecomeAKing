package com.scipath.becomeaking;

import android.app.Application;

import com.scipath.becomeaking.model.Category;
import com.scipath.becomeaking.model.Personage;
import com.scipath.becomeaking.model.StatBonus;
import com.scipath.becomeaking.model.StatBonusesMap;

import java.util.ArrayList;


public class BecomeAKing extends Application {

    // Fields
    private static BecomeAKing instance;
    private Personage currentPersonage;
    private ArrayList<Category> currentCategories;


    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
    }

    public static BecomeAKing getInstance() {
        return instance;
    }

    public Personage getCurrentPersonage() {
        return currentPersonage;
    }

    public void setCurrentPersonage(Personage currentPersonage) {
        this.currentPersonage = currentPersonage;
    }

    public ArrayList<Category> getCurrentCategories() {
        return currentCategories;
    }

    public ArrayList<Category> getCurrentCategoriesSublist(int fromIndex, int toIndex) {
        return new ArrayList<>(currentCategories.subList(fromIndex, toIndex));
    }

    public void setCurrentCategories (ArrayList<Category> categories) {
        currentCategories = categories;
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
}
