package com.example.mylistfragment;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import com.example.mylistfragment.Util.LogUtil;
import com.example.mylistfragment.placeholder.PlaceholderContent;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements Fragment1.OnNewItemAdd {
   private RecyclerView mRecyclerView;
   private List<PlaceholderContent.PlaceholderItem> mlist;
   private MyItemRecyclerViewAdapter myAdapter;

   private ArrayAdapter<String> simpleAdapter;
   private List<String>  datas;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//        mRecyclerView = findViewById(R.id.List);
//        mlist = new ArrayList<>();
//        myAdapter = new MyItemRecyclerViewAdapter(mlist);

        datas = new ArrayList<>();
        simpleAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1,datas);
        Fragment2 fragment = (Fragment2) getSupportFragmentManager().findFragmentById(R.id.fragment_2);

        fragment.setListAdapter(simpleAdapter);

    }

    @Override
    public void newItemAdded(String content) {
        Toast.makeText(this,content,Toast.LENGTH_SHORT).show();
        LogUtil.debug("Activity content---"+content);
//        int index;
//        index = mlist.size();
//
//        PlaceholderContent.PlaceholderItem item = new PlaceholderContent.PlaceholderItem(Integer.toString(index+1),content,null);
//        mlist.add(item);
        datas.add(content);
        simpleAdapter.notifyDataSetChanged();


    }
}