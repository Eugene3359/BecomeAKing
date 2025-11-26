package com.scipath.becomeaking.model;

import com.scipath.becomeaking.BecomeAKing;
import com.scipath.becomeaking.contract.model.ICategory;
import com.scipath.becomeaking.contract.model.IItem;
import com.scipath.becomeaking.contract.model.ILevel;
import com.scipath.becomeaking.contract.model.IPersonage;
import com.scipath.becomeaking.contract.model.IStats;
import com.scipath.becomeaking.model.enums.InteractionResult;
import com.scipath.becomeaking.model.enums.Sex;
import com.scipath.becomeaking.model.enums.Stat;
import com.scipath.becomeaking.model.enums.Title;
import com.scipath.becomeaking.model.item.Food;
import com.scipath.becomeaking.model.item.Item;
import com.scipath.becomeaking.model.item.SelectableItem;
import com.scipath.becomeaking.model.item.Work;

import java.util.ArrayList;


public class Personage implements IPersonage {

    // Fields
    protected static int idCounter = 0;
    protected final static int maxEnergy = 2;

    protected int id;
    protected String name;
    protected Sex sex;
    protected Title title;
    protected ILevel level;
    protected int age;
    protected int maxHealth;
    protected int health;
    protected int reputation;
    protected int money;
    protected int energy;
    protected int might;


    // Constructor
    public Personage(String name, Sex sex, Title title) {
        this.id = idCounter++;
        this.name = name;
        this.sex = sex;
        this.title = title;
        this.level = new Level();
        this.age = 20;
        this.maxHealth = title.maxHealth;
        this.health = maxHealth;
        this.reputation = 0;
        this.money = 100;
        this.energy = maxEnergy;
        this.might = 0;
    }


