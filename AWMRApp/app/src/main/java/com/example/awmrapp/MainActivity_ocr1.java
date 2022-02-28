package com.example.awmrapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity_ocr1 extends AppCompatActivity {

    private Button captureBtn;
    TextView tid,tname,tprevious;
    String sprevious;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_ocr1);
        captureBtn=findViewById(R.id.idBtnCapture);
        tid=(TextView)findViewById(R.id.idcustomer);
        tname=(TextView)findViewById(R.id.idname);
        tprevious=(TextView)findViewById(R.id.idprevious) ;
        Intent iin= getIntent();
        Bundle b = iin.getExtras();

        if(b!=null)
        {
            String sid =(String) b.get("id");
            String sname =(String) b.get("name");
            sprevious =(String) b.get("previous");
            tid.setText("Customer Id : "+sid);
            tname.setText("Name : "+sname);
            tprevious.setText("Previous Data : "+sprevious);
        }
        captureBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(MainActivity_ocr1.this,ScannerActivity.class);
                i.putExtra("previous",sprevious);
                startActivity(i);

            }
        });
    }
}

