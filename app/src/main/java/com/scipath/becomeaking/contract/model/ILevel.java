package com.scipath.becomeaking.contract.model;

import androidx.annotation.NonNull;

import java.io.Serializable;


public interface ILevel extends Serializable, Cloneable {

    // Accessors
    int getId();

    int getValue();

    int getCurrentExperience();

    int getNeededExperience();

    int getAvailableSkillPoints();

    int getStrength();

    int getLuck();


    // Methods
    void affectStrength(int value);

    void affectLuck(int value);

    /**
     * Adds experience points to the currentExperience.
     * If the accumulated experience meets or exceeds the neededExperience,
     * it triggers a level-up attempt.
     *
     * @param value The amount of experience points to be added.
     */
    void gainExperience(int value);

    /**
     * Attempts to level up the character.
     * If the current level is below the maximum defined in levelUpDemands,
     * it deducts the needed experience and increases the level.
     * Otherwise, it keeps the current experience at the neededExperience value.
     */
    void tryLevelUp();

    void dropSkillPoints();

    @NonNull
    ILevel clone();
}
