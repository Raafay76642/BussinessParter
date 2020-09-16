package com.example.adminparking;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.SharedPreferences;
import android.os.Bundle;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.auth.FirebaseAuth;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.drawable.DrawableCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class Bookings extends AppCompatActivity {

    private RecyclerView recyclerView;
    private ProgressDialog mProgressBarsaving;
    private BookingAdopterClass PAdapter;
    private List<Model_Booking> pList;
    DatabaseReference mref;
    Query query;
    ArrayList<P_Model> dataModels;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bookings); mProgressBarsaving = new ProgressDialog(Bookings.this);
        recyclerView = findViewById(R.id.nb_recycler);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        pList = new ArrayList<>();
        PAdapter = new BookingAdopterClass(this, pList);
        recyclerView.setAdapter(PAdapter);
        //for parent activity in booking
        SharedPreferences.Editor editor= getSharedPreferences("parent_booking", MODE_PRIVATE).edit();
        editor.putString("parentbooking","parking");
        editor.apply();



        query= FirebaseDatabase.getInstance().getReference().child("NearBy").child("Parkings").orderByChild("p_space");
        query.addListenerForSingleValueEvent(valueEventListener);
        mProgressBarsaving.setMessage("Loading . . .!");
        mProgressBarsaving.show();
    }
    ValueEventListener valueEventListener = new ValueEventListener() {
        @Override
        public void onDataChange(DataSnapshot dataSnapshot) {
            pList.clear();
            if (dataSnapshot.exists()) {
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    Model_Booking p_model = snapshot.getValue(Model_Booking.class);
                    pList.add(p_model);
                }
                PAdapter.notifyDataSetChanged();
                mProgressBarsaving.cancel();
            }
        }

        @Override
        public void onCancelled(DatabaseError databaseError) {

        }

    };
}