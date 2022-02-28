package com.example.awmrapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

public class user_register extends AppCompatActivity {
    EditText customerId,lat,longitude,name,previousData;
    Button register;
    User user2;
    String scustomer="0",slat="0",slongitude="0",sname="0",sprevious="0";
    DatabaseReference reference;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_register);
        customerId=(EditText) findViewById(R.id.customer);
        lat=(EditText) findViewById(R.id.lat);
        longitude=(EditText) findViewById(R.id.longitude);
        name=(EditText) findViewById(R.id.name);
        previousData=(EditText) findViewById(R.id.prev);
        register=(Button)findViewById(R.id.reg);
        user2=new User();
        reference= FirebaseDatabase.getInstance().getReference().child("User");
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (customerId.getText().toString().isEmpty() || lat.getText().toString().isEmpty() || longitude.getText().toString().isEmpty() || name.getText().toString().isEmpty() || previousData.getText().toString().isEmpty())
                {
                    Toast.makeText(getApplicationContext(), "enter every row", Toast.LENGTH_SHORT).show();

                } else {


                   /*
                    user.setCustomer_id(customerId.getText().toString());
                    user.setLat(lat.getText().toString());
                    user.setLongit(longitude.getText().toString());
                    user.setName(name.getText().toString());
                    user.setPrev_data(previousData.getText().toString());  */
                    scustomer=customerId.getText().toString();
                    slat=lat.getText().toString();
                    slongitude=longitude.getText().toString();
                    sname=name.getText().toString();
                    sprevious=previousData.getText().toString();
                    user2.setCustomer_id(scustomer);
                    user2.setLat(slat);
                    user2.setLongit(slongitude);
                    user2.setName(sname);
                    user2.setPrev_data(sprevious);
                   // Toast.makeText(getApplicationContext(),user2.getLat(), Toast.LENGTH_SHORT).show();

                    Query query = reference.orderByChild("customer_id").equalTo(scustomer);
                    query.addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                            if (dataSnapshot.exists()) {
                                Toast.makeText(getApplicationContext(), "failed to register", Toast.LENGTH_SHORT).show();
                            } else {


                                reference.child(scustomer).setValue(user2).addOnSuccessListener(new OnSuccessListener<Void>() {
                                    @Override
                                    public void onSuccess(Void aVoid) {

                                        Toast.makeText(getApplicationContext(), "succefully registered", Toast.LENGTH_SHORT).show();

                                    }
                                });

                            }
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError databaseError) {
                            Toast.makeText(getApplicationContext(), "failed to register", Toast.LENGTH_SHORT).show();


                        }
                    });

                }
            }
        });




    }
}