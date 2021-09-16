package com.example.myfragment;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioGroup;
import android.widget.Switch;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private RadioGroup  mRadioGroupType ;
    private Button mBtn_Create;

    public static final int DIALOG_TYPE_ALERT = 1;
    public static final int DIALOG_TYPE_DATE = 2;
    public static final int DIALOG_TYPE_TIME = 3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mRadioGroupType = findViewById(R.id.radio_dia_type);
        mBtn_Create = findViewById(R.id.btn_create);

        mBtn_Create.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int btn_num = 0;
                DialogFragment dialogFragment = null;
              switch (mRadioGroupType.getCheckedRadioButtonId())
              {
                  case R.id.btn_alert:
                      btn_num = 1;
                      dialogFragment = DialogFragment.getInstance(DIALOG_TYPE_ALERT);
                      break;
                  case R.id.btn_date:
                      btn_num = 2;
                      dialogFragment = DialogFragment.getInstance(DIALOG_TYPE_DATE);
                      break;
                  case R.id.btn_time:
                      btn_num = 3;
                      dialogFragment = DialogFragment.getInstance(DIALOG_TYPE_TIME);
                      break;

              }
              if(dialogFragment != null){
                  dialogFragment.show(getSupportFragmentManager(),"提示");
              }

                Toast.makeText(v.getContext(),"button" + Integer.toString(btn_num),Toast.LENGTH_SHORT).show();
            }
        });

        //  getFragmentManager().beginTransaction().add(new DialogFragment(),null).commit();

    }
}