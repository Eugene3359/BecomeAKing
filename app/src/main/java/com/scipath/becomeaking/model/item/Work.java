package com.scipath.becomeaking.model.item;

import com.scipath.becomeaking.BecomeAKing;
import com.scipath.becomeaking.R;
import com.scipath.becomeaking.contract.model.ICategory;
import com.scipath.becomeaking.contract.model.IItemState;
import com.scipath.becomeaking.model.Personage;
import com.scipath.becomeaking.model.enums.InteractionResult;
import com.scipath.becomeaking.model.enums.Stat;
import com.scipath.becomeaking.model.Stats;

import java.util.ArrayList;


public class Work extends BaseItem<Work.State> {

    public enum State implements IItemState {
        NotCompleted(R.string.start),
        Completed(R.string.completed),
        NotSelected(R.string.select),
        Selected(R.string.remove);

        private final int interactionNameId;

        State(int interactionNameId) {
            this.interactionNameId = interactionNameId;
        }

        @Override
        public int getInteractionNameId() {
            return interactionNameId;
        }
    }


    // Fields
    protected int energyConsumption;
    protected int experience;


    // Constructors
    public Work(int nameId, int imageId, int experience) {
        super(nameId, imageId);
        this.state = State.NotCompleted;
        this.energyConsumption = 1;
        setExperience(experience);
    }

    public Work(int nameId, int imageId, Stats stats, int experience) {
        super(nameId, imageId, stats);
        this.state = State.NotCompleted;
        this.energyConsumption = 1;
        setExperience(experience);
    }


    // Accessors
    public int getEnergyConsumption() {
        return energyConsumption;
    }

    public int getExperience() {
        return experience;
    }


    // Mutators
    public void setEnergyConsumption(int value) {
        this.energyConsumption = Math.max(0, value);
    }

    public void setExperience(int experience) {
        this.experience = Math.max(0, experience);
    }


    // Methods
    @Override
    public InteractionResult interact(Personage personage) {
        // Check personage for validity
        if (personage == null)
            return InteractionResult.NullPersonage;

        // Check for number of completed works
        if (state != State.Selected && energyConsumption > personage.getEnergy())
            return InteractionResult.NoTimeLeft;

        // Check for reputation requirement
        int personageReputation = personage.getReputation();
        int reputationRequired = stats.get(Stat.ReputationRequired);
        if (personageReputation < reputationRequired) {
            return InteractionResult.NotEnoughReputation;
        }

        // Check for horse and weapon requirement
        int horseAndWeaponRequired = stats.get(Stat.HorseAndWeaponRequired);
        if (horseAndWeaponRequired != 0) {
            ArrayList<ICategory> categories = BecomeAKing.getInstance().getCategories();
            if (categories.get(3).getBestItem() == categories.get(3).getItem(0) ||
                categories.get(9).getBestItem() == categories.get(9).getItem(0))
                return InteractionResult.HorseAndWeaponRequired;
        }

        // Interact
        if (state == State.NotCompleted) {
            setState(State.Completed);
            personage.affectHealth(stats.get(Stat.HealthImpact));
            personage.affectReputation(stats.get(Stat.ReputationImpact));
            personage.getLevel().gainExperience(experience);
            personage.affectEnergy(-energyConsumption);
        } else if (state == State.NotSelected) {
            setState(State.Selected);
            personage.affectEnergy(-energyConsumption);
        } else if (state == State.Selected) {
            setState(State.NotSelected);
            personage.affectEnergy(+energyConsumption);
        }

        return InteractionResult.Successful;
    }
}
