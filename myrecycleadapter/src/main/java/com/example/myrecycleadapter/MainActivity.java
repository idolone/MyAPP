package com.example.myrecycleadapter;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private RecyclerView mRecyclerView;
    private List<Bean> mList;
    private MyAdapter myAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        initView();
        initData();
        initEvent();
    }

    private void initEvent(){

        myAdapter = new MyAdapter(this,mList);
        mRecyclerView.setAdapter(myAdapter);

      //  RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false);

        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(this,1);
        mRecyclerView.setLayoutManager(layoutManager);
    }

    private void initData(){
        mList = new ArrayList<>();

        Bean bean1 = new Bean();
        bean1.setTitle("天天静听");
        bean1.setVersion("1.0.0");
        bean1.setSize("12.68MB");
        bean1.setResId(R.drawable.ic_baseline_account_circle_24);
        mList.add(bean1);

        Bean bean2 = new Bean();
        bean2.setTitle("时刻捏人");
        bean2.setVersion("1.0.0");
        bean2.setSize("12.68MB");
        bean2.setResId(R.drawable.ic_baseline_account_circle_24);
        mList.add(bean2);

        Bean bean3 = new Bean();
        bean3.setTitle("360News");
        bean3.setVersion("1.0.0");
        bean3.setSize("12.68MB");
        bean3.setResId(R.drawable.ic_baseline_account_circle_24);
        mList.add(bean3);

        Bean bean4 = new Bean();
        bean4.setTitle("百度");
        bean4.setVersion("1.0.0");
        bean4.setSize("12.68MB");
        bean4.setResId(R.drawable.ic_baseline_account_circle_24);
        mList.add(bean4);



    }

    private void initView() {
        mRecyclerView = findViewById(R.id.rc_view);

    }
}