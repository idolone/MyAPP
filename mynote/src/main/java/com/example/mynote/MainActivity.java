package com.example.mynote;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.example.mynote.utils.LogUtil;
import com.example.mynote.utils.SPUtil;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private RecyclerView mRecyclerView;
    private FloatingActionButton mBtnAdd;
    private List<Note> mNotes;
    private MyAdapter mMyAdapter;

    private NoteDbOpenHelper mNoteDBOpenHelper;

    private int currentListLayoutMode;
    private final String KEY_LAYOUT_MODE = "layout_mode";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();
        initData();
        initEvent();
    }

    @Override
    protected void onResume() {
        super.onResume();
        refreshDataFromDB();
        setListLayout(true);
    }

    private void setListLayout(boolean readfromDB)
    {
        if(readfromDB)
        currentListLayoutMode = SPUtil.getIntWithDefault(this,KEY_LAYOUT_MODE,MyAdapter.TYPE_LINEAR_LAYOUT);
        if(currentListLayoutMode == MyAdapter.TYPE_LINEAR_LAYOUT)
        {
            LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
            mRecyclerView.setLayoutManager(linearLayoutManager);
            mMyAdapter.setViewType(MyAdapter.TYPE_LINEAR_LAYOUT);

        }else {
            GridLayoutManager gridLayoutManager = new GridLayoutManager(this,2);
            mRecyclerView.setLayoutManager(gridLayoutManager);
            mMyAdapter.setViewType(MyAdapter.TYPE_GRID_LAYOUT);
        }
        mMyAdapter.notifyDataSetChanged();

    }

    private void refreshDataFromDB() {
        mNotes = getDataFromDb();
        mMyAdapter.refreshData(mNotes);
    }

    private void initEvent() {
        mMyAdapter = new MyAdapter(this,mNotes);
        mRecyclerView.setAdapter(mMyAdapter);
        setListLayout(true);

//        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
//
//        mRecyclerView.setLayoutManager(linearLayoutManager);
//        mMyAdapter.setViewType(MyAdapter.TYPE_LINEAR_LAYOUT);

    }

    private void initData() {
        mNotes = new ArrayList<>();
//        for (int i = 0; i < 30; i++) {
//            Note note = new Note();
//            note.setTitle("这是标题"+i);
//            note.setContent("这是内容"+i);
//            note.setCreatedTime(getCurTimeFormat());
//            mNotes.add(note);
//        }
        mNoteDBOpenHelper = new NoteDbOpenHelper(this);
       //  mNotes = getDataFromDb();

    }

    private List<Note> getDataFromDb() {
        return mNoteDBOpenHelper.queryAllFromDb();
    }

    private String getCurTimeFormat(){
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("MM月dd HH:mm:ss");
       // YYYY年
        Date date = new Date();
        return simpleDateFormat.format(date);

    }

    private void initView() {
        mRecyclerView = findViewById(R.id.rlv);
    }

    public void fabOnClick(View view) {
        Intent intent = new Intent(this,AddActivity.class);
        startActivity(intent);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main,menu);

        SearchView searchView = (SearchView) menu.findItem(R.id.menu_search).getActionView();
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
//               List<Note> notes = mNoteDBOpenHelper.queryFromDBByTitle(newText);
//               mMyAdapter.refreshData(notes);
                mNotes = mNoteDBOpenHelper.queryFromDBByTitle(newText);
                mMyAdapter.refreshData(mNotes);

                return false;
            }
        });
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        item.setChecked(true);
        switch(item.getItemId()){
            case R.id.menu_Linear:
//                LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
//                mRecyclerView.setLayoutManager(linearLayoutManager);
//                mMyAdapter.setViewType(MyAdapter.TYPE_LINEAR_LAYOUT);

                currentListLayoutMode = MyAdapter.TYPE_LINEAR_LAYOUT;
                setListLayout(false);
                SPUtil.saveInt(this,KEY_LAYOUT_MODE,MyAdapter.TYPE_LINEAR_LAYOUT);
                return true;

            case R.id.menu_Grid:
//                GridLayoutManager gridLayoutManager = new GridLayoutManager(this,2);
//
//                mRecyclerView.setLayoutManager(gridLayoutManager);
//                mMyAdapter.setViewType(MyAdapter.TYPE_GRID_LAYOUT);

                currentListLayoutMode = MyAdapter.TYPE_GRID_LAYOUT;
                setListLayout(false);
                SPUtil.saveInt(this,KEY_LAYOUT_MODE,MyAdapter.TYPE_GRID_LAYOUT);
                LogUtil.debug("here...");
                return true;

            default:
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        MenuItem item;
        if(currentListLayoutMode == MyAdapter.TYPE_LINEAR_LAYOUT){
            item = menu.findItem(R.id.menu_Linear);
            item.setChecked(true);

        }else{
            item = menu.findItem(R.id.menu_Grid);
            item.setChecked(true);

        }
        return super.onPrepareOptionsMenu(menu);
    }
}