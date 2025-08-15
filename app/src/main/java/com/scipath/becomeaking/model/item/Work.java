package com.scipath.becomeaking.model.item;

import android.content.Context;

import com.scipath.becomeaking.R;
import com.scipath.becomeaking.model.Personage;
import com.scipath.becomeaking.model.enums.Stat;
import com.scipath.becomeaking.model.Stats;


public class Work extends Item {

    // Variables
    protected static int interactionCounter = 0;


    // Constructors
    public Work(int nameId, int imageId) {
        super(nameId, imageId, 0);
        this.interactionNameId = R.string.start;
        this.interactionResultNameId = R.string.ended;
    }

    public Work(int nameId, int imageId, Stats stats) {
        super(nameId, imageId, 0, stats);
        this.interactionNameId = R.string.start;
        this.interactionResultNameId = R.string.ended;
    }


    // Mutators
    @Override
    public void setCost(int cost) {
        // Cost for work is always 0;
    }


    // Methods
    @Override
    public String getInteractionName(Context context) {
        return context.getString(interactionNameId);
    }

    @Override
    public int interact(Personage personage) {
        if (personage == null) return -10; // Null

        // Check for reputation
        int personageReputation = personage.getReputation();
        int reputationRequired = stats.get(Stat.ReputationRequired);
        if (personageReputation < reputationRequired) return -3; // Not enough reputation

        // Check for number of completed works
        if (interactionCounter >= 2) return -4;

        // Work
        interacted = true;
        personage.affectHealth(stats.get(Stat.HealthImpact));
        personage.affectReputation(stats.get(Stat.ReputationImpact));
        interactionCounter++;
        return 0;
    }

    public static void refreshInteractionCounter() {
        interactionCounter = 0;
    }
}
