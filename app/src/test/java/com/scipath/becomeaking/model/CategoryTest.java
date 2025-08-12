package com.scipath.becomeaking.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNotSame;
import static org.junit.jupiter.api.Assertions.assertNull;

import com.scipath.becomeaking.R;
import com.scipath.becomeaking.contract.model.ICategory;
import com.scipath.becomeaking.contract.model.ICategory.*;
import com.scipath.becomeaking.contract.model.IItem;
import com.scipath.becomeaking.contract.model.IStats;
import com.scipath.becomeaking.model.enums.Stat;
import com.scipath.becomeaking.model.item.Item;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;


public class CategoryTest {

    ICategory category;


    @BeforeEach
    void setUp() {
        Category.idCounter = 0;
        category = new Category(R.string.weapon);
        category.addItem(new Item(R.string.steel_sword, R.drawable.img_steel_sword, 1000))
                .addItem(new Item(R.string.two_blades, R.drawable.img_two_blades, 3000));

        // Steel sword
        category.getItems().get(0).setStats(new Stats()
                .add(Stat.Might, 40)
                .add(Stat.ReputationPerDay, 20)
                .add(Stat.CostPerDay, -20)
                .add(Stat.StrengthRequired, 2));
        // Two blades
        category.getItems().get(1).setStats(new Stats()
                .add(Stat.Might, 80)
                .add(Stat.ReputationPerDay, 50)
                .add(Stat.CostPerDay, -40)
                .add(Stat.StrengthRequired, 2));
    }

    @Test
    void getId_returnsExpectedId() {
        assertEquals(0, category.getId());
        assertEquals(1, new Category(R.string.armor).getId());
    }

    @Test
    void getNameId_returnsExpectedId() {
        assertEquals(R.string.weapon, category.getNameId());
    }

    @Test
    void getImageId_withNoBoughtItems_returnsFirstItemsImageId() {
        assertEquals(R.drawable.img_steel_sword, category.getImageId());
    }

    @Test
    void getImageId_withOneItemBought_returnsThisItemsImageId() {
        category.getItems().get(0).setBought(true);
        assertEquals(R.drawable.img_steel_sword, category.getImageId());
        category.getItems().get(0).setBought(false);
        category.getItems().get(1).setBought(true);
        assertEquals(R.drawable.img_two_blades, category.getImageId());
    }

    @Test
    void getImageId_withAllItemsBought_returnsBestItemsImageId() {
        category.getItems().get(0).setBought(true);
        category.getItems().get(1).setBought(true);
        assertEquals(R.drawable.img_two_blades, category.getImageId());
    }

    @Test
    void getImageId_withEmptyItemsList_returnsZero() {
        category.setItems(new ArrayList<>());
        assertEquals(0, category.getImageId());
    }

    @Test
    void getItems_returnsExpectedValue() {
        List<IItem> items = category.getItems();
        assertEquals(2, items.size());
        assertEquals(R.string.steel_sword, items.get(0).getNameId());
        assertEquals(R.string.two_blades, items.get(1).getNameId());
    }

    @Test
    void getStatsMod_returnsExpectedValue() {
        assertEquals(StatsMod.Best, category.getStatsMod()); // Initial value
    }

    @Test
    void getStats_returnsStatsDeepCopy() {
        IStats stats = category.getStats();
        assertNotSame(stats, category.getStats());
    }

    @Test
    void getStats_withNoBoughtItems_returnsEmptyStats() {
        IStats stats = category.getStats();
        assertEquals(0, stats.size());
    }

    @Test
    void getStats_withOneItemBought_returnsThisItemsStats() {
        category.getItems().get(0).setBought(true);
        IStats stats = category.getStats();
        assertEquals(4, stats.size());
        assertEquals(40, stats.get(Stat.Might));
        category.getItems().get(0).setBought(false);
        category.getItems().get(1).setBought(true);
        stats = category.getStats();
        assertEquals(4, stats.size());
        assertEquals(80, stats.get(Stat.Might));
    }

