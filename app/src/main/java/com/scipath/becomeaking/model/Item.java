package com.scipath.becomeaking.model;

import android.content.Context;
import android.graphics.drawable.Drawable;

import androidx.appcompat.content.res.AppCompatResources;

import java.io.Serializable;


public class Item implements Serializable {

    // Fields
    private int nameId;
    private int imageId;
    private int cost;
    private boolean bought;
    private StatBonusesMap statBonuses;

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
    public int getNameId() {
        return nameId;
    }

    public int getImageId() {
        return imageId;
    }

    public int getCost() {
        return cost;
    }

    public boolean isBought() {
        return bought;
    }

    public StatBonusesMap getStatBonuses() {
        return statBonuses;
    }


    // Mutators
    public void setNameId(int nameId) {
        this.nameId = nameId;
    }

    public void setImageId(int imageId) {
        this.imageId = imageId;
    }

    public void setCost(int cost) {
        this.cost = cost;
    }

    public void setBought(boolean bought) {
        this.bought = bought;
    }

    public void setStatBonuses(StatBonusesMap statBonuses) {
        this.statBonuses = statBonuses;
    }


    // Methods
    /***
     * Item name resource accessor
     *
     * @return The String that contains the item name
     */
    public String getName(Context context) {
        return context.getString(nameId);
    }

    /***
     * Item image resource accessor
     *
     * @return The Drawable that contains the item image
     */
    public Drawable getImage(Context context) {
        return AppCompatResources.getDrawable(context, imageId);
    }
}
