package com.scipath.becomeaking.model;

import com.scipath.becomeaking.BecomeAKing;

import java.io.Serializable;


public class Personage implements Serializable {

    // Fields
    private String name;
    private Sex sex;
    private Title title;
    private Level level;
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
        level = new Level();
        age = 16;
        maxHealth = title.getMaxHealth();
        health = maxHealth;
        reputation = 0;
        money = 100;
        might = 0;
    }


    // Accessors
    public String getName() {
        return name;
    }

    public Sex getSex() {
        return sex;
    }

    public Title getTitle() {
        return title;
    }

    public Level getLevel() {
        return level;
    }

    public int getAge() {
        return age;
    }

    public int getMaxHealth() {
        return maxHealth;
    }

    public int getHealth() {
        return health;
    }

    public int getReputation() {
        return reputation;
    }

    public int getMoney() {
        return money;
    }

    public int getMight() {
        return might;
    }


    // Mutators
    public void setName(String name) {
        this.name = name;
    }

    public void setSex(Sex sex) {
        this.sex = sex;
    }

    public void setTitle(Title title) {
        this.title = title;
    }

    public void setLevel(Level level) {
        this.level = level;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public void setMaxHealth(int maxHealth) {
        this.maxHealth = maxHealth;
    }

    public void setHealth(int health) {
        this.health = restrictHealth(health);
    }

    public void affectHealth(int health) {
        this.health = restrictHealth(this.health + health);
    }

    public void setReputation(int reputation) {
        this.reputation = reputation;
    }

    public void affectReputation(int reputation) {
        this.reputation += reputation;
    }

    public void setMoney(int money) {
        this.money = money;
    }

    public void affectMoney(int money) {
        this.money += money;
    }

    public void setMight(int might) {
        this.might = might;
    }


    // Methods
    private int restrictHealth(int health) {
        // Health can't be bellow zero or above maximum health
        return Math.max(0, Math.min(health, maxHealth));
    }

    public void recalculateStats() {
        StatBonusesMap statBonuses = BecomeAKing.getInstance().getCurrentStatBonuses();
        maxHealth = Math.max(title.getMaxHealth(), statBonuses.get(StatBonus.MaxHealth));
        might = statBonuses.get(StatBonus.Might);
    }
}
