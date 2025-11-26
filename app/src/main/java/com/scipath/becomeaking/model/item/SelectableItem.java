package com.scipath.becomeaking.model.item;

import android.content.Context;

import com.scipath.becomeaking.R;
import com.scipath.becomeaking.contract.model.IItemState;
import com.scipath.becomeaking.contract.model.IStats;


public class SelectableItem extends BaseItem<SelectableItem.State> {

    public enum State implements IItemState {
        NotBought(R.string.buy_d),
        Bought(R.string.select),
        Selected(R.string.selected);

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
    protected int cost;


    // Constructors
    public SelectableItem(int nameId, int imageId, int cost) {
        super(nameId, imageId);
        this.cost = cost;
        this.state = SelectableItem.State.NotBought;    }

    public SelectableItem(int nameId, int imageId, int cost, IStats stats) {
        super(nameId, imageId, stats);
        this.cost = cost;
        this.state = SelectableItem.State.NotBought;
    }


    // Accessors
    public int getCost() {
        return cost;
    }


    // Mutators
    public void setCost(int cost) {
        this.cost = cost;
    }

    @Override
    public void setState(State state) {
        super.setState(state);
        if (state == State.Selected && category != null) {
            category.setSelectedItem(this);
        }
    }


    // Methods
    @Override
    public String getInteractionName(Context context) {
        if (state == SelectableItem.State.NotBought) {
            return String.format(
                    context.getString(state.interactionNameId),
                    cost
            );
        } else {
            return super.getInteractionName(context);
        }
    }
}
