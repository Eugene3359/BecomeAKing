package com.scipath.becomeaking.model;

import android.content.Context;

import com.scipath.becomeaking.R;

import java.io.Serializable;

public enum Title implements Serializable {

    // Titles
    Rogue(R.string.rogue, 75),
    Villager(R.string.villager, 100),
    Bandit(R.string.bandit, 150);


    // Individual title fields
    private final int nameId;
    private final int maxHealth;


    // Constructor
    private Title(int nameId, int maxHealth) {
        this.nameId = nameId;
        this.maxHealth = maxHealth;
    }


    // Accessors
    public int getNameId() {
        return nameId;
    }

    public int getMaxHealth() {
        return maxHealth;
    }


    // Methods
    /**
     * Title name resource accessor
     *
     * @param context The Context that provides access to resources
     * @return The String that contain title name
     */
    public String getName(Context context) {
        return context.getString(nameId);
    }
}
