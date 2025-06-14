package com.scipath.becomeaking.model;

import java.io.Serializable;


public class Personage implements Serializable {

    // Fields
    private String name;
    private Sex sex;
    private Title title;
    private Level level;
    private int age;
    private int health;
    private int reputation;
    private int money;
    private int regeneration;
    private int strength;


    // Constructor
    public Personage(String name, Sex sex, Title title) {
        this.name = name;
        this.sex = sex;
        this.title = title;
        level = new Level();
        age = 16;
        health = 100;
        reputation = 0;
        money = 100;
        regeneration = 0;
        strength = 0;
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

    public int getHealth() {
        return health;
    }

    public int getReputation() {
        return reputation;
    }

    public int getMoney() {
        return money;
    }

    public int getRegeneration() {
        return regeneration;
    }

    public int getStrength() {
        return strength;
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

    public void setAge(int age) {
        this.age = age;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public void affectHealth(int health) {
        this.health += health;
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

    public void setRegeneration(int regeneration) {
        this.regeneration = regeneration;
    }

    public void setStrength(int strength) {
        this.strength = strength;
    }
}
