package com.example.mapdemo;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.location.Location;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.amap.api.location.AMapLocation;
import com.amap.api.location.AMapLocationClient;
import com.amap.api.location.AMapLocationClientOption;
import com.amap.api.location.AMapLocationListener;
import com.amap.api.maps.AMap;
import com.amap.api.maps.AMapOptions;
import com.amap.api.maps.CameraUpdate;
import com.amap.api.maps.CameraUpdateFactory;
import com.amap.api.maps.LocationSource;
import com.amap.api.maps.MapView;
import com.amap.api.maps.MapsInitializer;
import com.amap.api.maps.model.CameraPosition;
import com.amap.api.maps.model.LatLng;
import com.amap.api.maps.model.Marker;
import com.amap.api.maps.model.MarkerOptions;
import com.amap.api.maps.model.MyLocationStyle;
import com.amap.api.maps.model.Poi;
import com.amap.api.navi.AmapNaviPage;
import com.amap.api.navi.AmapNaviParams;
import com.amap.api.navi.AmapNaviType;
import com.amap.api.navi.AmapPageType;

public class MainActivity extends AppCompatActivity {

    MapView mMapView = null;
    private Button searchButton;
    private Button locateButton;
    private Button naviButton;
    private boolean followMove=true;
    AMap aMap;
    public AMapLocationListener mLocationListener;

    private Marker previousMarker;
    private Poi previousPoi;

    private ActivityResultLauncher<Intent> register;
    private double userlatitude;
    private  double userlongtitude;
    public static final int INITIAL_ZOOM_SIZE=20;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        MapsInitializer.updatePrivacyShow(this,true,true);
        MapsInitializer.updatePrivacyAgree(this,true);
        //获取地图控件引用
        mMapView = (MapView) findViewById(R.id.map);
        //在activity执行onCreate时执行mMapView.onCreate(savedInstanceState)，创建地图
        mMapView.onCreate(savedInstanceState);
        if (aMap == null) {
            aMap = mMapView.getMap();
        }
////老方法 精度也不行？
//        // 初始化定位
//        AMapLocationClient locationClient = null;
//        AMapLocationClientOption mLocationOption = new AMapLocationClientOption();
//        TextView textViewtest =findViewById(R.id.testtv2);
//        mLocationListener = new AMapLocationListener(){
//            public void onLocationChanged(AMapLocation amapLocation) {
//                if (amapLocation != null) {
//                    if (amapLocation.getErrorCode() == 0) {
////可在其中解析amapLocation获取相应内容。
//                        userlatitude=amapLocation.getLatitude();//获取纬度
//                        userlongtitude=amapLocation.getLongitude();//获取经度
//                       Double la=userlatitude;
//                       Double lo=userlongtitude;
//                       String las=la.toString();
//                       String los=lo.toString();
//                      textViewtest.setText(las+","+los);
//                        final Marker marker = aMap.addMarker(new MarkerOptions().position(new LatLng(la,lo)).title("新地点").snippet("DefaultMarker"));
//                        aMap.moveCamera(CameraUpdateFactory.newLatLng(new LatLng(la,lo)));
//                    }
//                }
//            }
//        };
//        try {
//            locationClient = new AMapLocationClient(getApplicationContext());
//        } catch (Exception e) {
//            throw new RuntimeException(e);
//        }
//        locationClient.setLocationListener(mLocationListener);
//        locationClient.setLocationOption(mLocationOption);
//        mLocationOption.setLocationMode(AMapLocationClientOption.AMapLocationMode.Hight_Accuracy);
////启动定位
//        locationClient.startLocation();
//
//        //声明AMapLocationClientOption对象
//        mLocationOption = null;
////初始化AMapLocationClientOption对象
//        mLocationOption = new AMapLocationClientOption();
//        mLocationOption.setLocationMode(AMapLocationClientOption.AMapLocationMode.Hight_Accuracy);
//        mLocationOption.setNeedAddress(true);
//

        //新显示小蓝点方法精度太差
        MyLocationStyle myLocationStyle;
        myLocationStyle = new MyLocationStyle();//初始化定位蓝点样式类
        myLocationStyle.myLocationType(MyLocationStyle.LOCATION_TYPE_LOCATION_ROTATE);//连续定位、且将视角移动到地图中心点，定位点依照设备方向旋转，并且会跟随设备移动。（1秒1次定位）如果不设置myLocationType，默认也会执行此种模式。
        myLocationStyle.strokeColor(0);
        myLocationStyle.radiusFillColor(0);
        myLocationStyle.interval(1000); //设置连续定位模式下的定位间隔，只在连续定位模式下生效，单次定位模式下不会生效。单位为毫秒。
        myLocationStyle.showMyLocation(true);
        aMap.setMyLocationStyle(myLocationStyle);//设置定位蓝点的Style
        aMap.getUiSettings().setMyLocationButtonEnabled(false);//设置默认定位按钮是否显示，非必需设置。
        aMap.setMyLocationEnabled(true);// 设置为true表示启动显示定位蓝点，false表示隐藏定位蓝点并不进行定位，默认是false。
        aMap.moveCamera(CameraUpdateFactory.zoomTo(INITIAL_ZOOM_SIZE));
        aMap.showIndoorMap(true);

