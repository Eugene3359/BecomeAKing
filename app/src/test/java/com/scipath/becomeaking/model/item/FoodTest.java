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


public class FoodTest {

    Food food;


    @BeforeEach
    void setUp() {
        food = new Food(R.string.bread, R.drawable.img_bread, new Stats()
                .add(Stat.HealthPerDay, 5)
                .add(Stat.CoinsPerDay, -1));
    }

    @Test
    void interact_withPersonage_returnsSuccessfulInteractionResult() {
        Personage personage = new Personage("Hero", Sex.Male, Title.Villager);
        assertEquals(InteractionResult.Successful, food.interact(personage));
    }

    @Test
    void interact_withNull_withNull_returnsNullPersonageInteractionResult() {
        assertEquals(InteractionResult.NullPersonage, food.interact(null));
    }

}