    @Test
    void getStats_withAllItemBoughtAndBestStatsMod_returnsBestItemsStats() {
        category.getItems().get(1).setBought(true);
        category.getItems().get(0).setBought(true);
        IStats stats = category.getStats();
        assertEquals(4, stats.size());
        assertEquals(80, stats.get(Stat.Might));
    }

    @Test
    void getStats_withAllItemBoughtAndSumStatsMod_returnsSumItemsStats() {
        category.setStatsMod(StatsMod.Sum);
        category.getItems().get(0).setBought(true);
        category.getItems().get(1).setBought(true);
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
    void setNameId_changesNameId() {
        category.setNameId(R.string.armor);
        assertEquals(R.string.armor, category.getNameId());
    }

    @Test
    void setItems_withValidItemsList_changesItemsList() {
        List<IItem> items = new ArrayList<>();
        items.add(new Item(R.string.ancient_artifacts, R.drawable.img_ancient_artifacts, 30000));
        items.get(0).getStats()
                .add(Stat.Might, 200)
                .add(Stat.ReputationPerDay, 100)
                .add(Stat.CostPerDay, -200)
                .add(Stat.StrengthRequired, 4);
        category.setItems(items);
        assertEquals(1, category.getItems().size());
        assertEquals(R.string.ancient_artifacts, category.getItems().get(0).getNameId());
        assertEquals(200, category.getItems().get(0).getStats().get(Stat.Might));
    }

    @Test
    void setItems_withNull_setsEmptyItemsList() {
        category.setItems(null);
        assertNotNull(category.getItems());
        assertEquals(0, category.getItems().size());
    }

    @Test
    void addItem_withValidItem_addsItemToItemsList() {
        IItem item = new Item(R.string.ancient_artifacts, R.drawable.img_ancient_artifacts, 30000);
        item.getStats()
                .add(Stat.Might, 200)
                .add(Stat.ReputationPerDay, 100)
                .add(Stat.CostPerDay, -200)
                .add(Stat.StrengthRequired, 4);
        category.addItem(item);
        assertEquals(3, category.getItems().size());
        assertEquals(R.string.ancient_artifacts, category.getItems().get(2).getNameId());
        assertEquals(200, category.getItems().get(2).getStats().get(Stat.Might));
    }

    @Test
    void addItem_withNull_doesNothing() {
        category.addItem(null);
        assertEquals(2, category.getItems().size());
    }

    @Test
    void setStatsMod_withValidStatsMod_changesStatsMod() {
        category.setStatsMod(StatsMod.Sum);
        assertEquals(StatsMod.Sum, category.getStatsMod());
    }

    @Test
    void setStatsMod_withNull_doesNothing() {
        category.setStatsMod(null);
        assertEquals(StatsMod.Best, category.getStatsMod());
    }

    @Test
    void getBestBoughtItem_withNoBoughItems_returnsNull() {
        assertNull(category.getBestBoughtItem());
    }

    @Test
    void getBestBoughtItem_withOneBoughtItem_returnsThisItem() {
        category.getItems().get(0).setBought(true);
        assertEquals(R.string.steel_sword, category.getBestBoughtItem().getNameId());
        category.getItems().get(0).setBought(false);
        category.getItems().get(1).setBought(true);
        assertEquals(R.string.two_blades, category.getBestBoughtItem().getNameId());
    }

    @Test
    void getBestBoughtItem_withAllItemsBought_returnsBestItem() {
        category.getItems().get(0).setBought(true);
        category.getItems().get(1).setBought(true);
        assertEquals(R.string.two_blades, category.getBestBoughtItem().getNameId());
    }

    @Test
    void getBestBoughtItem_withEmptyItemsList_returnsNull() {
        category.setItems(new ArrayList<>());
        assertNull(category.getBestBoughtItem());
    }
}
