package com.scipath.becomeaking.data;

import com.scipath.becomeaking.R;
import com.scipath.becomeaking.contract.model.IRegion;
import com.scipath.becomeaking.model.item.Region;

import java.util.ArrayList;

public class RegionsList {

    private static ArrayList<IRegion> regions;


    private static void init() {
        regions = new ArrayList<>();

        regions.add(new Region()
                .setNameId(R.string.grimshaven)
                .setDrawableId(R.drawable.vec_grimshaven_region)
                .setSizes(0.136f, 0.284f)
                .setCoordinates(0.164f, 0.167f)
        );
        regions.add(new Region()
                .setNameId(R.string.farendol)
                .setDrawableId(R.drawable.vec_farendol_region)
                .setSizes(0.161f, 0.310f)
                .setCoordinates(0.195f, 0.433f)
        );
        regions.add(new Region()
                .setNameId(R.string.thornford)
                .setDrawableId(R.drawable.vec_thornford_region)
                .setSizes(0.074f, 0.188f)
                .setCoordinates(0.231f, 0.335f)
        );
        regions.add(new Region()
                .setNameId(R.string.drakkenburg)
                .setDrawableId(R.drawable.vec_drakkenburg_region)
                .setSizes(0.165f, 0.305f)
                .setCoordinates(0.290f, 0.165f)
        );
        regions.add(new Region()
                .setNameId(R.string.steinhart)
                .setDrawableId(R.drawable.vec_steinhart_region)
                .setSizes(0.271f, 0.442f)
                .setCoordinates(0.377f, 0.694f)
        );
        regions.add(new Region()
                .setNameId(R.string.ravenholm)
                .setDrawableId(R.drawable.vec_ravenholm_region)
                .setSizes(0.274f, 0.400f)
                .setCoordinates(0.394f, 0.448f)
        );
        regions.add(new Region()
                .setNameId(R.string.valmir)
                .setDrawableId(R.drawable.vec_valmir_region)
                .setSizes(0.056f, 0.096f)
                .setCoordinates(0.425f, 0.379f)
        );
        regions.add(new Region()
                .setNameId(R.string.tenebris)
                .setDrawableId(R.drawable.vec_tenebris_region)
                .setSizes(0.048f, 0.148f)
                .setCoordinates(0.602f, 0.345f)
        );
        regions.add(new Region()
                .setNameId(R.string.wolfengard)
                .setDrawableId(R.drawable.vec_wolfengard_region)
                .setSizes(0.108f, 0.162f)
                .setCoordinates(0.710f, 0.233f)
        );
        regions.add(new Region()
                .setNameId(R.string.kastervik)
                .setDrawableId(R.drawable.vec_kastervik_region)
                .setSizes(0.159f, 0.263f)
                .setCoordinates(0.654f, 0.354f)
        );
        regions.add(new Region()
                .setNameId(R.string.morgenheim)
                .setDrawableId(R.drawable.vec_morgenheim_region)
                .setSizes(0.080f, 0.206f)
                .setCoordinates(0.680f, 0.670f)
        );
        regions.add(new Region()
                .setNameId(R.string.elmyria)
                .setDrawableId(R.drawable.vec_elmyria_region)
                .setSizes(0.236f, 0.296f)
                .setCoordinates(0.806f, 0.362f)
        );
        regions.add(new Region()
                .setNameId(R.string.blackhollow)
                .setDrawableId(R.drawable.vec_blackhollow_region)
                .setSizes(0.154f, 0.191f)
                .setCoordinates(0.822f, 0.492f)
        );
        regions.add(new Region()
                .setNameId(R.string.albreston)
                .setDrawableId(R.drawable.vec_albreston_region)
                .setSizes(0.175f, 0.428f)
                .setCoordinates(0.768f, 0.731f)
        );
        regions.add(new Region()
                .setNameId(R.string.schaderveld)
                .setDrawableId(R.drawable.vec_schaderveld_region)
                .setSizes(0.068f, 0.239f)
                .setCoordinates(0.842f, 0.672f)
        );
        regions.add(new Region()
                .setNameId(R.string.winterholm)
                .setDrawableId(R.drawable.vec_winterholm_region)
                .setSizes(0.039f, 0.157f)
                .setCoordinates(0.898f, 0.334f)
        );
    }


    public static ArrayList<IRegion> getRegions() {
        if (regions == null) {
            init();
        }
        return regions;
    }
}
