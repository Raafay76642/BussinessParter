package com.example.adminparking;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.google.firebase.auth.FirebaseAuth;

public class Home extends AppCompatActivity {
    private FirebaseAuth mAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        //for checking either the user allready logged in or not
        mAuth = FirebaseAuth.getInstance();
       if( mAuth.getInstance().getCurrentUser()==null){
           Intent intent = new Intent(Home.this, Signup.class);
            startActivity(intent);
        }
    }

    public void   openParking(View view)
    {
        Intent intent=new Intent(this,MainActivity.class);
        startActivity(intent);
    }


    public void   openServiceStations(View view)
    {
        Intent intent=new Intent(this,Service_Stations.class);
        startActivity(intent);
    }
    public void   openWorkshop(View view)
    {
        Intent intent=new Intent(this,Workshop.class);
        startActivity(intent);
    }
    public void   openbooking(View view)
    {
        Intent intent=new Intent(this,Bookings.class);
        startActivity(intent);
    }
    public void logout(View View)
    {

    }
}