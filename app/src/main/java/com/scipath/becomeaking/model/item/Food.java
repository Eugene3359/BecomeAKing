package com.scipath.becomeaking.model.item;

import com.scipath.becomeaking.R;
import com.scipath.becomeaking.contract.model.IItemState;
import com.scipath.becomeaking.contract.model.IStats;
import com.scipath.becomeaking.model.Personage;
import com.scipath.becomeaking.model.enums.InteractionResult;


public class Food extends BaseItem<Food.State> {

    public enum State implements IItemState {
        NotInRation(R.string.add_to_ration),
        InRation(R.string.in_ration);

        private final int interactionNameId;

        State(int interactionNameId) {
            this.interactionNameId = interactionNameId;
        }

        @Override
        public int getInteractionNameId() {
            return interactionNameId;
        }
    }


    // Constructor
    public Food(int nameId, int imageId) {
        super(nameId, imageId);
        this.state = State.NotInRation;
    }

    public Food(int nameId, int imageId, IStats stats) {
        super(nameId, imageId, stats);
        this.state = State.NotInRation;
    }


    // Methods
    @Override
    public InteractionResult interact(Personage personage) {
        // Check personage for validity
        if (personage == null)
            return InteractionResult.NullPersonage;

        // Interact
        if (state == State.NotInRation) {
            setState(State.InRation);
        } else {
            setState(State.NotInRation);
        }

        return InteractionResult.Successful;
    }
}
