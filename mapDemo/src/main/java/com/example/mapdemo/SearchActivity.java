package com.example.mapdemo;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;

import com.amap.api.fence.DistrictItem;
import com.amap.api.location.AMapLocation;
import com.amap.api.location.AMapLocationClient;
import com.amap.api.location.AMapLocationClientOption;
import com.amap.api.location.AMapLocationListener;
import com.amap.api.maps.AMapException;
import com.amap.api.services.core.LatLonPoint;
import com.amap.api.services.core.PoiItemV2;
import com.amap.api.services.help.Inputtips;
import com.amap.api.services.help.InputtipsQuery;
import com.amap.api.services.help.Tip;
import com.amap.api.navi.AMapNavi;
import com.amap.api.services.poisearch.PoiResultV2;
import com.amap.api.services.poisearch.PoiSearchV2;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class SearchActivity extends AppCompatActivity implements Inputtips.InputtipsListener, TextWatcher, PoiSearchV2.OnPoiSearchListener {

    private static final double EARTH_RADIUS = 6371.0; // 地球半径，单位千米
    private EditText editText;
    private  Inputtips inputtips;
    private RecyclerView recyclerView;
    private AMapNavi aMapNavi;
    //private DistrictItem districtItem;

    //private AMapLocationClient locationClient;
    private List<Tip> poiList;
    private PoiSearchV2.Query query;
    private PoiSearchV2 poiSearch;
    private MyListAdapter myListAdapter;

    public AMapLocationListener mLocationListener;

    public AMapLocationClientOption mLocationOption;
    private double userlatitude;
    private  double userlongtitude;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        // 初始化定位
        AMapLocationClient locationClient = null;
        mLocationListener = new AMapLocationListener(){
            public void onLocationChanged(AMapLocation amapLocation) {
                if (amapLocation != null) {
                    if (amapLocation.getErrorCode() == 0) {
//可在其中解析amapLocation获取相应内容。
                        userlatitude=amapLocation.getLatitude();//获取纬度
                        userlongtitude=amapLocation.getLongitude();//获取经度
                    }
                }
            }
        };
        try {
            locationClient = new AMapLocationClient(getApplicationContext());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        locationClient.setLocationListener(mLocationListener);
        locationClient.setLocationOption(mLocationOption);
//启动定位
        locationClient.startLocation();

        //声明AMapLocationClientOption对象
        mLocationOption = null;
//初始化AMapLocationClientOption对象
        mLocationOption = new AMapLocationClientOption();
        mLocationOption.setLocationMode(AMapLocationClientOption.AMapLocationMode.Hight_Accuracy);
        mLocationOption.setNeedAddress(true);
        //输入框
        editText=findViewById(R.id.edit_query);
        editText.addTextChangedListener(this);
        editText.requestFocus();

        //
        recyclerView =findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setFocusable(true);
        recyclerView.setFocusableInTouchMode(true);
        //recycleview设置适配器
        poiList=new ArrayList<>();
        myListAdapter=new MyListAdapter(poiList,this);
        recyclerView.setAdapter(myListAdapter);
        //recyclerView.setItemViewCacheSize(1000);



        //文本框设置监听器
        editText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editText.requestFocus();
                InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.showSoftInput(editText, InputMethodManager.SHOW_IMPLICIT);
            }
        });

        //地点搜索提示框
        inputtips =new Inputtips(this,(InputtipsQuery) null);
        inputtips.setInputtipsListener(this);
        //导航部分
        try {
            aMapNavi =AMapNavi.getInstance(this);
        } catch (AMapException e) {
            throw new RuntimeException(e);
        }
        aMapNavi.setUseInnerVoice(true,false);
    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

    }

    public void onTextChanged(CharSequence s, int start, int before, int count)
    {

        if (s.length() > 0) {
        InputtipsQuery inputtipsQuery =new InputtipsQuery(String.valueOf(s),"");
        inputtipsQuery.setCityLimit(true);
        inputtips.setQuery(inputtipsQuery);
        inputtips.requestInputtipsAsyn();}
        else{
            poiList.clear();
            myListAdapter.notifyDataSetChanged();
        }


    }

