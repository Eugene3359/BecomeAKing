package com.scipath.becomeaking.model;

import com.scipath.becomeaking.contract.model.ICategory;
import com.scipath.becomeaking.data.CitiesList;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Random;


public class GameState implements Serializable {

    public Personage personage;
    public ArrayList<ICategory> categories;
    public Goods goodsStorage;
    public Bank bank;
    public int cityId;
    public int day;


    public GameState(Personage personage, ArrayList<ICategory> categories, Goods goodsStorage) {
        this.personage = personage;
        this.categories = categories;
        this.goodsStorage = goodsStorage;
        this.bank = Bank.getInstance();
        this.cityId = new Random().nextInt(CitiesList.getCities().size());
        this.day = 1;
    }
}