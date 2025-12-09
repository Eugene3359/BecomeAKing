package com.scipath.becomeaking.data;

import com.scipath.becomeaking.R;
import com.scipath.becomeaking.contract.model.ICategory;
import com.scipath.becomeaking.model.Category;
import com.scipath.becomeaking.model.enums.Stat;
import com.scipath.becomeaking.model.item.Food;
import com.scipath.becomeaking.model.item.Item;
import com.scipath.becomeaking.model.Stats;
import com.scipath.becomeaking.model.item.SelectableItem;
import com.scipath.becomeaking.model.item.Work;

import java.util.ArrayList;


public class CategoriesList {

    private static ArrayList<ICategory> categories;


    public static void init(boolean isMale) {
        if (categories != null) return;

        categories = new ArrayList<>();

        // Nutrition
        categories.add(new Category(R.string.nutrition, false));
        categories.get(0).setBackgroundDrawableId(R.drawable.bg_food);
        categories.get(0)
                .addItem(new Food(R.string.bread, R.drawable.food_bread))
                .addItem(new Food(R.string.meat, R.drawable.food_meat))
                .addItem(new Food(R.string.cheese, R.drawable.food_cheese))
                .addItem(new Food(R.string.vegetables, R.drawable.food_vegetables))
                .addItem(new Food(R.string.fruits, R.drawable.food_fruits))
                .addItem(new Food(R.string.seafood, R.drawable.food_seafood))
                .addItem(new Food(R.string.soup, R.drawable.food_soup))
                .addItem(new Food(R.string.nourishing_food, R.drawable.food_nourishing))
                .addItem(new Food(R.string.luxurious_food, R.drawable.food_luxury))
                .addItem(new Food(R.string.aristocracy_food, R.drawable.food_nobleman))
                .addItem(new Food(R.string.royal_food, R.drawable.food_royal));
        // Bread
        categories.get(0).getItem(0).setStats(new Stats()
                .add(Stat.HealthPerDay, 5)
                .add(Stat.CoinsPerDay, -1));
        categories.get(0).getItem(0).setState(Food.State.InRation);
        // Meat
        categories.get(0).getItem(1).setStats(new Stats()
                .add(Stat.HealthPerDay, 10)
                .add(Stat.ReputationPerDay, 5)
                .add(Stat.CoinsPerDay, -5));
        // Cheese
        categories.get(0).getItem(2).setStats(new Stats()
                .add(Stat.HealthPerDay, 15)
                .add(Stat.ReputationPerDay, 15)
                .add(Stat.CoinsPerDay, -10));
        // Vegetables
        categories.get(0).getItem(3).setStats(new Stats()
                .add(Stat.HealthPerDay, 20)
                .add(Stat.ReputationPerDay, 20)
                .add(Stat.CoinsPerDay, -15));
        // Fruits
        categories.get(0).getItem(4).setStats(new Stats()
                .add(Stat.HealthPerDay, 25)
                .add(Stat.ReputationPerDay, 25)
                .add(Stat.CoinsPerDay, -20));
        // Seafood
        categories.get(0).getItem(5).setStats(new Stats()
                .add(Stat.HealthPerDay, 30)
                .add(Stat.ReputationPerDay, 30)
                .add(Stat.CoinsPerDay, -25));
        // Soup
        categories.get(0).getItem(6).setStats(new Stats()
                .add(Stat.HealthPerDay, 35)
                .add(Stat.ReputationPerDay, 35)
                .add(Stat.CoinsPerDay, -30));
        // Nourishing food
        categories.get(0).getItem(7).setStats(new Stats()
                .add(Stat.HealthPerDay, 90)
                .add(Stat.ReputationPerDay, 40)
                .add(Stat.CoinsPerDay, -60));
        // Luxurious food
        categories.get(0).getItem(8).setStats(new Stats()
                .add(Stat.HealthPerDay, 100)
                .add(Stat.ReputationPerDay, 45)
                .add(Stat.CoinsPerDay, -70));
        // Aristocracy food
        categories.get(0).getItem(9).setStats(new Stats()
                .add(Stat.HealthPerDay, 120)
                .add(Stat.ReputationPerDay, 50)
                .add(Stat.CoinsPerDay, -80));
        // Royal food
        categories.get(0).getItem(10).setStats(new Stats()
                .add(Stat.HealthPerDay, 200)
                .add(Stat.ReputationPerDay, 300)
                .add(Stat.CoinsPerDay, -500));


        // Clothes
        categories.add(new Category(R.string.clothes, true));
        categories.get(1).setBackgroundDrawableId(R.drawable.bg_carpet);
        categories.get(1)
                .addItem(new SelectableItem(R.string.poor_mans_clothes, isMale ?
                        R.drawable.clothes_m_poor_man : R.drawable.clothes_w_poor_woman, 10))
                .addItem(new SelectableItem(R.string.peasant_clothes, isMale ?
                        R.drawable.clothes_m_peasant : R.drawable.clothes_w_peasant, 50))
                .addItem(new SelectableItem(R.string.citizen_clothes, isMale ?
                        R.drawable.clothes_m_citizen : R.drawable.clothes_w_citizen, 120))
                .addItem(new SelectableItem(R.string.luxurious_clothes, isMale ?
                        R.drawable.clothes_m_luxurious : R.drawable.clothes_w_luxurious, 450))
                .addItem(new SelectableItem(R.string.aristocracy_clothes, isMale ?
                        R.drawable.clothes_m_aristocracy : R.drawable.clothes_w_aristocracy, 2000))
                .addItem(new SelectableItem(R.string.earl_clothes, isMale ?
                        R.drawable.clothes_m_earl : R.drawable.clothes_w_earl, 8000))
                .addItem(new SelectableItem(R.string.royal_clothes, isMale ?
                        R.drawable.clothes_m_royal : R.drawable.clothes_w_royal, 40000));
        // Poor man/woman clothes
        categories.get(1).getItem(0).setStats(new Stats()
                .add(Stat.HealthPerDay, 5));
        categories.get(1).setSelectedItem(categories.get(1).getItem(0));
        // Peasant clothes
        categories.get(1).getItem(1).setStats(new Stats()
                .add(Stat.HealthPerDay, 10)
                .add(Stat.ReputationPerDay, 5)
                .add(Stat.CoinsPerDay, -5));
        // Citizen clothes
        categories.get(1).getItem(2).setStats(new Stats()
                .add(Stat.HealthPerDay, 15)
                .add(Stat.ReputationPerDay, 15)
                .add(Stat.CoinsPerDay, -10));
        // Luxurious clothes
        categories.get(1).getItem(3).setStats(new Stats()
                .add(Stat.HealthPerDay, 30)
                .add(Stat.ReputationPerDay, 20)
                .add(Stat.CoinsPerDay, -30));
        // Aristocracy clothes
        categories.get(1).getItem(4).setStats(new Stats()
                .add(Stat.HealthPerDay, 40)
                .add(Stat.ReputationPerDay, 30)
                .add(Stat.CoinsPerDay, -40));
        // Earl clothes
        categories.get(1).getItem(5).setStats(new Stats()
                .add(Stat.HealthPerDay, 70)
                .add(Stat.ReputationPerDay, 50)
                .add(Stat.CoinsPerDay, -60));
        // Royal clothes
        categories.get(1).getItem(6).setStats(new Stats()
                .add(Stat.HealthPerDay, 100)
                .add(Stat.ReputationPerDay, 200)
                .add(Stat.CoinsPerDay, -400));


        // Entertainment
        categories.add(new Category(R.string.entertainment, true));
        categories.get(2).setBackgroundDrawableId(R.drawable.bg_games);
        categories.get(2)
                .addItem(new SelectableItem(R.string.none, R.drawable.entertainment_none, 0))
                .addItem(new SelectableItem(R.string.have_a_drink_at_the_tavern, R.drawable.entertainment_tavern, 20))
                .addItem(new SelectableItem(R.string.walk_around_the_fair, R.drawable.entertainment_fair, 200))
                .addItem(new SelectableItem(R.string.watch_the_tournament, R.drawable.entertainment_tournament, 500))
                .addItem(new SelectableItem(R.string.visit_the_theatre, R.drawable.entertainment_theatre, 1200))
                .addItem(new SelectableItem(R.string.organize_a_hunt, R.drawable.entertainment_hunt, 4500))
                .addItem(new SelectableItem(R.string.throw_a_feast, R.drawable.entertainment_feast, 30000));
        // None
        categories.get(2).setSelectedItem(categories.get(2).getItem(0));
        // Tavern
        categories.get(2).getItem(1).setStats(new Stats()
                .add(Stat.HealthPerDay, 10)
                .add(Stat.ReputationPerDay, 5)
                .add(Stat.CoinsPerDay, -5));
        // Fair
        categories.get(2).getItem(2).setStats(new Stats()
                .add(Stat.HealthPerDay, 20)
                .add(Stat.ReputationPerDay, 15)
                .add(Stat.CoinsPerDay, -20));
        // Tournament
        categories.get(2).getItem(3).setStats(new Stats()
                .add(Stat.HealthPerDay, 30)
                .add(Stat.ReputationPerDay, 30)
                .add(Stat.CoinsPerDay, -30));
        // Theatre
        categories.get(2).getItem(4).setStats(new Stats()
                .add(Stat.HealthPerDay, 50)
                .add(Stat.ReputationPerDay, 40)
                .add(Stat.CoinsPerDay, -40));
        // Hunt
        categories.get(2).getItem(5).setStats(new Stats()
                .add(Stat.HealthPerDay, 70)
                .add(Stat.ReputationPerDay, 60)
                .add(Stat.CoinsPerDay, -60));
        // Feast
        categories.get(2).getItem(6).setStats(new Stats()
                .add(Stat.HealthPerDay, 100)
                .add(Stat.ReputationPerDay, 150)
                .add(Stat.CoinsPerDay, -300));


        // Weapon
        categories.add(new Category(R.string.weapon, true));
        categories.get(3).setBackgroundDrawableId(R.drawable.bg_metal_wall);
        categories.get(3)
                .addItem(new SelectableItem(R.string.none, R.drawable.weapon_none, 0))
                .addItem(new SelectableItem(R.string.ax, R.drawable.weapon_ax, 100))
                .addItem(new SelectableItem(R.string.rusty_sword, R.drawable.weapon_rusty_sword, 300))
                .addItem(new SelectableItem(R.string.steel_sword, R.drawable.weapon_steel_sword, 1000))
                .addItem(new SelectableItem(R.string.two_blades, R.drawable.weapon_two_blades, 3000))
                .addItem(new SelectableItem(R.string.ax_and_shield, R.drawable.weapon_ax_and_shield, 8000))
                .addItem(new SelectableItem(R.string.sword_and_shield, R.drawable.weapon_sword_and_shield, 15000))
                .addItem(new SelectableItem(R.string.ancient_artifacts, R.drawable.weapon_ancient_artifacts, 30000));
        // None
        categories.get(3).setSelectedItem(categories.get(3).getItem(0));
        // Ax
        categories.get(3).getItem(1).setStats(new Stats()
                .add(Stat.Might, 10)
                .add(Stat.ReputationPerDay, 5)
                .add(Stat.CoinsPerDay, -5));
        // Rusty sword
        categories.get(3).getItem(2).setStats(new Stats()
                .add(Stat.Might, 20)
                .add(Stat.ReputationPerDay, 10)
                .add(Stat.CoinsPerDay, -10)
                .add(Stat.StrengthRequired, 1));
        // Steel sword
        categories.get(3).getItem(3).setStats(new Stats()
                .add(Stat.Might, 40)
                .add(Stat.ReputationPerDay, 20)
                .add(Stat.CoinsPerDay, -20)
                .add(Stat.StrengthRequired, 2));
        // Two blades
        categories.get(3).getItem(4).setStats(new Stats()
                .add(Stat.Might, 80)
                .add(Stat.ReputationPerDay, 50)
                .add(Stat.CoinsPerDay, -40)
                .add(Stat.StrengthRequired, 2));
        // Ax and shield
        categories.get(3).getItem(5).setStats(new Stats()
                .add(Stat.Might, 100)
                .add(Stat.ReputationPerDay, 60)
                .add(Stat.CoinsPerDay, -50)
                .add(Stat.StrengthRequired, 3));
        // Sword and shield
        categories.get(3).getItem(6).setStats(new Stats()
                .add(Stat.Might, 150)
                .add(Stat.ReputationPerDay, 80)
                .add(Stat.CoinsPerDay, -70)
                .add(Stat.StrengthRequired, 3));
        // Ancient artifacts
        categories.get(3).getItem(7).setStats(new Stats()
                .add(Stat.Might, 200)
                .add(Stat.ReputationPerDay, 100)
                .add(Stat.CoinsPerDay, -200)
                .add(Stat.StrengthRequired, 4));


        // Armor
        categories.add(new Category(R.string.armor, true));
        categories.get(4).setBackgroundDrawableId(R.drawable.bg_metal_wall);
        categories.get(4)
                .addItem(new SelectableItem(R.string.none, R.drawable.armor_none, 0))
                .addItem(new SelectableItem(R.string.leather_armor, R.drawable.armor_leather, 100))
                .addItem(new SelectableItem(R.string.rusty_armor, R.drawable.armor_rusty, 300))
                .addItem(new SelectableItem(R.string.old_armor, R.drawable.armor_old, 1000))
                .addItem(new SelectableItem(R.string.heavy_armor, R.drawable.armor_heavy, 3000))
                .addItem(new SelectableItem(R.string.good_armor, R.drawable.armor_good, 8000))
                .addItem(new SelectableItem(R.string.luxurious_armor, R.drawable.armor_luxury, 15000))
                .addItem(new SelectableItem(R.string.kings_armor, R.drawable.armor_kings, 30000));
        // None
        categories.get(4).setSelectedItem(categories.get(4).getItem(0));
        // Leather armor
        categories.get(4).getItem(1).setStats(new Stats()
                .add(Stat.Might, 10)
                .add(Stat.ReputationPerDay, 5)
                .add(Stat.CoinsPerDay, -5));
        // Rusty armor
        categories.get(4).getItem(2).setStats(new Stats()
                .add(Stat.Might, 30)
                .add(Stat.ReputationPerDay, 15)
                .add(Stat.CoinsPerDay, -10)
                .add(Stat.StrengthRequired, 1));
        // Old armor
        categories.get(4).getItem(3).setStats(new Stats()
                .add(Stat.Might, 40)
                .add(Stat.ReputationPerDay, 25)
                .add(Stat.CoinsPerDay, -15)
                .add(Stat.StrengthRequired, 2));
        // Heavy armor
        categories.get(4).getItem(4).setStats(new Stats()
                .add(Stat.Might, 80)
                .add(Stat.ReputationPerDay, 50)
                .add(Stat.CoinsPerDay, -40)
                .add(Stat.StrengthRequired, 2));
        // Good armor
        categories.get(4).getItem(5).setStats(new Stats()
                .add(Stat.Might, 120)
                .add(Stat.ReputationPerDay, 80)
                .add(Stat.CoinsPerDay, -60)
                .add(Stat.StrengthRequired, 3));
        // Luxurious armor
        categories.get(4).getItem(6).setStats(new Stats()
                .add(Stat.Might, 140)
                .add(Stat.ReputationPerDay, 90)
                .add(Stat.CoinsPerDay, -65)
                .add(Stat.StrengthRequired, 3));
        // King armor
        categories.get(4).getItem(7).setStats(new Stats()
                .add(Stat.Might, 200)
                .add(Stat.ReputationPerDay, 110)
                .add(Stat.CoinsPerDay, -200)
                .add(Stat.StrengthRequired, 4));


        // House
        categories.add(new Category(R.string.housing, true));
        categories.get(5)
                .addItem(new SelectableItem(R.string.tent, R.drawable.housing_tent, 0))
                .addItem(new SelectableItem(R.string.hut, R.drawable.housing_hut, 1000))
                .addItem(new SelectableItem(R.string.old_house, R.drawable.housing_old_house, 3000))
                .addItem(new SelectableItem(R.string.stone_house, R.drawable.housing_stone_house, 6000))
                .addItem(new SelectableItem(R.string.country_house, R.drawable.housing_country_house, 12000))
                .addItem(new SelectableItem(R.string.small_mansion, R.drawable.housing_small_mansion, 30000))
                .addItem(new SelectableItem(R.string.medium_mansion, R.drawable.housing_medium_mansion, 45000))
                .addItem(new SelectableItem(R.string.large_mansion, R.drawable.housing_large_mansion, 60000));
        // Tent
        categories.get(5).getItem(0).setStats(new Stats()
                .add(Stat.MaxHealth, 100)
                .add(Stat.CoinsPerDay, -1));
        categories.get(5).setSelectedItem(categories.get(5).getItem(0));
        // Hut
        categories.get(5).getItem(1).setStats(new Stats()
                .add(Stat.MaxHealth, 200)
                .add(Stat.CoinsPerDay, -10));
        // Old house
        categories.get(5).getItem(2).setStats(new Stats()
                .add(Stat.MaxHealth, 500)
                .add(Stat.CoinsPerDay, -20));
        // Stone house
        categories.get(5).getItem(3).setStats(new Stats()
                .add(Stat.MaxHealth, 1000)
                .add(Stat.CoinsPerDay, -300));
        // Country house
        categories.get(5).getItem(4).setStats(new Stats()
                .add(Stat.MaxHealth, 1500)
                .add(Stat.CoinsPerDay, -500));
        // Small mansion
        categories.get(5).getItem(5).setStats(new Stats()
                .add(Stat.MaxHealth, 2000)
                .add(Stat.CoinsPerDay, -1000));
        // Medium mansion
        categories.get(5).getItem(6).setStats(new Stats()
                .add(Stat.MaxHealth, 3500)
                .add(Stat.CoinsPerDay, -2500));
        // Large mansion
        categories.get(5).getItem(7).setStats(new Stats()
                .add(Stat.MaxHealth, 5000)
                .add(Stat.CoinsPerDay, -6000));


        // Furniture
        categories.add(new Category(R.string.furniture, true));
        categories.get(6)
                .addItem(new SelectableItem(R.string.none, R.drawable.furniture_none, 0))
                .addItem(new SelectableItem(R.string.old_furniture, R.drawable.furniture_old, 100))
                .addItem(new SelectableItem(R.string.ordinary_furniture, R.drawable.furniture_ordinary, 500))
                .addItem(new SelectableItem(R.string.new_furniture, R.drawable.furniture_new, 1500))
                .addItem(new SelectableItem(R.string.luxurious_furniture, R.drawable.furniture_luxurious, 3000))
                .addItem(new SelectableItem(R.string.royal_furniture, R.drawable.furniture_royal, 10000));
        // None
        categories.get(6).setSelectedItem(categories.get(6).getItem(0));
        // Old furniture
        categories.get(6).getItem(1).setStats(new Stats()
                .add(Stat.HealthPerDay, 5)
                .add(Stat.ReputationPerDay, 5)
                .add(Stat.CoinsPerDay, -2));
        // Ordinary furniture
        categories.get(6).getItem(2).setStats(new Stats()
                .add(Stat.HealthPerDay, 10)
                .add(Stat.ReputationPerDay, 10)
                .add(Stat.CoinsPerDay, -5));
        // New furniture
        categories.get(6).getItem(3).setStats(new Stats()
                .add(Stat.HealthPerDay, 20)
                .add(Stat.ReputationPerDay, 20)
                .add(Stat.CoinsPerDay, -10));
        // Luxurious furniture
        categories.get(6).getItem(4).setStats(new Stats()
                .add(Stat.HealthPerDay, 30)
                .add(Stat.ReputationPerDay, 30)
                .add(Stat.CoinsPerDay, -50));
        // Royal furniture
        categories.get(6).getItem(5).setStats(new Stats()
                .add(Stat.HealthPerDay, 60)
                .add(Stat.ReputationPerDay, 50)
                .add(Stat.CoinsPerDay, -200));


        // Books
        categories.add(new Category(R.string.books, false));
        categories.get(7).setBackgroundDrawableId(R.drawable.bg_books);
        categories.get(7)
                .addItem(new Item(R.string.old_book, R.drawable.book_old, 200))
                .addItem(new Item(R.string.new_book, R.drawable.book_new, 600))
                .addItem(new Item(R.string.scientific_book, R.drawable.book_scientific, 1200))
                .addItem(new Item(R.string.military_strategy_books, R.drawable.book_military_strategy, 8000))
                .addItem(new Item(R.string.small_library, R.drawable.book_small_library, 12000))
                .addItem(new Item(R.string.medium_library, R.drawable.book_medium_library, 20000))
                .addItem(new Item(R.string.huge_library, R.drawable.book_huge_library, 50000))
                .addItem(new Item(R.string.royal_library, R.drawable.book_royal_library, 100000));
        // Old book
        categories.get(7).getItem(0).setStats(new Stats()
                .add(Stat.ReputationImpact, 10));
        // New book
        categories.get(7).getItem(1).setStats(new Stats()
                .add(Stat.ReputationImpact, 30));
        // Science book
        categories.get(7).getItem(2).setStats(new Stats()
                .add(Stat.Might, 10)
                .add(Stat.ReputationImpact, 50));
        // Military strategy books
        // TODO: add stat bonuses
        // Small library
        categories.get(7).getItem(4).setStats(new Stats()
                .add(Stat.Might, 40)
                .add(Stat.ReputationImpact, 100));
        // Medium library
        // TODO: add stat bonuses
        // Huge library
        // TODO: add stat bonuses
        // Royal library
        // TODO: add stat bonuses


        // Artworks
        categories.add(new Category(R.string.artworks, false));
        categories.get(8).setBackgroundDrawableId(R.drawable.bg_art);
        categories.get(8)
                .addItem(new Item(R.string.workshop_figurine, R.drawable.artwork_figurine_from_the_workshop, 300))
                .addItem(new Item(R.string.local_painting, R.drawable.artwork_local_painting, 800))
                .addItem(new Item(R.string.painting_by_a_famous_artist, R.drawable.artwork_painting_by_a_famous_artist, 1500))
                .addItem(new Item(R.string.marble_statue, R.drawable.artwork_marble_statue, 4000))
                .addItem(new Item(R.string.private_museum, R.drawable.artwork_private_museum, 15000));
        // Figurine from the workshop
        categories.get(8).getItem(0).setStats(new Stats()
                .add(Stat.ReputationImpact, 30));
        // Local painting
        categories.get(8).getItem(1).setStats(new Stats()
                .add(Stat.ReputationImpact, 60));
        // Painting by a famous artist
        categories.get(8).getItem(2).setStats(new Stats()
                .add(Stat.ReputationImpact, 100));
        // Marble statue
        categories.get(8).getItem(3).setStats(new Stats()
                .add(Stat.ReputationImpact, 150));
        // Private museum
        categories.get(8).getItem(4).setStats(new Stats()
                .add(Stat.ReputationImpact, 250));


        // Horse
        categories.add(new Category(R.string.horse, true));
        categories.get(9).setBackgroundDrawableId(R.drawable.bg_horses);
        categories.get(9)
                .addItem(new SelectableItem(R.string.none, R.drawable.horse_none, 0))
                .addItem(new SelectableItem(R.string.lame_horse, R.drawable.horse_lame, 1500))
                .addItem(new SelectableItem(R.string.young_horse, R.drawable.horse_young, 5000))
                .addItem(new SelectableItem(R.string.fast_horse, R.drawable.horse_fast, 10000))
                .addItem(new SelectableItem(R.string.light_armored_horse, R.drawable.horse_light_armored, 20000))
                .addItem(new SelectableItem(R.string.medium_armored_horse, R.drawable.horse_medium_armored, 30000))
                .addItem(new SelectableItem(R.string.heavy_armored_horse, R.drawable.horse_heavy_armored, 50000));
        // None
        categories.get(9).setSelectedItem(categories.get(9).getItem(0));
        // Lame horse
        categories.get(9).getItem(1).setStats(new Stats()
                .add(Stat.Might, 10)
                .add(Stat.ReputationPerDay, 10)
                .add(Stat.CoinsPerDay, -5));
        // Young horse
        categories.get(9).getItem(2).setStats(new Stats()
                .add(Stat.Might, 20)
                .add(Stat.ReputationPerDay, 20)
                .add(Stat.CoinsPerDay, -10));
        // Fast horse
        categories.get(9).getItem(3).setStats(new Stats()
                .add(Stat.Might, 40)
                .add(Stat.ReputationPerDay, 25)
                .add(Stat.CoinsPerDay, -20));
        // Light armored horse
        categories.get(9).getItem(4).setStats(new Stats()
                .add(Stat.Might, 80)
                .add(Stat.ReputationPerDay, 40)
                .add(Stat.CoinsPerDay, -30));
        // Medium armored horse
        categories.get(9).getItem(5).setStats(new Stats()
                .add(Stat.Might, 120)
                .add(Stat.ReputationPerDay, 40)
                .add(Stat.CoinsPerDay, -50));
        // Heavy armored horse
        categories.get(9).getItem(6).setStats(new Stats()
                .add(Stat.Might, 160)
                .add(Stat.ReputationPerDay, 50)
                .add(Stat.CoinsPerDay, -250));


        // Work in village
        categories.add(new Category(R.string.work_in_a_village, false));
        categories.get(10)
                .addItem(new Work(R.string.grazing_animals, R.drawable.work_to_graze_animals, 5))
                .addItem(new Work(R.string.harvesting, R.drawable.work_harvesting, 10))
                .addItem(new Work(R.string.logging, R.drawable.work_logging, 15))
                .addItem(new Work(R.string.work_in_a_mine, R.drawable.work_in_a_mine, 20))
                .addItem(new Work(R.string.unloading_ships, R.drawable.work_unloading_ships, 25));
        // To graze animals
        categories.get(10).getItem(0).setStats(new Stats()
                .add(Stat.HealthImpact, -20)
                .add(Stat.ReputationImpact, 5)
                .add(Stat.MoneyPerClick, 1));
        // Harvesting
        categories.get(10).getItem(1).setStats(new Stats()
                .add(Stat.HealthImpact, -40)
                .add(Stat.ReputationImpact, 10)
                .add(Stat.MoneyPerClick, 2)
                .add(Stat.ReputationRequired, 500));
        // Logging
        categories.get(10).getItem(2).setStats(new Stats()
                .add(Stat.HealthImpact, -60)
                .add(Stat.ReputationImpact, 20)
                .add(Stat.MoneyPerClick, 3)
                .add(Stat.ReputationRequired, 2000));
        // Work in a mine
        categories.get(10).getItem(3).setStats(new Stats()
                .add(Stat.HealthImpact, -80)
                .add(Stat.ReputationImpact, 40)
                .add(Stat.MoneyPerClick, 4)
                .add(Stat.ReputationRequired, 4000));
        // Unloading ships
        categories.get(10).getItem(4).setStats(new Stats()
                .add(Stat.HealthImpact, -100)
                .add(Stat.ReputationImpact, 60)
                .add(Stat.MoneyPerClick, 5)
                .add(Stat.ReputationRequired, 8000));


        // Work in town
        categories.add(new Category(R.string.work_in_a_town, false));
        categories.get(11)
                .addItem(new Work(R.string.cart_repair, R.drawable.work_cart_repair, 25))
                .addItem(new Work(R.string.architects_apprentice, R.drawable.work_architects_apprentice, 30))
                .addItem(new Work(R.string.weavers_apprentice, R.drawable.work_weavers_apprentice, 35))
                .addItem(new Work(R.string.craftsmans_apprentice, R.drawable.work_craftsmans_apprentice, 40))
                .addItem(new Work(R.string.blacksmiths_apprentice, R.drawable.work_blacksmiths_apprentice, 45))
                .addItem(new Work(R.string.lords_courtier, R.drawable.work_lords_courtier, 50));
        // Cart repair
        categories.get(11).getItem(0).setStats(new Stats()
                .add(Stat.HealthImpact, -20)
                .add(Stat.ReputationImpact, 60)
                .add(Stat.MoneyPerClick, 6)
                .add(Stat.ReputationRequired, 8000));
        // Architect's apprentice
        categories.get(11).getItem(1).setStats(new Stats()
                .add(Stat.HealthImpact, -10)
                .add(Stat.ReputationImpact, 80)
                .add(Stat.MoneyPerClick, 8)
                .add(Stat.ReputationRequired, 10000));
        // Weaver's apprentice
        categories.get(11).getItem(2).setStats(new Stats()
                .add(Stat.HealthImpact, -15)
                .add(Stat.ReputationImpact, 100)
                .add(Stat.MoneyPerClick, 10)
                .add(Stat.ReputationRequired, 12000));
        // Craftsman's apprentice
        categories.get(11).getItem(3).setStats(new Stats()
                .add(Stat.HealthImpact, -15)
                .add(Stat.ReputationImpact, 150)
                .add(Stat.MoneyPerClick, 15)
                .add(Stat.ReputationRequired, 30000));
        // Blacksmith's apprentice
        categories.get(11).getItem(4).setStats(new Stats()
                .add(Stat.HealthImpact, -20)
                .add(Stat.ReputationImpact, 200)
                .add(Stat.MoneyPerClick, 20)
                .add(Stat.ReputationRequired, 60000));
        // Lord's courtier
        categories.get(11).getItem(5).setStats(new Stats()
                .add(Stat.HealthImpact, -10)
                .add(Stat.ReputationImpact, 500)
                .add(Stat.MoneyPerClick, 50)
                .add(Stat.ReputationRequired, 100000));


        // Caravan protection
        categories.add(new Category(R.string.caravan_protection, false));
        Work work = new Work(R.string.caravan_protection, R.drawable.work_caravan_protection, 0);
        work.setState(Work.State.NotSelected);
        work.setEnergyConsumption(2);
        work.setStats(new Stats()
                .add(Stat.HealthPerDay, -80)
                .add(Stat.ReputationPerDay, 80)
                .add(Stat.CoinsPerDay, 80)
                .add(Stat.HorseAndWeaponRequired, 1));
        categories.get(12).addItem(work);


        // Mercenary in the army
        categories.add(new Category(R.string.mercenary_in_the_army, false));
        work = new Work(R.string.mercenary_in_the_army, R.drawable.work_mercenary_in_the_army, 0);
        work.setState(Work.State.NotSelected);
        work.setEnergyConsumption(2);
        work.setStats(new Stats()
                .add(Stat.HealthPerDay, -50)
                .add(Stat.ReputationPerDay, 80)
                .add(Stat.CoinsPerMight, 3)
                .add(Stat.ReputationRequired, 8000));
        categories.get(13).addItem(work);
    }


    public static ArrayList<ICategory> getCategories() {
        return categories;
    }
}
