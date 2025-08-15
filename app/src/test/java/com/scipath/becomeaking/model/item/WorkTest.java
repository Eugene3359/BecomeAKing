package com.scipath.becomeaking.model.item;

import static org.junit.jupiter.api.Assertions.assertEquals;

import com.scipath.becomeaking.R;
import com.scipath.becomeaking.contract.model.IItem;
import com.scipath.becomeaking.model.Personage;
import com.scipath.becomeaking.model.Stats;
import com.scipath.becomeaking.model.enums.Sex;
import com.scipath.becomeaking.model.enums.Stat;
import com.scipath.becomeaking.model.enums.Title;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


public class WorkTest {

    IItem work;


    @BeforeEach
    void setUp() {
        Work.interactionCounter = 0;
        work = new Work(R.string.craftsmans_apprentice, R.drawable.img_craftsmans_apprentice, new Stats()
                .add(Stat.HealthImpact, -15)
                .add(Stat.ReputationImpact, 150)
                .add(Stat.MoneyPerClick, 15)
                .add(Stat.ReputationRequired, 1000));
    }

    @Test
    void getInteractionNameId_returnsExpectedId() {
        assertEquals(R.string.start, work.getInteractionNameId()); // Initial value
    }

    @Test
    void getInteractionResultNameId_returnsExpectedId() {
        assertEquals(R.string.ended, work.getInteractionResultNameId()); // Initial value
    }

    @Test
    void setCost_doesNothing() {
        work.setCost(100);
        assertEquals(0, work.getCost()); // Initial value
    }

    @Test
    void interact_withFulfilledRequirementsPersonage_returnsZeroAndModifiesPersonage() {
        Personage personage = new Personage("Hero", Sex.Male, Title.Villager);
        personage.setReputation(1000);
        assertEquals(0, work.interact(personage));
        assertEquals(85, personage.getHealth());
        assertEquals(1150, personage.getReputation());
    }

    @Test
    void interact_withNotEnoughReputationPersonage_returnsMinusThreeAndDoNotModifiesPersonage() {
        Personage personage = new Personage("Hero", Sex.Male, Title.Villager);
        personage.setReputation(500);
        assertEquals(-3, work.interact(personage));
        assertEquals(100, personage.getHealth());
        assertEquals(500, personage.getReputation());
    }

    @Test
    void interact_calledMoreThenTwoTimes_returnsMinusFourAndDoNotModifiesPersonage() {
        Personage personage = new Personage("Hero", Sex.Male, Title.Villager);
        personage.setReputation(1000);
        work.interact(personage);
        work.interact(personage);
        personage.setHealth(100);
        personage.setReputation(30000);
        assertEquals(-4, work.interact(personage));
        assertEquals(100, personage.getHealth());
        assertEquals(30000, personage.getReputation());
    }

    @Test
    void interact_withNull_returnsMinusTen() {
        assertEquals(-10, work.interact(null));
    }

    @Test
    void interact_increasesInteractionCounterByOne() {
        Personage personage = new Personage("Hero", Sex.Male, Title.Villager);
        personage.setReputation(1000);
        assertEquals(0, Work.interactionCounter);
        work.interact(personage);
        assertEquals(1, Work.interactionCounter);
    }

    @Test
    void refreshInteractionCounter_setsInteractionCounterToZero() {
        Personage personage = new Personage("Hero", Sex.Male, Title.Villager);
        personage.setReputation(1000);
        work.interact(personage);
        assertEquals(1, Work.interactionCounter);
        Work.refreshInteractionCounter();
        assertEquals(0, Work.interactionCounter);
    }
}
