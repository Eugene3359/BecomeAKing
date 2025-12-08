package com.scipath.becomeaking.data;

import com.scipath.becomeaking.model.Good;
import com.scipath.becomeaking.model.Goods;
import com.scipath.becomeaking.model.enums.GoodType;


public class GoodsList {

    public static Goods getDefaultGoods() {
        Goods goods = new Goods();

        goods.add(new Good(GoodType.Wheat, 200, 5, 25));
        goods.add(new Good(GoodType.Tools, 15, 70, 250));

        return goods;
    }
}