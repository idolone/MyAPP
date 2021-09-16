package com.example.myviewmodesp;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.SavedStateHandle;

public class MyViewModel extends AndroidViewModel {
    SavedStateHandle handle;
    private static final String VM_KEY ="VM_KEY";
    public MyViewModel(@NonNull Application application,SavedStateHandle handle) {
        super(application);
        this.handle = handle;
        if(!handle.contains(VM_KEY)){
           load();
        }
    }

    public MutableLiveData<Integer> getNum(){
        return handle.getLiveData(VM_KEY);
    }

    private void load() {
        int x = SPUtil.getIntWithDefault(getApplication().getApplicationContext(),VM_KEY,0 );
        handle.set(VM_KEY,x);
    }

    public void save(){
        SPUtil.saveInt(getApplication().getApplicationContext(),VM_KEY,getNum().getValue()==null?0:getNum().getValue());
    }

    public void add(int p){
        handle.set(VM_KEY,getNum().getValue()==null?0:getNum().getValue() + p);
    }


}
