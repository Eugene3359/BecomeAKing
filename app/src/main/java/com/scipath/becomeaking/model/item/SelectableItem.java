package com.scipath.becomeaking.model.item;

import android.content.Context;

import com.scipath.becomeaking.R;
import com.scipath.becomeaking.contract.model.IItemState;
import com.scipath.becomeaking.contract.model.IStats;
import com.scipath.becomeaking.model.Personage;
import com.scipath.becomeaking.model.enums.InteractionResult;
import com.scipath.becomeaking.model.enums.Stat;


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

    @Override
    public InteractionResult interact(Personage personage) {
        // Check personage for validity
        if (personage == null)
            return InteractionResult.NullPersonage;

        // Check for money
        if (state == SelectableItem.State.NotBought && personage.getMoney() < cost)
            return InteractionResult.NotEnoughMoney;

        // Check for strength requirement
        int personageStrength = personage.getLevel().getStrength();
        int strengthRequired = stats.get(Stat.StrengthRequired);
        if (personageStrength < strengthRequired)
            return InteractionResult.NotEnoughStrength;

        // Interact
        if (state == SelectableItem.State.NotBought) {
            setState(SelectableItem.State.Bought);
            personage.affectMoney(-cost);
            personage.affectReputation(stats.get(Stat.ReputationImpact));
        } else if (state == SelectableItem.State.Bought) {
            setState(SelectableItem.State.Selected);
        } else if (state == SelectableItem.State.Selected) {
            setState(SelectableItem.State.Bought);
            if (category != null) {
                category.setSelectedItem(category.getItem(0));
            }
        }
        personage.recalculateStats();

        return InteractionResult.Successful;
    }
}
