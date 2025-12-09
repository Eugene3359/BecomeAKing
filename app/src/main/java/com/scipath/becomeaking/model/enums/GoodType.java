package com.scipath.becomeaking.model.enums;

import com.scipath.becomeaking.R;

public enum GoodType {

    Wheat(R.string.wheat, R.drawable.good_wheat),
    Tools(R.string.tools, R.drawable.good_tools),
    Spices(R.string.spices, R.drawable.good_spices),
    Fish(R.string.fish, R.drawable.good_fish),
    Jewelry(R.string.jewelry, R.drawable.good_jewelry);


    public final int nameId;
    public final int imageId;


    GoodType(int nameId, int imageId) {
        this.nameId = nameId;
        this.imageId = imageId;
    }
}
