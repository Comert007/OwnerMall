package com.yw.android.store.vm.views;

import android.content.Context;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.youth.banner.Banner;
import com.youth.banner.loader.ImageLoader;
import com.yw.android.store.R;

import java.util.List;

import butterknife.ButterKnife;
import ww.com.core.ScreenUtil;

/**
 * Created by feng on 2017/9/20.
 */

public class BannerView extends RefreshView {
    private Banner banner;

    private View bannerView;
    private List<String> urls;

    RecyclerView rvSpecial; //专场滑动

    TextView tvMiddleTitle;
    RecyclerView rvHor; //横向滑动

    @Override
    public void attach() {
        super.attach();
        bannerView = LayoutInflater.from(context).inflate(R.layout.layout_banner,null);

        ScreenUtil.scale(bannerView);
        banner = ButterKnife.findById(bannerView,R.id.banner);
        tvMiddleTitle = ButterKnife.findById(bannerView,R.id.tv_middle_title);
        rvHor = ButterKnife.findById(bannerView,R.id.rv_hor);

        initSpecial();
        initHor();
    }

    /**
     * 初始化专场布局
     */
    private void initSpecial(){
        rvSpecial = ButterKnife.findById(bannerView,R.id.rv_special);
        LinearLayoutManager manager = new LinearLayoutManager(context);
        manager.setOrientation(LinearLayoutManager.HORIZONTAL);
        rvSpecial.setItemAnimator(new DefaultItemAnimator());
        rvSpecial.setLayoutManager(manager);
    }

    /**
     * 初始化横向title
     */
    private void initHor(){
        LinearLayoutManager manager = new LinearLayoutManager(context);
        manager.setOrientation(LinearLayoutManager.HORIZONTAL);
        rvHor.setLayoutManager(manager);
        rvHor.setItemAnimator(new DefaultItemAnimator());
    }


    public RecyclerView getRvSpecial() {
        return rvSpecial;
    }

    public void addBanner(){
        crv.addHeadView(bannerView);
    }


    public void setUrls(List<String> urls) {
        this.urls = urls;
    }


    public void startBanner(){
        banner.setImageLoader(new ImageLoader() {
            @Override
            public void displayImage(Context context, Object path, ImageView imageView) {
                com.nostra13.universalimageloader.core.ImageLoader.getInstance().displayImage(
                        (String) path, imageView);
            }
        });

        if (urls!=null&& urls.size()>0){
            banner.setImages(urls);
            banner.start();
        }
    }

    public Banner getBanner() {
        return banner;
    }

    public RecyclerView getRvHor() {
        return rvHor;
    }

    public void setMiddleTitle(String title){
        tvMiddleTitle.setText(title);
    }

    @Override
    public void onResume() {

    }

    @Override
    public void onDestroy() {

    }
}
