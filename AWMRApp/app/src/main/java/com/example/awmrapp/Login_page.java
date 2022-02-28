package com.example.awmrapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class Login_page extends AppCompatActivity {

    EditText useridedit1,pinedit1;
    Button loginbtn1;
    TextView user_id1,pin1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_page);
        user_id1=findViewById(R.id.user_id);
        pin1=findViewById(R.id.pin);
        useridedit1=findViewById(R.id.useridedit);
        pinedit1=findViewById(R.id.pinedit);
        loginbtn1=findViewById(R.id.loginbtn);

    }
}