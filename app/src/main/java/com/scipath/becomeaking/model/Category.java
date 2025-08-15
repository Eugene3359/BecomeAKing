package com.scipath.becomeaking.model;

import android.content.Context;
import android.graphics.drawable.Drawable;

import androidx.appcompat.content.res.AppCompatResources;

import com.scipath.becomeaking.contract.model.ICategory;
import com.scipath.becomeaking.contract.model.IItem;
import com.scipath.becomeaking.contract.model.IStats;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;


public class Category implements ICategory {

    // Fields
    protected static int idCounter = 0;

    protected int id;
    protected int nameId;
    protected int imageId;
    protected List<IItem> items;
    protected boolean itemsMutated;
    protected StatsMod statsMod;
    protected IStats stats;


    // Constructor
    public Category(int nameId) {
        this.id = idCounter++;
        this.nameId = nameId;
        this.imageId = 0;
        this.items = new ArrayList<>();
        this.itemsMutated = false;
        this.statsMod = StatsMod.Best;
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
        if (itemsMutated) recalculateStats();
        return imageId;
    }

    @Override
    public List<IItem> getItems() {
        return items;
    }

    @Override
    public StatsMod getStatsMod() {
        return statsMod;
    }

    @Override
    public IStats getStats() {
        if (itemsMutated) recalculateStats();
        return stats.clone();
    }


    // Mutators
    @Override
    public void setNameId(int nameId) {
        this.nameId = nameId;
    }

    @Override
    public void setItems(List<IItem> items) {
        this.items = Objects.requireNonNullElseGet(items, ArrayList::new);
        itemsMutated = true;
    }

    @Override
    public ICategory addItem(IItem item) {
        if (item != null) {
            this.items.add(item);
            itemsMutated = true;
        }
        return this;
    }

    @Override
    public void setStatsMod(StatsMod statsMod) {
        if (statsMod != null) this.statsMod = statsMod;
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
    public IItem getBestBoughtItem() {
        IItem item;
        for (int i = items.size()-1; i >= 0; i--) {
            item = items.get(i);
            if (item.isInteracted()) return item;
        }
        return null;
    }

    /**
     * Updates the category-level stats based on the values of all items.
     * This method recalculates stats and sets the image from the last item
     * marked as bought.
     */
    protected void recalculateStats() {
        imageId = 0;
        stats = new Stats();
        if (items.isEmpty()) return;

        // Updating category imageId
        IItem bestBoughtItem = getBestBoughtItem();
        imageId = bestBoughtItem == null ?
                items.get(0).getImageId() :
                bestBoughtItem.getImageId();

        // Updating category stats
        if (statsMod == StatsMod.Best) {
            if (bestBoughtItem != null) stats = bestBoughtItem.getStats();
        } else if (statsMod == StatsMod.Sum) {
            for (IItem item : items) {
                if (item.isInteracted()) {
                    stats.merge(item.getStats());
                }
            }
        }
    }
}
