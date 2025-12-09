package com.scipath.becomeaking.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNotSame;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import com.scipath.becomeaking.R;
import com.scipath.becomeaking.contract.model.IItem;
import com.scipath.becomeaking.contract.model.IStats;
import com.scipath.becomeaking.model.enums.Stat;
import com.scipath.becomeaking.model.item.SelectableItem;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;


public class CategoryTest {

    Category category;
    IItem item1;
    IItem item2;


    @BeforeEach
    void setUp() {
        Category.idCounter = 0;
        category = new Category(R.string.weapon, true);
        category.setBackgroundDrawableId(R.drawable.bg_metal_wall);

        item1 = new SelectableItem(R.string.steel_sword, R.drawable.weapon_steel_sword, 1000);
        item1.setStats(new Stats()
                .add(Stat.Might, 40)
                .add(Stat.ReputationPerDay, 20)
                .add(Stat.CoinsPerDay, -20)
                .add(Stat.StrengthRequired, 2));
        item2 = new SelectableItem(R.string.two_blades, R.drawable.weapon_two_blades, 3000);
        item2.setStats(new Stats()
                .add(Stat.Might, 80)
                .add(Stat.ReputationPerDay, 50)
                .add(Stat.CoinsPerDay, -40)
                .add(Stat.StrengthRequired, 2));

        category.addItem(item1)
                .addItem(item2);
    }


    // Accessors
    @Test
    void getId_returnsExpectedId() {
        assertEquals(0, category.getId());
        assertEquals(1, new Category(R.string.armor, true).getId());
    }

    @Test
    void getNameId_returnsExpectedId() {
        assertEquals(R.string.weapon, category.getNameId());
    }

    @Test
    void getImageId_withNoItemsInteracted_returnsFirstItemsImageId() {
        assertEquals(item1.getImageId(), category.getImageId());
    }

    @Test
    void getImageId_withOneItemInteracted_returnsThisItemsImageId() {
        category.getItem(1).setState(SelectableItem.State.Selected);
        assertEquals(item2.getImageId(), category.getImageId());
    }

    @Test
    void getImageId_withAllItemsInteracted_returnsBestItemsImageId() {
        category.getItem(0).setState(SelectableItem.State.Selected);
        category.getItem(1).setState(SelectableItem.State.Selected);
        assertEquals(item2.getImageId(), category.getImageId());
    }

    @Test
    void getImageId_withEmptyItemsList_returnsZero() {
        category.setItems(new ArrayList<>());
        assertEquals(0, category.getImageId());
    }

    @Test
    void getBackgroundDrawableId_returnsExpectedId() {
        assertEquals(R.drawable.bg_metal_wall, category.getBackgroundDrawableId());
    }

    @Test
    void getItem_withValidIndex_returnsExpectedItem() {
        assertEquals(item2, category.getItem(1));
    }

    @Test
    void getItem_withIndexOutOfRange_returnsNull() {
        assertNull(category.getItem(-1));
        assertNull(category.getItem(2));
    }
    
    @Test
    void getItems_returnsExpectedValue() {
        List<IItem> items = category.getItems();
        assertEquals(item1, items.get(0));
        assertEquals(item2, items.get(1));
    }

    @Test
    void getSelectedItem_returnsExpectedValue() {
        category.selectedItemId = item1.getId();
        assertEquals(item1, category.getSelectedItem());
    }

    @Test
    void getStats_returnsStatsDeepCopy() {
        assertNotSame(category.stats, category.getStats());
    }

    @Test
    void getStats_withNoItemsInteracted_returnsEmptyStats() {
        IStats stats = category.getStats();
        assertEquals(0, stats.size());
    }

    @Test
    void getStats_withOneItemInteracted_returnsThisItemsStats() {
        category.getItem(0).setState(SelectableItem.State.Selected);
        IStats stats = category.getStats();
        assertEquals(4, stats.size());
        assertEquals(40, stats.get(Stat.Might));
    }