    // Accessors
    @Override
    public int getId() {
        return id;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public Sex getSex() {
        return sex;
    }

    @Override
    public Title getTitle() {
        return title;
    }

    @Override
    public ILevel getLevel() {
        return level;
    }

    @Override
    public int getAge() {
        return age;
    }

    @Override
    public int getMaxHealth() {
        return maxHealth;
    }

    @Override
    public int getHealth() {
        return health;
    }

    @Override
    public int getReputation() {
        return reputation;
    }

    @Override
    public int getMoney() {
        return money;
    }

    @Override
    public int getEnergy() {
        return energy;
    }

    @Override
    public int getMight() {
        return might;
    }


    // Mutators
    @Override
    public void setName(String name) {
        this.name = name;
    }

    @Override
    public void setSex(Sex sex) {
        this.sex = sex;
    }

    @Override
    public void setTitle(Title title) {
        this.title = title;
        recalculateStats();
    }

    @Override
    public void setLevel(ILevel ILevel) {
        this.level = ILevel;
    }

    @Override
    public void setAge(int age) {
        this.age = age;
    }

    @Override
    public void setHealth(int health) {
        this.health = restrictValue(health, 0, maxHealth);
    }

    @Override
    public void affectHealth(int health) {
        this.health = restrictValue(this.health + health, 0, maxHealth);
    }

    @Override
    public void setReputation(int reputation) {
        this.reputation = reputation;
    }

    @Override
    public void affectReputation(int reputation) {
        this.reputation += reputation;
    }

       @Override
    public void setMoney(int money) {
        this.money = money;
    }

    @Override
    public void affectMoney(int money) {
        this.money += money;
    }

    @Override
    public void setEnergy(int energy) {
        this.energy = restrictValue(energy, 0, maxEnergy);
    }

    @Override
    public void affectEnergy(int energy) {
        this.energy = restrictValue(this.energy + energy, 0, maxEnergy);
    }

    @Override
    public void renewEnergy() {
        energy = maxEnergy;
    }

    @Override
    public void setMight(int might) {
        this.might = might;
    }


    // Methods
    @Override
    public InteractionResult interact(IItem item) {
        // Check item for validity
        if (item == null)
            return InteractionResult.NullItem;

        InteractionResult interactionResult = InteractionResult.Successful;
        if (item instanceof Item) {
            interactionResult = interact((Item) item);
        } else if (item instanceof SelectableItem) {
            interactionResult = interact ((SelectableItem) item);
        } else if (item instanceof Food) {
            interactionResult = interact((Food) item);
        } else if (item instanceof Work) {
            interactionResult = interact((Work) item);
        }

        return interactionResult;
    }

    private InteractionResult interact(Item item) {
        // Check for money
        if (!checkForMoney(item))
            return InteractionResult.NotEnoughMoney;

        // Check for strength requirement
        if (!checkForStrength(item))
            return InteractionResult.NotEnoughStrength;

        // Interact
        if (item.getState() == Item.State.NotBought) {
            item.setState(Item.State.Bought);
            affectMoney(- item.getCost());
            affectReputation(item.getStat(Stat.ReputationImpact));
        }
        recalculateStats();

        return InteractionResult.Successful;
    }

    private InteractionResult interact(SelectableItem item) {
        // Check for money
        if (!checkForMoney(item))
            return InteractionResult.NotEnoughMoney;

        // Check for strength requirement
        if (!checkForStrength(item))
            return InteractionResult.NotEnoughStrength;

        // Interact
        switch (item.getState()) {
            case NotBought:
                item.setState(SelectableItem.State.Bought);
                affectMoney(- item.getCost());
                affectReputation(item.getStat(Stat.ReputationImpact));
                break;
            case Bought:
                item.setState(SelectableItem.State.Selected);
                break;
            case Selected:
                item.setState(SelectableItem.State.Bought);
                if (item.getCategory() != null)
                    item.getCategory().setSelectedItem(item.getCategory().getItem(0));
                break;
        }
        recalculateStats();

        return InteractionResult.Successful;
    }

    private InteractionResult interact(Food food) {
        // Interact
        if (food.getState() == Food.State.NotInRation)
            food.setState(Food.State.InRation);
        else
            food.setState(Food.State.NotInRation);

        return InteractionResult.Successful;
    }

    private InteractionResult interact(Work work) {
        // Work is already completed, no interaction available
        if (work.getState() == Work.State.Completed)
            return InteractionResult.Successful;

        // Work is already selected, no need for checks
        if (work.getState() == Work.State.Selected) {
            work.setState(Work.State.NotSelected);
            affectEnergy(work.getEnergyConsumption());
            return InteractionResult.Successful;
        }

        // Check energy
        if (work.getEnergyConsumption() > energy)
            return InteractionResult.NoTimeLeft;

        // Check for reputation requirement
        int personageReputation = getReputation();
        int reputationRequired = work.getStat(Stat.ReputationRequired);
        if (personageReputation < reputationRequired) {
            return InteractionResult.NotEnoughReputation;
        }

        // Check for horse and weapon requirement
        int horseAndWeaponRequired = work.getStat(Stat.HorseAndWeaponRequired);
        if (horseAndWeaponRequired != 0) {
            ArrayList<ICategory> categories = BecomeAKing.getInstance().getCategories();
            if (categories.get(3).getBestItem() == categories.get(3).getItem(0) ||
                    categories.get(9).getBestItem() == categories.get(9).getItem(0))
                return InteractionResult.HorseAndWeaponRequired;
        }

        // Interact in other cases
        if (work.getState() == Work.State.NotCompleted) {
            work.setState(Work.State.Completed);
            affectHealth(work.getStat(Stat.HealthImpact));
            affectReputation(work.getStat(Stat.ReputationImpact));
            getLevel().gainExperience(work.getExperience());
            affectEnergy(- work.getEnergyConsumption());
        } else if (work.getState() == Work.State.NotSelected) {
            work.setState(Work.State.Selected);
            affectEnergy(- work.getEnergyConsumption());
        }

        return InteractionResult.Successful;
    }

    private boolean checkForMoney(IItem item) {
        if (item.getState() != Item.State.NotBought &&
                item.getState() != SelectableItem.State.NotBought)
            return true; // Item is already bought, no money needed to interact

        int cost = 0;
        if (item instanceof Item)
            cost = ((Item) item).getCost();
        if (item instanceof SelectableItem)
            cost = ((SelectableItem) item).getCost();

        return (money >= cost);
    }

    private boolean checkForStrength(IItem item) {
        int personageStrength = getLevel().getStrength();
        int strengthRequired = item.getStat(Stat.StrengthRequired);
        return personageStrength >= strengthRequired;
    }

    @Override
    public void recalculateStats() {
        maxHealth = title.maxHealth;
        BecomeAKing app = BecomeAKing.getInstance();
        if (app != null) {
            IStats stats = app.getCurrentStatBonuses();
            maxHealth = Math.max(maxHealth, stats.get(Stat.MaxHealth));
            might = stats.get(Stat.Might);
        }
        health = restrictValue(health, 0, maxHealth);
    }

    private int restrictValue(int value, int minValue, int maxValue) {
        return Math.max(minValue, Math.min(value, maxValue));
    }
}
