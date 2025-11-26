package com.scipath.becomeaking.contract.model;

import com.scipath.becomeaking.model.enums.Sex;
import com.scipath.becomeaking.model.enums.Title;

import java.io.Serializable;


public interface IPersonage extends Serializable {

    // Accessors
    int getId();

    String getName();

    Sex getSex();

    Title getTitle();

    ILevel getLevel();

    int getAge();

    int getMaxHealth();

    int getHealth();

    int getReputation();

    int getMoney();

    int getEnergy();

    int getMight();


    // Mutators
    void setName(String name);

    void setSex(Sex sex);

    void setTitle(Title title);

    void setLevel(ILevel ILevel);

    void setAge(int age);

    void setHealth(int health);

    void affectHealth(int health);

    void setReputation(int reputation);

    void affectReputation(int reputation);

    void setMoney(int money);

    void affectMoney(int money);

    void setEnergy(int energy);

    void affectEnergy(int energy);

    void renewEnergy();

    void setMight(int might);


    // Methods
    void recalculateStats();
}
