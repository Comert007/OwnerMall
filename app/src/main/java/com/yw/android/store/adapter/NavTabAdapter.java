package com.yw.android.store.adapter;

import android.content.Context;
import android.graphics.Color;
import android.support.v4.view.ViewPager;
import android.view.View;

import com.yw.android.store.R;

import net.lucode.hackware.magicindicator.buildins.commonnavigator.abs.CommonNavigatorAdapter;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.abs.IPagerIndicator;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.abs.IPagerTitleView;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.indicators.LinePagerIndicator;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.titles.ColorTransitionPagerTitleView;

import java.util.ArrayList;
import java.util.List;

import ww.com.core.Debug;

/**
 * Created by feng on 2017/9/19.
 */

public class NavTabAdapter extends CommonNavigatorAdapter {

    private List<String> mData;

    private ViewPager viewPager;

    public NavTabAdapter() {
    }

    public NavTabAdapter(ViewPager viewPager) {
        this.viewPager = viewPager;
    }

    public NavTabAdapter(ViewPager viewPager,List<String> mData) {
        this.mData = mData;
        this.viewPager = viewPager;
    }

    //添加数据
    public void addList(List<String> data) {
        if (mData == null) {
            mData = new ArrayList<>();
        }
        mData.clear();
        mData.addAll(data);
        notifyDataSetChanged();
    }

    public void appendList(List<String> data) {
        int size = this.mData.size();
        this.mData.addAll(data);
        int end = this.mData.size();
        notifyDataSetChanged();
    }

    public String getItem(int index) {
        return mData.get(index);
    }


    @Override
    public int getCount() {
        return mData == null ? 0 : mData.size();
    }

    @Override
    public IPagerTitleView getTitleView(Context context, final int index) {
        ColorTransitionPagerTitleView colorTransitionPagerTitleView = new
                ColorTransitionPagerTitleView(context);
        colorTransitionPagerTitleView.setNormalColor(Color.parseColor("#333333"));
        colorTransitionPagerTitleView.setSelectedColor(Color.parseColor("#b4282d"));
        colorTransitionPagerTitleView.setText(mData.get(index));
        colorTransitionPagerTitleView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Debug.d("viewpager is onclick");
                viewPager.setCurrentItem(index);

            }
        });
        return colorTransitionPagerTitleView;
    }

    @Override
    public IPagerIndicator getIndicator(Context context) {
        LinePagerIndicator indicator = new LinePagerIndicator(context);
        indicator.setColors(context.getResources().getColor(R.color.color_b4282d));
        indicator.setMode(LinePagerIndicator.MODE_WRAP_CONTENT);
        return indicator;
    }
}
