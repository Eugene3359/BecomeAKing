package com.scipath.becomeaking.model;

import com.scipath.becomeaking.contract.model.ILevel;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;


class LevelTest {

    private ILevel level;


    @BeforeEach
    void setUp() {
        Level.idCounter = 0;
        level = new Level();
    }

    @Test
    void getId_returnsExpectedId() {
        assertEquals(0, level.getId());
        assertEquals(1, new Level().getId());
    }

    @Test
    void getValue_returnsExpectedValue() {
        assertEquals(1, level.getValue()); // Initial value
    }

    @Test
    void getCurrentExperience_returnsExpectedValue() {
        assertEquals(0, level.getCurrentExperience()); // Initial value
    }

    @Test
    void getNeededExperience_returnsExpectedValue() {
        assertEquals(100, level.getNeededExperience()); // Initial value
    }

    @Test
    void getAvailableSkillPoints_returnsExpectedValue() {
        assertEquals(2, level.getAvailableSkillPoints()); // Initial value
    }

    @Test
    void getStrength_returnsExpectedValue() {
        assertEquals(0, level.getStrength()); // Initial value
    }

    @Test
    void getLuck_returnsExpectedValue() {
        assertEquals(0, level.getLuck()); // Initial value
    }

    @Test
    void affectStrength_withEnoughAvailableSkillPoints_increasesStrengthAndDecreasesAvailableSkillPoints() {
        level.affectStrength(1);
        assertEquals(1, level.getStrength());
        assertEquals(1, level.getAvailableSkillPoints());
    }

    @Test
    void affectStrength_withNotEnoughAvailableSkillPoints_doesNothing() {
        level.affectStrength(3); // Only 2 available
        assertEquals(0, level.getStrength());
        assertEquals(2, level.getAvailableSkillPoints());
    }

    @Test
    void affectLuck_withEnoughAvailableSkillPoints_increaseLuckAndDecreasesAvailableSkillPoints() {
        level.affectLuck(1);
        assertEquals(1, level.getLuck());
        assertEquals(1, level.getAvailableSkillPoints());
    }

    @Test
    void affectLuck_withNotEnoughAvailableSkillPoints_doesNothing() {
        level.affectLuck(3); // Only 2 available
        assertEquals(0, level.getLuck());
        assertEquals(2, level.getAvailableSkillPoints());
    }

    @Test
    void gainExperience_withValueLessThenNeededExperience_increasesExperience() {
        level.gainExperience(50); // 100 needed for level-up
        assertEquals(1, level.getValue());
        assertEquals(50, level.getCurrentExperience());
    }

    @Test
    void gainExperience_withValueMoreThenNeededExperience_levelUp() {
        level.gainExperience(150); // 100 needed for level-up
        assertEquals(2, level.getValue());
        assertEquals(50, level.getCurrentExperience());
        assertEquals(4, level.getAvailableSkillPoints()); // 2 initial + 2 on level up
        assertEquals(200, level.getNeededExperience());
    }

    @Test
    void gainExperience_withExcessiveValue_doesNotLevelUpPastMaximumLevel() {
        // Level cap is 4 (index 3)
        level.gainExperience(10000); // Should reach level 4, then stop
        assertEquals(4, level.getValue());
        assertEquals(1000, level.getCurrentExperience()); // Locked at max
        assertEquals(8, level.getAvailableSkillPoints());
    }

    @Test
    void dropSkillPoints_resetsSkillPoints() {
        level.affectStrength(1);
        level.affectLuck(1);
        level.dropSkillPoints();
        assertEquals(2, level.getAvailableSkillPoints());
        assertEquals(0, level.getStrength());
        assertEquals(0, level.getLuck());
    }

    @Test
    void clone_returnsDeepCopy() {
        level.gainExperience(100);
        level.affectStrength(1);
        ILevel cloned = level.clone();

        assertNotSame(level, cloned);
        assertEquals(level.getValue(), cloned.getValue());
        assertEquals(level.getCurrentExperience(), cloned.getCurrentExperience());
        assertEquals(level.getStrength(), cloned.getStrength());
    }
}