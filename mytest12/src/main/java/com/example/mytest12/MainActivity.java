package com.example.mytest12;

import androidx.annotation.FractionRes;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;


import android.os.Bundle;
import android.util.Log;

import com.example.mytest12.Util.LogUtil;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        LogUtil.debug("onCreate");

        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();


        fragmentTransaction.add(R.id.frml1,new BlankFragment1());
        fragmentTransaction.add(R.id.frml2,new BlankFragment2());
        fragmentTransaction.commit();



    }

    @Override
    protected void onStart() {
        super.onStart();
        LogUtil.debug("onStart");
    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);

        LogUtil.debug("onSaveInstanceState");

    }

    @Override
    protected void onRestart() {
        super.onRestart();
        LogUtil.debug("onRestart");
    }

    @Override
    protected void onResume() {
        LogUtil.debug("onResume");
        super.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
        LogUtil.debug("onPause");
    }

    @Override
    protected void onStop() {
        super.onStop();
        LogUtil.debug("onStop");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        LogUtil.debug("onDestroy");
    }
}