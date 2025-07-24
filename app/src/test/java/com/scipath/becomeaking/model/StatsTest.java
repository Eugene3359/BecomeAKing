package com.scipath.becomeaking.model;

import com.scipath.becomeaking.contract.model.IStats;
import com.scipath.becomeaking.model.enums.Stat;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

import androidx.core.util.Pair;

import java.util.List;


public class StatsTest {

    private IStats stats;


    @BeforeEach
    void setUp() {
        stats = new Stats();
    }

    @Test
    void get_withExistingStat_returnsExpectedValue() {
        stats.add(Stat.MaxHealth, 100);
        assertEquals(100, stats.get(Stat.MaxHealth));
    }

    @Test
    void get_withMissingStat_returnsZero() {
        assertEquals(0, stats.get(Stat.Might));
    }

    @Test
    void get_withNull_returnsZero() {
        assertEquals(0, stats.get(null));
    }

    @Test
    void getKeys_returnsCopy() {
        stats.add(Stat.MaxHealth, 100);
        List<Stat> keys = stats.getKeys();
        assertEquals(Stat.MaxHealth, keys.get(0));
        keys.clear(); // modifying copy
        assertEquals(1, stats.size()); // original still intact
    }

    @Test
    void getValues_returnsCopy() {
        stats.add(Stat.MaxHealth, 100);
        List<Integer> values = stats.getValues();
        assertEquals(100, values.get(0));
        values.clear();
        assertEquals(1, stats.size());
    }

    @Test
    void getPair_withValidIndex_returnsExpectedValue() {
        stats.add(Stat.MaxHealth, 100);
        Pair<Stat, Integer> pair = stats.getPair(0);
        assertNotNull(pair);
        assertEquals(Stat.MaxHealth, pair.first);
        assertEquals(100, pair.second);
    }

    @Test
    void getPair_withInvalidIndex_returnsNull() {
        stats.add(Stat.MaxHealth, 100);
        stats.add(Stat.Might, 1);
        assertNull(stats.getPair(-1));
        assertNull(stats.getPair(2));
    }

    @Test
    void add_withNewStat() {
        stats.add(Stat.MaxHealth, 100);
        assertEquals(1, stats.size());
        assertEquals(100, stats.get(Stat.MaxHealth));
    }

    @Test
    void add_withExistingStat_updatesValue() {
        stats.add(Stat.MaxHealth, 100);
        stats.add(Stat.MaxHealth, 150);
        assertEquals(1, stats.size());
        assertEquals(150, stats.get(Stat.MaxHealth));
    }

    @Test
    void add_withNull_doesNothing() {
        stats.add(null, 5);
        assertEquals(0, stats.size());
    }

    @Test
    void remove_withExistingStat_removesStat() {
        stats.add(Stat.MaxHealth, 100);
        stats.remove(Stat.MaxHealth);
        assertEquals(0, stats.size());
        assertEquals(0, stats.get(Stat.MaxHealth));
    }

    @Test
    void remove_withMissingStat_doesNothing() {
        stats.add(Stat.MaxHealth, 100);
        stats.remove(Stat.Might);
        assertEquals(1, stats.size());
        assertEquals(100, stats.get(Stat.MaxHealth));
    }

    @Test
    void remove_withNull_doesNothing() {
        stats.add(Stat.MaxHealth, 100);
        stats.remove(null);
        assertEquals(1, stats.size());
    }

    @Test
    void merge_withValidValue() {
        stats.add(Stat.MaxHealth, 100);
        stats.add(Stat.Might, 1);

        Stats other = new Stats();
        other.add(Stat.HealthPerDay, 10);
        other.add(Stat.Might, 2);

        stats.merge(other);

        assertEquals(3, stats.size());
        assertEquals(100, stats.get(Stat.MaxHealth));
        assertEquals(3, stats.get(Stat.Might));
        assertEquals(10, stats.get(Stat.HealthPerDay));
    }

    @Test
    void merge_withNull_doesNothing() {
        stats.add(Stat.MaxHealth, 100);
        stats.merge(null);
        assertEquals(1, stats.size());
        assertEquals(100, stats.get(Stat.MaxHealth));
    }
}
