package com.scipath.becomeaking.model.item;

import android.content.Context;
import android.graphics.drawable.Drawable;

import androidx.appcompat.content.res.AppCompatResources;

import com.scipath.becomeaking.R;
import com.scipath.becomeaking.model.Personage;
import com.scipath.becomeaking.model.StatBonus;
import com.scipath.becomeaking.model.StatBonusesMap;

import java.io.Serializable;


public class Item implements IItem, Serializable {

    // Fields
    protected int nameId;
    protected int imageId;
    protected final int interactionNameId = R.id.buy;
    protected int cost;
    protected boolean bought;
    protected StatBonusesMap statBonuses;


    // Constructors
    public Item(int nameId, int imageId, int cost) {
        this.nameId = nameId;
        this.imageId = imageId;
        this.cost = cost;
        bought = false;
        statBonuses = new StatBonusesMap();
    }

    public Item(int nameId, int imageId, int cost, StatBonusesMap statBonuses) {
        this.nameId = nameId;
        this.imageId = imageId;
        this.cost = cost;
        bought = false;
        this.statBonuses = statBonuses;
    }


    // Accessors
    @Override
    public int getNameId() {
        return nameId;
    }

    @Override
    public int getImageId() {
        return imageId;
    }

    @Override
    public int getInteractionNameId() {
        return interactionNameId;
    }

    @Override
    public int getCost() {
        return cost;
    }

    @Override
    public boolean isBought() {
        return bought;
    }

    @Override
    public StatBonusesMap getStatBonuses() {
        return statBonuses;
    }


    // Mutators
    @Override
    public void setNameId(int nameId) {
        this.nameId = nameId;
    }

    @Override
    public void setImageId(int imageId) {
        this.imageId = imageId;
    }

    @Override
    public void setCost(int cost) {
        this.cost = cost;
    }

    @Override
    public void setBought(boolean isBought) {
        this.bought = isBought;
    }

    @Override
    public void setStatBonuses(StatBonusesMap statBonuses) {
        this.statBonuses = statBonuses;
    }


    // Methods
    /***
     * Item name resource accessor
     *
     * @return The String that contains the item name
     */

    @Override
    public String getName(Context context) {
        return context.getString(nameId);
    }

    /***
     * Item image resource accessor
     *
     * @return The Drawable that contains the item image
     */

    @Override
    public Drawable getImage(Context context) {
        return AppCompatResources.getDrawable(context, imageId);
    }

    @Override
    public int interact(Personage personage) {
        if (personage.getMoney() < cost) return -1; // Not enough money

        int personageStrength = personage.getLevel().getStrength();
        int strengthRequired = statBonuses.get(StatBonus.StrengthRequired);
        if (personageStrength < strengthRequired) return -2; // Not enough strength

        personage.affectMoney(-cost);
        personage.affectReputation(statBonuses.get(StatBonus.ReputationImpact));
        personage.recalculateStats();
        bought = true;
        return 0; // Item bought
    }
}
