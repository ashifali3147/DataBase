package com.example.database;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
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
    private static final String EMP_PASSWORD = "password";

    public List<EMPModel> empModelArrayList;


    public DBHandler(@Nullable Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String query = "CREATE TABLE " +TABLE_NAME + " ("
                + EMP_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + EMP_NAME + " TEXT, "
                + EMP_NUMBER + " NUMBER, "
                + EMP_PASSWORD + " TEXT)";
        sqLiteDatabase.execSQL(query);
    }
   public void addData(String name, String number, String password){

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(EMP_NAME, name);
        values.put(EMP_NUMBER, number);
        values.put(EMP_PASSWORD, password);

        db.insert(TABLE_NAME, null, values);
        db.close();
       Log.e("DataBase", "Created");
   }

   @SuppressLint("Range")
   public List<EMPModel> readData(){

        SQLiteDatabase sqLiteDatabase = this.getReadableDatabase();

//        Cursor cursor = sqLiteDatabase.rawQuery("SELECT * FROM " +TABLE_NAME, null);
//       SQLiteDatabase db = this.getWritableDatabase();
       Cursor cursor = sqLiteDatabase.query(TABLE_NAME,
               new String[]{EMP_ID, EMP_NAME, EMP_NUMBER, EMP_PASSWORD},
               null, null, null,
               null, null);

       empModelArrayList = new ArrayList<>();
//       do{
////           String hh = cursor.getColumnName(2);
////           String gg = cursor.getColumnName(3);
//           empModelArrayList.add(new EMPModel("" +cursor.getInt(cursor.getColumnIndex("id")),"" +cursor.getString(cursor.getColumnIndex("name")), "" +cursor.getString(cursor.getColumnIndex("number"))));
//           cursor.moveToNext();
//           Log.e("Cursor Data", "Name "+cursor.getColumnIndex(EMP_NAME));
//       }while (cursor.moveToNext());
        cursor.moveToFirst();
       while(!cursor.isAfterLast()){
//           EMPModel empModel = new EMPModel("" +cursor.getInt(cursor.getColumnIndex("id")),"" +cursor.getString(cursor.getColumnIndex("name")), "" +cursor.getString(cursor.getColumnIndex("number")));
//
           EMPModel empModel = new EMPModel(""+cursor.getInt(cursor.getColumnIndex(EMP_ID)),cursor.getString(cursor.getColumnIndex(EMP_NAME)), cursor.getString(cursor.getColumnIndex(EMP_NUMBER)), cursor.getString(cursor.getColumnIndex(EMP_PASSWORD)));
//           EMPModel empModel = new EMPModel(cursor.getString(0), cursor.getString(1), cursor.getString(2));
           empModelArrayList.add(empModel);
           cursor.moveToNext();
       }

       cursor.close();
       sqLiteDatabase.close();
    return empModelArrayList;
   }

    public void insertDummyData() {
        SQLiteDatabase db = this.getWritableDatabase();
        final String INSERT_QUERY = "INSERT INTO " +TABLE_NAME +" (id, name, number, password) " +
                "SELECT null, 'Rajesh', '6545454654', 'nopass@1234' " +
                "UNION SELECT null, 'Tapas', '2587884554', 'nopass@1234' " +
                "UNION SELECT null, 'Shyamal', '2587884554', 'nopass@1234' " +
                "UNION SELECT null, 'Sudipta', '2587884554', 'nopass@1234' " +
                "UNION SELECT null, 'Manas', '2587884554', 'nopass@1234' "+
                "UNION SELECT null, 'Niranjan', '2587884554', 'nopass@1234' " +
                "UNION SELECT null, 'Kaushik', '2587884554', 'nopass@1234' " +
                "UNION SELECT null, 'Dibyendu', '2587884554', 'nopass@1234' " +
                "UNION SELECT null, 'Tanmoy', '2587884554', 'nopass@1234' " +
                "UNION SELECT null, 'Amit', '2587884554', 'nopass@1234';";
        db.execSQL(INSERT_QUERY);
    }

    public DBHandler open() throws SQLException {
        SQLiteDatabase db = this.getWritableDatabase();
//        db = DBHandler.getWritableDatabase();
        return this;
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " +TABLE_NAME);
        onCreate(sqLiteDatabase);
        Log.e("DataBase", "Update");
    }
}
///data/data/com.example.database/databases/TEST_DB-shm

