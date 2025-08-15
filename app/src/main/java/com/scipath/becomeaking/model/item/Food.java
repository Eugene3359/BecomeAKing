package com.scipath.becomeaking.model.item;

import android.content.Context;

import com.scipath.becomeaking.R;
import com.scipath.becomeaking.contract.model.IStats;
import com.scipath.becomeaking.model.Personage;

public class Food extends Item {

    // Constructor
    public Food(int nameId, int imageId) {
        super(nameId, imageId, 0);
        this.interactionNameId = R.string.add_to_ration;
        this.interactionResultNameId = R.string.in_ration;
    }

    public Food(int nameId, int imageId, IStats stats) {
        super(nameId, imageId, 0, stats);
        this.interactionNameId = R.string.add_to_ration;
        this.interactionResultNameId = R.string.in_ration;
    }


    // Mutators
    @Override
    public void setCost(int cost) {
        // Cost for food is always 0;
    }


    // Methods
    @Override
    public String getInteractionName(Context context) {
        return context.getString(interactionNameId);
    }

    @Override
    public int interact(Personage personage) {
        if (personage == null) return -10; // Null

        // Buy
        interacted = true;
        return 0; // Item bought
    }
}
