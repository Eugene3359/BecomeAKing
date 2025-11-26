package com.scipath.becomeaking.contract.model;

import android.content.Context;
import android.graphics.drawable.Drawable;

import com.scipath.becomeaking.contract.callback.Callback;
import com.scipath.becomeaking.model.enums.Stat;

import java.io.Serializable;


public interface IItem<State extends Enum<State> & IItemState> extends Serializable {

    // Accessors
    int getId();

    int getNameId();

    int getImageId();

    IStats getStats();

    int getStat(Stat stat);

    State getState();

    int getInteractionNameId();

    ICategory getCategory();


    // Mutators
    void setNameId(int nameId);

    void setImageId(int imageId);

    void setStats(IStats stats);

    void setStat(Stat stat, int value);

    void setState(State state);

    void setOnStateChanged(Callback callback);

    void setCategory(ICategory category);


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
}
