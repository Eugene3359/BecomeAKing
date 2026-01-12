package com.scipath.becomeaking.model;

import com.scipath.becomeaking.contract.model.IGood;
import com.scipath.becomeaking.contract.model.IGoods;
import com.scipath.becomeaking.model.enums.GoodType;

import java.util.ArrayList;
import java.util.List;


public class Goods implements IGoods {

    private final List<IGood> goods;


    public Goods() {
        goods = new ArrayList<>();
    }


    @Override
    public IGood get(int position) {
        if (position < 0 || position >= goods.size()) return null;
        return goods.get(position);
    }

    @Override
    public IGood get(GoodType type) {
        return get(find(type));
    }

    @Override
    public IGoods add(IGood good) {
        int position = find(good.getType());
        if (position == -1) {
            goods.add(good);
        } else {
            goods.set(position, good);
        }
        return this;
    }

    @Override
    public IGoods remove(int position) {
        if (position < 0 || position >= goods.size()) return this;
        goods.remove(position);
        return this;
    }

    @Override
    public IGoods remove(GoodType type) {
        int position = find(type);
        if (position != -1) goods.remove(position);
        return this;
    }

    @Override
    public int size() {
        return goods.size();
    }

    private int find(GoodType type) {
        for (int i = 0; i < goods.size(); i++) {
            if (goods.get(i).getType() == type) return i;
        }
        return -1;
    }
}
