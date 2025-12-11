package com.scipath.becomeaking.data;

import com.scipath.becomeaking.model.Good;
import com.scipath.becomeaking.model.Goods;
import com.scipath.becomeaking.model.enums.GoodType;


public class GoodsList {

    public static Goods getDefaultGoods() {
        Goods goods = new Goods();

        goods   .add(new Good(GoodType.Wheat, 250, 5, 25))
                .add(new Good(GoodType.Meat, 100, 15, 60))
                .add(new Good(GoodType.Fish, 100, 10, 40))
                .add(new Good(GoodType.Cheese, 100, 25, 105))
                .add(new Good(GoodType.Spices, 85, 150, 1000))
                .add(new Good(GoodType.Honey, 85, 20, 110))
                .add(new Good(GoodType.Beer, 75, 50,150))
                .add(new Good(GoodType.Vine, 75, 25, 230))
                .add(new Good(GoodType.Wool, 50, 25, 80))
                .add(new Good(GoodType.Velvet, 50, 135, 600))
                .add(new Good(GoodType.Silk, 50,40, 150))
                .add(new Good(GoodType.Dishes, 50, 80, 250))
                .add(new Good(GoodType.Jewelry, 50, 200, 1000))
                .add(new Good(GoodType.BuildingMaterials, 100, 10, 80))
                .add(new Good(GoodType.Tools, 40, 70, 250))
                .add(new Good(GoodType.Weapon, 25, 200, 3000))
                .add(new Good(GoodType.Armor, 10, 500, 5000))
                .add(new Good(GoodType.Medicine, 75, 150,1000));

        return goods;
    }
}