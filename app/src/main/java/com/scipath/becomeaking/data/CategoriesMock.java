package com.scipath.becomeaking.data;

import com.scipath.becomeaking.R;
import com.scipath.becomeaking.model.Category;
import com.scipath.becomeaking.model.item.Item;
import com.scipath.becomeaking.model.StatBonus;
import com.scipath.becomeaking.model.StatBonusesMap;
import com.scipath.becomeaking.model.item.Work;

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
                .addItem(new Item(R.string.royal_food, R.drawable.img_royal_food, 0));
        // Bread
        categories.get(0).getItems().get(0).setStatBonuses(new StatBonusesMap()
                .put(StatBonus.HealthPerDay, 5)
                .put(StatBonus.CostPerDay, -1));
        categories.get(0).getItems().get(0).setBought(true);
        // Meat
        categories.get(0).getItems().get(1).setStatBonuses(new StatBonusesMap()
                .put(StatBonus.HealthPerDay, 10)
                .put(StatBonus.ReputationPerDay, 5)
                .put(StatBonus.CostPerDay, -5));
        // Cheese
        categories.get(0).getItems().get(2).setStatBonuses(new StatBonusesMap()
                .put(StatBonus.HealthPerDay, 15)
                .put(StatBonus.ReputationPerDay, 15)
                .put(StatBonus.CostPerDay, -10));
        // Vegetables
        categories.get(0).getItems().get(3).setStatBonuses(new StatBonusesMap()
                .put(StatBonus.HealthPerDay, 20)
                .put(StatBonus.ReputationPerDay, 20)
                .put(StatBonus.CostPerDay, -15));
        // Soup
        categories.get(0).getItems().get(4).setStatBonuses(new StatBonusesMap()
                .put(StatBonus.HealthPerDay, 30)
                .put(StatBonus.ReputationPerDay, 20)
                .put(StatBonus.CostPerDay, -30));
        // Nourishing food
        categories.get(0).getItems().get(5).setStatBonuses(new StatBonusesMap()
                .put(StatBonus.HealthPerDay, 40)
                .put(StatBonus.ReputationPerDay, 30)
                .put(StatBonus.CostPerDay, -40));
        // Luxurious food
        categories.get(0).getItems().get(6).setStatBonuses(new StatBonusesMap()
                .put(StatBonus.HealthPerDay, 50)
                .put(StatBonus.ReputationPerDay, 40)
                .put(StatBonus.CostPerDay, -45));
        // Aristocracy food
        categories.get(0).getItems().get(7).setStatBonuses(new StatBonusesMap()
                .put(StatBonus.HealthPerDay, 70)
                .put(StatBonus.ReputationPerDay, 50)
                .put(StatBonus.CostPerDay, -60));
        // Royal food
        categories.get(0).getItems().get(8).setStatBonuses(new StatBonusesMap()
                .put(StatBonus.HealthPerDay, 100)
                .put(StatBonus.ReputationPerDay, 300)
                .put(StatBonus.CostPerDay, -500));

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
                .put(StatBonus.HealthPerDay, 5));
        categories.get(1).getItems().get(0).setBought(true);
        // Peasant clothes
        categories.get(1).getItems().get(1).setStatBonuses(new StatBonusesMap()
                .put(StatBonus.HealthPerDay, 10)
                .put(StatBonus.ReputationPerDay, 5)
                .put(StatBonus.CostPerDay, -5));
        // Citizen clothes
        categories.get(1).getItems().get(2).setStatBonuses(new StatBonusesMap()
                .put(StatBonus.HealthPerDay, 15)
                .put(StatBonus.ReputationPerDay, 15)
                .put(StatBonus.CostPerDay, -10));
        // Luxurious clothes
        categories.get(1).getItems().get(3).setStatBonuses(new StatBonusesMap()
                .put(StatBonus.HealthPerDay, 30)
                .put(StatBonus.ReputationPerDay, 20)
                .put(StatBonus.CostPerDay, -30));
        // Aristocracy clothes
        categories.get(1).getItems().get(4).setStatBonuses(new StatBonusesMap()
                .put(StatBonus.HealthPerDay, 40)
                .put(StatBonus.ReputationPerDay, 30)
                .put(StatBonus.CostPerDay, -40));
        // Earl clothes
        categories.get(1).getItems().get(5).setStatBonuses(new StatBonusesMap()
                .put(StatBonus.HealthPerDay, 70)
                .put(StatBonus.ReputationPerDay, 50)
                .put(StatBonus.CostPerDay, -60));
        // Royal clothes
        categories.get(1).getItems().get(6).setStatBonuses(new StatBonusesMap()
                .put(StatBonus.HealthPerDay, 100)
                .put(StatBonus.ReputationPerDay, 200)
                .put(StatBonus.CostPerDay, -400));

        // Entertainment
        categories.add(new Category(R.string.entertainment));
        categories.get(2)
                .addItem(new Item(R.string.have_a_drink_at_the_tavern, R.drawable.img_tavern, 20))
                .addItem(new Item(R.string.walk_around_the_fair, R.drawable.img_fair, 200))
                .addItem(new Item(R.string.watch_the_tournament, R.drawable.img_tournament, 500))
                .addItem(new Item(R.string.visit_the_theatre, R.drawable.img_theatre, 1200))
                .addItem(new Item(R.string.organize_a_hunt, R.drawable.img_hunt, 4500))
                .addItem(new Item(R.string.throw_a_feast, R.drawable.img_feast, 30000));
        // Tavern
        categories.get(2).getItems().get(0).setStatBonuses(new StatBonusesMap()
                .put(StatBonus.HealthPerDay, 10)
                .put(StatBonus.ReputationPerDay, 5)
                .put(StatBonus.CostPerDay, -5));
        // Fair
        categories.get(2).getItems().get(1).setStatBonuses(new StatBonusesMap()
                .put(StatBonus.HealthPerDay, 20)
                .put(StatBonus.ReputationPerDay, 15)
                .put(StatBonus.CostPerDay, -20));
        // Tournament
        categories.get(2).getItems().get(2).setStatBonuses(new StatBonusesMap()
                .put(StatBonus.HealthPerDay, 30)
                .put(StatBonus.ReputationPerDay, 30)
                .put(StatBonus.CostPerDay, -30));
        // Theatre
        categories.get(2).getItems().get(3).setStatBonuses(new StatBonusesMap()
                .put(StatBonus.HealthPerDay, 50)
                .put(StatBonus.ReputationPerDay, 40)
                .put(StatBonus.CostPerDay, -40));
        // Hunt
        categories.get(2).getItems().get(4).setStatBonuses(new StatBonusesMap()
                .put(StatBonus.HealthPerDay, 70)
                .put(StatBonus.ReputationPerDay, 60)
                .put(StatBonus.CostPerDay, -60));
        // Feast
        categories.get(2).getItems().get(5).setStatBonuses(new StatBonusesMap()
                .put(StatBonus.HealthPerDay, 100)
                .put(StatBonus.ReputationPerDay, 150)
                .put(StatBonus.CostPerDay, -300));

        // Weapon
        categories.add(new Category(R.string.weapon));
        categories.get(3)
                .addItem(new Item(R.string.ax, R.drawable.img_ax, 100))
                .addItem(new Item(R.string.rusty_sword, R.drawable.img_rusty_sword, 300))
                .addItem(new Item(R.string.steel_sword, R.drawable.img_steel_sword, 1000))
                .addItem(new Item(R.string.two_blades, R.drawable.img_two_blades, 3000))
                .addItem(new Item(R.string.ax_and_shield, R.drawable.img_ax_and_shield, 8000))
                .addItem(new Item(R.string.sword_and_shield, R.drawable.img_sword_and_shield, 15000))
                .addItem(new Item(R.string.ancient_artifacts, R.drawable.img_ax_and_shield, 30000));
        // Ax
        categories.get(3).getItems().get(0).setStatBonuses(new StatBonusesMap()
                .put(StatBonus.Might, 10)
                .put(StatBonus.ReputationPerDay, 5)
                .put(StatBonus.CostPerDay, 5));
        // Rusty sword
        categories.get(3).getItems().get(1).setStatBonuses(new StatBonusesMap()
                .put(StatBonus.Might, 20)
                .put(StatBonus.ReputationPerDay, 10)
                .put(StatBonus.CostPerDay, 10));
        // Steel sword
        categories.get(3).getItems().get(2).setStatBonuses(new StatBonusesMap()
                .put(StatBonus.Might, 40)
                .put(StatBonus.ReputationPerDay, 20)
                .put(StatBonus.CostPerDay, 20));
        // Two blades
        categories.get(3).getItems().get(3).setStatBonuses(new StatBonusesMap()
                .put(StatBonus.Might, 80)
                .put(StatBonus.ReputationPerDay, 50)
                .put(StatBonus.CostPerDay, 40));
        // Ax and shield
        categories.get(3).getItems().get(4).setStatBonuses(new StatBonusesMap()
                .put(StatBonus.Might, 100)
                .put(StatBonus.ReputationPerDay, 60)
                .put(StatBonus.CostPerDay, 50));
        // Sword and shield
        categories.get(3).getItems().get(5).setStatBonuses(new StatBonusesMap()
                .put(StatBonus.Might, 150)
                .put(StatBonus.ReputationPerDay, 80)
                .put(StatBonus.CostPerDay, 70));
        // Ancient artifacts
        categories.get(3).getItems().get(6).setStatBonuses(new StatBonusesMap()
                .put(StatBonus.Might, 200)
                .put(StatBonus.ReputationPerDay, 100)
                .put(StatBonus.CostPerDay, 200));

        // Armor
        categories.add(new Category(R.string.armor));
        categories.get(4)
                .addItem(new Item(R.string.leather_armor, R.drawable.img_leather_armor, 100))
                .addItem(new Item(R.string.rusty_armor, R.drawable.img_rusty_armor, 300))
                .addItem(new Item(R.string.old_armor, R.drawable.img_old_armor, 1000))
                .addItem(new Item(R.string.heavy_armor, R.drawable.img_heavy_armor, 3000))
                .addItem(new Item(R.string.good_armor, R.drawable.img_good_armor, 8000))
                .addItem(new Item(R.string.luxurious_armor, R.drawable.img_luxury_armor, 15000))
                .addItem(new Item(R.string.king_armor, R.drawable.img_king_armor, 30000));
        // Leather armor
        categories.get(4).getItems().get(0).setStatBonuses(new StatBonusesMap()
                .put(StatBonus.Might, 10)
                .put(StatBonus.ReputationPerDay, 5)
                .put(StatBonus.CostPerDay, 5));
        // Rusty armor
        categories.get(4).getItems().get(1).setStatBonuses(new StatBonusesMap()
                .put(StatBonus.Might, 30)
                .put(StatBonus.ReputationPerDay, 15)
                .put(StatBonus.CostPerDay, 10));
        // Old armor
        categories.get(4).getItems().get(2).setStatBonuses(new StatBonusesMap()
                .put(StatBonus.Might, 40)
                .put(StatBonus.ReputationPerDay, 25)
                .put(StatBonus.CostPerDay, 15));
        // Heavy armor
        categories.get(4).getItems().get(3).setStatBonuses(new StatBonusesMap()
                .put(StatBonus.Might, 80)
                .put(StatBonus.ReputationPerDay, 50)
                .put(StatBonus.CostPerDay, 40));
        // Good armor
        categories.get(4).getItems().get(4).setStatBonuses(new StatBonusesMap()
                .put(StatBonus.Might, 120)
                .put(StatBonus.ReputationPerDay, 80)
                .put(StatBonus.CostPerDay, 60));
        // Luxurious armor
        categories.get(4).getItems().get(5).setStatBonuses(new StatBonusesMap()
                .put(StatBonus.Might, 140)
                .put(StatBonus.ReputationPerDay, 90)
                .put(StatBonus.CostPerDay, 65));
        // King armor
        categories.get(4).getItems().get(6).setStatBonuses(new StatBonusesMap()
                .put(StatBonus.Might, 200)
                .put(StatBonus.ReputationPerDay, 110)
                .put(StatBonus.CostPerDay, 200));

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

        // Work in village
        categories.add(new Category(R.string.work_in_a_village));
        categories.get(10)
                .addItem(new Work(R.string.logging, R.drawable.img_logging, 0))
                .addItem(new Work(R.string.work_in_a_mine, R.drawable.img_work_in_a_mine, 0));
        // Logging
        categories.get(10).getItems().get(0).setStatBonuses(new StatBonusesMap()
                .put(StatBonus.HealthImpact, -10)
                .put(StatBonus.ReputationImpact, 5)
                .put(StatBonus.MoneyPerClick, 1));
        // Work in a mine
        categories.get(10).getItems().get(1).setStatBonuses(new StatBonusesMap()
                .put(StatBonus.HealthImpact, -20)
                .put(StatBonus.ReputationImpact, 10)
                .put(StatBonus.MoneyPerClick, 2));

        return categories;
    }
}
