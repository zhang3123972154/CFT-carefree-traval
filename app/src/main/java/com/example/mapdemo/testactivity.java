package com.example.mapdemo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import com.amap.api.maps.CameraUpdate;
import com.amap.api.maps.CameraUpdateFactory;
import com.amap.api.maps.model.CameraPosition;
import com.amap.api.maps.model.LatLng;
import com.amap.api.maps.model.Marker;
import com.amap.api.maps.model.MarkerOptions;

public class testactivity extends AppCompatActivity {

    private TextView textViewtest;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_testactivity);
        Intent intent = getIntent();
        Double Latitude=intent.getDoubleExtra("Latitude",0);
        Double Longitude=intent.getDoubleExtra("Longitude",0);
        textViewtest =findViewById(R.id.testtv);
        if(Latitude!=0){
        textViewtest.setText(Latitude.toString()+"\n"+Longitude.toString());}

    }
}