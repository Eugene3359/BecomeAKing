package com.scipath.becomeaking.contract.model;

import android.content.Context;
import android.graphics.drawable.Drawable;

import com.scipath.becomeaking.model.Personage;

import java.io.Serializable;


public interface IItem extends Serializable {

    // Accessors
    int getId();
    int getNameId();

    int getImageId();

    int getInteractionNameId();

    int getCost();

    boolean isBought();

    IStats getStats();


    // Mutators
    void setNameId(int nameId);

    void setImageId(int imageId);

    void setCost(int cost);

    void setBought(boolean isBought);

    void setStats(IStats stats);


    // Methods
    /**
     Item name resource accessor
     *
     * @param context   The Context that provides access to resources
     * @return          The String that contains the item name
     */
    String getName(Context context);

    /**
     * Item image resource accessor
     *
     * @param context   The Context that provides access to resources
     * @return          The Drawable that contains the item image
     */
    Drawable getImage(Context context);

    /**
     * Item interaction name resource accessor
     *
     * @param context   The Context that provides access to resources
     * @return          The String that contains the item interaction name
     */
    String getInteractionName(Context context);

    int interact(Personage personage);
}
