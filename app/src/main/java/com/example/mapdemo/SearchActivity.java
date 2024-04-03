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
import com.amap.api.maps.AMapException;
import com.amap.api.services.core.PoiItemV2;
import com.amap.api.services.help.Inputtips;
import com.amap.api.services.help.InputtipsQuery;
import com.amap.api.services.help.Tip;
import com.amap.api.navi.AMapNavi;
import com.amap.api.services.poisearch.PoiResultV2;
import com.amap.api.services.poisearch.PoiSearchV2;

import java.util.List;

public class SearchActivity extends AppCompatActivity implements Inputtips.InputtipsListener, TextWatcher, PoiSearchV2.OnPoiSearchListener {

    private EditText editText;
    private  Inputtips inputtips;
    private RecyclerView recyclerView;
    private AMapNavi aMapNavi;
    private DistrictItem districtItem;

    private PoiSearchV2.Query query;
    private PoiSearchV2 poiSearch;

    private InputMethodManager imm;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

       //输入框
        editText=findViewById(R.id.edit_query);
        editText.addTextChangedListener(this);
        editText.requestFocus();

        //
        recyclerView =findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setFocusable(true);
        recyclerView.setFocusableInTouchMode(true);

        InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.showSoftInput(editText, InputMethodManager.SHOW_IMPLICIT);



        // 获取InputMethodManager

        // 为布局中的非输入框区域设置点击监听器
        findViewById(R.id.recyclerView).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 清除当前获取焦点的视图的焦点

                editText.clearFocus();
                // 隐藏软键盘
                imm.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), 0);
            }
        });
        editText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editText.requestFocus();
                InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.showSoftInput(editText, InputMethodManager.SHOW_IMPLICIT);
            }
        });


        inputtips =new Inputtips(this,(InputtipsQuery) null);
        inputtips.setInputtipsListener(this);

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
//        String citycode=districtItem.getCitycode();
//        InputtipsQuery inputtipsQuery =new InputtipsQuery(String.valueOf(s),citycode);
//        inputtipsQuery.setCityLimit(true);
//
//        inputtips.setQuery(inputtipsQuery);
//        inputtips.requestInputtipsAsyn();
    }

    public void doSerchQuery(String key) throws com.amap.api.services.core.AMapException {
// 第一个参数表示搜索字符串，第二个参数表示poi搜索类型，第三个参数表示poi搜索区域（空字符串代表全国）
        query = new PoiSearchV2.Query(key, "", "");
        poiSearch = new PoiSearchV2(this, query);
        poiSearch.setOnPoiSearchListener(this);
        //调用 PoiSearch 的 searchPOIAsyn() 方法发送请求。
        poiSearch.searchPOIAsyn();


    }

    @Override
    public void afterTextChanged(Editable s) {

    }

    @Override
    public void onGetInputtips(List<Tip> list, int rCode) {
        if (rCode == 1000)
        {

        }

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

    public void showKeyboard()
    {

        EditText editText = findViewById(R.id.edit_query);
        editText.requestFocus();
        InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.showSoftInput(editText, InputMethodManager.SHOW_IMPLICIT);
    }
}