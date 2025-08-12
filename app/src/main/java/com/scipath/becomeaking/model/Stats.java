package com.scipath.becomeaking.model;

import androidx.annotation.NonNull;
import androidx.core.util.Pair;

import com.scipath.becomeaking.contract.model.IStats;
import com.scipath.becomeaking.model.enums.Stat;

import java.util.ArrayList;
import java.util.List;


public class Stats implements IStats {

    // Fields
    protected static int idCounter = 0;

    protected int id;
    protected ArrayList<Stat> keys;
    protected ArrayList<Integer> values;


    // Constructor
    public Stats() {
        id = idCounter++;
        keys = new ArrayList<>();
        values = new ArrayList<>();
    }


    // Accessors
    @Override
    public int getId() {
        return id;
    }

    @Override
    public int get(Stat stat) {
        if (stat == null) return 0;

        int index = find(stat);
        if (index == -1) return 0;

        return values.get(index);
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
            values.set(find(stat), value);
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
            add(stat, current + value);
        }
    }


    // Methods
    protected int find(Stat stat) {
        return keys.indexOf(stat);
    }

    @Override
    public int size() {
        return keys.size();
    }

    @NonNull
    @Override
    public IStats clone() {
        try {
            return (IStats) super.clone();
        } catch (CloneNotSupportedException e) {
            throw new AssertionError();
        }
    }
}
