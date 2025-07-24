package com.scipath.becomeaking.model;

import androidx.annotation.NonNull;

import com.scipath.becomeaking.contract.model.ILevel;

public class Level implements ILevel {

    // Fields
    private final int[] levelUpDemands = { 100, 200, 500, 1000 };
    private final int skillPointsPerLevel = 2;

    private int value;
    private int currentExperience;
    private int neededExperience;
    private int availableSkillPoints;
    private int strength;
    public int luck;


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
    @Override
    public int getValue() {
        return value;
    }

    @Override
    public int getCurrentExperience() {
        return currentExperience;
    }

    @Override
    public int getNeededExperience() {
        return neededExperience;
    }

    @Override
    public int getAvailableSkillPoints() {
        return availableSkillPoints;
    }

    @Override
    public int getStrength() {
        return strength;
    }

    @Override
    public int getLuck() {
        return luck;
    }


    // Methods
    @Override
    public void affectStrength(int value) {
        if (availableSkillPoints < value) return;
        strength += value;
        availableSkillPoints -= value;
    }

    @Override
    public void affectLuck(int value) {
        if (availableSkillPoints < value) return;
        luck += value;
        availableSkillPoints -= value;
    }

    @Override
    public void gainExperience(int value) {
        currentExperience += value;
        if (currentExperience >= neededExperience) tryLevelUp();
    }

    @Override
    public void tryLevelUp() {
        // If there is no more levelUpDemands do not level up
        if (value >= levelUpDemands.length) {
            currentExperience = neededExperience;
            return;
        }

        currentExperience -= neededExperience;
        neededExperience = levelUpDemands[value];
        value++;
        availableSkillPoints += skillPointsPerLevel;

        if (currentExperience >= neededExperience) tryLevelUp();
    }

    @Override
    public void dropSkillPoints() {
        availableSkillPoints = value * skillPointsPerLevel;
        strength = 0;
        luck = 0;
    }

    @NonNull
    @Override
    public ILevel clone() {
        try {
            return (ILevel) super.clone();
        } catch (CloneNotSupportedException e) {
            throw new AssertionError();
        }
    }
}