//    public void doSerchQuery(String key) throws com.amap.api.services.core.AMapException {
//// 第一个参数表示搜索字符串，第二个参数表示poi搜索类型，第三个参数表示poi搜索区域（空字符串代表全国）
//        query = new PoiSearchV2.Query(key, "", "");
//        poiSearch = new PoiSearchV2(this, query);
//        poiSearch.setOnPoiSearchListener(this);
//        //调用 PoiSearch 的 searchPOIAsyn() 方法发送请求。
//        poiSearch.searchPOIAsyn();
//    }

    @Override
    public void afterTextChanged(Editable s) {
        if (s.length() == 0) {
            poiList.clear();
            myListAdapter.notifyDataSetChanged();
        }
    }

    @Override
    public void onGetInputtips(List<Tip> tipslist, int rCode) {
        if (rCode == 1000)
        {
            sortPoiListByDistance(tipslist,userlatitude,userlongtitude);
            poiList.clear();
           poiList.addAll(tipslist);
           myListAdapter.notifyDataSetChanged();
        }
    }
    private static double calculateDistance(double lat1, double lon1, double lat2, double lon2) {
        double dLat = Math.toRadians(lat2 - lat1);
        double dLon = Math.toRadians(lon2 - lon1);

        lat1 = Math.toRadians(lat1);
        lat2 = Math.toRadians(lat2);

        double a = Math.sin(dLat / 2) * Math.sin(dLat / 2) +
                Math.sin(dLon / 2) * Math.sin(dLon / 2) * Math.cos(lat1) * Math.cos(lat2);
        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
        return EARTH_RADIUS * c;
    }
    private void sortPoiListByDistance(List<Tip> list,double userLatitude,double userLongtitude) {


            LatLonPoint userPoint = new LatLonPoint( userLatitude, userLongtitude);
            Collections.sort(list, new Comparator<Tip>() {
                @Override
                public int compare(Tip o1, Tip o2) {
                    LatLonPoint poi1 = o1.getPoint();
                    LatLonPoint poi2 = o2.getPoint();
                    double distance1 = calculateDistance(userPoint.getLatitude(),userPoint.getLongitude(),poi1.getLatitude(), poi1.getLongitude());
                    double distance2 = calculateDistance(userPoint.getLatitude(),userPoint.getLongitude(),poi2.getLatitude(), poi2.getLongitude());
                    return Double.compare(distance1, distance2);
                }
            });

    }
    @Override
    public void onPoiSearched(PoiResultV2 poiResultV2, int rCode) {
//        if (rCode == 1000) {
//            // 解析result   获取搜索poi的结果
//            if (result != null && result.getQuery() != null) {
////                if (result.getQuery().equals(query)) {  // 是否是同一条
////                    poiResult = result;
////                    ArrayList<LocationAddressInfo> data = new ArrayList<LocationAddressInfo>();//自己创建的数据集合
////                    // 取得第一页的poiitem数据，页数从数字0开始
////                    //poiResult.getPois()可以获取到PoiItem列表
////                    List<PoiItem> poiItems = poiResult.getPois();
////
////                    //若当前城市查询不到所需POI信息，可以通过result.getSearchSuggestionCitys()获取当前Poi搜索的建议城市
////                    List<SuggestionCity> suggestionCities = poiResult.getSearchSuggestionCitys();
////                    //如果搜索关键字明显为误输入，则可通过result.getSearchSuggestionKeywords()方法得到搜索关键词建议。
//////                    List<String> suggestionKeywords =  poiResult.getSearchSuggestionKeywords();
////
////                    //解析获取到的PoiItem列表
////                    for(PoiItem item : poiItems){
////                        //获取经纬度对象
////                        LatLonPoint llp = item.getLatLonPoint();
////                        double lon = llp.getLongitude();
////                        double lat = llp.getLatitude();
////                        //返回POI的名称
////                        String title = item.getTitle();
////                        //返回POI的地址
////                        String text = item.getSnippet();
////                        data.add(new LocationAddressInfo(String.valueOf(lon), String.valueOf(lat), title, text));
////                    }
////                    listAdapter = new LocationListAdapter(this, data);
////                    listView.setAdapter(listAdapter);
////                }
////            } else {
////                Toast.makeText(SearchActivity.this,"无搜索结果",Toast.LENGTH_SHORT).show();
////            }
////        } else {
////            Toast.makeText(SearchActivity.this,"错误码"+rCode,Toast.LENGTH_SHORT).show();
//        }
//
    }

    @Override
    public void onPoiItemSearched(PoiItemV2 poiItemV2, int i) {

    }


}