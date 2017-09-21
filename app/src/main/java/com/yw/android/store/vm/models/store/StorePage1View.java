package com.yw.android.store.vm.models.store;

import android.view.LayoutInflater;
import android.view.View;

import com.yw.android.store.R;
import com.yw.android.store.vm.views.BannerView;

import ww.com.core.ScreenUtil;

/**
 * Created by feng on 2017/9/20.
 */

public class StorePage1View extends BannerView {

    private View footView;

    @Override
    public void attach() {
        super.attach();
        footView = LayoutInflater.from(context).inflate(R.layout.layout_foot_view, null);
        ScreenUtil.scale(footView);
    }

    public View getFootView() {
        return footView;
    }
}
