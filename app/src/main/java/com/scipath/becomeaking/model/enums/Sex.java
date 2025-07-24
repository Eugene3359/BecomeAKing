package com.scipath.becomeaking.model.enums;

import android.content.Context;

import com.scipath.becomeaking.R;

import java.io.Serializable;


public enum Sex implements Serializable {

    // Sexes
    Male(R.string.male),
    Female(R.string.female);


    // Individual sex fields
    private final int nameId;


    // Constructor
    private Sex(int nameId) {
        this.nameId = nameId;
    }


    // Accessors
    public int getNameId() {
        return nameId;
    }


    // Methods
    /**
     * Sex name resource accessor
     *
     * @param context   The Context that provides access to resources
     * @return          The String that contain sex name
     */
    public String getName(Context context) {
        return context.getString(nameId);
    }
}