        //防止拖动地图时自动回到中心
        aMap.setOnMapTouchListener(new AMap.OnMapTouchListener() {
            @Override
            public void onTouch(MotionEvent motionEvent) {
                myLocationStyle.myLocationType(MyLocationStyle.LOCATION_TYPE_LOCATION_ROTATE_NO_CENTER);
                aMap.setMyLocationStyle(myLocationStyle);
            }
        });
        searchButton=findViewById(R.id.search_button);
        searchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myLocationStyle.myLocationType(MyLocationStyle.LOCATION_TYPE_LOCATION_ROTATE_NO_CENTER);
                aMap.setMyLocationStyle(myLocationStyle);
                startActivity(new Intent(MainActivity.this, SearchActivity.class));
            }
        });
        locateButton=findViewById(R.id.locate_button);
        locateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (previousMarker != null) {
                    previousMarker.remove();
                }
                if(myLocationStyle.getMyLocationType()!=MyLocationStyle.LOCATION_TYPE_LOCATION_ROTATE)
                {
                    Location location = aMap.getMyLocation();
                    if (location != null) {
                        aMap.moveCamera(CameraUpdateFactory.newLatLng(new LatLng(location.getLatitude(), location.getLongitude())));
                    }

                myLocationStyle.myLocationType(MyLocationStyle.LOCATION_TYPE_LOCATION_ROTATE);
                aMap.setMyLocationStyle(myLocationStyle);
                }
            }
        });



        //导航按钮跳转导航界面
        naviButton=findViewById(R.id.navi_button);
        naviButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (previousMarker != null) {
                    previousMarker.remove();
                }
                Intent intent=new Intent(MainActivity.this,testactivity.class);
                // Bundle bundle =new Bundle();
                intent.putExtra("Poi",previousPoi);
                startActivity(intent);
//                //构建导航组件配置类，没有传入起点，所以起点默认为 “我的位置”
//                AmapNaviParams params = new AmapNaviParams(null, null, null, AmapNaviType.DRIVER, AmapPageType.ROUTE);
//
//                //启动导航组件
//                AmapNaviPage.getInstance().showRouteActivity(getApplicationContext(), params, null);

            }
        });

        //构建导航组件配置类，没有传入起点，所以起点默认为 “我的位置”
        //AmapNaviParams params = new AmapNaviParams(null, null, null, AmapNaviType.DRIVER, AmapPageType.ROUTE);

        //启动导航组件
        //AmapNaviPage.getInstance().showRouteActivity(getApplicationContext(), params, null);

    }

    @Override
    protected void  onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        Double Latitude=intent.getDoubleExtra("Latitude",0);
        Double Longitude=intent.getDoubleExtra("Longitude",0);
        String PoiID=intent.getStringExtra("PoiID");
        String Name =intent.getStringExtra("Name");
        LatLng latLng = new LatLng(Latitude,Longitude);
        Marker marker = aMap.addMarker(new MarkerOptions().position(latLng).title("新地点").snippet("DefaultMarker"));
        aMap.moveCamera(CameraUpdateFactory.newLatLng(latLng));
        // 删除上一个marker
        if (previousMarker != null) {
            previousMarker.remove();
        }
        // 保存新marker的引用
        previousMarker = marker;

        if (previousPoi != null) {
            previousPoi=null;
        }
        previousPoi=new Poi(Name,new LatLng(Latitude,Longitude),PoiID);

    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        //在activity执行onDestroy时执行mMapView.onDestroy()，销毁地图
        mMapView.onDestroy();
    }
    @Override
    protected void onResume() {
        super.onResume();
        //在activity执行onResume时执行mMapView.onResume ()，重新绘制加载地图
        mMapView.onResume();
    }
    @Override
    protected void onPause() {
        super.onPause();
        //在activity执行onPause时执行mMapView.onPause ()，暂停地图的绘制
        mMapView.onPause();
    }
    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        //在activity执行onSaveInstanceState时执行mMapView.onSaveInstanceState (outState)，保存地图当前的状态
        mMapView.onSaveInstanceState(outState);
    }



}