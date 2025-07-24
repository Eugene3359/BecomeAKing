package com.scipath.becomeaking.model;

import androidx.core.util.Pair;

import com.scipath.becomeaking.contract.model.IStats;
import com.scipath.becomeaking.model.enums.Stat;

import java.util.ArrayList;
import java.util.List;


public class Stats implements IStats {

    // Fields
    private ArrayList<Stat> keys;
    private ArrayList<Integer> values;


    // Constructor
    public Stats() {
        keys = new ArrayList<>();
        values = new ArrayList<>();
    }


    // Accessors
    @Override
    public int get(Stat stat) {
        if (stat != null) {
            int index = find(stat);
            if (index == -1) return 0;
            return values.get(index);
        }
        return 0;
    }

    @Override
    public List<Stat> getKeys() {
        return new ArrayList<>(keys);
    }

    @Override
    public List<Integer> getValues() {
        return new ArrayList<>(values);
    }

    @Override
    public Pair<Stat, Integer> getPair(int index) {
        if (index < 0 || index >= keys.size()) return null;
        return new Pair<>(keys.get(index), values.get(index));
    }


    // Mutators
    @Override
    public Stats add(Stat stat, int value) {
        if (stat == null) return this;

        if (keys.contains(stat)) {
            int index = find(stat);
            keys.set(index, stat);
            values.set(index, value);
        } else {
            keys.add(stat);
            values.add(value);
        }
        return this;
    }

    @Override
    public Stats remove(Stat stat) {
        if (stat != null) {
            int index = find(stat);
            if (index == -1) return this;
            keys.remove(index);
            values.remove(index);
        }
        return this;
    }

    @Override
    public void merge(IStats stats) {
        if (stats == null) return;

        for (Stat stat : stats.getKeys()) {
            int current = this.get(stat);
            int value = stats.get(stat);
            this.add(stat, current + value);
        }
    }


    // Methods
    @Override
    public int size() {
        return keys.size();
    }

    protected int find(Stat stat) {
        return keys.indexOf(stat);
    }
}
