package com.example.database;

import androidx.appcompat.app.AppCompatActivity;


import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    Button btn_add_data, btn_show_data;
    EditText edt_name, edt_number;
    DBHandler dbHandler;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        dbHandler = new DBHandler(this);
        edt_name = (EditText) findViewById(R.id.edt_name);
        edt_number = (EditText) findViewById(R.id.edt_number);
        btn_add_data = (Button) findViewById(R.id.btn_add_data);
        btn_show_data = (Button) findViewById(R.id.btn_show_data);



        btn_add_data.setOnClickListener(view -> {
            String name = String.valueOf(edt_name.getText());
            String number = String.valueOf(edt_number.getText());
            if(!number.isEmpty() && !name.isEmpty()){
                if (dbHandler.getReadableDatabase() == null){
                    Toast.makeText(this, "No DataBase exist", Toast.LENGTH_SHORT).show();
                }
                else{
                    dbHandler.addData(name, number);
                    Toast.makeText(this, "Data Added", Toast.LENGTH_SHORT).show();
                }

            }
            else {
                Toast.makeText(this, "Field must not empty!", Toast.LENGTH_SHORT).show();
            }

        });
        btn_show_data.setOnClickListener(view -> {
            Log.e("Data: " , "Model Data ");
//            Toast.makeText(this, "Name: " +dbHandler.readData().get(1), Toast.LENGTH_SHORT).show();
        });

    }
}