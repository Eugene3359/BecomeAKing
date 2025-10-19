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

    int getBackgroundDrawableId();

    IItem getItem(int index);

    List<IItem> getItems();

    IItem getSelectedItem();

    IStats getStats();

    boolean isSelectable();


    // Mutators
    void setNameId(int nameId);

    void setBackgroundDrawableId(int drawableId);

    ICategory addItem(IItem item);

    ICategory removeItem(IItem item);

    void setItems(List<IItem> items);

    void setSelectedItem(IItem item);

    void setSelectable(boolean isSelectable);


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

    boolean containsItem(IItem item);

    IItem getBestItem();
}
