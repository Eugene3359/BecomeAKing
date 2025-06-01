package com.scipath.becomeaking.model;

import android.content.Context;
import android.graphics.drawable.Drawable;

import androidx.appcompat.content.res.AppCompatResources;

import com.scipath.becomeaking.R;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;


public class Category implements Serializable {

    // Fields
    private static int counter = -1;
    private int id;
    private int nameId;
    private int imageId;
    private List<Item> items;
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

    public List<Item> getItems() {
        return items;
    }

    public StatBonusesMap getStatBonuses() {
        return statBonuses;
    }


    // Mutators
    public void setItems(List<Item> items) {
        this.items = items;
        recalculateStats();
    }

    /***
     * Adds an item to the category
     *
     * @param item The Item to be added to the category
     */
    public Category addItem(Item item) {
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

    /***
     * Updates the category-level stats based on the values of all items.
     * This method recalculates stats and sets the image from the last item
     * marked as bought.
     */
    public void recalculateStats() {
        if (!items.isEmpty()) {
            imageId = items.get(0).getImageId();
        }
        statBonuses = new StatBonusesMap();

        for (Item item : items) {
            if (item.isBought()) {
                imageId = item.getImageId();

                for (StatBonus statBonus : StatBonus.values()) {
                    if (item.getStatBonuses().getStatBonusValue(statBonus) != 0) {
                        if (nameId == R.string.nutrition) {
                            statBonuses.addStatBonus(statBonus,
                                    statBonuses.getStatBonusValue(statBonus)
                                            + item.getStatBonuses().getStatBonusValue(statBonus));
                        } else {
                            statBonuses.addStatBonus(statBonus, item.getStatBonuses().getStatBonusValue(statBonus));
                        }
                    }
                }
            }
        }
    }
}
