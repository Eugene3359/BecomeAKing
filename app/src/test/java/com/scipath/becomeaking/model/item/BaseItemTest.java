package com.scipath.becomeaking.model.item;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import com.scipath.becomeaking.R;
import com.scipath.becomeaking.contract.callback.Callback;
import com.scipath.becomeaking.contract.model.ICategory;
import com.scipath.becomeaking.contract.model.IStats;
import com.scipath.becomeaking.model.Category;
import com.scipath.becomeaking.model.Stats;
import com.scipath.becomeaking.model.enums.Stat;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


public class BaseItemTest {

    private Item item;


    @BeforeEach
    void setUp() {
        Item.idCounter = 0;
        item = new Item(R.string.steel_sword, R.drawable.weapon_steel_sword, 1000, new Stats()
                .add(Stat.Might, 40)
                .add(Stat.ReputationPerDay, 20)
                .add(Stat.CoinsPerDay, -20)
                .add(Stat.StrengthRequired, 2));
    }


    // Accessors
    @Test
    void getId_returnsExpectedId() {
        assertEquals(0, item.getId());
        assertEquals(1, new Item(R.string.two_blades, R.drawable.weapon_two_blades, 2000).getId());
    }

    @Test
    void getNameId_returnsExpectedId() {
        assertEquals(R.string.steel_sword, item.getNameId());
    }

    @Test
    void getImageId_returnsExpectedId() {
        assertEquals(R.drawable.weapon_steel_sword, item.getImageId());
    }

    @Test
    void getStats_returnsExpectedStats() {
        IStats stats = item.getStats();
        assertEquals(4, stats.size());
        assertEquals(40, stats.get(Stat.Might));
        assertEquals(20, stats.get(Stat.ReputationPerDay));
        assertEquals(-20, stats.get(Stat.CoinsPerDay));
        assertEquals(2, stats.get(Stat.StrengthRequired));
    }

    @Test
    void getState_returnsExpectedValue() {
        assertEquals(Item.State.NotBought, item.getState());
    }

    @Test
    void getInteractionNameId_returnsExpectedId() {
        assertEquals(item.state.getInteractionNameId(), item.getInteractionNameId());
    }

    @Test
    void getCategory_returnsExpectedValue() {
        ICategory category = new Category(0, true);
        category.addItem(item);
        assertEquals(category, item.getCategory());
    }


    // Mutators
    @Test
    void setNameId_changesItemsNameId() {
        item.setNameId(R.string.two_blades);
        assertEquals(R.string.two_blades, item.getNameId());
    }

    @Test
    void setImageId_changesItemsImageId() {
        item.setImageId(R.drawable.weapon_two_blades);
        assertEquals(R.drawable.weapon_two_blades, item.getImageId());
    }

    @Test
    void setStats_withValidStats_changesItemsStats() {
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
        assertNotNull(item.getStats());
        assertEquals(0, item.getStats().size());
    }

    @Test
    void setState_changesItemsState() {
        item.setState(Item.State.Bought);
        assertEquals(Item.State.Bought, item.getState());
    }

    @Test
    void setOnStateChanged_changesOnStateChangedHandler() {
        Callback callback = () -> {};
        item.setOnStateChanged(callback);
        assertEquals(callback, item.onStateChanged);
    }

    @Test
    void setState_invokesOnStateChangedHandler() {
        final boolean[] wasCalled = { false };
        Callback callback = () -> wasCalled[0] = true;
        item.setOnStateChanged(callback);
        item.setState(Item.State.Bought);
        assertTrue(wasCalled[0]);
    }

    @Test
    void setCategory_changesItemsCategory() {
        ICategory category = new Category(0, true);
        item.setCategory(category);
        assertEquals(category, item.getCategory());
        assertEquals(1, category.getItems().size());
    }
}
