package com.example.dbactivity.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import com.example.dbactivity.database.FeedReaderContract.FeedEntry;
public class NotesDao {
    DbHelper dbHelper;
    SQLiteDatabase database; //declaration
    public NotesDao(Context context) {
        dbHelper = new DbHelper(context);
    }

//    public void createRow(String title, String subtitle){}
    public void openDb(){
        database = dbHelper.getWritableDatabase();//instantiation
    }

    public String readRow(){
        Cursor cursor = database.query(FeedEntry.TABLE_NAME,null,null,null,null,null,null);
        cursor.moveToLast();
        int titleIndex = cursor.getColumnIndexOrThrow(FeedEntry.COLUMN_NAME_TITLE); //1
        int subTitleIndex = cursor.getColumnIndexOrThrow(FeedEntry.COLUMN_NAME_SUBTITLE);//2
        String title =  cursor.getString(titleIndex);
        String subtitle = cursor.getString(subTitleIndex);
        return title + "\n"+ subtitle;
    }
    public void updateRow(){}
    public void deleteRow(){}

    public void createRow(String title, String subtitle) {
        //insert title and subtitle in db
        ContentValues values = new ContentValues();
        values.put(FeedEntry.COLUMN_NAME_TITLE,title);
        values.put(FeedEntry.COLUMN_NAME_SUBTITLE,subtitle);
        database.insert(FeedEntry.TABLE_NAME,null,values);
    }
    public String[] getRow(int position){
        Cursor cursor = database.query(FeedEntry.TABLE_NAME,null,null,null,null,null,null);
        cursor.moveToPosition(position);

        int titleIndex = cursor.getColumnIndexOrThrow(FeedEntry.COLUMN_NAME_TITLE); //1
        int subTitleIndex = cursor.getColumnIndexOrThrow(FeedEntry.COLUMN_NAME_SUBTITLE);//2

        String[] result = new String[2];
        result[0] = cursor.getString(titleIndex);
        result[1] = cursor.getString(subTitleIndex);
        return  result;

    }

    public int getNoRows(){
        Cursor cursor = database.query(FeedEntry.TABLE_NAME,null,null,null,null,null,null);
        return cursor.getCount();
    }

}
