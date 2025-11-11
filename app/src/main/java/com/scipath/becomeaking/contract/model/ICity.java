package com.scipath.becomeaking.contract.model;

import androidx.core.util.Pair;

import java.util.Set;

public interface ICity {

    // Accessors
    int getId();

    int getNameId();

    int getDescriptionId();

    int getImageId();

    /**
     * City coordinates accessor
     *
     * @return a Pair of Float that represent x and y coordinates in range from 0 to 1
     */
    Pair<Float, Float> getCoordinates();

    float getX();

    float getY();

    /**
     * Routes set accessor
     *
     * @return a Set of cities that are directly connected to this city by a route
     */
    Set<ICity> getRoutes();


    // Mutators
    ICity setNameId(int nameId);

    ICity setDescriptionId(int descriptionId);

    ICity setImageId(int imageId);

    /**
     * City coordinates mutator
     * @param x a float that contains x-axis coordinate in range from 0 to 1
     * @param y a float that contains y-axis coordinate in range from 0 to 1
     */
    ICity setCoordinates(float x, float y);

    ICity addRoute(ICity city);

    ICity setRoutes(Set<ICity> cities);
}
