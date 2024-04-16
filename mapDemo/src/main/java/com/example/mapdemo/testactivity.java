package com.example.mapdemo;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.amap.api.maps.model.Poi;
import com.amap.api.navi.AmapNaviPage;
import com.amap.api.navi.AmapNaviParams;
import com.amap.api.navi.AmapNaviType;
import com.amap.api.navi.AmapPageType;
import com.amap.api.navi.INaviInfoCallback;
import com.amap.api.navi.model.AMapNaviLocation;

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
        AmapNaviPage.getInstance().showRouteActivity(getApplicationContext(), params, new INaviInfoCallback() {
            @Override
            public void onInitNaviFailure() {

            }

            @Override
            public void onGetNavigationText(String s) {

            }

            @Override
            public void onLocationChange(AMapNaviLocation aMapNaviLocation) {

            }

            @Override
            public void onArriveDestination(boolean b) {

            }

            @Override
            public void onStartNavi(int i) {

            }

            @Override
            public void onCalculateRouteSuccess(int[] ints) {

            }

            @Override
            public void onCalculateRouteFailure(int i) {

            }

            @Override
            public void onStopSpeaking() {

            }

            @Override
            public void onReCalculateRoute(int i) {

            }

            @Override
            public void onExitPage(int i) {

            }

            @Override
            public void onStrategyChanged(int i) {

            }

            @Override
            public void onArrivedWayPoint(int i) {

            }

            @Override
            public void onMapTypeChanged(int i) {

            }

            @Override
            public void onNaviDirectionChanged(int i) {

            }

            @Override
            public void onDayAndNightModeChanged(int i) {

            }

            @Override
            public void onBroadcastModeChanged(int i) {

            }

            @Override
            public void onScaleAutoChanged(boolean b) {

            }

            @Override
            public View getCustomMiddleView() {
                return null;
            }

            @Override
            public View getCustomNaviView() {
                return null;
            }

            @Override
            public View getCustomNaviBottomView() {
                return null;
            }
        });
       //AMapNavi





    }
}