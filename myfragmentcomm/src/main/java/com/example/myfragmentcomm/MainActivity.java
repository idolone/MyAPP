package com.example.myfragmentcomm;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.FrameLayout;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private CheckBox  checkBox;
    private Button    mButton;
    private FrameLayout fmLayout;
    private BlankFragment fragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        checkBox = findViewById(R.id.ck_box);

        fmLayout = findViewById(R.id.frm_1);

        fragment = new BlankFragment();
        getSupportFragmentManager()
                .beginTransaction()
                .add(R.id.frm_1,fragment)
                .commit();

        checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {

//            BlankFragment fragment = (BlankFragment) getSupportFragmentManager().findFragmentById(R.id.fragment_1);

            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (checkBox.isChecked()) {

                    if(fragment != null){
                      TextView txtResult =  fragment.getView().findViewById(R.id.check_result);
                      txtResult.setText("married");
                    }

                } else {

                    if(fragment != null){
                        TextView txtResult =  fragment.getView().findViewById(R.id.check_result);
                        txtResult.setText(" not married");
                    }

                }
            }
        });
    }
}