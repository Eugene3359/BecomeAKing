package com.scipath.becomeaking.model;

import java.io.Serializable;

public class Level implements Serializable {

    // Fields
    private int value;
    private int currentExperience;
    private int neededExperience;

    private final int[] levelUpDemands = { 100, 200, 500, 1000 };


    // Constructor
    public Level() {
        value = 1;
        currentExperience = 0;
        neededExperience = levelUpDemands[0];
    }


    // Accessors
    public int getValue() {
        return value;
    }

    public int getCurrentExperience() {
        return currentExperience;
    }

    public int getNeededExperience() {
        return neededExperience;
    }


    // Methods
    /***
     * Adds experience points to the currentExperience.
     * If the accumulated experience meets or exceeds the neededExperience,
     * it triggers a level-up attempt.
     *
     * @param value The amount of experience points to be added.
     */
    public void gainExperience(int value) {
        currentExperience += value;
        if (currentExperience >= neededExperience) tryLevelUp();
    }

    /***
     * Attempts to level up the character.
     * If the current level is below the maximum defined in levelUpDemands,
     * it deducts the needed experience and increases the level.
     * Otherwise, it keeps the current experience at the neededExperience value.
     */
    public void tryLevelUp () {
        // If there is no more levelUpDemands do not level up
        if (value >= levelUpDemands.length) {
            currentExperience = neededExperience;
            return;
        }

        currentExperience -= neededExperience;
        neededExperience = levelUpDemands[value];
        value++;
    }
}
