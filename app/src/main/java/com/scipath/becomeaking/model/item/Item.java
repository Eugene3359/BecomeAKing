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

import java.util.Objects;


public class Item implements IItem {

    // Fields
    protected static int idCounter = 0;

    protected int id;
    protected int nameId;
    protected int imageId;
    protected int interactionNameId;
    protected int interactionResultNameId;
    protected int cost;
    protected boolean bought;
    protected IStats stats;


    // Constructors
    public Item(int nameId, int imageId, int cost) {
        this.id = idCounter++;
        this.nameId = nameId;
        this.imageId = imageId;
        this.interactionNameId = R.string.buy_d;
        this.interactionResultNameId = R.string.bought;
        this.cost = cost;
        this.bought = false;
        this.stats = new Stats();
    }

    public Item(int nameId, int imageId, int cost, IStats stats) {
        this.id = idCounter++;
        this.nameId = nameId;
        this.imageId = imageId;
        this.interactionNameId = R.string.buy_d;
        this.interactionResultNameId = R.string.bought;
        this.cost = cost;
        this.bought = false;
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
    public int getInteractionResultNameId() {
        return interactionResultNameId;
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
        this.stats = Objects.requireNonNullElseGet(stats, Stats::new);
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
        return String.format(context.getString(interactionNameId), cost);
    }

    @Override
    public String getInteractionResultName(Context context) {
        return context.getString(interactionResultNameId);
    }

    @Override
    public int interact(Personage personage) {
        if (personage == null) return -10; // Null

        // Check for money
        if (personage.getMoney() < cost) return -1; // Not enough money

        // Check for strength
        int personageStrength = personage.getLevel().getStrength();
        int strengthRequired = stats.get(Stat.StrengthRequired);
        if (personageStrength < strengthRequired) return -2; // Not enough strength

        // Buy
        personage.affectMoney(-cost);
        personage.affectReputation(stats.get(Stat.ReputationImpact));
        bought = true;
        personage.recalculateStats();
        return 0; // Item bought
    }
}
