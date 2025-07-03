package com.scipath.becomeaking.model.item;

import com.scipath.becomeaking.model.Personage;
import com.scipath.becomeaking.model.StatBonus;
import com.scipath.becomeaking.model.StatBonusesMap;


public class Work extends Item {

    // Constructors
    public Work(int nameId, int imageId, int cost) {
        super(nameId, imageId, cost);
    }

    public Work(int nameId, int imageId, int cost, StatBonusesMap statBonuses) {
        super(nameId, imageId, cost, statBonuses);
    }


    // Methods
    @Override
    public int interact(Personage personage) {
        personage.affectHealth(statBonuses.get(StatBonus.HealthImpact));
        personage.affectReputation(statBonuses.get(StatBonus.ReputationImpact));
        return 0;
    }
}
