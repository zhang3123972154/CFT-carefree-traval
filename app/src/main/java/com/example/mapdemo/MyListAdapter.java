package com.example.mapdemo;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.style.AbsoluteSizeSpan;
import android.text.style.TypefaceSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;

import com.amap.api.services.help.Tip;

import java.util.List;

public class MyListAdapter extends RecyclerView.Adapter<MyListAdapter.ViewHolder> {

    private final List<Tip> mData;
    private Context mContext;

    public MyListAdapter(List<Tip> data,Context context) {
        mData = data;
        mContext=context;
    }
//创建条目的view
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_layout, parent, false);
        return new ViewHolder(view);
    }
//用于绑定holder，用于设置数据
    @Override
    public void onBindViewHolder(ViewHolder holder, @SuppressLint("RecyclerView") int position) {
        Tip item = mData.get(position);
        String District=item.getDistrict();
        //int num =getItemCount();
        String Name=item.getName();
        int length1=District.length();
        int length2=Name.length();
        // 创建一个SpannableString对象
        SpannableString spannableString = new SpannableString(District+"\n"+Name);
        // 设置第一种字体（例如，默认字体）
        spannableString.setSpan(new TypefaceSpan("sans-serif"), 0, length1, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        spannableString.setSpan(new AbsoluteSizeSpan(16, true), 0, length1, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        // 设置第二种字体（例如，斜体）
        spannableString.setSpan(new TypefaceSpan("serif"), length1, length2+length1+1, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        spannableString.setSpan(new AbsoluteSizeSpan(22, true), length1, length2+length1+1, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        // 设置TextView的文本为SpannableString
        holder.textView.setText(spannableString);
        holder.textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Tip selectedItem = mData.get(position);
                Intent intent=new Intent(mContext,MainActivity.class);
                // Bundle bundle =new Bundle();
                intent.putExtra("Latitude",selectedItem.getPoint().getLatitude());
                intent.putExtra("Longitude",selectedItem.getPoint().getLongitude());
                intent.putExtra("Name",selectedItem.getName());
                intent.putExtra("PoiID",selectedItem.getPoiID());
                mContext.startActivity(intent);
                // ((Activity) mContext).finish();

            }
        });


    }
//返回条目个数wu
    @Override
    public int getItemCount() {
        return mData.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView textView;
        public ViewHolder(View itemView) {
            super(itemView);
            textView = itemView.findViewById(R.id.text_view_item);

        }
    }
    //private void sendSelectedItemBack(Tip selectedItem) {
        // 创建一个Intent用于返回数据
        //Intent resultIntent = new Intent();
        // 将选中项的名称作为数据放入Intent
        //resultIntent.putExtra("selected_item", selectedItem);
        // 设置结果并结束当前Activity
        //((Activity) mData).setResult(Activity.RESULT_OK, resultIntent);
        //((Activity) mData).finish();
    //}
}
