package com.scipath.becomeaking.model.enums;

import android.content.Context;
import android.graphics.drawable.Drawable;

import androidx.appcompat.content.res.AppCompatResources;

import com.scipath.becomeaking.R;

import java.io.Serializable;


public enum Stat implements Serializable {

    // Stats
    // Persistent stats
    MaxHealth(R.string.health, R.string.maximum_amount_d, R.drawable.icon_heart),
    Might(R.string.might, R.string.c_d, R.drawable.icon_might),

    // Single stats change
    HealthImpact(R.string.health, R.string.c_d, R.drawable.icon_heart),
    ReputationImpact(R.string.reputation, R.string.c_d, R.drawable.icon_civic_crown),

    // Daily stats change
    HealthPerDay(R.string.health, R.string.c_d_per_day, R.drawable.icon_heart),
    ReputationPerDay(R.string.reputation, R.string.c_d_per_day, R.drawable.icon_civic_crown),
    CostPerDay(R.string.money, R.string.c_d_per_day, R.drawable.icon_coin),

    // Requirement stats
    ReputationRequired(R.string.reputation, R.string.required_reputation_d, 0),
    StrengthRequired(R.string.strength, R.string.required_strength_skill_points_d, 0),
    HorseAndWeaponRequired(R.string.horse_and_weapon_required, R.string.horse_and_weapon_required, 0),

    // Work stats
    MoneyPerClick(R.string.money, R.string.c_d_per_click, R.drawable.icon_coin);


    // Fields
    private final int nameId;
    private final int descriptionId;
    private final int iconId;


    // Constructor
    private Stat(int nameId, int descriptionId, int iconId) {
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
     * Stat name accessor
     *
     * @param context   The Context that provides access to resources
     * @return          The String that contain stat name
     */
    public String getName(Context context) {
        return context.getString(nameId);
    }

    /**
     * Stat description accessor
     *
     * @param value     The int that contains value of the stat
     * @param context   The Context that provides access to resources
     * @return          The String that contain stat description
     */
    public String getDescription(int value, Context context) {
        char sign = value < 0 ? '-' : '+';
        String formatted = context.getString(descriptionId);

        if (formatted.contains("%1") && formatted.contains("%2")) {
            return String.format(formatted, sign, Math.abs(value));
        } else {
            return String.format(formatted, value);
        }
    }

    /**
     * Stat icon resource accessor
     *
     * @param context   The Context that provides access to resources
     * @return          The Drawable that contain stat icon
     */
    public Drawable getIcon(Context context) {
        return AppCompatResources.getDrawable(context, iconId);
    }
}
