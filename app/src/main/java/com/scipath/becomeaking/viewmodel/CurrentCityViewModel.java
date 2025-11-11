package com.scipath.becomeaking.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.scipath.becomeaking.BecomeAKing;
import com.scipath.becomeaking.contract.model.ICity;


public class CurrentCityViewModel extends AndroidViewModel {

    private final MutableLiveData<ICity> currentCity = new MutableLiveData<>(BecomeAKing.getInstance().getCity());

    public CurrentCityViewModel(@NonNull Application application) {
        super(application);
    }

    public LiveData<ICity> getCity() {
        return currentCity;
    }

    public void setCity(ICity city) {
        currentCity.postValue(city);
        BecomeAKing.getInstance().setCity(city);
    }
}
