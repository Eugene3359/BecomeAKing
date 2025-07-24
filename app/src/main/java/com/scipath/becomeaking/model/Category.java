package com.scipath.becomeaking.model;

import android.content.Context;
import android.graphics.drawable.Drawable;

import androidx.appcompat.content.res.AppCompatResources;

import com.scipath.becomeaking.R;
import com.scipath.becomeaking.contract.model.ICategory;
import com.scipath.becomeaking.contract.model.IItem;
import com.scipath.becomeaking.contract.model.IStats;

import java.util.ArrayList;
import java.util.List;


public class Category implements ICategory {

    // Fields
    protected static int idCounter = 0;
    protected int id;
    protected int nameId;
    protected int imageId;
    protected List<IItem> items;
    protected boolean itemsMutated;
    protected IStats stats;


    // Constructor
    public Category(int nameId) {
        this.id = idCounter++;
        this.nameId = nameId;
        this.imageId = 0;
        this.items = new ArrayList<>();
        itemsMutated = false;
        this.stats = new Stats();
    }


    // Accessors
    @Override
    public int getId() {
        return id;
    }

    @Override
    public int getNameId() {
        return nameId;
    }

    @Override
    public int getImageId() {
        return imageId;
    }

    @Override
    public List<IItem> getItems() {
        return items;
    }

    @Override
    public IStats getStats() {
        if (itemsMutated) recalculateStats();
        return stats;
    }


    // Mutators
    @Override
    public void setNameId(int nameId) {
        this.nameId = nameId;
    }

    @Override
    public void setItems(List<IItem> items) {
        this.items = items;
        itemsMutated = true;
    }

    @Override
    public ICategory addItem(IItem item) {
        this.items.add(item);
        itemsMutated = true;
        return this;
    }


    // Methods
    @Override
    public String getName(Context context) {
        return context.getString(nameId);
    }

    @Override
    public Drawable getImage(Context context) {
        return AppCompatResources.getDrawable(context, imageId);
    }

    @Override
    public IItem getLastBoughtItem() {
        IItem item;
        for (int i = items.size()-1; i >= 0; i--) {
            item = items.get(i);
            if (item.isBought()) return item;
        }
        return null;
    }

    /**
     * Updates the category-level stats based on the values of all items.
     * This method recalculates stats and sets the image from the last item
     * marked as bought.
     */
    protected void recalculateStats() {
        if (items.isEmpty()) return;

        // Updating category imageId
        imageId = getLastBoughtItem() == null ?
                items.get(0).getImageId() :
                getLastBoughtItem().getImageId();

        // Updating category stats
        stats = new Stats();
        if (nameId == R.string.nutrition | nameId == R.string.books) {
            for (IItem item : items) {
                if (item.isBought()) {
                    stats.merge(item.getStats());
                }
            }
        } else {
            IItem item = getLastBoughtItem();
            if (item != null) stats = item.getStats();
        }
    }
}
