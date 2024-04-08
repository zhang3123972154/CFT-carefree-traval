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
import com.amap.api.maps.model.Poi;
import com.amap.api.navi.AmapNaviPage;
import com.amap.api.navi.AmapNaviParams;
import com.amap.api.navi.AmapNaviType;
import com.amap.api.navi.AmapPageType;

import java.util.ArrayList;
import java.util.List;

public class testactivity extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_testactivity);
        Intent intent=getIntent();
        //起点
        //Poi start = new Poi("北京首都机场", new LatLng(40.080525,116.603039), "B000A28DAE");
        //途经点
        //List<Poi> poiList = new ArrayList();
        //poiList.add(new Poi("故宫", new LatLng(39.918058,116.397026), "B000A8UIN8"));
        //终点
        Poi end = intent.getParcelableExtra("Poi");
// 组件参数配置
        AmapNaviParams params = new AmapNaviParams(null,null, end, AmapNaviType.DRIVER, AmapPageType.ROUTE);
        //启动导航组件
        AmapNaviPage.getInstance().showRouteActivity(getApplicationContext(), params, null);

    }
}