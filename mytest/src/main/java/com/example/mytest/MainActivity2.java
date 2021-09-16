package com.example.mytest;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MainActivity2 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
    }

    public void JumpTo3(View view) {

        Intent intent = new Intent(MainActivity2.this,MainActivity3.class);

//        intent.putExtra("name","kin");
//        intent.putExtra("age",29);
//        intent.putExtra("sex",'M');

//        Bundle bundle = new Bundle();
//        bundle.putString("name","Mary");
//        bundle.putChar("sex",'F');
//        bundle.putInt("age",18);
//
//        intent.putExtras(bundle);

//        Student student = new Student("Jeson",22,'M');
//
//         intent.putExtra("MSG",student);


          StudentP studentP = new StudentP("JJK",41,'M');
          intent.putExtra("PAR",studentP);

         startActivity(intent);
    }
}