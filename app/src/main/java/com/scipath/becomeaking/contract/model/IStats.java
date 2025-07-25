package com.scipath.becomeaking.contract.model;

import androidx.core.util.Pair;

import com.scipath.becomeaking.model.enums.Stat;

import java.util.List;


public interface IStats {

    // Accessors
    int get(Stat stat);

    List<Stat> getKeys();

    List<Integer> getValues();

    Pair<Stat, Integer> getPair(int index);


    // Mutators
    IStats add(Stat stat, int value);

    IStats remove(Stat stat);

    void merge(IStats stats);


    // Methods
    int size();
}
