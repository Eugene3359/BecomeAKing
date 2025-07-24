package com.scipath.becomeaking.model;

import com.scipath.becomeaking.contract.model.ICategory;

import java.io.Serializable;
import java.util.ArrayList;

public class GameState implements Serializable {

    public int day;
    public Personage personage;
    public ArrayList<ICategory> categories;

    public GameState(int day, Personage personage, ArrayList<ICategory> categories) {
        this.day = day;
        this.personage = personage;
        this.categories = categories;
    }
}