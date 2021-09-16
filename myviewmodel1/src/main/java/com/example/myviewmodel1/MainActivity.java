package com.example.myviewmodel1;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProviders;

public class MainActivity extends AppCompatActivity {
    private Button btn1,btn2;
    private TextView textView;

    private MyViewModel myViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        myViewModel = ViewModelProviders.of(this).get(MyViewModel.class);

        textView = findViewById(R.id.tx1);
        btn1 = findViewById(R.id.btn1);
        btn2 = findViewById(R.id.btn2);
        textView.setText(String.valueOf(myViewModel.number));
        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
             myViewModel.number++;
                textView.setText(String.valueOf(myViewModel.number));
            }
        });
        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myViewModel.number +=2;
                textView.setText(String.valueOf(myViewModel.number));
            }
        });
    }
}