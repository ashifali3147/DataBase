package com.example.database;

import androidx.appcompat.app.AppCompatActivity;


import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MainActivity extends AppCompatActivity {

    Button btn_add_data, btn_show_data, btn_login_page;
    EditText edt_name, edt_number, edt_password;
    DBHandler dbHandler;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        dbHandler = new DBHandler(this);

        edt_name = (EditText) findViewById(R.id.edt_name);
        edt_number = (EditText) findViewById(R.id.edt_number);
        edt_password = (EditText) findViewById(R.id.edt_password);
        btn_add_data = (Button) findViewById(R.id.btn_add_data);
        btn_show_data = (Button) findViewById(R.id.btn_show_data);
        btn_login_page = (Button) findViewById(R.id.btn_login_page);


        insertDummyData();

        btn_add_data.setOnClickListener(view -> {
            String name = String.valueOf(edt_name.getText());
            String number = String.valueOf(edt_number.getText());
            String password = String.valueOf(edt_password.getText());
            if(!number.isEmpty() && !name.isEmpty() && !password.isEmpty()){
                if (dbHandler.getReadableDatabase() == null){
                    Toast.makeText(this, "No DataBase exist", Toast.LENGTH_SHORT).show();
                }
                else{
                    if (isValidPassword(password)){
                        dbHandler.addData(name, number, password);
                        edt_name.setText("");
                        edt_number.setText("");
                        edt_password.setText("");
                        Toast.makeText(this, "Data Added", Toast.LENGTH_SHORT).show();
                    }
                    else{
                        edt_password.setError("Use Uppercase & Special Character");
                    }

                }

            }
            else {
                Toast.makeText(this, "Field must not empty!", Toast.LENGTH_SHORT).show();
            }

        });
        btn_show_data.setOnClickListener(view -> {
            Log.e("Data: " , "Model Data ");
//            Toast.makeText(this, "Name: " +dbHandler.readData().get(0), Toast.LENGTH_SHORT).show();
            startActivity(new Intent(this, ShowData.class));


        });

        btn_login_page.setOnClickListener(view -> {
            startActivity(new Intent(this, LoginPage.class));
        });

    }

    public void insertDummyData(){
        if(dbHandler.getReadableDatabase() != null){
            if (dbHandler.readData().isEmpty()){
                dbHandler.insertDummyData();
                dbHandler.close();
            }
        }
    }

    public static boolean isValidPassword(String password) {
        Matcher matcher = Pattern.compile("((?=.*[a-z])(?=.*\\d)(?=.*[A-Z])(?=.*[@#$%!]).{4,20})").matcher(password);
        return matcher.matches();
    }

}