package com.scipath.becomeaking.model;

import static org.junit.jupiter.api.Assertions.*;

import com.scipath.becomeaking.R;
import com.scipath.becomeaking.contract.model.ICity;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashSet;
import java.util.Set;


class CityTest {

    ICity city1;
    ICity city2;


    @BeforeEach
    void setUp() {
        City.idCounter = 0;
        city1 = new City(R.string.grimshaven, 0.135f, 0.245f);
        city2 = new City(R.string.farendol, 0.175f, 0.405f);
    }

    @Test
    void getId_returnsExpectedId() {
        assertEquals(0, city1.getId());
        assertEquals(1, city2.getId());
    }

    @Test
    void getNameId_returnsExpectedId() {
        assertEquals(R.string.grimshaven, city1.getNameId());
    }

    @Test
    void getCoordinates_returnsExpectedValue() {
        assertEquals(0.135f, city1.getCoordinates().first);
        assertEquals(0.245f, city1.getCoordinates().second);
    }

    @Test
    void getX_returnsExpectedValue() {
        assertEquals(0.135f, city1.getX());
    }

    @Test
    void getY_returnsExpectedValue() {
        assertEquals(0.245f, city1.getY());
    }

    @Test
    void getRoutes_withEmptyRoutesSet_returnsEmptySet() {
        assertEquals(0, city1.getRoutes().size());
    }

    @Test
    void getRoutes_withFilledRoutesSet_returnsExpectedValues() {
        city1.addRoute(city2);
        assertEquals(1, city1.getRoutes().size());
        assertTrue(city1.getRoutes().contains(city2));
    }

    @Test
    void setNameId_changesNameId() {
        city1.setNameId(R.string.thornford);
        assertEquals(R.string.thornford, city1.getNameId());
    }

    @Test
    void setCoordinates_withValuesBelowZero_setsCoordinatesToZero() {
        city1.setCoordinates(-0.5f, -0.5f);
        assertEquals(0, city1.getX());
        assertEquals(0, city1.getY());
    }

    @Test
    void setCoordinates_withValuesAboveOne_setsCoordinatesToOne() {
        city1.setCoordinates(1.5f, 1.5f);
        assertEquals(1, city1.getX());
        assertEquals(1, city1.getY());
    }

    @Test
    void setCoordinates_withValidValues_changesCoordinates() {
        city1.setCoordinates(0.25f, 0.75f);
        assertEquals(0.25f, city1.getX());
        assertEquals(0.75f, city1.getY());
    }

    @Test
    void addRoute_withNull_doesNothing() {
        city1.addRoute(null);
        assertEquals(0, city1.getRoutes().size());
    }

    @Test
    void addRoute_withSameCity_doesNothing() {
        city1.addRoute(city1);
        assertEquals(0, city1.getRoutes().size());
    }

    @Test
    void addRoute_withValidValue_connectsCities() {
        city1.addRoute(city2);
        assertEquals(1, city1.getRoutes().size());
        assertTrue(city1.getRoutes().contains(city2));
        city2.addRoute(city1);
        assertEquals(1, city2.getRoutes().size());
        assertTrue(city2.getRoutes().contains(city1));
    }

    @Test
    void setRoutes_withNull_doesNoting() {
        city1.setRoutes(null);
        assertEquals(0, city1.getRoutes().size());
    }

    @Test
    void setRoutes_withValidValue_changesRoutesSet() {
        ICity city3 = new City(R.string.thornford, 0.230f, 0.385f);
        Set<ICity> cities = new HashSet<>();
        cities.add(city2);
        cities.add(city3);
        city1.setRoutes(cities);
        assertEquals(2, city1.getRoutes().size());
        assertTrue(city1.getRoutes().contains(city2));
        assertTrue(city1.getRoutes().contains(city3));
        assertEquals(1, city2.getRoutes().size());
        assertTrue(city2.getRoutes().contains(city1));
        assertEquals(1, city3.getRoutes().size());
        assertTrue(city3.getRoutes().contains(city1));
    }
}