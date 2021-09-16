package com.example.myviewmodesavedstate;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.SavedStateHandle;
import androidx.lifecycle.ViewModel;

public class MyViewModel extends ViewModel {
    private SavedStateHandle handler;

    private final String KEY = "key";

    public MyViewModel(SavedStateHandle handler) {
        this.handler = handler;
    }
    public MutableLiveData<Integer> getLiveData(){
        if(!handler.contains(KEY)){
            handler.set(KEY,0);
        }
        return handler.getLiveData(KEY);
    }
    public void add(){
       getLiveData().setValue(getLiveData().getValue() + 1);
    }

}
