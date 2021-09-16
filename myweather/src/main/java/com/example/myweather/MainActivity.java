package com.example.myweather;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatSpinner;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.myweather.Util.NetUtil1;
import com.google.gson.Gson;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private AppCompatSpinner mSpinner;
    private ArrayAdapter<String> mSpAdapter;
    private String[] mCities;

    private TextView tvWeather,tvTem,tvTemLowHigh,tvWin,tvAir;
    private ImageView ivWeather;
    private RecyclerView rlvFutureWeather;

    private DayWeatherBean todayWeather;

    private FutureWeatherAdapter mAdapter;

    private List<DayWeatherBean> mList;

    private Handler mHandler = new Handler(Looper.myLooper()){
        @Override
        public void handleMessage(@NonNull Message msg) {
            super.handleMessage(msg);
            if(msg.what == 1){

                String weather = (String)msg.obj;

                Log.e("TAG","handleMessage---"+weather);
                Gson gson = new Gson();
                Weather7Bean weather7Bean = gson.fromJson(weather, Weather7Bean.class);

                Log.e("TAG","weather---"+weather7Bean.toString());

                UpdateUIOfWeather(weather7Bean);


            }
        }
    };

    private void UpdateUIOfWeather(Weather7Bean weather7Bean) {

        if(weather7Bean == null){
            Log.e("TAG", "UpdateUIOfWeather 0: " + weather7Bean.toString());
            return;
        }
        List<DayWeatherBean> dayWeathers = weather7Bean.getDayWeathers();
         todayWeather = dayWeathers.get(0);
        if(todayWeather == null){
            Log.e("TAG", "UpdateUIOfWeather 1: " + todayWeather.toString());
            return;
        }
        Log.e("TAG", "UpdateUIOfWeather 1: " + todayWeather.toString());
        //当前天气得显示
        tvTem.setText(todayWeather.getTem1());
        tvWeather.setText(todayWeather.getWea() +"("+todayWeather.getDate()+todayWeather.getWeek()+")");
        tvTemLowHigh.setText(todayWeather.getTem2()+"~"+todayWeather.getTem1());
        tvWin.setText(todayWeather.getWin()[0] + todayWeather.getWin_speed());
        tvAir.setText("空气："+ todayWeather.getAir()+todayWeather.getAir_level()+"\n"+todayWeather.getAir_tips());

        ivWeather.setImageResource(getImgResOfWeather(todayWeather.getWea_img()));


        //未来天气的显示
        dayWeathers.remove(0);//去掉当天得天气

        mAdapter = new FutureWeatherAdapter(this,dayWeathers);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false);
        rlvFutureWeather.setLayoutManager(linearLayoutManager);

        rlvFutureWeather.setAdapter(mAdapter);
    }

    private int getImgResOfWeather(String weaStr){
       // xue、lei、shachen、wu、bingbao、yun、yu、yin、qing
        int result = 0;
        switch(weaStr){
            case "qing":
                result = R.drawable.sunny_day;
                break;
            case "yin":
                result = R.drawable.cloud1;
                break;
            case "yu":
                result = R.drawable.rain;
                break;
            case "yun":
                result = R.drawable.cloud0;
                break;
            case "bingbao":
                result = R.drawable.lei_ice_day;
                break;
            case "wu":
                result = R.drawable.fog0;
                break;
            case "shachen":
                result = R.drawable.sand;
                break;
            case "lei":
                result = R.drawable.lei_rain_day;
                break;
            case "xue":
                result = R.drawable.snow0;
                break;

        }
        return result;

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();
        initEvent();
    }

    private void initEvent() {

        tvAir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            //
                Intent intent = new Intent(MainActivity.this,TipsActivity.class);
                Log.e("TAG", "onClick: "+todayWeather);
                intent.putExtra("tips",todayWeather);

                startActivity(intent);
            }
        });

    }

    private void initView() {

        mSpinner = findViewById(R.id.sp_city);
        mCities = getResources().getStringArray(R.array.cities);

        mSpAdapter = new ArrayAdapter<>(this,R.layout.sp_item_layout,mCities);
        mSpinner.setAdapter(mSpAdapter);

        mSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
               String selectedCity = mCities[position];

               getWeatherOfCity1(selectedCity);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        tvWeather = findViewById(R.id.tv_weather);
        tvAir = findViewById(R.id.tv_air);
        tvTem = findViewById(R.id.tv_tem);
        tvTemLowHigh = findViewById(R.id.tv_tem_low_high);
        tvWin = findViewById(R.id.tv_win);

        ivWeather = findViewById(R.id.iv_weather);

        rlvFutureWeather = findViewById(R.id.rclv_future_weather);


    }

    private void getWeatherOfCity1(String selectedCity) {
        //开启子线程，请求网络
        new Thread(new Runnable() {
            @RequiresApi(api = Build.VERSION_CODES.KITKAT)
            @Override
            public void run() {
             //请求网络
             String weatherofCity = NetUtil1.getWeatherOfCity(selectedCity);
             //使用handler将数据传递给主线程
                Message msg = Message.obtain();
                msg.what = 1;
                msg.obj = (String)weatherofCity;

                mHandler.sendMessage(msg);

            }
        }).start();
    }

}