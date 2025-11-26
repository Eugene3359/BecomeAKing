package com.scipath.becomeaking.model;

import com.scipath.becomeaking.BecomeAKing;
import com.scipath.becomeaking.contract.model.ILevel;
import com.scipath.becomeaking.contract.model.IPersonage;
import com.scipath.becomeaking.contract.model.IStats;
import com.scipath.becomeaking.model.enums.Sex;
import com.scipath.becomeaking.model.enums.Stat;
import com.scipath.becomeaking.model.enums.Title;


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
