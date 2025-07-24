package com.scipath.becomeaking.contract.model;

import com.scipath.becomeaking.model.enums.Sex;
import com.scipath.becomeaking.model.enums.Title;

import java.io.Serializable;


public interface IPersonage extends Serializable {

    // Accessors
    String getName();

    Sex getSex();

    Title getTitle();

    ILevel getLevel();

    int getAge();

    int getMaxHealth();

    int getHealth();

    int getReputation();

    int getMoney();

    int getMight();


    // Mutators
    void setName(String name);

    void setSex(Sex sex);

    void setTitle(Title title);

    void setLevel(ILevel ILevel);

    void setAge(int age);

    void setMaxHealth(int maxHealth);

    void setHealth(int health);

    void setReputation(int reputation);

    void setMoney(int money);

    void setMight(int might);


    // Methods
    void affectHealth(int health);

    void affectReputation(int reputation);

    void affectMoney(int money);

    int restrictHealth(int health);

    void recalculateStats();
}
