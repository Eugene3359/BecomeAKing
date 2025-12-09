package com.scipath.becomeaking.data;

import com.scipath.becomeaking.model.Good;
import com.scipath.becomeaking.model.Goods;
import com.scipath.becomeaking.model.enums.GoodType;


public class GoodsList {

    public static Goods getDefaultGoods() {
        Goods goods = new Goods();

        goods   .add(new Good(GoodType.Wheat, 200, 5, 25))
                .add(new Good(GoodType.Tools, 15, 70, 250))
                .add(new Good(GoodType.Spices, 50, 150, 1000))
                .add(new Good(GoodType.Fish, 75, 10, 40))
                .add(new Good(GoodType.Jewelry, 20, 200, 1000));

        return goods;
    }
}