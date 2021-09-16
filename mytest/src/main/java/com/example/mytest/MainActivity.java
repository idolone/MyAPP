package com.example.mytest;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Typeface;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "KIN";
    private TextView tv1;
   private String[] contents ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

         tv1 = findViewById(R.id.tv_1);
         tv1.setTypeface(Typeface.createFromAsset(getAssets(),"calibri.ttf"));
         getResources().getString(R.string.app_name);
         contents =  getResources().getStringArray(R.array.myStringArr);
        for (int i = 0; i < contents.length ; i++) {
            Log.d(TAG, "Contents " +contents[i]);
        }
        for (String str:contents
             ) {
            Log.d(TAG, "Contents " +str);
        }

        int color = getResources().getColor(R.color.black);
    }
}