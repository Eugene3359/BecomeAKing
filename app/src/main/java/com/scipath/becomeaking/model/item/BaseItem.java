package com.scipath.becomeaking.model.item;

import android.content.Context;
import android.graphics.drawable.Drawable;

import androidx.appcompat.content.res.AppCompatResources;

import com.scipath.becomeaking.contract.callback.Callback;
import com.scipath.becomeaking.contract.model.ICategory;
import com.scipath.becomeaking.contract.model.IItem;
import com.scipath.becomeaking.contract.model.IItemState;
import com.scipath.becomeaking.contract.model.IStats;
import com.scipath.becomeaking.model.Stats;
import com.scipath.becomeaking.model.enums.Stat;

import java.util.Objects;


public abstract class BaseItem<State extends Enum<State> & IItemState>  implements IItem<State>{

    // Fields
    protected static int idCounter = 0;
    
    protected int id;
    protected int nameId;
    protected int imageId;
    protected IStats stats;
    protected State state;
    protected transient Callback onStateChanged;
    protected transient ICategory category;


    // Constructors
    public BaseItem(int nameId, int imageId) {
        this(nameId, imageId, new Stats());
    }

    public BaseItem(int nameId, int imageId, IStats stats) {
        this.id = idCounter++;
        this.nameId = nameId;
        this.imageId = imageId;
        this.setStats(stats);
        this.onStateChanged = null;
        this.category = null;
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
    public IStats getStats() {
        return stats;
    }

    @Override
    public int getStat(Stat stat) {
        return stats.get(stat);
    }

    @Override
    public State getState() {
        return state;
    }

    @Override
    public int getInteractionNameId() {
        return state.getInteractionNameId();
    }

    @Override
    public ICategory getCategory() {
        return category;
    }


    // Mutators
    @Override
    public void setNameId(int nameId) {
        this.nameId = nameId;
    }

    @Override
    public void setImageId(int imageId) {
        this.imageId = imageId;
    }

    @Override
    public void setStats(IStats stats) {
        this.stats = Objects.requireNonNullElseGet(stats, Stats::new);
    }

    @Override
    public void setStat(Stat stat, int value) {
        stats.add(stat, value);
    }

    @Override
    public void setState(State state) {
        if (this.state != state) {
            this.state = state;
            if (onStateChanged != null)
                try {
                    onStateChanged.call();
                } catch (Exception e) {
                    onStateChanged = null;
                }
        }
    }

    @Override
    public void setOnStateChanged(Callback callback) {
        onStateChanged = callback;
    }

    @Override
    public void setCategory(ICategory category) {
        if (this.category != category) {
            if (this.category != null && this.category.containsItem(this)) {
                this.category.removeItem(this);
            }
            this.category = category;
            if (this.category != null && !this.category.containsItem(this)) {
                this.category.addItem(this);
            }
        }
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
    public String getInteractionName(Context context) {
        return context.getString(state.getInteractionNameId());
    }
}
