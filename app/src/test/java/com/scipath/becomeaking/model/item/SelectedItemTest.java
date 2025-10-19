package com.scipath.becomeaking.model.item;

import static org.junit.jupiter.api.Assertions.assertEquals;

import com.scipath.becomeaking.R;
import com.scipath.becomeaking.contract.model.ICategory;
import com.scipath.becomeaking.model.Category;
import com.scipath.becomeaking.model.Personage;
import com.scipath.becomeaking.model.Stats;
import com.scipath.becomeaking.model.enums.InteractionResult;
import com.scipath.becomeaking.model.enums.Sex;
import com.scipath.becomeaking.model.enums.Stat;
import com.scipath.becomeaking.model.enums.Title;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


public class SelectedItemTest {

    private SelectableItem item;


    @BeforeEach
    void setUp() {
        Item.idCounter = 0;
        item = new SelectableItem(R.string.steel_sword, R.drawable.img_steel_sword, 1000, new Stats()
                .add(Stat.Might, 40)
                .add(Stat.ReputationPerDay, 20)
                .add(Stat.CoinsPerDay, -20)
                .add(Stat.StrengthRequired, 2));
    }


    // Accessors
    @Test
    void getCost_returnsExpectedValue() {
        assertEquals(1000, item.getCost());
    }


    // Mutators
    @Test
    void setState_withSelectedState_changesItsCategorySelectedItem_ifCategoryIsSelectable() {
        ICategory category = new Category(0, true);
        category.addItem(item);
        item.setState(SelectableItem.State.Selected);
        assertEquals(item, category.getSelectedItem());
    }

    @Test
    void setCost_changesItemsCost() {
        item.setCost(2000);
        assertEquals(2000, item.getCost());
    }


    // Methods
    @Test
    void interact_withFulfillingRequirementsPersonage_returnsSuccessfulInteractionResultAndModifiesPersonage() {
        Personage personage = new Personage("Hero", Sex.Male, Title.Villager);
        personage.setMoney(1000);
        personage.getLevel().affectStrength(2);
        assertEquals(InteractionResult.Successful, item.interact(personage));
        assertEquals(0, personage.getMoney());
    }

    @Test
    void interact_withFulfillingRequirementsPersonage_returnsSuccessfulInteractionResultAndChangesItemsState() {
        Personage personage = new Personage("Hero", Sex.Male, Title.Villager);
        personage.setMoney(1000);
        personage.getLevel().affectStrength(2);
        assertEquals(InteractionResult.Successful, item.interact(personage));
        assertEquals(SelectableItem.State.Bought, item.getState());
        assertEquals(InteractionResult.Successful, item.interact(personage));
        assertEquals(SelectableItem.State.Selected, item.getState());
        assertEquals(InteractionResult.Successful, item.interact(personage));
        assertEquals(SelectableItem.State.Bought, item.getState());
    }

    @Test
    void interact_withNotEnoughMoneyPersonage_returnsNotEnoughMoneyInteractionResultAndDoNotModifiesPersonage() {
        Personage personage = new Personage("Hero", Sex.Male, Title.Villager);
        personage.setMoney(500);
        personage.getLevel().affectStrength(2);
        assertEquals(InteractionResult.NotEnoughMoney, item.interact(personage));
        assertEquals(500, personage.getMoney());
    }

    @Test
    void interact_withNotEnoughStrengthPersonage_returnsNotEnoughStrengthInteractionResultAndDoNotModifiesPersonage() {
        Personage personage = new Personage("Hero", Sex.Male, Title.Villager);
        personage.setMoney(1000);
        personage.getLevel().affectStrength(1);
        assertEquals(InteractionResult.NotEnoughStrength, item.interact(personage));
        assertEquals(1000, personage.getMoney());
    }

    @Test
    void interact_withNull_returnsNullPersonageInteractionResult() {
        assertEquals(InteractionResult.NullPersonage, item.interact(null));
    }
}
