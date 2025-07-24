package com.scipath.becomeaking.model.item;

import com.scipath.becomeaking.model.Personage;
import com.scipath.becomeaking.model.enums.Stat;
import com.scipath.becomeaking.model.Stats;


public class Work extends Item {

    // Constructors
    public Work(int nameId, int imageId, int cost) {
        super(nameId, imageId, cost);
    }

    public Work(int nameId, int imageId, int cost, Stats stats) {
        super(nameId, imageId, cost, stats);
    }


    // Methods
    @Override
    public int interact(Personage personage) {
        // Check for reputation
        int personageReputation = personage.getReputation();
        int reputationRequired = stats.get(Stat.ReputationRequired);
        if (personageReputation < reputationRequired) return -3; // Not enough reputation

        // Work
        personage.affectHealth(stats.get(Stat.HealthImpact));
        personage.affectReputation(stats.get(Stat.ReputationImpact));
        return 0;
    }
}
