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
    private String name;
    private Sex sex;
    private Title title;
    private com.scipath.becomeaking.contract.model.ILevel ILevel;
    private int age;
    private int maxHealth;
    private int health;
    private int reputation;
    private int money;
    private int might;


    // Constructor
    public Personage(String name, Sex sex, Title title) {
        this.name = name;
        this.sex = sex;
        this.title = title;
        ILevel = new Level();
        age = 20;
        maxHealth = title.getMaxHealth();
        health = maxHealth;
        reputation = 0;
        money = 100;
        might = 0;
    }


    // Accessors
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
        return ILevel;
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
    }

    @Override
    public void setLevel(ILevel ILevel) {
        this.ILevel = ILevel;
    }

    @Override
    public void setAge(int age) {
        this.age = age;
    }

    @Override
    public void setMaxHealth(int maxHealth) {
        this.maxHealth = maxHealth;
    }

    @Override
    public void setHealth(int health) {
        this.health = restrictHealth(health);
    }

    @Override
    public void setReputation(int reputation) {
        this.reputation = reputation;
    }

       @Override
    public void setMoney(int money) {
        this.money = money;
    }

    @Override
    public void setMight(int might) {
        this.might = might;
    }


    // Methods
    @Override
    public void affectHealth(int health) {
        this.health = restrictHealth(this.health + health);
    }

    @Override
    public void affectReputation(int reputation) {
        this.reputation += reputation;
    }

    @Override
    public void affectMoney(int money) {
        this.money += money;
    }

    @Override
    public int restrictHealth(int health) {
        // Health can't be bellow zero or above maximum health
        return Math.max(0, Math.min(health, maxHealth));
    }

    @Override
    public void recalculateStats() {
        IStats stats = BecomeAKing.getInstance().getCurrentStatBonuses();
        maxHealth = Math.max(title.getMaxHealth(), stats.get(Stat.MaxHealth));
        might = stats.get(Stat.Might);
    }
}
