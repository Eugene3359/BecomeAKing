package com.scipath.becomeaking.contract.model;

import com.scipath.becomeaking.model.Good;
import com.scipath.becomeaking.model.enums.GoodType;

import java.io.Serializable;


public interface IGoods extends Serializable {

    Good get(int position);

    Good get(GoodType type);

    IGoods add(Good good);

    IGoods remove(int position);

    IGoods remove(GoodType type);

    int size();
}
