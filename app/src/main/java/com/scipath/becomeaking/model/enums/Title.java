package com.scipath.becomeaking.model.enums;

import com.scipath.becomeaking.R;

import java.io.Serializable;


public enum Title implements Serializable {
    Rogue(R.string.rogue, 100),
    Villager(R.string.villager, 125),
    Bandit(R.string.bandit, 150);


    public final int nameId;
    public final int maxHealth;


    private Title(int nameId, int maxHealth) {
        this.nameId = nameId;
        this.maxHealth = maxHealth;
    }
}
