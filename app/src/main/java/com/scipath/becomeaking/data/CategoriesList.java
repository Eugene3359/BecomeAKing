package com.scipath.becomeaking.data;

import com.scipath.becomeaking.R;
import com.scipath.becomeaking.model.Category;
import com.scipath.becomeaking.model.item.Item;
import com.scipath.becomeaking.model.StatBonus;
import com.scipath.becomeaking.model.StatBonusesMap;
import com.scipath.becomeaking.model.item.Work;

import java.util.ArrayList;

public class CategoriesList {
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
                .addItem(new Item(R.string.ancient_artifacts, R.drawable.img_ancient_artifacts, 30000));
        // Ax
        categories.get(3).getItems().get(0).setStatBonuses(new StatBonusesMap()
                .put(StatBonus.Might, 10)
                .put(StatBonus.ReputationPerDay, 5)
                .put(StatBonus.CostPerDay, -5));
        // Rusty sword
        categories.get(3).getItems().get(1).setStatBonuses(new StatBonusesMap()
                .put(StatBonus.Might, 20)
                .put(StatBonus.ReputationPerDay, 10)
                .put(StatBonus.CostPerDay, -10)
                .put(StatBonus.StrengthRequired, 1));
        // Steel sword
        categories.get(3).getItems().get(2).setStatBonuses(new StatBonusesMap()
                .put(StatBonus.Might, 40)
                .put(StatBonus.ReputationPerDay, 20)
                .put(StatBonus.CostPerDay, -20)
                .put(StatBonus.StrengthRequired, 2));
        // Two blades
        categories.get(3).getItems().get(3).setStatBonuses(new StatBonusesMap()
                .put(StatBonus.Might, 80)
                .put(StatBonus.ReputationPerDay, 50)
                .put(StatBonus.CostPerDay, -40)
                .put(StatBonus.StrengthRequired, 2));
        // Ax and shield
        categories.get(3).getItems().get(4).setStatBonuses(new StatBonusesMap()
                .put(StatBonus.Might, 100)
                .put(StatBonus.ReputationPerDay, 60)
                .put(StatBonus.CostPerDay, -50)
                .put(StatBonus.StrengthRequired, 3));
        // Sword and shield
        categories.get(3).getItems().get(5).setStatBonuses(new StatBonusesMap()
                .put(StatBonus.Might, 150)
                .put(StatBonus.ReputationPerDay, 80)
                .put(StatBonus.CostPerDay, -70)
                .put(StatBonus.StrengthRequired, 3));
        // Ancient artifacts
        categories.get(3).getItems().get(6).setStatBonuses(new StatBonusesMap()
                .put(StatBonus.Might, 200)
                .put(StatBonus.ReputationPerDay, 100)
                .put(StatBonus.CostPerDay, -200)
                .put(StatBonus.StrengthRequired, 4));


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
                .put(StatBonus.CostPerDay, -5));
        // Rusty armor
        categories.get(4).getItems().get(1).setStatBonuses(new StatBonusesMap()
                .put(StatBonus.Might, 30)
                .put(StatBonus.ReputationPerDay, 15)
                .put(StatBonus.CostPerDay, -10)
                .put(StatBonus.StrengthRequired, 1));
        // Old armor
        categories.get(4).getItems().get(2).setStatBonuses(new StatBonusesMap()
                .put(StatBonus.Might, 40)
                .put(StatBonus.ReputationPerDay, 25)
                .put(StatBonus.CostPerDay, -15)
                .put(StatBonus.StrengthRequired, 2));
        // Heavy armor
        categories.get(4).getItems().get(3).setStatBonuses(new StatBonusesMap()
                .put(StatBonus.Might, 80)
                .put(StatBonus.ReputationPerDay, 50)
                .put(StatBonus.CostPerDay, -40)
                .put(StatBonus.StrengthRequired, 2));
        // Good armor
        categories.get(4).getItems().get(4).setStatBonuses(new StatBonusesMap()
                .put(StatBonus.Might, 120)
                .put(StatBonus.ReputationPerDay, 80)
                .put(StatBonus.CostPerDay, -60)
                .put(StatBonus.StrengthRequired, 3));
        // Luxurious armor
        categories.get(4).getItems().get(5).setStatBonuses(new StatBonusesMap()
                .put(StatBonus.Might, 140)
                .put(StatBonus.ReputationPerDay, 90)
                .put(StatBonus.CostPerDay, -65)
                .put(StatBonus.StrengthRequired, 3));
        // King armor
        categories.get(4).getItems().get(6).setStatBonuses(new StatBonusesMap()
                .put(StatBonus.Might, 200)
                .put(StatBonus.ReputationPerDay, 110)
                .put(StatBonus.CostPerDay, -200)
                .put(StatBonus.StrengthRequired, 4));


        // House
        categories.add(new Category(R.string.housing));
        categories.get(5)
                .addItem(new Item(R.string.hut, R.drawable.img_hut, 1000))
                .addItem(new Item(R.string.old_house, R.drawable.img_old_house, 3000))
                .addItem(new Item(R.string.stone_house, R.drawable.img_stone_house, 6000))
                .addItem(new Item(R.string.country_house, R.drawable.img_country_house, 12000))
                .addItem(new Item(R.string.small_mansion, R.drawable.img_small_mansion, 30000))
                .addItem(new Item(R.string.medium_mansion, R.drawable.img_medium_mansion, 45000))
                .addItem(new Item(R.string.large_mansion, R.drawable.img_large_mansion, 60000));
        // Hut
        categories.get(5).getItems().get(0).setStatBonuses(new StatBonusesMap()
                .put(StatBonus.MaxHealth, 200)
                .put(StatBonus.CostPerDay, -10));
        // Old house
        categories.get(5).getItems().get(1).setStatBonuses(new StatBonusesMap()
                .put(StatBonus.MaxHealth, 500)
                .put(StatBonus.CostPerDay, -20));
        // Stone house
        categories.get(5).getItems().get(2).setStatBonuses(new StatBonusesMap()
                .put(StatBonus.MaxHealth, 1000)
                .put(StatBonus.CostPerDay, -300));
        // Country house
        categories.get(5).getItems().get(3).setStatBonuses(new StatBonusesMap()
                .put(StatBonus.MaxHealth, 1500)
                .put(StatBonus.CostPerDay, -500));
        // Small mansion
        categories.get(5).getItems().get(4).setStatBonuses(new StatBonusesMap()
                .put(StatBonus.MaxHealth, 2000)
                .put(StatBonus.CostPerDay, -1000));
        // Medium mansion
        categories.get(5).getItems().get(5).setStatBonuses(new StatBonusesMap()
                .put(StatBonus.MaxHealth, 3500)
                .put(StatBonus.CostPerDay, -2500));
        // Large mansion
        categories.get(5).getItems().get(6).setStatBonuses(new StatBonusesMap()
                .put(StatBonus.MaxHealth, 5000)
                .put(StatBonus.CostPerDay, -6000));


        // Furniture
        categories.add(new Category(R.string.furniture));
        categories.get(6)
                .addItem(new Item(R.string.old_furniture, R.drawable.img_old_furniture, 100))
                .addItem(new Item(R.string.ordinary_furniture, R.drawable.img_ordinary_furniture, 500))
                .addItem(new Item(R.string.new_furniture, R.drawable.img_new_furniture, 1500))
                .addItem(new Item(R.string.luxurious_furniture, R.drawable.img_luxurious_furniture, 3000))
                .addItem(new Item(R.string.royal_furniture, R.drawable.img_royal_furniture, 10000));
        // Old furniture
        categories.get(6).getItems().get(0).setStatBonuses(new StatBonusesMap()
                .put(StatBonus.HealthPerDay, 5)
                .put(StatBonus.ReputationPerDay, 5)
                .put(StatBonus.CostPerDay, -2));
        // Ordinary furniture
        categories.get(6).getItems().get(1).setStatBonuses(new StatBonusesMap()
                .put(StatBonus.HealthPerDay, 10)
                .put(StatBonus.ReputationPerDay, 10)
                .put(StatBonus.CostPerDay, -5));
        // New furniture
        categories.get(6).getItems().get(2).setStatBonuses(new StatBonusesMap()
                .put(StatBonus.HealthPerDay, 20)
                .put(StatBonus.ReputationPerDay, 20)
                .put(StatBonus.CostPerDay, -10));
        // Luxurious furniture
        categories.get(6).getItems().get(3).setStatBonuses(new StatBonusesMap()
                .put(StatBonus.HealthPerDay, 30)
                .put(StatBonus.ReputationPerDay, 30)
                .put(StatBonus.CostPerDay, -50));
        // Royal furniture
        categories.get(6).getItems().get(4).setStatBonuses(new StatBonusesMap()
                .put(StatBonus.HealthPerDay, 60)
                .put(StatBonus.ReputationPerDay, 50)
                .put(StatBonus.CostPerDay, -20));


        // Books
        categories.add(new Category(R.string.books));
        categories.get(7)
                .addItem(new Item(R.string.old_book, R.drawable.img_old_book, 200))
                .addItem(new Item(R.string.new_book, R.drawable.img_new_book, 600))
                .addItem(new Item(R.string.science_book, R.drawable.img_science_book, 1200))
                .addItem(new Item(R.string.military_strategy_books, R.drawable.img_military_strategy_books, 8000))
                .addItem(new Item(R.string.small_library, R.drawable.img_small_library, 12000))
                .addItem(new Item(R.string.medium_library, R.drawable.img_medium_library, 20000))
                .addItem(new Item(R.string.huge_library, R.drawable.img_huge_library, 50000))
                .addItem(new Item(R.string.royal_library, R.drawable.img_royal_library, 100000));
        // Old book
        categories.get(7).getItems().get(0).setStatBonuses(new StatBonusesMap()
                .put(StatBonus.ReputationImpact, 10));
        // New book
        categories.get(7).getItems().get(1).setStatBonuses(new StatBonusesMap()
                .put(StatBonus.ReputationImpact, 30));
        // Science book
        categories.get(7).getItems().get(2).setStatBonuses(new StatBonusesMap()
                .put(StatBonus.Might, 10)
                .put(StatBonus.ReputationImpact, 50));
        // Military strategy books
        // TODO: add stat bonuses
        // Small library
        categories.get(7).getItems().get(4).setStatBonuses(new StatBonusesMap()
                .put(StatBonus.Might, 40)
                .put(StatBonus.ReputationImpact, 100));
        // Medium library
        // TODO: add stat bonuses
        // Huge library
        // TODO: add stat bonuses
        // Royal library
        // TODO: add stat bonuses


        // Artworks
        categories.add(new Category(R.string.artworks));
        categories.get(8)
                .addItem(new Item(R.string.figurine_from_the_workshop, R.drawable.img_figurine_from_the_workshop, 300))
                .addItem(new Item(R.string.local_painting, R.drawable.img_local_painting, 800))
                .addItem(new Item(R.string.painting_by_a_famous_artist, R.drawable.img_painting_by_a_famous_artist, 1500))
                .addItem(new Item(R.string.marble_statue, R.drawable.img_marble_statue, 4000))
                .addItem(new Item(R.string.private_museum, R.drawable.img_private_museum, 15000));
        // Figurine from the workshop
        categories.get(8).getItems().get(0).setStatBonuses(new StatBonusesMap()
                .put(StatBonus.ReputationImpact, 30));
        // Local painting
        categories.get(8).getItems().get(1).setStatBonuses(new StatBonusesMap()
                .put(StatBonus.ReputationImpact, 60));
        // Painting by a famous artist
        categories.get(8).getItems().get(2).setStatBonuses(new StatBonusesMap()
                .put(StatBonus.ReputationImpact, 100));
        // Marble statue
        categories.get(8).getItems().get(3).setStatBonuses(new StatBonusesMap()
                .put(StatBonus.ReputationImpact, 150));
        // Private museum
        categories.get(8).getItems().get(4).setStatBonuses(new StatBonusesMap()
                .put(StatBonus.ReputationImpact, 250));


        // Horse
        categories.add(new Category(R.string.horse));
        categories.get(9)
                .addItem(new Item(R.string.lame_horse, R.drawable.img_lame_horse, 1500))
                .addItem(new Item(R.string.young_horse, R.drawable.img_young_horse, 5000))
                .addItem(new Item(R.string.fast_horse, R.drawable.img_fast_horse, 10000))
                .addItem(new Item(R.string.light_armored_horse, R.drawable.img_light_armored_horse, 20000))
                .addItem(new Item(R.string.medium_armored_horse, R.drawable.img_medium_armored_horse, 30000))
                .addItem(new Item(R.string.heavy_armored_horse, R.drawable.img_heavy_armored_horse, 50000));
        // Lame horse
        categories.get(9).getItems().get(0).setStatBonuses(new StatBonusesMap()
                .put(StatBonus.Might, 10)
                .put(StatBonus.ReputationPerDay, 10)
                .put(StatBonus.CostPerDay, -5));
        // Young horse
        categories.get(9).getItems().get(1).setStatBonuses(new StatBonusesMap()
                .put(StatBonus.Might, 20)
                .put(StatBonus.ReputationPerDay, 20)
                .put(StatBonus.CostPerDay, -10));
        // Fast horse
        categories.get(9).getItems().get(2).setStatBonuses(new StatBonusesMap()
                .put(StatBonus.Might, 40)
                .put(StatBonus.ReputationPerDay, 25)
                .put(StatBonus.CostPerDay, -20));
        // Light armored horse
        categories.get(9).getItems().get(3).setStatBonuses(new StatBonusesMap()
                .put(StatBonus.Might, 80)
                .put(StatBonus.ReputationPerDay, 40)
                .put(StatBonus.CostPerDay, -30));
        // Medium armored horse
        categories.get(9).getItems().get(4).setStatBonuses(new StatBonusesMap()
                .put(StatBonus.Might, 120)
                .put(StatBonus.ReputationPerDay, 40)
                .put(StatBonus.CostPerDay, -50));
        // Heavy armored horse
        categories.get(9).getItems().get(5).setStatBonuses(new StatBonusesMap()
                .put(StatBonus.Might, 160)
                .put(StatBonus.ReputationPerDay, 50)
                .put(StatBonus.CostPerDay, -250));


        // Work in village
        categories.add(new Category(R.string.work_in_a_village));
        categories.get(10)
                .addItem(new Work(R.string.to_graze_animals, R.drawable.img_to_graze_animals, 0))
                .addItem(new Work(R.string.harvesting, R.drawable.img_harvesting, 0))
                .addItem(new Work(R.string.logging, R.drawable.img_logging, 0))
                .addItem(new Work(R.string.work_in_a_mine, R.drawable.img_work_in_a_mine, 0))
                .addItem(new Work(R.string.unloading_ships, R.drawable.img_unloading_ships, 0));
        // To graze animals
        categories.get(10).getItems().get(0).setStatBonuses(new StatBonusesMap()
                .put(StatBonus.HealthImpact, -20)
                .put(StatBonus.ReputationImpact, 5)
                .put(StatBonus.MoneyPerClick, 1));
        // Harvesting
        categories.get(10).getItems().get(1).setStatBonuses(new StatBonusesMap()
                .put(StatBonus.HealthImpact, -40)
                .put(StatBonus.ReputationImpact, 10)
                .put(StatBonus.MoneyPerClick, 2)
                .put(StatBonus.ReputationRequired, 500));
        // Logging
        categories.get(10).getItems().get(2).setStatBonuses(new StatBonusesMap()
                .put(StatBonus.HealthImpact, -60)
                .put(StatBonus.ReputationImpact, 20)
                .put(StatBonus.MoneyPerClick, 3)
                .put(StatBonus.ReputationRequired, 2000));
        // Work in a mine
        categories.get(10).getItems().get(3).setStatBonuses(new StatBonusesMap()
                .put(StatBonus.HealthImpact, -80)
                .put(StatBonus.ReputationImpact, 40)
                .put(StatBonus.MoneyPerClick, 4)
                .put(StatBonus.ReputationRequired, 4000));
        // Unloading ships
        categories.get(10).getItems().get(4).setStatBonuses(new StatBonusesMap()
                .put(StatBonus.HealthImpact, -100)
                .put(StatBonus.ReputationImpact, 60)
                .put(StatBonus.MoneyPerClick, 5)
                .put(StatBonus.ReputationRequired, 8000));


        // Work in town
        categories.add(new Category(R.string.work_in_a_town));
        categories.get(11)
                .addItem(new Work(R.string.cart_repair, R.drawable.img_cart_repair, -1))
                .addItem(new Work(R.string.architects_apprentice, R.color.placeholder, -1))
                .addItem(new Work(R.string.weavers_apprentice, R.drawable.img_weavers_apprentice, -1))
                .addItem(new Work(R.string.craftsmans_apprentice, R.drawable.img_craftsmans_apprentice, -1))
                .addItem(new Work(R.string.blacksmiths_apprentice, R.drawable.img_blacksmiths_apprentice, -1))
                .addItem(new Work(R.string.lords_courtier, R.drawable.img_lords_courtier, -1));
        // Cart repair
        categories.get(11).getItems().get(0).setStatBonuses(new StatBonusesMap()
                .put(StatBonus.HealthImpact, -20)
                .put(StatBonus.ReputationImpact, 60)
                .put(StatBonus.MoneyPerClick, 6)
                .put(StatBonus.ReputationRequired, 8000));
        // Architect's apprentice
        categories.get(11).getItems().get(1).setStatBonuses(new StatBonusesMap()
                .put(StatBonus.HealthImpact, -10)
                .put(StatBonus.ReputationImpact, 80)
                .put(StatBonus.MoneyPerClick, 8)
                .put(StatBonus.ReputationRequired, 10000));
        // Weaver's apprentice
        categories.get(11).getItems().get(2).setStatBonuses(new StatBonusesMap()
                .put(StatBonus.HealthImpact, -15)
                .put(StatBonus.ReputationImpact, 100)
                .put(StatBonus.MoneyPerClick, 10)
                .put(StatBonus.ReputationRequired, 12000));
        // Craftsman's apprentice
        categories.get(11).getItems().get(3).setStatBonuses(new StatBonusesMap()
                .put(StatBonus.HealthImpact, -15)
                .put(StatBonus.ReputationImpact, 150)
                .put(StatBonus.MoneyPerClick, 15)
                .put(StatBonus.ReputationRequired, 30000));
        // Blacksmith's apprentice
        categories.get(11).getItems().get(4).setStatBonuses(new StatBonusesMap()
                .put(StatBonus.HealthImpact, -20)
                .put(StatBonus.ReputationImpact, 200)
                .put(StatBonus.MoneyPerClick, 20)
                .put(StatBonus.ReputationRequired, 60000));
        // Lord's courtier
        categories.get(11).getItems().get(5).setStatBonuses(new StatBonusesMap()
                .put(StatBonus.HealthImpact, -10)
                .put(StatBonus.ReputationImpact, 500)
                .put(StatBonus.MoneyPerClick, 50)
                .put(StatBonus.ReputationRequired, 100000));

        return categories;
    }
}
