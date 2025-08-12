package com.scipath.becomeaking.model.item;

import android.content.Context;

import com.scipath.becomeaking.R;
import com.scipath.becomeaking.contract.model.IStats;

public class Food extends Item {

    // Constructor
    public Food(int nameId, int imageId, int cost) {
        super(nameId, imageId, cost);
        this.interactionNameId = R.string.add_to_ration;
        this.interactionResultNameId = R.string.in_ration;
    }

    public Food(int nameId, int imageId, int cost, IStats stats) {
        super(nameId, imageId, cost, stats);
        this.interactionNameId = R.string.add_to_ration;
        this.interactionResultNameId = R.string.in_ration;
    }


    // Methods
    @Override
    public String getInteractionName(Context context) {
        return context.getString(interactionNameId);
    }
}
