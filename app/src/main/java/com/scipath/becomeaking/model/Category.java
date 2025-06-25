package com.scipath.becomeaking.model;

import android.content.Context;
import android.graphics.drawable.Drawable;

import androidx.appcompat.content.res.AppCompatResources;

import com.scipath.becomeaking.R;
import com.scipath.becomeaking.model.item.IItem;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;


public class Category implements Serializable {

    // Fields
    private static int counter = -1;
    private int id;
    private int nameId;
    private int imageId;
    private List<IItem> items;
    private StatBonusesMap statBonuses;


    // Constructor
    public Category(int nameId) {
        counter ++;
        this.id = counter;
        this.nameId = nameId;
        this.imageId = 0;
        this.items = new ArrayList<>();
        this.statBonuses = new StatBonusesMap();
    }


    // Accessors
    public int getId() {
        return id;
    }

    public int getNameId() {
        return nameId;
    }

    public int getImageId() {
        return imageId;
    }

    public List<IItem> getItems() {
        return items;
    }

    public StatBonusesMap getStatBonuses() {
        return statBonuses;
    }


    // Mutators
    public void setItems(List<IItem> items) {
        this.items = items;
        recalculateStats();
    }

    /***
     * Adds an item to the category
     *
     * @param item The Item to be added to the category
     */
    public Category addItem(IItem item) {
        this.items.add(item);
        recalculateStats();
        return this;
    }


    // Methods
    /***
     * Category name resource accessor
     *
     * @return The String that contains the category name
     */
    public String getName(Context context) {
        return context.getString(nameId);
    }

    /***
     * Category image resource accessor
     *
     * @return The Drawable that contains the category image
     */
    public Drawable getImage(Context context) {
        return AppCompatResources.getDrawable(context, imageId);
    }

    public IItem getLastBoughtItem() {
        IItem item;
        for (int i = items.size()-1; i >= 0; i--) {
            item = items.get(i);
            if (item.isBought()) return item;
        }
        return null;
    }

    /***
     * Updates the category-level stats based on the values of all items.
     * This method recalculates stats and sets the image from the last item
     * marked as bought.
     */
    public void recalculateStats() {
        if (items.isEmpty()) return;

        imageId = getLastBoughtItem() == null ?
                items.get(0).getImageId() :
                getLastBoughtItem().getImageId();
        statBonuses = new StatBonusesMap();

        if (nameId == R.string.nutrition | nameId == R.string.books) {
            for (IItem item : items) {
                if (item.isBought()) {
                    statBonuses.sum(item.getStatBonuses());
                }
            }
        } else {
            IItem item = getLastBoughtItem();
            if (item != null) statBonuses = item.getStatBonuses();
        }
    }
}
