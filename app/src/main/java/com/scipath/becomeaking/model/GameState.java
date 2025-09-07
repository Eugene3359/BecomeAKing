package com.scipath.becomeaking.model;

import com.scipath.becomeaking.contract.model.ICategory;

import java.io.Serializable;
import java.util.ArrayList;


public class GameState implements Serializable {

    public Personage personage;
    public ArrayList<ICategory> categories;
    public int day;


    public GameState(Personage personage, ArrayList<ICategory> categories, int day) {
        this.personage = personage;
        this.categories = categories;
        this.day = day;
    }
}