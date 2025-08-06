package com.scipath.becomeaking.model;

import static org.junit.jupiter.api.Assertions.*;

import com.scipath.becomeaking.contract.model.ILevel;
import com.scipath.becomeaking.contract.model.IPersonage;
import com.scipath.becomeaking.model.enums.Sex;
import com.scipath.becomeaking.model.enums.Title;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


public class PersonageTest {

    IPersonage personage;


    @BeforeEach
    void setUp() {
        personage = new Personage("Hero", Sex.Male, Title.Villager);
    }

    @Test
    void getName_returnsExpectedValue() {
        assertEquals("Hero", personage.getName());
    }

    @Test
    void getSex_returnsExpectedValue() {
        assertEquals(Sex.Male, personage.getSex());
    }

    @Test
    void getTitle_returnsExpectedValue() {
        assertEquals(Title.Villager, personage.getTitle());
    }

    @Test
    void getLevel_returnsExpectedValue() {
        assertInstanceOf(ILevel.class, personage.getLevel());
        assertEquals(1, personage.getLevel().getValue()); // initial value
    }

    @Test
    void getAge_returnsExpectedValue() {
        assertEquals(20, personage.getAge()); // initial value
    }

    @Test
    void getMaxHealth_returnsExpectedValue() {
        assertEquals(Title.Villager.getMaxHealth(), personage.getMaxHealth());
    }

    @Test
    void getHealth_returnsExpectedValue() {
        assertEquals(Title.Villager.getMaxHealth(), personage.getHealth());
    }

    @Test
    void getReputation_returnsExpectedValue() {
        assertEquals(0, personage.getReputation()); // initial value
    }

    @Test
    void getMoney_returnsExpectedValue() {
        assertEquals(100, personage.getMoney()); // initial value
    }

    @Test
    void getMight_returnsExpectedValue() {
        assertEquals(0, personage.getMight()); // initial value
    }

    @Test
    void setName_changesName() {
        personage.setName("Villain");
        assertEquals("Villain", personage.getName());
    }

    @Test
    void setSex_changesSex() {
        personage.setSex(Sex.Female);
        assertEquals(Sex.Female, personage.getSex());
    }

    @Test
    void setTitle_changesTitleAndUpdatesMaxHealth() {
        personage.setTitle(Title.Bandit);
        assertEquals(Title.Bandit, personage.getTitle());
        assertEquals(Title.Bandit.getMaxHealth(), personage.getMaxHealth());
    }

    @Test
    void setLevel_changesLevel() {
        ILevel level = new Level();
        level.gainExperience(100);
        assertEquals(1, personage.getLevel().getValue());
        personage.setLevel(level);
        assertEquals(2, personage.getLevel().getValue());
    }

    @Test
    void setAge_changesAge() {
        personage.setAge(25);
        assertEquals(25, personage.getAge());
    }

    @Test
    void setHealth_withValueBetweenZeroAndMaxHealth_changesHealth() {
        personage.setHealth(50);
        assertEquals(50, personage.getHealth());
    }

    @Test
    void setHealth_withValueAboveMaxHealth_changesHealthToMaxHealth() {
        personage.setHealth(200);
        assertEquals(100, personage.getHealth());
    }

    @Test
    void setHealth_withValueBelowZero_changesHealthToZero() {
        personage.setHealth(-50);
        assertEquals(0, personage.getHealth());
    }

    @Test
    void setReputation_changesReputation() {
        personage.setReputation(100);
        assertEquals(100, personage.getReputation());
    }

    @Test
    void setMoney_changesMoney() {
        personage.setMoney(1000);
        assertEquals(1000, personage.getMoney());
    }

    @Test
    void setMight_changesMight() {
        personage.setMight(2);
        assertEquals(2, personage.getMight());
    }

    @Test
    void affectHealth_addsValueToHealthWithinBounds() {
        personage.affectHealth(-50);
        assertEquals(50, personage.getHealth());
        personage.affectHealth(200);
        assertEquals(100, personage.getHealth());
        personage.affectHealth(-200);
        assertEquals(0, personage.getHealth());
    }

    @Test
    void affectReputation_addsValueToReputation() {
        personage.affectReputation(100);
        assertEquals(100, personage.getReputation());
        personage.affectReputation(-50);
        assertEquals(50, personage.getReputation());
    }

    @Test
    void affectMoney_addsValueToMoney() {
        personage.affectMoney(1000);
        assertEquals(1100, personage.getMoney());
        personage.affectMoney(-600);
        assertEquals(500, personage.getMoney());
    }
}
