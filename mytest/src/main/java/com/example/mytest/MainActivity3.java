package com.example.mytest;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

public class MainActivity3 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);

       // testforNormalorBundle();

       // testforSerializable();

        testforParcelable();

    }

    private void testforNormalorBundle() {
        Intent intent = getIntent();

        String name = intent.getStringExtra("name");
        int age = intent.getIntExtra("age",18);
        char sex = intent.getCharExtra("sex",'M');

        Log.e("TAG", "name: "+name+" age:"+age+
                " sex:"+sex );
    }


    public void testforSerializable() {
        Intent intent = getIntent();
        Student student = (Student) intent.getSerializableExtra("MSG");

        Log.e("TAG", "testforSerializable: " + student.toString());
    }

    public void testforParcelable() {
        Intent intent = getIntent();
        StudentP student =  intent.getParcelableExtra("PAR");

        Log.e("TAG", "testforSerializable: " + student.toString());
    }

    public void JumpTo2(View view) {
        finish();
    }
}