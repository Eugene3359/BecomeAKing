package com.scipath.becomeaking.model.item;

import android.content.Context;

import com.scipath.becomeaking.BecomeAKing;
import com.scipath.becomeaking.R;
import com.scipath.becomeaking.contract.model.ICategory;
import com.scipath.becomeaking.model.Personage;
import com.scipath.becomeaking.model.enums.Stat;
import com.scipath.becomeaking.model.Stats;

import java.util.ArrayList;


public class Work extends Item {

    // Variables
    protected static int interactionCounter = 0;
    protected int interactionValue;
    protected int experience;


    // Constructors
    public Work(int nameId, int imageId, int experience) {
        super(nameId, imageId, 0);
        setExperience(experience);
        this.interactionNameId = R.string.start;
        this.interactionResultNameId = R.string.done;
        interactionValue = 1;
    }

    public Work(int nameId, int imageId, Stats stats, int experience) {
        super(nameId, imageId, 0, stats);
        setExperience(experience);
        this.interactionNameId = R.string.start;
        this.interactionResultNameId = R.string.done;
        interactionValue = 1;
    }


    // Accessors
    public int getExperience() {
        return experience;
    }

    public int getInteractionValue() {
        return interactionValue;
    }


    // Mutators
    @Override
    public void setCost(int cost) {
        // Cost for work is always 0;
    }

    public void setExperience(int experience) {
        this.experience = Math.max(0, experience);
    }

    public void setInteractionValue(int value) {
        interactionValue = value;
    }


    // Methods
    @Override
    public String getInteractionName(Context context) {
        return context.getString(interactionNameId);
    }

    @Override
    public int interact(Personage personage) {
        if (personage == null) return -10; // Null

        // Check for reputation requirement
        int personageReputation = personage.getReputation();
        int reputationRequired = stats.get(Stat.ReputationRequired);
        if (personageReputation < reputationRequired) return -3; // Not enough reputation

        // Check for number of completed works
        if (interactionCounter + interactionValue > 2) return -4;

        // Check for horse and weapon requirement
        int horseAndWeaponRequired = stats.get(Stat.HorseAndWeaponRequired);
        if (horseAndWeaponRequired != 0) {
            ArrayList<ICategory> categories = BecomeAKing.getInstance().getCategories();
            if (categories.get(3).getBestItem() == null || categories.get(9).getBestItem() == null)
                return -5;
        }

        // Work
        interacted = true;
        personage.affectHealth(stats.get(Stat.HealthImpact));
        personage.affectReputation(stats.get(Stat.ReputationImpact));
        personage.getLevel().gainExperience(experience);
        interactionCounter += interactionValue;
        return 0;
    }

    public static void refreshInteractionCounter() {
        interactionCounter = 0;
    }
}
