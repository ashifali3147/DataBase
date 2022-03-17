package com.example.database;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

public class DBHandler extends SQLiteOpenHelper {

    private static final String DB_NAME = "TEST_DB";
    private static final int DB_VERSION = 1;
    private static final String TABLE_NAME = "EMPLOYEE";
    private static final String EMP_ID = "id";
    private static final String EMP_NAME = "name";
    private static final String EMP_NUMBER = "number";


    public DBHandler(@Nullable Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String query = "CREATE TABLE " +TABLE_NAME + " ("
                + EMP_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + EMP_NAME + " TEXT, "
                + EMP_NUMBER + " NUMBER)";
        sqLiteDatabase.execSQL(query);
    }
   public void addData(String name, String number){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(EMP_NAME, name);
        values.put(EMP_NAME, number);

        db.insert(TABLE_NAME, null, values);
        db.close();
       Log.e("DataBase", "Created");
   }

   @SuppressLint("Range")
   public List<EMPModel> readData(){

        SQLiteDatabase sqLiteDatabase = this.getReadableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery("SELECT * FROM " +TABLE_NAME, null);

       ArrayList<EMPModel> empModelArrayList = new ArrayList<>();
       do{
//           String hh = cursor.getColumnName(2);
//           String gg = cursor.getColumnName(3);
//           empModelArrayList.add(new EMPModel("" +cursor.getString(cursor.getColumnIndex(EMP_NAME)), "" +cursor.getString(cursor.getColumnIndex(EMP_NUMBER))));
           Log.e("Cursor Data", "Name "+cursor.getColumnIndex(EMP_NAME));
       }while (cursor.moveToNext());

    cursor.close();
    return empModelArrayList;
   }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " +TABLE_NAME);
        onCreate(sqLiteDatabase);
        Log.e("DataBase", "Update");
    }
}
///data/data/com.example.database/databases/TEST_DB-shm