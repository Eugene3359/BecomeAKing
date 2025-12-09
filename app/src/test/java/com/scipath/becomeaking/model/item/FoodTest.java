package com.scipath.becomeaking.model.item;

import static org.junit.jupiter.api.Assertions.assertEquals;

import com.scipath.becomeaking.R;
import com.scipath.becomeaking.model.Stats;
import com.scipath.becomeaking.model.enums.Stat;

import org.junit.jupiter.api.BeforeEach;


public class FoodTest {

    Food food;


    @BeforeEach
    void setUp() {
        food = new Food(R.string.bread, R.drawable.food_bread, new Stats()
                .add(Stat.HealthPerDay, 5)
                .add(Stat.CoinsPerDay, -1));
    }
}
