package com.scipath.becomeaking.model.enums;

import com.scipath.becomeaking.R;

import java.io.Serializable;


public enum Sex implements Serializable {
    Male(R.string.male),
    Female(R.string.female);


    public final int nameId;


    Sex(int nameId) {
        this.nameId = nameId;
    }
}
