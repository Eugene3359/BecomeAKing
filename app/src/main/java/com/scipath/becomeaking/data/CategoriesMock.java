package com.scipath.becomeaking.data;

import com.scipath.becomeaking.R;
import com.scipath.becomeaking.model.Category;
import com.scipath.becomeaking.model.Item;
import com.scipath.becomeaking.model.StatBonus;
import com.scipath.becomeaking.model.StatBonusesMap;

import java.util.ArrayList;

public class CategoriesMock {
    public static ArrayList<Category> getCategories(boolean isMale) {
        ArrayList<Category> categories = new ArrayList<>();

        // Nutrition
        categories.add(new Category(R.string.nutrition));
        categories.get(0)
                .addItem(new Item(R.string.bread, R.drawable.img_bread, 0))
                .addItem(new Item(R.string.meat, R.drawable.img_meat, 0))
                .addItem(new Item(R.string.cheese, R.drawable.img_cheese, 0))
                .addItem(new Item(R.string.vegetables, R.drawable.img_vegetables, 0))
                .addItem(new Item(R.string.soup, R.drawable.img_soup, 0))
                .addItem(new Item(R.string.nourishing_food, R.drawable.img_nourishing_food, 0))
                .addItem(new Item(R.string.luxurious_food, R.drawable.img_luxury_food, 0))
                .addItem(new Item(R.string.aristocracy_food, R.drawable.img_nobleman_food, 0))
                .addItem(new Item(R.string.royal_food, R.drawable.img_king_food, 0));
        // Bread
        categories.get(0).getItems().get(0).setStatBonuses(new StatBonusesMap()
                .addStatBonus(StatBonus.HealthPerDay, 5)
                .addStatBonus(StatBonus.CostPerDay, 1));
        categories.get(0).getItems().get(0).setBought(true);
        // Meat
        categories.get(0).getItems().get(1).setStatBonuses(new StatBonusesMap()
                .addStatBonus(StatBonus.HealthPerDay, 10)
                .addStatBonus(StatBonus.ReputationPerDay, 5)
                .addStatBonus(StatBonus.CostPerDay, 5));
        // Cheese
        categories.get(0).getItems().get(2).setStatBonuses(new StatBonusesMap()
                .addStatBonus(StatBonus.HealthPerDay, 15)
                .addStatBonus(StatBonus.ReputationPerDay, 15)
                .addStatBonus(StatBonus.CostPerDay, 10));
        // Vegetables
        categories.get(0).getItems().get(3).setStatBonuses(new StatBonusesMap()
                .addStatBonus(StatBonus.HealthPerDay, 20)
                .addStatBonus(StatBonus.ReputationPerDay, 20)
                .addStatBonus(StatBonus.CostPerDay, 15));
        // Soup
        categories.get(0).getItems().get(4).setStatBonuses(new StatBonusesMap()
                .addStatBonus(StatBonus.HealthPerDay, 30)
                .addStatBonus(StatBonus.ReputationPerDay, 20)
                .addStatBonus(StatBonus.CostPerDay, 30));
        // Nourishing food
        categories.get(0).getItems().get(5).setStatBonuses(new StatBonusesMap()
                .addStatBonus(StatBonus.HealthPerDay, 40)
                .addStatBonus(StatBonus.ReputationPerDay, 30)
                .addStatBonus(StatBonus.CostPerDay, 40));
        // Luxurious food
        categories.get(0).getItems().get(6).setStatBonuses(new StatBonusesMap()
                .addStatBonus(StatBonus.HealthPerDay, 50)
                .addStatBonus(StatBonus.ReputationPerDay, 40)
                .addStatBonus(StatBonus.CostPerDay, 45));
        // Aristocracy food
        categories.get(0).getItems().get(7).setStatBonuses(new StatBonusesMap()
                .addStatBonus(StatBonus.HealthPerDay, 70)
                .addStatBonus(StatBonus.ReputationPerDay, 50)
                .addStatBonus(StatBonus.CostPerDay, 60));
        // Royal food
        categories.get(0).getItems().get(8).setStatBonuses(new StatBonusesMap()
                .addStatBonus(StatBonus.HealthPerDay, 100)
                .addStatBonus(StatBonus.ReputationPerDay, 300)
                .addStatBonus(StatBonus.CostPerDay, 500));

        // Clothes
        categories.add(new Category(R.string.clothes));
        categories.get(1)
                .addItem(new Item(R.string.poor_man_clothes, isMale ?
                        R.drawable.img_poor_man_clothes : R.drawable.img_poor_woman_clothes, 10))
                .addItem(new Item(R.string.peasant_clothes, isMale ?
                        R.drawable.img_peasant_clothes : R.drawable.img_peasant_clothes_w, 50))
                .addItem(new Item(R.string.citizen_clothes, isMale ?
                        R.drawable.img_citizen_clothes_m : R.drawable.img_citizen_clothes_w, 120))
                .addItem(new Item(R.string.luxurious_clothes, isMale ?
                        R.drawable.img_luxurious_clothes_m : R.drawable.img_luxurious_clothes_w, 450))
                .addItem(new Item(R.string.aristocracy_clothes, isMale ?
                        R.drawable.img_aristocracy_clothes_m : R.drawable.img_aristocracy_clothes_w, 2000))
                .addItem(new Item(R.string.earl_clothes, isMale ?
                        R.drawable.img_earl_clothes_m : R.drawable.img_earl_clothes_w, 8000))
                .addItem(new Item(R.string.royal_clothes, isMale ?
                        R.drawable.img_royal_clothes_m : R.drawable.img_royal_clothes_w, 40000));
        // Poor man/woman clothes
        categories.get(1).getItems().get(0).setStatBonuses(new StatBonusesMap()
                .addStatBonus(StatBonus.HealthPerDay, 5));
        categories.get(1).getItems().get(0).setBought(true);
        // Peasant clothes
        categories.get(1).getItems().get(1).setStatBonuses(new StatBonusesMap()
                .addStatBonus(StatBonus.HealthPerDay, 10)
                .addStatBonus(StatBonus.ReputationPerDay, 5)
                .addStatBonus(StatBonus.CostPerDay, 5));
        // Citizen clothes
        categories.get(1).getItems().get(2).setStatBonuses(new StatBonusesMap()
                .addStatBonus(StatBonus.HealthPerDay, 15)
                .addStatBonus(StatBonus.ReputationPerDay, 15)
                .addStatBonus(StatBonus.CostPerDay, 10));
        // Luxurious clothes
        categories.get(1).getItems().get(3).setStatBonuses(new StatBonusesMap()
                .addStatBonus(StatBonus.HealthPerDay, 30)
                .addStatBonus(StatBonus.ReputationPerDay, 20)
                .addStatBonus(StatBonus.CostPerDay, 30));
        // Aristocracy clothes
        categories.get(1).getItems().get(4).setStatBonuses(new StatBonusesMap()
                .addStatBonus(StatBonus.HealthPerDay, 40)
                .addStatBonus(StatBonus.ReputationPerDay, 30)
                .addStatBonus(StatBonus.CostPerDay, 40));
        // Earl clothes
        categories.get(1).getItems().get(5).setStatBonuses(new StatBonusesMap()
                .addStatBonus(StatBonus.HealthPerDay, 70)
                .addStatBonus(StatBonus.ReputationPerDay, 50)
                .addStatBonus(StatBonus.CostPerDay, 60));
        // Royal clothes
        categories.get(1).getItems().get(6).setStatBonuses(new StatBonusesMap()
                .addStatBonus(StatBonus.HealthPerDay, 100)
                .addStatBonus(StatBonus.ReputationPerDay, 200)
                .addStatBonus(StatBonus.CostPerDay, 400));

        // Entertainment
        categories.add(new Category(R.string.entertainment));

        // Weapon
        categories.add(new Category(R.string.weapon));

        // Armor
        categories.add(new Category(R.string.armor));

        // House
        categories.add(new Category(R.string.housing));

        // Furniture
        categories.add(new Category(R.string.furniture));

        // Books
        categories.add(new Category(R.string.books));

        // Artworks
        categories.add(new Category(R.string.artworks));

        // Horse
        categories.add(new Category(R.string.horse));

        return categories;
    }
}
