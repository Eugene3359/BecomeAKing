package com.scipath.becomeaking.model;


import com.scipath.becomeaking.R;
import com.scipath.becomeaking.contract.model.IItem;
import com.scipath.becomeaking.contract.model.IStats;
import com.scipath.becomeaking.model.enums.Sex;
import com.scipath.becomeaking.model.enums.Stat;
import com.scipath.becomeaking.model.enums.Title;
import com.scipath.becomeaking.model.item.Item;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;


public class ItemTest {

    private IItem item;


    @BeforeEach
    void setUp() {
        item = new Item(R.string.steel_sword, R.drawable.img_steel_sword, 1000, new Stats()
                .add(Stat.Might, 40)
                .add(Stat.ReputationPerDay, 20)
                .add(Stat.CostPerDay, -20)
                .add(Stat.StrengthRequired, 2));
    }

    @Test
    void getNameId_returnsExpectedId() {
        assertEquals(R.string.steel_sword, item.getNameId());
    }

    @Test
    void getImageId_returnsExpectedId() {
        assertEquals(R.drawable.img_steel_sword, item.getImageId());
    }

    @Test
    void getInteractionNameId_returnsExpectedId() {
        assertEquals(R.string.buy_d, item.getInteractionNameId());
    }

    @Test
    void getCost_returnsExpectedValue() {
        assertEquals(1000, item.getCost());
    }

    @Test
    void isBought_withNotBoughtItem_returnsFalse() {
        assertFalse(item.isBought());
    }

    @Test
    void isBought_withBoughtItem_returnsTrue() {
        item.setBought(true);
        assertTrue(item.isBought());
    }

    @Test
    void getStats_returnsExpectedStats() {
        IStats stats = item.getStats();
        assertEquals(4, stats.size());
        assertEquals(40, stats.get(Stat.Might));
        assertEquals(20, stats.get(Stat.ReputationPerDay));
        assertEquals(-20, stats.get(Stat.CostPerDay));
        assertEquals(2, stats.get(Stat.StrengthRequired));
    }

    @Test
    void setNameId_changesItemsNameId() {
        item.setNameId(R.string.two_blades);
        assertEquals(R.string.two_blades, item.getNameId());
    }

    @Test
    void setImageId_changesItemsImageId() {
        item.setImageId(R.drawable.img_two_blades);
        assertEquals(R.drawable.img_two_blades, item.getImageId());
    }

    @Test
    void setCost_changesItemsCost() {
        item.setCost(3000);
        assertEquals(3000, item.getCost());
    }

    @Test
    void setBought_changesItemsBoughtState() {
        assertFalse(item.isBought());
        item.setBought(true);
        assertTrue(item.isBought());
    }

    @Test
    void setStats_withValidStats_setsItemsNewStats() {
        IStats stats = new Stats();
        stats.add(Stat.MaxHealth, 150);
        item.setStats(stats);
        assertEquals(1, item.getStats().size());
        assertEquals(150, item.getStats().get(Stat.MaxHealth));
        assertEquals(0, item.getStats().get(Stat.Might));
    }

    @Test
    void setStats_withNull_setsEmptyStats() {
        item.setStats(null);
        assertEquals(0, item.getStats().size());
    }

    @Test
    void interact_withFulfilledRequirementsPersonage_returnsZeroAndModifiesPersonage() {
        Personage personage = new Personage("Hero", Sex.Male, Title.Villager);
        personage.setMoney(1000);
        personage.getLevel().affectStrength(2);
        assertEquals(0, item.interact(personage));
    }

    @Test
    void interact_withNotEnoughMoneyPersonage_returnsMinusOneAndDoNotModifiesPersonage() {
        Personage personage = new Personage("Hero", Sex.Male, Title.Villager);
        personage.setMoney(500);
        personage.getLevel().affectStrength(2);
        assertEquals(-1, item.interact(personage));
    }

    @Test
    void interact_withNotStrengthPersonage_returnsMinusTwoAndDoNotModifiesPersonage() {
        Personage personage = new Personage("Hero", Sex.Male, Title.Villager);
        personage.setMoney(1000);
        personage.getLevel().affectStrength(1);
        assertEquals(-2, item.interact(personage));
    }

    @Test
    void interact_withNull_returnsMinusTenAndDoNotModifiesPersonage() {
        assertEquals(-10, item.interact(null));
    }
}
