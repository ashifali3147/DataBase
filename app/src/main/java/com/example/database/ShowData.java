package com.example.database;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.database.Cursor;
import android.os.Bundle;

public class ShowData extends AppCompatActivity {

    DBHandler dbHandler;
    RecyclerView rv;
    RCVAdapter rcvAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_data);

        dbHandler = new DBHandler(this);
        rv = (RecyclerView) findViewById(R.id.rv);
        rv.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        rcvAdapter = new RCVAdapter(this, dbHandler.readData());
        rv.setAdapter(rcvAdapter);



    }
}