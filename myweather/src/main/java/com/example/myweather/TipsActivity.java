package com.example.myweather;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

public class TipsActivity extends AppCompatActivity {
    private RecyclerView rlvTips;

    private TipsAdapter mTipsAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tips);

        rlvTips = findViewById(R.id.rclv_tips);

        Intent intent = getIntent();
        DayWeatherBean weather =(DayWeatherBean) intent.getSerializableExtra("tips");
        if(weather == null){
            Log.e("TAG", "TipsActivity:  not get Data" );
            return;
        }
        mTipsAdapter = new TipsAdapter(this,weather.getOtherTips());
        rlvTips.setAdapter(mTipsAdapter);
        rlvTips.setLayoutManager(new LinearLayoutManager(this));


    }
}