package com.example.mymenu;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

public class OptionMenuActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_option_menu);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("选项菜单");
        actionBar.setDisplayHomeAsUpEnabled(true);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.menu_options,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int itemId = item.getItemId();
        switch(itemId)
        {
            case R.id.menu_setting:
                Toast.makeText(this,"选中了设置",Toast.LENGTH_SHORT).show();
                break;
            case R.id.menu_about:
                Toast.makeText(this,"选中了关于",Toast.LENGTH_SHORT).show();
                break;
            case R.id.menu_help:
                Toast.makeText(this,"选中了帮助",Toast.LENGTH_SHORT).show();
                break;
            case R.id.menu_home:
                Toast.makeText(this,"选中了z主页",Toast.LENGTH_SHORT).show();
                break;
            case R.id.menu_exit:
                Toast.makeText(this,"选中了退出",Toast.LENGTH_SHORT).show();
                break;
            case android.R.id.home:
                Toast.makeText(this,"选中了fanhuijian",Toast.LENGTH_SHORT).show();
                this.finish();
                break;

            default:
                break;

        }

        return super.onOptionsItemSelected(item);
    }
}