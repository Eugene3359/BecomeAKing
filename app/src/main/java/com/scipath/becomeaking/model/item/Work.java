package com.scipath.becomeaking.model.item;

import com.scipath.becomeaking.R;
import com.scipath.becomeaking.contract.model.IItemState;
import com.scipath.becomeaking.model.Stats;


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
}
