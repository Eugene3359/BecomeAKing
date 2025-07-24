package com.scipath.becomeaking.model;

import com.scipath.becomeaking.contract.model.ILevel;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;


class LevelTest {

    private ILevel level;


    @BeforeEach
    void setUp() {
        level = new Level();
    }

    @Test
    void initialValues() {
        assertEquals(1, level.getValue());
        assertEquals(0, level.getCurrentExperience());
        assertEquals(100, level.getNeededExperience());
        assertEquals(2, level.getAvailableSkillPoints());
        assertEquals(0, level.getStrength());
        assertEquals(0, level.getLuck());
    }

    @Test
    void affectStrength_WithEnoughPoints() {
        level.affectStrength(1);
        assertEquals(1, level.getStrength());
        assertEquals(1, level.getAvailableSkillPoints());
    }

    @Test
    void affectStrength_WithoutEnoughPoints_doesNothing() {
        level.affectStrength(3); // only 2 available
        assertEquals(0, level.getStrength()); // should not apply
        assertEquals(2, level.getAvailableSkillPoints());
    }

    @Test
    void affectLuck_WithEnoughPoints() {
        level.affectLuck(2);
        assertEquals(2, level.getLuck());
        assertEquals(0, level.getAvailableSkillPoints());
    }

    @Test
    void affectLuck_WithoutEnoughPoints_doesNothing() {
        level.affectLuck(3); // only 2 available
        assertEquals(0, level.getLuck()); // should not apply
        assertEquals(2, level.getAvailableSkillPoints());
    }

    @Test
    void gainExperience_withValueLessThenNeededExperience_increasesExperience() {
        level.gainExperience(50); // 100 needed for level-up
        assertEquals(50, level.getCurrentExperience());
        assertEquals(1, level.getValue());
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
        // level cap is 4 (index 3), so go beyond it
        level.gainExperience(10000); // should reach level 4, then stop
        assertEquals(4, level.getValue());
        assertEquals(1000, level.getCurrentExperience()); // locked at max
        assertEquals(8, level.getAvailableSkillPoints());
    }

    @Test
    void dropSkillPoints_resetsSkillPoints() {
        level.affectStrength(1);
        level.affectLuck(1);
        assertEquals(0, level.getAvailableSkillPoints());

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