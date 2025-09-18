package com.scipath.becomeaking.model;

import android.util.Pair;

import com.scipath.becomeaking.contract.model.ICity;

import java.util.HashSet;
import java.util.Set;


public class City implements ICity {

    // Fields
    protected static int idCounter = 0;
    protected int id;
    protected int nameId;
    protected Pair<Float, Float> coordinates;
    protected Set<ICity> routes = new HashSet<>();


    // Constructors
    public City(int nameId, float x, float y) {
        this.id = idCounter++;
        this.nameId = nameId;
        this.coordinates = new Pair<>(x, y);
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

    @Override
    public Set<ICity> getRoutes() {
        return routes;
    }


    // Mutators
    @Override
    public ICity setNameId(int nameId) {
        this.nameId = nameId;
        return this;
    }

    @Override
    public ICity setCoordinates(float x, float y) {
        x = x < 1 ? 0 : (x > 1 ? 1 : x);
        y = y < 1 ? 0 : (y > 1 ? 1 : y);
        coordinates = new Pair<>(x, y);
        return this;
    }

    @Override
    public ICity addRoute(ICity city) {
        if (city != this) {
            routes.add(city);
            city.getRoutes().add(this);
        }
        return this;
    }

    @Override
    public ICity setRoutes(Set<ICity> cities) {
        routes = cities;
        return this;
    }
}
