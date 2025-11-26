package com.scipath.becomeaking.model.item;

import com.scipath.becomeaking.R;
import com.scipath.becomeaking.contract.model.IItemState;
import com.scipath.becomeaking.contract.model.IStats;


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
}
