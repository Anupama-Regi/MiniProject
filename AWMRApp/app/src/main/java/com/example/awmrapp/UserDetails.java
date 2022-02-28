package com.example.awmrapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class UserDetails extends AppCompatActivity {
    private EditText presreading1,prevreading1;
    private TextView totalvalue1;
    private Button btnnext3;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_details);
        totalvalue1=findViewById(R.id.totalvalue);
        presreading1=findViewById(R.id.presreading);
        prevreading1=findViewById(R.id.prevreading);
        btnnext3=findViewById(R.id.BtnNext3);
        Intent iin= getIntent();
        Bundle b = iin.getExtras();

        if(b!=null)
        {
            String sprevious =(String) b.get("previous");
            prevreading1.setText(sprevious);



        }
        btnnext3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                double prev=Double.parseDouble(prevreading1.getText().toString());
                double pres=Double.parseDouble(presreading1.getText().toString());
                double c=pres-prev;
                double t;
                if(c<=5000)
                    t=21;
                else if(c<=10000)
                    t=21+((c-5000)/1000)*4.2;
                else if(c<=15000)
                    t=42+((c-5000)/1000)*5.25;
                else if(c<=20000)
                    t=(c/1000)*6.3;
                else if(c<=25000)
                    t=(c/1000)*7.35;
                else if(c<=30000)
                    t=(c/1000)*9.45;
                else if(c<=40000)
                    t=(c/1000)*12.6;
                else if(c<=50000)
                    t=(c/1000)*14.7;
                else
                    t=735+((c-50000)/1000)*42;
                totalvalue1.setText(String.valueOf(t));
            }
        });
    }
}