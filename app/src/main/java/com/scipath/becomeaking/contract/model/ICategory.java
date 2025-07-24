package com.scipath.becomeaking.contract.model;

import android.content.Context;
import android.graphics.drawable.Drawable;

import java.io.Serializable;
import java.util.List;


public interface ICategory extends Serializable {

    // Accessors
    int getId();

    int getNameId();

    int getImageId();

    List<IItem> getItems();

    IStats getStats();


    // Mutators
    void setNameId(int nameId);

    void setItems(List<IItem> items);

    ICategory addItem(IItem item);


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

    IItem getLastBoughtItem();
}
