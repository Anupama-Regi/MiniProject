package com.example.awmrapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import com.google.android.gms.location.LocationRequest;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationManager;
//import android.location.LocationRequest;
import android.os.Bundle;
import android.os.Looper;
import android.provider.Settings;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationCallback;
import com.google.android.gms.location.LocationResult;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuthException;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.security.acl.Owner;

public class MainActivity extends AppCompatActivity {
    //Initialize variable
    Button btLocation,btnnext;
    TextView tvLatitude,tvLongitude;
    FirebaseDatabase firebaseDatabase;
    DatabaseReference reference2;
    User user;
    ImageView img;


    FusedLocationProviderClient fusedLocationProviderClient;
    String lati;
    String lat,lon;
    float flat,flon;
    EditText cuId;
    //Owner owner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Assign Variable
        btLocation=findViewById(R.id.bt_location);
        tvLatitude=findViewById(R.id.tv_latitude);
        tvLongitude=findViewById(R.id.tv_longitude);
        btnnext=findViewById(R.id.BtnNext1);
        img=(ImageView)findViewById(R.id.idfirstLogo);
        cuId=(EditText) findViewById(R.id.cuid);

        user=new User();

        reference2=FirebaseDatabase.getInstance().getReference().child("User");
        //owner=new Owner();
        img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               // Intent i=new Intent(getApplicationContext(),user_register.class);
               // startActivity(i);
                Toast.makeText(getApplicationContext(), "App logo", Toast.LENGTH_SHORT).show();

            }
        });
        btnnext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String slat=tvLatitude.getText().toString();
                Double dlat=Double.parseDouble(slat);
                String slong=tvLongitude.getText().toString();
                Double dlong=Double.parseDouble(slong);
                String scuid=cuId.getText().toString();
                if (scuid.isEmpty()) {
                    Toast.makeText(getApplicationContext(), "enter customerId", Toast.LENGTH_SHORT).show();
                }
                else if (scuid.equals("admin123"))
                {
                    Toast.makeText(getApplicationContext(), "going to adminPage", Toast.LENGTH_SHORT).show();
                    Intent i=new Intent(getApplicationContext(),user_register.class);
                    startActivity(i);


                }
                else
                {
                    Query query = reference2.orderByChild("customer_id").equalTo(scuid);
                    query.addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                            if (dataSnapshot.exists()) {
                                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                                    user = snapshot.getValue(User.class);
                                    Double dUserLat=Double.parseDouble(user.getLat().toString());
                                    if (dlat<(dUserLat+10.0)&&dlat>(dUserLat-10.0)) {


                                        Toast.makeText(getApplicationContext(), "logged successfully", Toast.LENGTH_SHORT).show();
                                        Intent i=new Intent(MainActivity.this,MainActivity_ocr1.class);
                                        i.putExtra("id",user.getCustomer_id());
                                        i.putExtra("name",user.getName());
                                        i.putExtra("previous",user.getPrev_data());
                                        startActivity(i);


                                    } else {
                                        Toast.makeText(getApplicationContext(), "location mismatch", Toast.LENGTH_SHORT).show();
                                    }
                                }
                            } else {
                                Toast.makeText(getApplicationContext(), "no account in this Id", Toast.LENGTH_SHORT).show();
                            }
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError databaseError) {
                            Toast.makeText(getApplicationContext(), "error", Toast.LENGTH_SHORT).show();
                        }
                    });





                }



               // Query query=reference.orderByChild("lat").equalTo(lati);
               // query.addListenerForSingleValueEvent(new ValueEventListener() {
                    /*@Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        if (snapshot.exists())
                        {
                            for(DataSnapshot snap:snapshot.getChildren())
                            {
                                owner=snap.getValue(Owner.class);
                                if (longi.equalsIgnoreCase(owner.longit))
                                {
                                    Intent i=new Intent(MainActivity.this,MainActivity_ocr1.class);
                                    startActivity(i);
                                }
                            }
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });*/

               // Toast.makeText(getApplicationContext(),"latitude is"+flat, Toast.LENGTH_SHORT).show();






                /*


                String customer= "achu";
                Query query = reference.orderByChild("customer_id").equalTo(customer);
                query.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                        if (dataSnapshot.exists())
                        {
                            Toast.makeText(getApplicationContext(),"failed to register",Toast.LENGTH_SHORT).show();
                        }
                        else
                        {
                            user.setCustomer_id(customer);
                            user.setLat("138.12");
                            user.setLongit("140.12");
                            user.setName("amal");
                            user.setPrev_data("413");

                            reference.child(customer).setValue(user).addOnSuccessListener(new OnSuccessListener<Void>() {
                                @Override
                                public void onSuccess(Void aVoid) {

                                    Toast.makeText(getApplicationContext(),"succefully registered",Toast.LENGTH_SHORT).show();

                                }
                            });

                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {
                        Toast.makeText(getApplicationContext(),"failed to register",Toast.LENGTH_SHORT).show();


                    }
                });

                 */



              /*  query.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        if (dataSnapshot.exists()) {
                            for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                                user = snapshot.getValue(User.class);
                                if (lat.equals(user.lat)) {
                                    SharedPreferences.Editor editor=getSharedPreferences("userLogin",MODE_PRIVATE).edit();
                                    editor.putString("userId",suserName);
                                    editor.commit();
                                    Intent intent=new Intent(getApplicationContext(),MainActivity.class);
                                    startActivity(intent);

                                    userName.setText("");
                                    password.setText("");
                                    Toast.makeText(getApplicationContext(), "logged successfully", Toast.LENGTH_SHORT).show();


                                } else {
                                    Toast.makeText(getApplicationContext(), "test", Toast.LENGTH_SHORT).show();
                                }
                            }
                        } else {
                            Toast.makeText(getApplicationContext(), "no account ", Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {
                        Toast.makeText(getApplicationContext(), "error", Toast.LENGTH_SHORT).show();
                    }
                }); */

            }
        });

        fusedLocationProviderClient= LocationServices.getFusedLocationProviderClient(
                MainActivity.this
        );

        btLocation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Check condition
                if(ActivityCompat.checkSelfPermission(MainActivity.this
                        , Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED
                && ActivityCompat.checkSelfPermission(MainActivity.this
                        ,Manifest.permission.ACCESS_COARSE_LOCATION) == PackageManager.PERMISSION_GRANTED){
                    //When both permission are granted
                    //Call method
                    getCurrentLocation();
                }else {
                    //When permission is not granted
                    //Request permission
                    ActivityCompat.requestPermissions(MainActivity.this
                    ,new String[]{Manifest.permission.ACCESS_FINE_LOCATION
                    ,Manifest.permission.ACCESS_COARSE_LOCATION}
                    ,100);
                }
            }
        });

    }

    @SuppressLint("MissingSuperCall")
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        //Check condition
        if (requestCode == 100 && grantResults.length > 0 && (grantResults[0] + grantResults[1]
        == PackageManager.PERMISSION_GRANTED)) {
            //When permission granted
            //Call method
            getCurrentLocation();
        }else
        {
            //When permission are denied
            //Display toast
            Toast.makeText(getApplicationContext(), "Permission Denied !!!", Toast.LENGTH_SHORT).show();
        }
    }

    @SuppressLint("MissingPermission")
    private void getCurrentLocation()
    {
        //Initialize location manager
        LocationManager locationManager=(LocationManager) getSystemService(
                Context.LOCATION_SERVICE
        );
        //Check condition
        if(locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)
        || locationManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER)){
            //When location service is enabled
            //Get last location
            fusedLocationProviderClient.getLastLocation().addOnCompleteListener(new OnCompleteListener<Location>() {
                @Override
                public void onComplete(@NonNull Task<Location> task) {
                    //Initialize location
                    Location location = task.getResult();
                    //Check condition
                    if (location!= null){
                        //When location result is not null
                        //Set latitude
                        tvLatitude.setText(String.valueOf(location.getLatitude()));
                        lati=String.valueOf(location.getLatitude());
                        //Set Longitude
                        tvLongitude.setText(String.valueOf(location.getLongitude()));
                        lon=String.valueOf(location.getLongitude());
                    }else {
                        //When Location result is null
                        //Initialize Location request
                        LocationRequest locationRequest=new LocationRequest()
                                .setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY)
                                .setInterval(10000)
                                .setFastestInterval(1000)
                                .setNumUpdates(1);
                        //Initialize location call back
                        LocationCallback locationCallback=new LocationCallback() {
                            @Override
                            public void onLocationResult(LocationResult locationResult) {
                                //Initialize location
                                Location location1=locationResult.getLastLocation();
                                        //super.onLocationResult(locationResult);
                                //set latitude
                                tvLatitude.setText(String.valueOf(location1.getLatitude()));
                                //set longitude
                                tvLongitude.setText(String.valueOf(location1.getLongitude()));

                            }
                        };
                        // Request location updates
                        fusedLocationProviderClient.requestLocationUpdates(locationRequest
                        ,locationCallback, Looper.myLooper());
                        }
                    }

            });
        }else {
            //when location service is not enabled
            //Open location setting
            startActivity(new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS)
            .setFlags(Intent.FLAG_ACTIVITY_NEW_TASK));
        }
    }

}