package com.scipath.becomeaking.model;

import androidx.core.util.Pair;

import com.scipath.becomeaking.contract.model.IRegion;

public class Region implements IRegion {

    // Fields
    protected static int idCounter = 0;

    protected int id;
    protected int nameId;
    protected int drawableId;
    protected Pair<Float, Float> sizes;
    protected Pair<Float, Float> coordinates;


    // Constructors
    public Region() {
        this(0, 0, 0f, 0f, 0f, 0f);
    }

    public Region(int nameId, int drawableId, float width, float height, float x, float y) {
        this.id = idCounter++;
        this.nameId = nameId;
        this.drawableId = drawableId;
        setSizes(width, height);
        setCoordinates(x, y);
    }


    // Accessors
    @Override
    public int getId() {
        return id;
    }

    @Override
    public int getNameId() {
        return nameId;
    }

    @Override
    public int getDrawableId() {
        return drawableId;
    }

    @Override
    public Pair<Float, Float> getSizes() {
        return sizes;
    }

    @Override
    public float getWidth() {
        return sizes.first;
    }

    @Override
    public float getHeight() {
        return sizes.second;
    }

    @Override
    public Pair<Float, Float> getCoordinates() {
        return coordinates;
    }

    @Override
    public float getX() {
        return coordinates.first;
    }

    @Override
    public float getY() {
        return coordinates.second;
    }


    // Mutators
    @Override
    public IRegion setNameId(int nameId) {
        this.nameId = nameId;
        return this;
    }

    @Override
    public IRegion setDrawableId(int drawableId) {
        this.drawableId = drawableId;
        return this;
    }

    @Override
    public IRegion setSizes(float width, float height) {
        width = Math.max(0, Math.min(1, width));
        height = Math.max(0, Math.min(1, height));
        sizes = new Pair<>(width, height);
        return this;
    }

    @Override
    public IRegion setCoordinates(float x, float y) {
        x = Math.max(0, Math.min(1, x));
        y = Math.max(0, Math.min(1, y));
        coordinates = new Pair<>(x, y);
        return this;
    }
}
