package com.scipath.becomeaking.contract.model;

import com.scipath.becomeaking.model.enums.GoodType;


public interface IGoods {

    IGood get(int position);

    IGood get(GoodType type);

    IGoods add(IGood good);

    IGoods remove(int position);

    IGoods remove(GoodType type);

    int size();
}
