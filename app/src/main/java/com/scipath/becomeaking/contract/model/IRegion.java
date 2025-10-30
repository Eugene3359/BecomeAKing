package com.scipath.becomeaking.contract.model;

import androidx.core.util.Pair;

import com.scipath.becomeaking.model.item.Region;

public interface IRegion {

    // Accessors
    int getId();

    int getNameId();

    int getDrawableId();

    /**
     * Region sizes accessor
     *
     * @return a Pair of Float that represent width and height in range from 0 to 1
     */
    Pair<Float, Float> getSizes();

    float getWidth();

    float getHeight();

    /**
     * Region coordinates accessor
     *
     * @return a Pair of Float that represent x and y coordinates in range from 0 to 1
     */
    Pair<Float, Float> getCoordinates();

    float getX();

    float getY();


    // Mutators
    Region setNameId(int nameId);

    Region setDrawableId(int drawableId);

    /**
     * Region sizes mutator
     *
     * @param width a float that contains width in range from 0 to 1
     * @param height a float that contains height coordinate in range from 0 to 1
     */
    Region setSizes(float width, float height);

    /**
     * Region coordinates mutator
     *
     * @param x a float that contains x-axis coordinate in range from 0 to 1
     * @param y a float that contains y-axis coordinate in range from 0 to 1
     */
    Region setCoordinates(float x, float y);
}
