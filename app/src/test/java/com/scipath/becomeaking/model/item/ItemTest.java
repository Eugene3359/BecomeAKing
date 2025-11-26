package com.scipath.becomeaking.model.item;


import com.scipath.becomeaking.R;
import com.scipath.becomeaking.model.Personage;
import com.scipath.becomeaking.model.Stats;
import com.scipath.becomeaking.model.enums.InteractionResult;
import com.scipath.becomeaking.model.enums.Sex;
import com.scipath.becomeaking.model.enums.Stat;
import com.scipath.becomeaking.model.enums.Title;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;


public class ItemTest {

    private Item item;


    @BeforeEach
    void setUp() {
        Item.idCounter = 0;
        item = new Item(R.string.steel_sword, R.drawable.img_steel_sword, 1000, new Stats()
                .add(Stat.Might, 40)
                .add(Stat.ReputationPerDay, 20)
                .add(Stat.CoinsPerDay, -20)
                .add(Stat.StrengthRequired, 2));
    }


    // Accessors
    @Test
    void getCost_returnsExpectedValue() {
        assertEquals(1000, item.getCost());
    }


    // Mutators
    @Test
    void setCost_changesItemsCost() {
        item.setCost(2000);
        assertEquals(2000, item.getCost());
    }
}
