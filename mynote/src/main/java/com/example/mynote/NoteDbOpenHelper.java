package com.example.mynote;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.text.TextUtils;
import android.util.Log;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

public class NoteDbOpenHelper extends SQLiteOpenHelper {
    private static final String DB_NAME = "data.db";
    private static final String TABLE_NAME = "note";
    private static final String CREATE_TABLE_SQL = "create table "+ TABLE_NAME
            + "(id integer primary key autoincrement,title text,content text,create_time text)";
    private static final String TAG = "KIN";


    public NoteDbOpenHelper(@Nullable Context context) {
        super(context, DB_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE_SQL);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
    public long insertData(Note note)
    {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("title",note.getTitle().toString());
        values.put("content",note.getContent().toString());
        values.put("create_time",note.getCreatedTime().toString());

        return db.insert(TABLE_NAME,null,values);
    }

    public List<Note> queryAllFromDb(){
        SQLiteDatabase db = getWritableDatabase();
        List<Note> noteList = new ArrayList<>();

        Cursor cursor = db.query(TABLE_NAME,null,null,
           null,null,null,null);

        if(cursor != null){
            while (cursor.moveToNext()){
                String id = cursor.getString(cursor.getColumnIndex("id"));
                String title = cursor.getString(cursor.getColumnIndex("title"));
                String content = cursor.getString(cursor.getColumnIndex("content"));
                String create_time = cursor.getString(cursor.getColumnIndex("create_time"));

                Note note = new Note();
                note.setId(id);
                note.setTitle(title);
                note.setContent(content);
                note.setCreatedTime(create_time);
                noteList.add(note);
            }
            cursor.close();
        }



         return noteList;
    }

    public List<Note> queryFromDBByTitle(String title){
        if(TextUtils.isEmpty(title)){
            return queryAllFromDb();
        }

        SQLiteDatabase db = getWritableDatabase();
        List<Note> noteList = new ArrayList<>();

        Cursor cursor = db.query(TABLE_NAME,null,"title like ?",
                new String[]{"%"+title+"%"},null,null,null);

        if(cursor != null){
            while (cursor.moveToNext()){
                String id = cursor.getString(cursor.getColumnIndex("id"));
                String title2 = cursor.getString(cursor.getColumnIndex("title"));
                String content = cursor.getString(cursor.getColumnIndex("content"));
                String create_time = cursor.getString(cursor.getColumnIndex("create_time"));

                Note note = new Note();
                note.setId(id);
                note.setTitle(title2);
                note.setContent(content);
                note.setCreatedTime(create_time);
                noteList.add(note);
            }
            cursor.close();
        }

        return noteList;
    }

    public int updateData(Note note) {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("title",note.getTitle().toString());
        values.put("content",note.getContent().toString());
        values.put("create_time",note.getCreatedTime().toString());

        Log.e(TAG, "updateData: "+ note.getTitle() + "----" + note.getContent() +"----"+ note.getId());

        return db.update(TABLE_NAME,values,"id=?",new String[]{note.getId()});
    }

    public int deleteFromDBByID(String id) {
        SQLiteDatabase db = getWritableDatabase();
        return db.delete(TABLE_NAME,"id=?",new String[]{id});
    }




}
