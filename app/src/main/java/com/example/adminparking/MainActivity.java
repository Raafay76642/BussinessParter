package com.example.adminparking;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class MainActivity extends AppCompatActivity {

    EditText name,city,slot,addres;
    String sname,scity,sslot,saddress,Uid;
    DatabaseReference databaseProfileRef;
    FirebaseDatabase database;
    FirebaseAuth mauth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);
        database =FirebaseDatabase.getInstance();
        databaseProfileRef = database.getReference();
        name=findViewById(R.id.Uname);
        city=findViewById(R.id.city);
        slot=findViewById(R.id.USlots);
        addres=findViewById(R.id.Uaddres);
    }
    public void savedate(View view){
        SharedPreferences prfs = getSharedPreferences("Parkings", Context.MODE_PRIVATE);
        String parkinglatitude = prfs.getString("Parkinglatitude", "");
        String parkinglongitude = prfs.getString("Parkinglongitude", "");

        sname =name.getText().toString();
        scity = city.getText().toString();
        sslot = slot.getText().toString();
        saddress = addres.getText().toString();
        Uid = mauth.getInstance().getUid();
        P_Model p_model = new P_Model(scity,sname,parkinglongitude,parkinglatitude,saddress,sslot,Uid);
        databaseProfileRef.child("NearBy").child("Parkings").child(Uid).setValue(p_model).addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void aVoid) {

                final Toast toast = Toast.makeText(MainActivity.this, "Data is Saved", Toast.LENGTH_LONG);
                toast.show();

            }


        });

    }
    public void   openlocation(View view)
    {
        Intent intent=new Intent(this,dropLocation.class);
        intent.putExtra("id","Parkings");
        startActivity(intent);
    }
}