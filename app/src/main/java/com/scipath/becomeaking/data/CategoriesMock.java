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
                .addItem(new Item(R.string.luxury_food, R.drawable.img_luxury_food, 0))
                .addItem(new Item(R.string.nobleman_food, R.drawable.img_nobleman_food, 0))
                .addItem(new Item(R.string.king_food, R.drawable.img_king_food, 0));
        categories.get(0).getItems().get(0).setStatBonuses(new StatBonusesMap()
                .addStatBonus(StatBonus.HealthPerDay, 5)
                .addStatBonus(StatBonus.CostPerDay, 1));
        categories.get(0).getItems().get(1).setStatBonuses(new StatBonusesMap()
                .addStatBonus(StatBonus.HealthPerDay, 10)
                .addStatBonus(StatBonus.ReputationPerDay, 5)
                .addStatBonus(StatBonus.CostPerDay, 5));
        categories.get(0).getItems().get(2).setStatBonuses(new StatBonusesMap()
                .addStatBonus(StatBonus.HealthPerDay, 15)
                .addStatBonus(StatBonus.ReputationPerDay, 15)
                .addStatBonus(StatBonus.CostPerDay, 10));
        categories.get(0).getItems().get(3).setStatBonuses(new StatBonusesMap()
                .addStatBonus(StatBonus.HealthPerDay, 20)
                .addStatBonus(StatBonus.ReputationPerDay, 20)
                .addStatBonus(StatBonus.CostPerDay, 15));
        categories.get(0).getItems().get(4).setStatBonuses(new StatBonusesMap()
                .addStatBonus(StatBonus.HealthPerDay, 30)
                .addStatBonus(StatBonus.ReputationPerDay, 20)
                .addStatBonus(StatBonus.CostPerDay, 30));
        categories.get(0).getItems().get(5).setStatBonuses(new StatBonusesMap()
                .addStatBonus(StatBonus.HealthPerDay, 40)
                .addStatBonus(StatBonus.ReputationPerDay, 30)
                .addStatBonus(StatBonus.CostPerDay, 40));
        categories.get(0).getItems().get(6).setStatBonuses(new StatBonusesMap()
                .addStatBonus(StatBonus.HealthPerDay, 50)
                .addStatBonus(StatBonus.ReputationPerDay, 40)
                .addStatBonus(StatBonus.CostPerDay, 45));
        categories.get(0).getItems().get(7).setStatBonuses(new StatBonusesMap()
                .addStatBonus(StatBonus.HealthPerDay, 70)
                .addStatBonus(StatBonus.ReputationPerDay, 50)
                .addStatBonus(StatBonus.CostPerDay, 60));
        categories.get(0).getItems().get(8).setStatBonuses(new StatBonusesMap()
                .addStatBonus(StatBonus.HealthPerDay, 100)
                .addStatBonus(StatBonus.ReputationPerDay, 300)
                .addStatBonus(StatBonus.CostPerDay, 500));
        categories.get(0).getItems().get(0).setBought(true);

        // Clothes
        categories.add(new Category(R.string.clothes));
        categories.get(1)
                .addItem(new Item(R.string.poor_man_clothes, isMale ?
                        R.drawable.img_poor_man_clothes : R.drawable.img_poor_woman_cloth, 10))
                .addItem(new Item(R.string.peasant_clothes, isMale ?
                        R.drawable.img_peasant_clothes : R.drawable.img_peasant_woman_cloth, 50));
        categories.get(1).getItems().get(0).setStatBonuses(new StatBonusesMap()
                .addStatBonus(StatBonus.HealthPerDay, 5));
        categories.get(1).getItems().get(1).setStatBonuses(new StatBonusesMap()
                .addStatBonus(StatBonus.HealthPerDay, 10)
                .addStatBonus(StatBonus.ReputationPerDay, 5)
                .addStatBonus(StatBonus.CostPerDay, 5));
        categories.get(1).getItems().get(0).setBought(true);
        return categories;
    }
}
