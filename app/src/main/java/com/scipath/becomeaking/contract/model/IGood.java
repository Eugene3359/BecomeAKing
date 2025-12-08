package com.scipath.becomeaking.contract.model;

import com.scipath.becomeaking.model.enums.GoodType;


public interface IGood {

    // Accessors
    GoodType getType();
    int getAmount();
    int getMinPrice();
    int getMaxPrice();
    int getBankAveragePrice();
    int getBuyPrice();
    int getSellPrice();


    // Mutators
    void setAmount(int amount);
    void affectAmount(int amount);
    void setMinPrice(int price);
    void setMaxPrice(int price);


    // Methods
    void recalculatePrices();
}
