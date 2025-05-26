package com.scipath.becomeaking.model;

import android.content.Context;
import android.graphics.drawable.Drawable;

import androidx.appcompat.content.res.AppCompatResources;

import com.scipath.becomeaking.R;

import java.io.Serializable;


public enum StatBonus implements Serializable {

    // List of stat bonuses
    HealthPerDay(R.string.health, R.string.c_d_per_day, R.drawable.icon_heart),
    ReputationPerDay(R.string.reputation, R.string.c_d_per_day, R.drawable.icon_civic_crown),
    CostPerDay(R.string.money, R.string.c_d_per_day, R.drawable.icon_coin);


    // Individual stat bonus fields
    private final int nameId;
    private final int descriptionId;
    private final int iconId;


    // Constructor
    /**
     * Enum constructor
     *
     * @param nameId        int is resource string id for stat name
     * @param descriptionId int is resource string id for stat bonus description
     * @param iconId        int is resource drawable id for stat bonus icon
     */
    private StatBonus(int nameId, int descriptionId, int iconId) {
        this.nameId = nameId;
        this.descriptionId = descriptionId;
        this.iconId = iconId;
    }


    // Accessors
    public int getNameId() {
        return nameId;
    }

    public int getDescriptionId() {
        return descriptionId;
    }

    public int getIconId() {
        return iconId;
    }


    // Methods
    /**
     * StatBonus name accessor
     *
     * @param context The Context that provides access to resources
     * @return The String that contain stat bonus name
     */
    public String getName(Context context) {
        return context.getString(nameId);
    }

    /**
     * StatBonus description accessor
     *
     * @param value     The int that contains value of the stat bonus
     * @param context   The Context that provides access to resources
     * @return The String that contain stat bonus description
     */
    public String getDescription(int value, Context context) {
        char sign = value < 0 ? '-' : '+';
        return context.getString(descriptionId, sign, Math.abs(value));
    }

    /**
     * StatBonus icon resource accessor
     *
     * @param context The Context that provides access to resources
     * @return The Drawable that contain stat bonus icon
     */
    public Drawable getIcon(Context context) {
        return AppCompatResources.getDrawable(context, iconId);
    }
}
