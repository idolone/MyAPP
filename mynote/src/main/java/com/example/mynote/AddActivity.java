package com.example.mynote;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.mynote.utils.ToastUtil;

import java.text.SimpleDateFormat;
import java.util.Date;

public class AddActivity extends AppCompatActivity {

    private EditText etTitle;
    private EditText etContent;
    private NoteDbOpenHelper noteDbOpenHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);
        etTitle = findViewById(R.id.et_title);
        etContent = findViewById(R.id.et_content);
        noteDbOpenHelper = new NoteDbOpenHelper(this);
    }
    private String getCurTimeFormat(){
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("MM月dd HH:mm:ss");
        // YYYY年
        Date date = new Date();
        return simpleDateFormat.format(date);

    }
    public void addOnclick(View view) {
        String title = etTitle.getText().toString();
        String content = etContent.getText().toString();

        if(TextUtils.isEmpty(title)){
            ToastUtil.toastShort(this,"标题不能为空！");
            return;
        }

        Note note = new Note();
        note.setTitle(title);
        note.setContent(content);
        note.setCreatedTime(getCurTimeFormat());

       long row = noteDbOpenHelper.insertData(note);

       if(row != -1){
           ToastUtil.toastShort(this,"添加成功！");
           this.finish();
       }else{
           ToastUtil.toastShort(this,"添加失败！");
       }

    }
}