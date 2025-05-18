package com.scipath.becomeaking.model;

import android.content.Context;

import com.scipath.becomeaking.R;

import java.io.Serializable;

public enum Title implements Serializable {

    // Titles
    Rogue(R.string.rogue),
    Villager(R.string.villager),
    Bandit(R.string.bandit);


    // Individual title fields
    private final int nameId;


    // Constructor
    private Title(int nameId) {
        this.nameId = nameId;
    }


    // Accessors
    public int getNameId() {
        return nameId;
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
