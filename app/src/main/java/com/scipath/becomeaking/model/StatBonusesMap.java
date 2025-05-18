package com.scipath.becomeaking.model;

import java.io.Serializable;
import java.util.HashMap;


public class StatBonusesMap implements Serializable {

    // StatBonuses map
    private HashMap<StatBonus, Integer> statBonuses;


    // Constructor
    public StatBonusesMap() {
        statBonuses = new HashMap<>();
    }


    // Accessors
    /***
     * Stat bonuses map accessor
     *
     * @return Map<StatBonus, Integer> that contains all stat bonuses
     */
    public HashMap<StatBonus, Integer> getStatBonusesMap() {
        return statBonuses;
    }

    /***
     * Stat bonus value accessor
     *
     * @param statBonus The StatBonus which value must be returned
     * @return The int that contains the stat bonus value
     */
    public int getStatBonusValue (StatBonus statBonus) {
        if(statBonus == null) return 0;
        else return statBonuses.getOrDefault(statBonus, 0);
    }


    // Mutators
    /***
     * Add stat bonus to the StatBonusesMap
     *
     * @param statBonus The StatBonus which value must be added
     * @param value     The int that contains the value of the stat bonus
     */
    public StatBonusesMap addStatBonus (StatBonus statBonus, int value) {
        statBonuses.put(statBonus, value);
        return this;
    }
}
