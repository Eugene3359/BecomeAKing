package com.scipath.becomeaking.model.item;

import android.content.Context;
import android.graphics.drawable.Drawable;

import com.scipath.becomeaking.model.Personage;
import com.scipath.becomeaking.model.StatBonusesMap;

import java.io.Serializable;


public interface IItem extends Serializable {

    // Accessors
    public int getNameId();

    public int getImageId();

    public int getInteractionNameId();

    public int getCost();

    public boolean isBought();

    public StatBonusesMap getStatBonuses();


    // Mutators
    public void setNameId(int nameId);

    public void setImageId(int imageId);

    public void setCost(int cost);

    public void setBought(boolean isBought);

    public void setStatBonuses(StatBonusesMap statBonuses);


    // Methods
    /***
     * Item name resource accessor
     *
     * @return The String that contains the item name
     */
    public String getName(Context context);

    /***
     * Item image resource accessor
     *
     * @return The Drawable that contains the item image
     */
    public Drawable getImage(Context context);

    public boolean interact(Personage personage);
}
