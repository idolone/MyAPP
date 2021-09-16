package com.example.mynote;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import com.example.mynote.utils.ToastUtil;

import java.text.SimpleDateFormat;
import java.util.Date;

public class EditActivity extends AppCompatActivity {

    private static final String TAG = "KIN";
    private  Note note;
    private EditText etTitle,etContent;
    private NoteDbOpenHelper noteDbOpenHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);

        etTitle = findViewById(R.id.et_title);
        etContent = findViewById(R.id.et_content);
        initEditData();
    }

    private void initEditData() {
        Intent intent = getIntent();
        note = (Note)intent.getSerializableExtra("note");
        Log.e(TAG, "initEditData: " + note.getId() );
        if(note != null){
            etTitle.setText(note.getTitle());
            etContent.setText(note.getContent());
        }
        noteDbOpenHelper = new NoteDbOpenHelper(this);
    }
    private String getCurTimeFormat(){
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("MM月dd HH:mm:ss");
        // YYYY年
        Date date = new Date();
        return simpleDateFormat.format(date);

    }

    public void save(View view) {


            String title = etTitle.getText().toString();
            String content = etContent.getText().toString();

            if(TextUtils.isEmpty(title)){
                ToastUtil.toastShort(this,"标题不能为空！");
                return;
            }
            note.setTitle(title);
            note.setContent(content);
            note.setCreatedTime(getCurTimeFormat());

            long row = noteDbOpenHelper.updateData(note);

            if(row != -1){
                ToastUtil.toastShort(this,"修改成功！");
                this.finish();
            }else{
                ToastUtil.toastShort(this,"修改失败！");
            }




    }
}