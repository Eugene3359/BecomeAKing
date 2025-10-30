package com.scipath.becomeaking.model;

import android.content.Context;
import android.graphics.drawable.Drawable;

import androidx.appcompat.content.res.AppCompatResources;

import com.scipath.becomeaking.contract.model.ICategory;
import com.scipath.becomeaking.contract.model.IItem;
import com.scipath.becomeaking.contract.model.IStats;
import com.scipath.becomeaking.model.item.Food;
import com.scipath.becomeaking.model.item.Item;
import com.scipath.becomeaking.model.item.SelectableItem;
import com.scipath.becomeaking.model.item.Work;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;


public class Category implements ICategory {

    // Fields
    protected static int idCounter = 0;

    protected int id;
    protected int nameId;
    protected int imageId;
    protected int backgroundDrawableId;
    protected List<IItem> items;
    protected int selectedItemId;
    protected IStats stats;
    protected boolean isSelectable;
    protected boolean itemsMutated;


    // Constructor
    public Category(int nameId, boolean isSelectable) {
        this.id = idCounter++;
        this.nameId = nameId;
        this.imageId = 0;
        this.backgroundDrawableId = 0;
        this.items = new ArrayList<>();
        this.selectedItemId = 0;
        this.stats = new Stats();
        this.isSelectable = isSelectable;
        this.itemsMutated = false;
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
        if (itemsMutated) {
            recalculateStats();
        }
        return imageId;
    }

    @Override
    public int getBackgroundDrawableId() {
        return backgroundDrawableId;
    }

    @Override
    public IItem getItem(int index) {
        if (index < 0 || index >= items.size()) return null;
        return items.get(index);
    }

    @Override
    public List<IItem> getItems() {
        return items;
    }

    @Override
    public IItem getSelectedItem() {
        for (IItem item : items) {
            if (item.getId() == selectedItemId)
                return item;
        }
        return null;
    }

    @Override
    public IStats getStats() {
        if (itemsMutated) {
            recalculateStats();
        }
        return stats.clone();
    }

    @Override
    public boolean isSelectable() {
        return isSelectable;
    }


    // Mutators
    @Override
    public void setNameId(int nameId) {
        this.nameId = nameId;
    }

    @Override
    public void setBackgroundDrawableId(int drawableId) {
        this.backgroundDrawableId = drawableId;
    }

    @Override
    public ICategory addItem(IItem item) {
        if (item != null) {
            this.items.add(item);
            item.setCategory(this);
            itemsMutated = true;
        }
        return this;
    }

    @Override
    public ICategory removeItem(IItem item) {
        if (item != null) {
            items.remove(item);
        }
        return this;
    }

    @Override
    public void setItems(List<IItem> items) {
        this.items = Objects.requireNonNullElseGet(items, ArrayList::new);
        for (IItem item : this.items) {
            item.setCategory(this);
        }
        itemsMutated = true;
    }

    @Override
    public void setSelectedItem(IItem item) {
        if (!isSelectable ||
            !(item instanceof SelectableItem) ||
            item.getId() == selectedItemId ||
            !items.contains(item)
        ) return;
        // Deselect old item
        if (getSelectedItem() instanceof SelectableItem)
            getSelectedItem().setState(SelectableItem.State.Bought);
        // Set new selected item and check its state
        selectedItemId = item.getId();
        if (getSelectedItem().getState() != SelectableItem.State.Selected)
            getSelectedItem().setState(SelectableItem.State.Selected);
    }

    @Override
    public void setSelectable(boolean isSelectable) {
        this.isSelectable = isSelectable;
        if (!isSelectable) selectedItemId = 0;
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
    public boolean containsItem(IItem item) {
        return items.contains(item);
    }

    @Override
    public IItem getBestItem() {
        if (isSelectable && selectedItemId != 0) return getSelectedItem();
        IItem item;
        for (int i = items.size()-1; i >= 0; i--) {
            item = items.get(i);
            if (item.getState() == Item.State.Bought ||
                item.getState() == Food.State.InRation)
                return item;
        }
        return null;
    }

    /**
     * Updates the category-level stats based on the values of all items.
     * This method recalculates stats and sets the image.
     */
    protected void recalculateStats() {
        imageId = 0;
        stats = new Stats();
        if (items.isEmpty()) return;

        // Updating category imageId
        IItem bestItem = getBestItem();
        imageId = bestItem == null ?
                items.get(0).getImageId() :
                bestItem.getImageId();

        if (bestItem instanceof Work && items.size() > 1) return;

        // Updating category stats
        if (isSelectable) {
            if (bestItem != null)
                stats = bestItem.getStats();
        } else {
            for (IItem item : items) {
                if (item.getState() == Item.State.Bought ||
                    item.getState() == SelectableItem.State.Bought ||
                    item.getState() == Food.State.InRation) {
                    stats.merge(item.getStats());
                }
            }
        }
    }
}