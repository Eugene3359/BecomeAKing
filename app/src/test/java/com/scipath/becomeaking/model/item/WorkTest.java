package com.scipath.becomeaking.model.item;

import static org.junit.jupiter.api.Assertions.assertEquals;

import com.scipath.becomeaking.R;
import com.scipath.becomeaking.model.Stats;
import com.scipath.becomeaking.model.enums.Stat;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


public class WorkTest {

    Work work;


    @BeforeEach
    void setUp() {
        work = new Work(R.string.craftsmans_apprentice, R.drawable.work_craftsmans_apprentice, new Stats()
                .add(Stat.HealthImpact, -25)
                .add(Stat.ReputationImpact, 150)
                .add(Stat.MoneyPerClick, 15)
                .add(Stat.ReputationRequired, 1000),
                10);
    }


    // Accessors
    @Test
    void getInteractionValue_returnsExpectedValue() {
        assertEquals(1, work.getEnergyConsumption()); // Default value
    }

    @Test
    void getExperience_returnsExpectedValue() {
        assertEquals(10, work.getExperience()); // Initial value
    }


    // Mutators
    @Test
    void setInteractionValue_changesInteractionValue() {
        work.setEnergyConsumption(2);
        assertEquals(2, work.getEnergyConsumption());
    }

    @Test
    void setExperience_withValidValue_changesExperience() {
        work.setExperience(25);
        assertEquals(25, work.getExperience());
    }

    @Test
    void setExperience_withNegativeValue_setsZero() {
        work.setExperience(-10);
        assertEquals(0, work.getExperience());
    }
}
