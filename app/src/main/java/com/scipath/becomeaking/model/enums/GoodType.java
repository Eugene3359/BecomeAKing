package com.scipath.becomeaking.model.enums;

import com.scipath.becomeaking.R;

public enum GoodType {

    Wheat(R.string.wheat, R.drawable.img_wheat),
    Tools(R.string.tools, R.drawable.img_tools);


    public final int nameId;
    public final int imageId;


    GoodType(int nameId, int imageId) {
        this.nameId = nameId;
        this.imageId = imageId;
    }
}