    @Test
    void getStats_withAllItemsSelectedInSelectableCategory_returnsBestItemsStats() {
        category.getItem(0).setState(SelectableItem.State.Selected);
        category.getItem(1).setState(SelectableItem.State.Selected);
        IStats stats = category.getStats();
        assertEquals(4, stats.size());
        assertEquals(80, stats.get(Stat.Might));
    }

    @Test
    void getStats_withAllItemInteractedInNotSelectableCategory_returnsSumItemsStats() {
        category.setSelectable(false);
        category.getItem(0).setState(SelectableItem.State.Bought);
        category.getItem(1).setState(SelectableItem.State.Bought);
        IStats stats = category.getStats();
        assertEquals(4, stats.size());
        assertEquals(120, stats.get(Stat.Might));
    }

    @Test
    void getStats_withEmptyItemsList_returnsEmptyStats() {
        category.setItems(new ArrayList<>());
        IStats stats = category.getStats();
        assertEquals(0, stats.size());
    }

    @Test
    void getSelectable_returnsExpectedValue() {
        assertTrue(category.isSelectable());
    }


    // Mutators
    @Test
    void setNameId_changesNameId() {
        category.setNameId(R.string.armor);
        assertEquals(R.string.armor, category.getNameId());
    }

    @Test
    void setBackgroundDrawableId_changesBackgroundDrawableId() {
        category.setBackgroundDrawableId(R.drawable.bg_wood_wall);
        assertEquals(R.drawable.bg_wood_wall, category.getBackgroundDrawableId());
    }

    @Test
    void addItem_withValidItem_addsItemToItemsList() {
        IItem item3 = new SelectableItem(R.string.ancient_artifacts, R.drawable.weapon_ancient_artifacts, 30000);
        category.addItem(item3);
        assertEquals(3, category.getItems().size());
        assertEquals(item3, category.getItem(2));
    }

    @Test
    void addItem_withNull_doesNothing() {
        category.addItem(null);
        assertEquals(2, category.getItems().size());
    }

    @Test
    void removeItem_withValidItem_removesItemFromItemsList() {
        category.removeItem(item1);
        assertEquals(1, category.getItems().size());
        assertEquals(item2, category.getItem(0));
    }

    @Test
    void removeItem_withNull_doesNothing() {
        category.removeItem(null);
        assertEquals(2, category.getItems().size());
    }

    @Test
    void setItems_withValidItemsList_changesItemsList() {
        List<IItem> items = new ArrayList<>();
        items.add(new SelectableItem(R.string.ancient_artifacts, R.drawable.weapon_ancient_artifacts, 30000));
        category.setItems(items);
        assertEquals(1, category.getItems().size());
        assertEquals(items, category.getItems());
    }

    @Test
    void setItems_withNull_setsEmptyItemsList() {
        category.setItems(null);
        assertNotNull(category.getItems());
        assertEquals(0, category.getItems().size());
    }

    @Test
    void setSelectedItem_changesSelectedItem() {
        category.setSelectedItem(item2);
        assertEquals(item2, category.getSelectedItem());
    }

    @Test
    void setSelectable_changesIsSelectable() {
        category.setSelectable(false);
        assertFalse(category.isSelectable());
    }


    // Methods
    @Test
    void containsItem_checksIfCategoryContainsItem() {
        IItem item3 = new SelectableItem(R.string.ancient_artifacts, R.drawable.weapon_ancient_artifacts, 30000);
        assertTrue(category.containsItem(item1));
        assertFalse(category.containsItem(item3));
    }

    @Test
    void getBestItem_withNoItemsInteracted_returnsNull() {
        assertNull(category.getBestItem());
    }

    @Test
    void getBestItem_withOneItemInteracted_returnsThisItem() {
        category.getItem(1).setState(SelectableItem.State.Selected);
        assertEquals(item2, category.getBestItem());
    }

    @Test
    void getBestItem_withAllItemsInteracted_returnsBestItem() {
        category.getItem(0).setState(SelectableItem.State.Selected);
        category.getItem(1).setState(SelectableItem.State.Selected);
        assertEquals(item2, category.getBestItem());
    }

    @Test
    void getBestItem_withEmptyItemsList_returnsNull() {
        category.setItems(new ArrayList<>());
        assertNull(category.getBestItem());
    }
}
