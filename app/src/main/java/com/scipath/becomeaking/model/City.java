package com.scipath.becomeaking.model;

import androidx.core.util.Pair;

import com.scipath.becomeaking.contract.model.ICity;

import java.util.HashSet;
import java.util.Set;


public class City implements ICity {

    // Fields
    protected static int idCounter = 0;
    protected int id;
    protected int nameId;
    protected Pair<Float, Float> coordinates;
    protected Set<ICity> routes;


    // Constructors
    public City(int nameId, float x, float y) {
        this.id = idCounter++;
        this.nameId = nameId;
        this.coordinates = new Pair<>(x, y);
        this.routes = new HashSet<>();
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
        x = Math.max(0, Math.min(1, x));
        y = Math.max(0, Math.min(1, y));
        coordinates = new Pair<>(x, y);
        return this;
    }

    @Override
    public ICity addRoute(ICity city) {
        if (city != null && city != this) {
            routes.add(city);
            city.getRoutes().add(this);
        }
        return this;
    }

    @Override
    public ICity setRoutes(Set<ICity> cities) {
        if (cities != null) {
            routes = new HashSet<>();
            for(ICity city : cities) {
                if (city == this) continue;
                addRoute(city);
            }
        }
        return this;
    }
}
