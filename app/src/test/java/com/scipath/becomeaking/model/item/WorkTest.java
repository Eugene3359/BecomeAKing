package com.scipath.becomeaking.model.item;

import static org.junit.jupiter.api.Assertions.assertEquals;

import com.scipath.becomeaking.R;
import com.scipath.becomeaking.model.Personage;
import com.scipath.becomeaking.model.Stats;
import com.scipath.becomeaking.model.enums.InteractionResult;
import com.scipath.becomeaking.model.enums.Sex;
import com.scipath.becomeaking.model.enums.Stat;
import com.scipath.becomeaking.model.enums.Title;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


public class WorkTest {

    Work work;


    @BeforeEach
    void setUp() {
        work = new Work(R.string.craftsmans_apprentice, R.drawable.img_craftsmans_apprentice, new Stats()
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


    // Methods
    @Test
    void interact_withFulfillingRequirementsPersonage_returnsSuccessfulInteractionResultAndModifiesPersonage() {
        Personage personage = new Personage("Hero", Sex.Male, Title.Villager);
        personage.setReputation(1000);
        assertEquals(InteractionResult.Successful, work.interact(personage));
        assertEquals(100, personage.getHealth());
        assertEquals(1150, personage.getReputation());
        assertEquals(10, personage.getLevel().getCurrentExperience());
    }

    @Test
    void interact_whenWorkLimitExceeded_returnsNoTimeLeftInteractionResultAndDoNotModifiesPersonage() {
        Personage personage = new Personage("Hero", Sex.Male, Title.Villager);
        personage.setReputation(1000);
        personage.setEnergy(0);
        assertEquals(InteractionResult.NoTimeLeft, work.interact(personage));
        assertEquals(125, personage.getHealth());
        assertEquals(1000, personage.getReputation());
    }

    @Test
    void interact_withNotEnoughReputationPersonage_returnsNotEnoughReputationInteractionResultAndDoNotModifiesPersonage() {
        Personage personage = new Personage("Hero", Sex.Male, Title.Villager);
        personage.setReputation(500);
        assertEquals(InteractionResult.NotEnoughReputation, work.interact(personage));
        assertEquals(125, personage.getHealth());
        assertEquals(500, personage.getReputation());
    }

    @Test
    void interact_withNull_returnsNullPersonageInteractionResult() {
        assertEquals(InteractionResult.NullPersonage, work.interact(null));
    }
}
