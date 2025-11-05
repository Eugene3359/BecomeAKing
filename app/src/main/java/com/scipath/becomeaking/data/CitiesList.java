package com.scipath.becomeaking.data;

import com.scipath.becomeaking.R;
import com.scipath.becomeaking.contract.model.ICity;
import com.scipath.becomeaking.model.City;

import java.util.ArrayList;


public class CitiesList {

    private static ArrayList<ICity> cities;


    private static void init () {
        cities = new ArrayList<>();

        cities.add(new City(R.string.grimshaven, 0.135f, 0.245f));

        cities.add(new City(R.string.farendol, 0.175f, 0.405f));
        cities.get(1).addRoute(cities.get(0));                  // Farendol - Grimshaven

        cities.add(new City(R.string.thornford, 0.230f, 0.385f));
        cities.get(2).addRoute(cities.get(1));                  // Thornford - Farendol

        cities.add(new City(R.string.drakkenburg, 0.330f, 0.145f));
        cities.get(3).addRoute(cities.get(0))                   // Drakkenburg - Grimshaven
                .addRoute(cities.get(1))                        // Drakkenburg - Farendol
                .addRoute(cities.get(2));                       // Drakkenburg - Thornford

        cities.add(new City(R.string.steinhart, 0.335f, 0.685f));
        cities.get(4).addRoute(cities.get(2))                   // Steinhart - Thornford
                .addRoute(cities.get(3));                       // Steinhart - Drakkenburg

        cities.add(new City(R.string.ravenholm, 0.390f, 0.460f));
        cities.get(5).addRoute(cities.get(3))                   // Ravenholm - Drakkenburg
                .addRoute(cities.get(4));                       // Ravenholm - Steinhart

        cities.add(new City(R.string.valmir, 0.430f, 0.355f));
        cities.get(6).addRoute(cities.get(3))                   // Valmir - Drakkenburg
                .addRoute(cities.get(5));                       // Valmir - Ravenholm

        cities.add(new City(R.string.tenebris, 0.590f, 0.340f));
        cities.get(7).addRoute(cities.get(3))                   // Tenebris - Drakkenburg
                .addRoute(cities.get(6));                       // Tenebris - Valmir

        cities.add(new City(R.string.wolfengard, 0.675f, 0.165f));
        cities.get(8).addRoute(cities.get(3));                  // Wolfengard - Drakkenburg

        cities.add(new City(R.string.kastervik, 0.655f, 0.280f));
        cities.get(9).addRoute(cities.get(3))                   // Kastervik - Drakkenburg
                .addRoute(cities.get(7))                        // Kastervik - Tenebris
                .addRoute(cities.get(8));                       // Kastervik - Wolfengard

        cities.add(new City(R.string.morgenheim, 0.690f, 0.720f));
        cities.get(10).addRoute(cities.get(7));                 // Morgenheim - Tenebris

        cities.add(new City(R.string.elmyria, 0.830f, 0.335f));
        cities.get(11).addRoute(cities.get(8));                 // Elmyria - Wolfengard

        cities.add(new City(R.string.blackhollow, 0.820f, 0.435f));
        cities.get(12).addRoute(cities.get(9))                  // Blackhollow - Kastervik
                .addRoute(cities.get(10));                      // Blackhollow - Morgenheim

        cities.add(new City(R.string.albreston, 0.790f, 0.825f));
        cities.get(13).addRoute(cities.get(10));                // Albreston - Morgenheim

        cities.add(new City(R.string.schaderveld, 0.855f, 0.730f));
        cities.get(14).addRoute(cities.get(12))                 // Schaderveld - Blackhollow
                .addRoute(cities.get(13));                      // Schaderveld - Albreston

        cities.add(new City(R.string.winterholm, 0.905f, 0.380f));
        cities.get(15).addRoute(cities.get(11))                 // Winterholm - Elmyria
                .addRoute(cities.get(12));                      // Winterholm - Blackhollow
    }


    public static ArrayList<ICity> getCities() {
        if (cities == null) {
            init();
        }
        return cities;
    }

    public static ICity getCity(int id) {
        if (id < 0 || id >= cities.size()) return null;
        return cities.get(id);
    }
}
