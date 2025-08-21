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

public class FoodTest {

    IItem food;


    @BeforeEach
    void setUp() {
        food = new Food(R.string.bread, R.drawable.img_bread, new Stats()
                .add(Stat.HealthPerDay, 5)
                .add(Stat.CoinsPerDay, -1));
    }

    @Test
    void getInteractionNameId_returnsExpectedId() {
        assertEquals(R.string.add_to_ration, food.getInteractionNameId()); // Initial value
    }

    @Test
    void getInteractionResultNameId_returnsExpectedId() {
        assertEquals(R.string.in_ration, food.getInteractionResultNameId()); // Initial value
    }

    @Test
    void setCost_doesNothing() {
        food.setCost(100);
        assertEquals(0, food.getCost()); // Initial value
    }

    @Test
    void interact_withPersonage_returnsZero() {
        Personage personage = new Personage("Hero", Sex.Male, Title.Villager);
        assertEquals(0, food.interact(personage));
    }

    @Test
    void interact_withNull_returnsMinusTen() {
        assertEquals(-10, food.interact(null));
    }

}
