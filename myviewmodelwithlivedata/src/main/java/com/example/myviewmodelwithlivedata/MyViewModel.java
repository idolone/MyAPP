package com.example.myviewmodelwithlivedata;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class MyViewModel extends ViewModel {
    private MutableLiveData<Integer> likedNum;

    public MyViewModel() {
        likedNum = new MutableLiveData<>();
        likedNum.setValue(0);
    }

    public MutableLiveData<Integer> getLikedNum() {
        return likedNum;
    }

    public void setLikedNum(MutableLiveData<Integer> likedNum) {
        this.likedNum = likedNum;
    }

    public void addLikedNum(int n){
        likedNum.setValue(n + likedNum.getValue());
    }

}
