package com.scipath.becomeaking.model.enums;

import com.scipath.becomeaking.R;

public enum GoodType {

    Wheat(R.string.wheat, R.drawable.good_wheat),
    Meat(R.string.meat, R.drawable.good_meat),
    Fish(R.string.fish, R.drawable.good_fish),
    Cheese(R.string.cheese, R.drawable.good_cheese),
    Spices(R.string.spices, R.drawable.good_spices),
    Honey(R.string.honey, R.drawable.good_honey),
    Beer(R.string.beer, R.drawable.good_beer),
    Vine(R.string.vine, R.drawable.good_vine),
    Wool(R.string.wool, R.drawable.good_wool),
    Velvet(R.string.velvet, R.drawable.good_velvet),
    Silk(R.string.silk, R.drawable.good_silk),
    Dishes(R.string.dishes, R.drawable.good_dishes),
    Jewelry(R.string.jewelry, R.drawable.good_jewelry),
    BuildingMaterials(R.string.building_materials, R.drawable.good_building_materials),
    Tools(R.string.tools, R.drawable.good_tools),
    Weapon(R.string.weapon, R.drawable.good_weapon),
    Armor(R.string.armor, R.drawable.good_armor),
    Medicine(R.string.medicine, R.drawable.good_medicine);


    public final int nameId;
    public final int imageId;


    GoodType(int nameId, int imageId) {
        this.nameId = nameId;
        this.imageId = imageId;
    }
}
