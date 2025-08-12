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
        Stats.idCounter = 0;
        stats = new Stats()
                .add(Stat.MaxHealth, 100)
                .add(Stat.Might, 10);
    }

    @Test
    void getId_returnsExpectedId() {
        assertEquals(0, stats.getId());
        assertEquals(1, new Stats().getId());
    }

    @Test
    void get_withExistingStat_returnsExpectedValue() {
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
        List<Stat> keys = stats.getKeys();
        assertEquals(2, keys.size());
        assertEquals(Stat.MaxHealth, keys.get(0));
        keys.clear(); // Modifying copy
        assertEquals(2, stats.size()); // Original still intact
    }

    @Test
    void getValues_returnsCopy() {
        List<Integer> values = stats.getValues();
        assertEquals(2, values.size());
        assertEquals(100, values.get(0));
        values.clear();
        assertEquals(2, stats.size());
    }

    @Test
    void getPair_withValidIndex_returnsExpectedValue() {
        Pair<Stat, Integer> pair = stats.getPair(0);
        assertEquals(Stat.MaxHealth, pair.first);
        assertEquals(100, pair.second);
    }

    @Test
    void getPair_withIndexOutOfRange_returnsNull() {
        assertNull(stats.getPair(-1));
        assertNull(stats.getPair(2));
    }

    @Test
    void add_withNewStat_addsStat() {
        stats.add(Stat.StrengthRequired, 1);
        assertEquals(3, stats.size());
        assertEquals(1, stats.get(Stat.StrengthRequired));
    }

    @Test
    void add_withExistingStat_updatesStatsValue() {
        stats.add(Stat.MaxHealth, 150);
        assertEquals(2, stats.size());
        assertEquals(150, stats.get(Stat.MaxHealth));
    }

    @Test
    void add_withNull_doesNothing() {
        stats.add(null, 10);
        assertEquals(2, stats.size());
    }

    @Test
    void remove_withExistingStat_removesStat() {
        stats.remove(Stat.MaxHealth);
        assertEquals(1, stats.size());
        assertEquals(0, stats.get(Stat.MaxHealth));
    }

    @Test
    void remove_withMissingStat_doesNothing() {
        stats.remove(Stat.StrengthRequired);
        assertEquals(2, stats.size());
    }

    @Test
    void remove_withNull_doesNothing() {
        stats.remove(null);
        assertEquals(2, stats.size());
    }

    @Test
    void merge_withValidValue_mergesStats() {
        Stats other = new Stats();
        other.add(Stat.Might, 20);
        other.add(Stat.StrengthRequired, 2);
        stats.merge(other);
        assertEquals(3, stats.size());
        assertEquals(100, stats.get(Stat.MaxHealth));
        assertEquals(30, stats.get(Stat.Might));
        assertEquals(2, stats.get(Stat.StrengthRequired));
    }

    @Test
    void merge_withNull_doesNothing() {
        stats.merge(null);
        assertEquals(2, stats.size());
    }

    @Test
    void size_returnsSize() {
        assertEquals(2, stats.size());
    }

    @Test
    void clone_returnsDeepCopy() {
        IStats cloned = stats.clone();
        assertNotSame(stats, cloned);
        assertEquals(stats.size(), cloned.size());
        assertEquals(stats.get(Stat.MaxHealth), cloned.get(Stat.MaxHealth));
    }
}
