package com.yw.android.store.vm.views.store;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.design.widget.CoordinatorLayout;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;

import com.ww.mvp.view.IView;
import com.yw.android.store.R;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by feng on 2017/9/21.
 */

public class StoreView implements IView {
    @BindView(R.id.iv_arrow_down)
    ImageView ivShow;
    @BindView(R.id.ll_nav)
    LinearLayout llNav;
    @BindView(R.id.mall_coordinator)
    CoordinatorLayout coordinator;

    private RecyclerView rvPop;

    private boolean isShow;

    private Context context;
    private PopupWindow popupWindow;


    @BindView(R.id.rv_pop)
    View popView;
    @BindView(R.id.ll_pop)
    LinearLayout llPop;

    @Override
    public void onAttach(@NonNull Activity activity, @NonNull View view) {
        this.context = view.getContext();
        ButterKnife.bind(this, view);

        initRePop();
//        createPopup();
    }

    private void initRePop(){
        rvPop = ButterKnife.findById(popView,R.id.rv_pop);
        GridLayoutManager manager = new GridLayoutManager(context,4);
        rvPop.setLayoutManager(manager);
    }


    public RecyclerView getRvPop() {
        return rvPop;
    }

    public void showPopup(){
        if (llPop.getVisibility()==View.VISIBLE){
            llPop.setVisibility(View.GONE);
        }else {
            llPop.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public void onResume() {

    }

    @Override
    public void onDestroy() {

    }
}
