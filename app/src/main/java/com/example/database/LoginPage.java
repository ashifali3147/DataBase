package com.example.database;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.List;

public class LoginPage extends AppCompatActivity {

    DBHandler dbHandler;
    EditText login_number, login_password;
    Button btn_login_id, btn_login_password;
    List<EMPModel> empModelList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_page);

        login_number = (EditText) findViewById(R.id.login_number);
        login_password = (EditText) findViewById(R.id.login_password);
        btn_login_id = (Button) findViewById(R.id.btn_login_id);
        btn_login_password = (Button) findViewById(R.id.btn_login_password);

        dbHandler = new DBHandler(this);

        empModelList = dbHandler.readData();

        btn_login_id.setOnClickListener(view -> {
            String loginUsingNumber = String.valueOf(login_number.getText());
            for (int i=0; i< empModelList.size(); i++){
                Log.e("Login", "index --> " +i);
                Log.e("Login", "b-login_id --> " +loginUsingNumber);

                if((empModelList.get(i).number != null) && ((empModelList.get(i).number).equalsIgnoreCase(loginUsingNumber))){
                    Log.e("Login", "login_id --> " +loginUsingNumber);
//                    Log.e("Login", "login_check --> " +empModelList.get(i).number.contains(loginUsingNumber));
                    Toast.makeText(this, "Valid user", Toast.LENGTH_SHORT).show();
                    login_number.setVisibility(View.GONE);
                    login_password.setVisibility(View.VISIBLE);
                    Log.e("Login", "if-index --> " +i);
                    //---------------------------------------
                    btn_login_id.setVisibility(View.GONE);
                    btn_login_id.setClickable(false);
                    btn_login_password.setClickable(true);
                    btn_login_password.setVisibility(View.VISIBLE);
                    int indexValue = i;
                    btn_login_password.setOnClickListener(view1 -> {
                        Toast.makeText(this, "BBBB", Toast.LENGTH_SHORT).show();
                        if((empModelList.get(indexValue).password).equals(String.valueOf(login_password.getText()))){
                            Toast.makeText(this, "Login Successful", Toast.LENGTH_SHORT).show();
                        }
                        else{
                            Toast.makeText(this, "Invalid Credential", Toast.LENGTH_SHORT).show();
                        }
                    });


                }
                else {
                    Toast.makeText(this, "Not Valid", Toast.LENGTH_SHORT).show();
                    Log.e("Login", "else-db_check --> " +empModelList.get(i).number);
                }
            }
//            Toast.makeText(this, "Data "+empModelList.get(9).number, Toast.LENGTH_SHORT).show();
        });

    }
}