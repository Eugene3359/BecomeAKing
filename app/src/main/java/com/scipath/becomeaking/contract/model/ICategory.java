package com.scipath.becomeaking.contract.model;

import android.content.Context;
import android.graphics.drawable.Drawable;

import com.scipath.becomeaking.model.enums.StatsMod;

import java.io.Serializable;
import java.util.List;


public interface ICategory extends Serializable {

    // Accessors
    int getId();

    int getNameId();

    int getImageId();

    List<IItem> getItems();

    StatsMod getStatsMod();

    IStats getStats();


    // Mutators
    void setNameId(int nameId);

    void setItems(List<IItem> items);

    ICategory addItem(IItem item);

    void setStatsMod(StatsMod statsMod);


    // Methods
    /**
     * Category name resource accessor
     *
     * @param context   The Context that provides access to resources
     * @return          The String that contains the category name
     */
    String getName(Context context);

    /**
     * Category image resource accessor
     *
     * @param context   The Context that provides access to resources
     * @return          The Drawable that contains the category image
     */
    Drawable getImage(Context context);

    IItem getBestBoughtItem();
}
