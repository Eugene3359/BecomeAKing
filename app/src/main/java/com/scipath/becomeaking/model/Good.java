package com.scipath.becomeaking.model;

import com.scipath.becomeaking.contract.model.IGood;
import com.scipath.becomeaking.model.enums.GoodType;

import java.util.Random;


public class Good implements IGood {

    private GoodType type;
    private int amount;
    private int minPrice;
    private int maxPrice;
    private int bankAveragePrice;
    private int buyPrice;
    private int sellPrice;


    // Constructors
    public Good(GoodType type, int amount) {
        this.type = type;
        setAmount(amount);
    }

    public Good(GoodType type, int minPrice, int maxPrice) {
        this.type = type;
        setMinPrice(minPrice);
        setMaxPrice(maxPrice);
        recalculatePrices();
    }

    public Good(GoodType type, int amount, int minPrice, int maxPrice) {
        this(type, minPrice, maxPrice);
        setAmount(amount);
    }


    // Accessors
    @Override
    public GoodType getType() { return type; }

    @Override
    public int getAmount() {
        return amount;
    }

    @Override
    public int getMinPrice() {
        return minPrice;
    }

    @Override
    public int getMaxPrice() {
        return maxPrice;
    }

    @Override
    public int getBankAveragePrice() {
        return bankAveragePrice;
    }

    @Override
    public int getBuyPrice() {
        return buyPrice;
    }

    @Override
    public int getSellPrice() {
        return sellPrice;
    }


    // Mutators
    @Override
    public void setAmount(int amount) {
        this.amount = Math.max(amount, 0);
    }

    @Override
    public void affectAmount(int amount) {
        this.amount = Math.max(this.amount + amount, 0);
    }

    @Override
    public void setMinPrice(int price) {
        minPrice = Math.max(price, 0);
    }

    @Override
    public void setMaxPrice(int price) {
        maxPrice = Math.max(price, 0);
    }


    // Methods
    @Override
    public void recalculatePrices() {
        Random random = new Random();
        int third = Math.round((maxPrice - minPrice) / 3.0f);
        // bankAveragePrice is in middle third of prices
        bankAveragePrice = third + random.nextInt(third + 1);

        // buyPrice is over bankAveragePrice
        buyPrice = bankAveragePrice + random.nextInt(third) + 2;

        // sellPrice is under bankAveragePrice
        sellPrice = bankAveragePrice - random.nextInt(third) - 2;
    }
}
