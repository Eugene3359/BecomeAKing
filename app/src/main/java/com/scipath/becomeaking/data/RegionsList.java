package com.scipath.becomeaking.data;

import com.scipath.becomeaking.R;
import com.scipath.becomeaking.contract.model.IRegion;
import com.scipath.becomeaking.model.item.Region;

import java.util.ArrayList;

public class RegionsList {

    public static ArrayList<IRegion> getRegions() {
        ArrayList<IRegion> regions = new ArrayList<>();

        regions.add(new Region()
                .setNameId(R.string.grimshaven)
                .setDrawableId(R.drawable.vec_grimshaven_region)
                .setSizes(0.135f, 0.283f)
                .setCoordinates(0.164f, 0.167f)
        );

        return regions;
    }
}
