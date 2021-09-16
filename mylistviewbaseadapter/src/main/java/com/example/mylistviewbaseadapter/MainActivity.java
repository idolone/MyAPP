package com.example.mylistviewbaseadapter;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getSupportActionBar().setTitle("ListView");

        ListView listView = findViewById(R.id.lv_main);

        List<Map<String,Object>> list = new ArrayList<>();
        Map<String,Object>   map = new HashMap<>();

        map.put("logo",R.drawable.ic_baseline_account_circle_24);
        map.put("title","千千静听");
        map.put("version","版本：0.1");
        map.put("size","大小：32.81MB");
        list.add(map);

        map = new HashMap<>();
        map.put("logo",R.drawable.ic_baseline_add_alarm_24);
        map.put("title","时刻捏人");
        map.put("version","版本：0.1");
        map.put("size","大小：32.81MB");
        list.add(map);

        map = new HashMap<>();
        map.put("logo",R.drawable.ic_baseline_add_circle_outline_24);
        map.put("title","360News");
        map.put("version","版本：0.1");
        map.put("size","大小：32.81MB");
        list.add(map);

        MyAdapter myAdapter = new MyAdapter(this);

        myAdapter.setMlist(list);

        listView.setAdapter(myAdapter);

        listView.setOnItemClickListener(this);
        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(MainActivity.this,"长按" + position,Toast.LENGTH_SHORT).show();
                return false;
            }
        });




    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Toast.makeText(this,"点击" + position,Toast.LENGTH_SHORT).show();
    }
}