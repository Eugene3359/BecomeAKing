package com.scipath.becomeaking.model;

import androidx.annotation.NonNull;

import java.io.Serializable;

public class Level implements Serializable, Cloneable {

    // Fields
    private int value;
    private int currentExperience;
    private int neededExperience;
    private int availableSkillPoints;
    private int strength;
    public int luck;

    private final int skillPointsPerLevel = 2;
    private final int[] levelUpDemands = { 100, 200, 500, 1000 };


    // Constructor
    public Level() {
        value = 1;
        currentExperience = 0;
        neededExperience = levelUpDemands[0];
        availableSkillPoints = skillPointsPerLevel;
        strength = 0;
        luck = 0;
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

    public int getAvailableSkillPoints() {
        return availableSkillPoints;
    }

    public int getStrength() {
        return strength;
    }

    public int getLuck() {
        return luck;
    }


    // Mutators
    public void affectStrength(int value) {
        if (availableSkillPoints < value) return;
        strength += value;
        availableSkillPoints -= value;
    }

    public void affectLuck(int value) {
        if (availableSkillPoints < value) return;
        luck += value;
        availableSkillPoints -= value;
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
        availableSkillPoints += skillPointsPerLevel;
    }

    public void dropSkillPoints() {
        availableSkillPoints = value * skillPointsPerLevel;
        strength = 0;
        luck = 0;
    }

    @NonNull
    @Override
    public Level clone() {
        try {
            return (Level) super.clone();
        } catch (CloneNotSupportedException e) {
            throw new AssertionError();
        }
    }
}
