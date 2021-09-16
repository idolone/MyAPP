package com.example.mysharedpreference;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {
    private SharedPreferences sp;
    private EditText et;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        et = findViewById(R.id.et_data);

        sp = getSharedPreferences("sp_data",Context.MODE_PRIVATE);

    }

    public void saveData(View view) {
        SharedPreferences.Editor edit = sp.edit();
        edit.putString("et_data",et.getText().toString());
        edit.apply();
        //edit.commit();
        et.setText("");
    }

    public void getData(View view) {
        String str = sp.getString("et_data","");
        et.setText(str);
        et.setSelection(str.length());
    }
}