package com.scipath.becomeaking.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.scipath.becomeaking.model.Personage;


public class PersonageViewModel extends ViewModel {

    // Fields
    private MutableLiveData<Personage> personage;


    // Constructor
    public PersonageViewModel() {
        personage = new MutableLiveData<>();
    }


    // Accessors
    public LiveData<Personage> getPersonage() {
        return personage;
    }


    // Mutators
    public void setPersonage(Personage personage) {
        this.personage.setValue(personage);
    }

    public void updateMoney(int value) {
        if (personage.getValue() != null) {
            personage.getValue().setMoney(value);
            personage.setValue(personage.getValue()); // This will trigger LiveData observers
        }
    }
}
