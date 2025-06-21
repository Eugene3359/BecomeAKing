package com.scipath.becomeaking.model;

import java.util.HashMap;
import java.util.Map;


public class StatBonusesMap extends HashMap<StatBonus, Integer> {

    @Override
    public Integer get(Object statBonus) {
        if (statBonus == null) return 0;
        if (!(statBonus instanceof StatBonus)) return 0;
        return getOrDefault(statBonus, 0);
    }

    public StatBonusesMap put(StatBonus statBonus, int value) {
        super.put(statBonus, value);
        return this;
    }

    public void sum(StatBonusesMap statBonuses) {
        for (Map.Entry<StatBonus, Integer> entry : statBonuses.entrySet()) {
            this.merge(entry.getKey(), entry.getValue(), Integer::sum);
        }
    }
}
