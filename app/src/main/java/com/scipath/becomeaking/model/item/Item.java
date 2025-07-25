package com.scipath.becomeaking.model.item;

import android.content.Context;
import android.graphics.drawable.Drawable;

import androidx.appcompat.content.res.AppCompatResources;

import com.scipath.becomeaking.R;
import com.scipath.becomeaking.contract.model.IItem;
import com.scipath.becomeaking.contract.model.IStats;
import com.scipath.becomeaking.model.Stats;
import com.scipath.becomeaking.model.Personage;
import com.scipath.becomeaking.model.enums.Stat;


public class Item implements IItem {

    // Fields
    protected static int idCounter = 0;

    protected int id;
    protected int nameId;
    protected int imageId;
    protected final int interactionNameId = R.id.buy;
    protected int cost;
    protected boolean bought;
    protected IStats stats;


    // Constructors
    public Item(int nameId, int imageId, int cost) {
        id = idCounter++;
        this.nameId = nameId;
        this.imageId = imageId;
        this.cost = cost;
        bought = false;
        stats = new Stats();
    }

    public Item(int nameId, int imageId, int cost, IStats stats) {
        id = idCounter++;
        this.nameId = nameId;
        this.imageId = imageId;
        this.cost = cost;
        bought = false;
        this.stats = stats;
    }


    // Accessors
    @Override
    public int getId() {
        return id;
    }

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
    public IStats getStats() {
        return stats;
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
    public void setStats(IStats stats) {
        this.stats = stats;
    }


    // Methods
    @Override
    public String getName(Context context) {
        return context.getString(nameId);
    }

    @Override
    public Drawable getImage(Context context) {
        return AppCompatResources.getDrawable(context, imageId);
    }

    @Override
    public String getInteractionName(Context context) {
        return context.getString(interactionNameId);
    }

    @Override
    public int interact(Personage personage) {
        // Check for money
        if (personage.getMoney() < cost) return -1; // Not enough money

        // Check for strength
        int personageStrength = personage.getLevel().getStrength();
        int strengthRequired = stats.get(Stat.StrengthRequired);
        if (personageStrength < strengthRequired) return -2; // Not enough strength

        // Buy
        personage.affectMoney(-cost);
        personage.affectReputation(stats.get(Stat.ReputationImpact));
        personage.recalculateStats();
        bought = true;
        return 0; // Item bought
    }
}
