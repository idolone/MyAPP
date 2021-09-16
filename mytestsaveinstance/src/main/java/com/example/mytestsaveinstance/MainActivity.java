package com.example.mytestsaveinstance;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private Button btn1;
    private TextView tx1;
    private final String SAVE_KEY1 = "SAVE_1";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
       if(savedInstanceState != null) {
           String str = savedInstanceState.getString(SAVE_KEY1);
           Log.d("TAG", "onCreate: get.."+str);
           tx1.setText(str);
       }

        tx1 = findViewById(R.id.title);
        btn1 = findViewById(R.id.button);
        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                   tx1.setText("welcome");
            }
        });
        
    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString(SAVE_KEY1,tx1.getText().toString());
        Log.d("TAG", "onSaveInstanceState: "+ tx1.getText().toString());
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d("TAG", "onDestroy: ....");
    }
}