package com.example.mylistviewsimpleadapter;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

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

        SimpleAdapter myAdapter = new SimpleAdapter(this,
                list,
                R.layout.item_list,
                new String[]{"logo","title","version","size"},
                new int[]{R.id.logo,R.id.title,R.id.version,R.id.size}
        );

        listView.setAdapter(myAdapter);






    }
}