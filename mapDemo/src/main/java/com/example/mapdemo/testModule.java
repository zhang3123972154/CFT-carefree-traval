package com.example.mapdemo;

import android.app.Activity;
import android.util.Log;
import android.content.Intent;

import io.dcloud.feature.uniapp.annotation.UniJSMethod;
import io.dcloud.feature.uniapp.common.UniModule;
import io.dcloud.feature.uniapp.bridge.UniJSCallback;

import com.alibaba.fastjson.JSONObject;

public class testModule extends UniModule {

    String TAG = "TestModule";
    public static int REQUEST_CODE = 1000;

    // info 进入 MainActivity 的原生渲染
    @UniJSMethod(uiThread = true)
    public void gotoNativePage(){
        if(mUniSDKInstance != null && mUniSDKInstance.getContext() instanceof Activity) {
            Intent intent = new Intent(mUniSDKInstance.getContext(), MainActivity.class);
            ((Activity)mUniSDKInstance.getContext()).startActivityForResult(intent, REQUEST_CODE);
        }
    }

    // info 获取uniapp的起终点，然后直接开始导航。
    @UniJSMethod(uiThread = true)
    public void getNavigationThenGotoPage(JSONObject path) {
        // DATA INIT
        Log.e(TAG, "testJSON--path--" + path);
            // mark 数据在 path 里 // eg.testJSON--{"start":"武汉大学","end":"武汉站"}

        // JUMP TO Navigation Acyivity
            // bug 好像没法直接跳转过去
            // info 猜测是默认需要当前位置的信息？
//        if(mUniSDKInstance != null && mUniSDKInstance.getContext() instanceof Activity) {
//            Intent intent = new Intent(mUniSDKInstance.getContext(), NaviActivity.class);
//            ((Activity)mUniSDKInstance.getContext()).startActivityForResult(intent, REQUEST_CODE);
//        }
    }

    // 点位大致展示的 接口
    @UniJSMethod(uiThread = true)
    public void positionPreShow(JSONObject position) {
        Log.e(TAG, "testJson--positions--" + position);

        // other... // eg. {"positions": ["武汉大学口腔", "武汉大学情人坡", "武汉大学(澄波门)", "武汉大学"]}
    }


}
