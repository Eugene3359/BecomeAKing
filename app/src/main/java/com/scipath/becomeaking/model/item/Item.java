package com.scipath.becomeaking.model.item;

import android.content.Context;

import com.scipath.becomeaking.R;
import com.scipath.becomeaking.contract.model.IItemState;
import com.scipath.becomeaking.contract.model.IStats;
import com.scipath.becomeaking.model.Personage;
import com.scipath.becomeaking.model.enums.InteractionResult;
import com.scipath.becomeaking.model.enums.Stat;


public class Item extends BaseItem<Item.State> {

    public enum State implements IItemState {
        NotBought(R.string.buy_d),
        Bought(R.string.select),
        Selected(R.string.selected);

        private final int interactionNameId;

        private State(int interactionNameId) {
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
    public Item(int nameId, int imageId, int cost) {
        super(nameId, imageId);
        this.cost = cost;
        this.state = Item.State.NotBought;
    }

    public Item(int nameId, int imageId, int cost, IStats stats) {
        super(nameId, imageId, stats);
        this.cost = cost;
        this.state = Item.State.NotBought;
    }


    // Accessors
    public int getCost() {
        return cost;
    }


    // Mutators
    @Override
    public void setState(State state) {
        super.setState(state);
        if (state == State.Selected && category != null) {
            category.setSelectedItem(this);
        }
    }

    public void setCost(int cost) {
        this.cost = cost;
    }


    // Methods
    @Override
    public String getInteractionName(Context context) {
        if (state == State.NotBought) {
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
        if (state == State.NotBought && personage.getMoney() < cost)
            return InteractionResult.NotEnoughMoney;

        // Check for strength requirement
        int personageStrength = personage.getLevel().getStrength();
        int strengthRequired = stats.get(Stat.StrengthRequired);
        if (personageStrength < strengthRequired)
            return InteractionResult.NotEnoughStrength;

        // Interact
        if (state == State.NotBought) {
            setState(State.Bought);
            personage.affectMoney(-cost);
            personage.affectReputation(stats.get(Stat.ReputationImpact));
        } else if (state == State.Bought) {
            setState(State.Selected);
        } else if (state == State.Selected){
            setState(State.Bought);
            if (category != null) {
                category.setSelectedItem(category.getItem(0));
            }
        }
        personage.recalculateStats();

        return InteractionResult.Successful;
    }
}
