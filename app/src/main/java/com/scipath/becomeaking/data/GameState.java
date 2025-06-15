package com.scipath.becomeaking.data;

import com.scipath.becomeaking.model.Category;
import com.scipath.becomeaking.model.Personage;

import java.io.Serializable;
import java.util.ArrayList;

public class GameState implements Serializable {

    public int day;
    public Personage personage;
    public ArrayList<Category> categories;

    public GameState(int day, Personage personage, ArrayList<Category> categories) {
        this.day = day;
        this.personage = personage;
        this.categories = categories;
    }
}