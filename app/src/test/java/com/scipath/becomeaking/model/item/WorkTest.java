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
        work = new Work(R.string.craftsmans_apprentice, R.drawable.img_craftsmans_apprentice, 0, new Stats()
                .add(Stat.HealthImpact, -15)
                .add(Stat.ReputationImpact, 150)
                .add(Stat.MoneyPerClick, 15)
                .add(Stat.ReputationRequired, 30000));
    }

    @Test
    void getInteractionNameId_returnsExpectedId() {
        assertEquals(R.string.start, work.getInteractionNameId()); // Initial value
    }

    @Test
    void interact_withFulfilledRequirementsPersonage_returnsZeroAndModifiesPersonage() {
        Personage personage = new Personage("Hero", Sex.Male, Title.Villager);
        personage.setReputation(30000);
        assertEquals(0, work.interact(personage));
        assertEquals(85, personage.getHealth());
        assertEquals(30150, personage.getReputation());
    }

    @Test
    void interact_withNotEnoughReputationPersonage_returnsMinusThreeAndDoNotModifiesPersonage() {
        Personage personage = new Personage("Hero", Sex.Male, Title.Villager);
        personage.setReputation(20000);
        assertEquals(-3, work.interact(personage));
        assertEquals(100, personage.getHealth());
        assertEquals(20000, personage.getReputation());
    }

    @Test
    void interact_withNull_returnsMinusTen() {
        assertEquals(-10, work.interact(null));
    }
}
