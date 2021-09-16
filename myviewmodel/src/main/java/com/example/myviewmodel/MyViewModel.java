package com.example.myviewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.SavedStateHandle;
import androidx.lifecycle.ViewModel;

public class MyViewModel extends ViewModel {
   private SavedStateHandle handle;

   private final String  KEY_1 = "NUMBER";

   public MyViewModel(SavedStateHandle handle) {
      if(!handle.contains(KEY_1)){
         handle.set(KEY_1,0);
      }
      this.handle = handle;
   }

   public LiveData<Integer> getNumber(){
      return handle.getLiveData(KEY_1);
   }

   public void add(){
      handle.set(KEY_1,(int)handle.get(KEY_1) + 1);
   }
}
