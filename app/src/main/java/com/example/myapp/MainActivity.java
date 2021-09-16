package com.example.myapp;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.myapp.Util.NetUtil;
import com.google.gson.Gson;

import org.json.JSONException;
import org.json.JSONObject;

public class MainActivity extends AppCompatActivity {

    private Runnable mRun;
    private static final String TAG = "KIN" ;
    private TextView tvContent;
    private TextView tvWeather,tvWin,tvWinLevel,tvTemLow,tvTemHigh;

    private Handler mhandler = new Handler(Looper.myLooper()){
        @Override
        public void handleMessage(@NonNull Message msg) {
            Log.d(TAG, "handleMessage: "+msg.what+"---"+msg.obj);

            super.handleMessage(msg);

            if(msg.what == 1){
                String strData = (String)msg.obj;
               // tvContent.setText(strData);
                parseJsonDataAndShow(strData);

                Toast.makeText(MainActivity.this,"主线程收到",Toast.LENGTH_SHORT).show();
            }
        }
    };

    /**
     *
     *jason数据示例
     * {
     *     “cityid”：“101120101,
     *     “city":"济南",
     *     "update_time","20:55,
     *     "wea":"晴",
     *     "wea_img":"qing",
     *     "tem":"11",
     *     "tem_day":"17",
     *     "tem_night":"7",
     *     "win":"东南风",
     *     "win_speed":"1级",
     *     “win_meter":"小于12km/h",
     *     "air":"73",
     * }
     */
    public void parseJsonDataAndShow(String jsonStr){

        try {
            JSONObject jsonObject = new JSONObject(jsonStr);
            String cityId = jsonObject.optString("cityid");
            String city = jsonObject.optString("city");
            String updateTime = jsonObject.optString("update_time");
            String weather = jsonObject.optString("wea");
            String weatherImg = jsonObject.optString("wea_img");
            String tem = jsonObject.optString("tem");
            String temDay = jsonObject.optString("tem_day");
            String temNight = jsonObject.optString("tem_night");
            String win = jsonObject.optString("win");
            String winSpeed = jsonObject.optString("win_speed");
            String winMeter = jsonObject.optString("win_meter");
            String air = jsonObject.optString("air");

            getSupportActionBar().setTitle(city);
            tvWeather.setText(weather);
            tvWin.setText(win);
            tvWinLevel.setText(winSpeed);
            tvTemHigh.setText(temDay);
            tvTemLow.setText(temNight);

            //把json字符串直接转换成JavaBean对象
            Gson gson = new Gson();
            WeatherBean weatherBean = gson.fromJson(jsonStr,WeatherBean.class);
            Log.e("TAG", "weatherBean: "+weatherBean.toString() );

            //把JavaBean 对象转换成json数据
            String jsonWeather = gson.toJson(weatherBean);

            Log.e("TAG", "jsonWeather: "+jsonWeather );


        } catch (JSONException e) {
            e.printStackTrace();
        }

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tvWeather = findViewById(R.id.et_weather);
        tvWin = findViewById(R.id.et_win);
        tvWinLevel = findViewById(R.id.et_winlevel);
        tvTemLow = findViewById(R.id.et_temLow);
        tvTemHigh = findViewById(R.id.et_temHigh);


    }


    public void sendMsg(View view) {
//        new Thread(new Runnable() {
//            @Override
//            public void run() {
//                Log.d(TAG, "run: " );
//                Message msg = Message.obtain();
//                msg.what = 1;
//                msg.obj = "AA";
//                mhandler.sendMessage(msg);
//
//
//            }
//        }).start();

        MyThread myThread = new MyThread();
        myThread.start();
       // mhandler.post(mRun);

    }

    public void getMessage(View view) {
        MyThread myThread = new MyThread();
        myThread.start();
    }

    class MyThread extends Thread{
        @RequiresApi(api = Build.VERSION_CODES.KITKAT)
        @Override
        public void run() {
            super.run();
            Log.d(TAG, "run 1: " );
            String stringFromNet = getStringFromNet();
            Message msg = Message.obtain();
            msg.what = 1;
            msg.obj = stringFromNet;//"AA";
            mhandler.sendMessage(msg);

        }
    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    private String getStringFromNet(){
        return NetUtil.getWeatherOfCity("济南");
    }
}