package com.yw.android.store.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.View;

import com.trello.rxlifecycle.FragmentEvent;
import com.yw.android.store.R;
import com.yw.android.store.adapter.NavTabAdapter;
import com.yw.android.store.adapter.NavTabPagerAdapter;
import com.yw.android.store.adapter.PopupAdapter;
import com.yw.android.store.api.rx.HttpSubscriber;
import com.yw.android.store.bean.store.StoreNavBean;
import com.yw.android.store.config.Constant;
import com.yw.android.store.fragment.base.BaseFragment;
import com.yw.android.store.fragment.store.StorePage1Fragment;
import com.yw.android.store.fragment.store.StorePage2Fragment;
import com.yw.android.store.listener.OnItemClickListener;
import com.yw.android.store.vm.models.store.StoreModel;
import com.yw.android.store.vm.views.store.StoreView;

import net.lucode.hackware.magicindicator.MagicIndicator;
import net.lucode.hackware.magicindicator.ViewPagerHelper;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.CommonNavigator;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by feng on 2017/9/19.
 */

public class StoreFragment extends BaseFragment<StoreView, StoreModel> implements OnItemClickListener{
    @BindView(R.id.magic_indicator)
    MagicIndicator magicIndicator;
    @BindView(R.id.view_pager)
    ViewPager viewPager;


    private NavTabPagerAdapter pagerAdapter;
    private List<Fragment> fragments;

    private NavTabAdapter navTabAdapter;
    private List<String> titles;

    private PopupAdapter popupAdapter;


    @Override
    protected int getLayoutResId() {
        return R.layout.fragment_store;
    }

    @Override
    protected void init() {
        viewPager.setOffscreenPageLimit(4);
        CommonNavigator commonNavigator = new CommonNavigator(getContext());
        navTabAdapter = new NavTabAdapter(viewPager);
        commonNavigator.setAdapter(navTabAdapter);
        magicIndicator.setNavigator(commonNavigator);
        ViewPagerHelper.bind(magicIndicator, viewPager);

        initPop();

        onNavigation();
    }


    private void onNavigation(){
        titles = new ArrayList<>();

        m.onNavigation(Constant.TYPE_NAV, bindUntilEvent(FragmentEvent.DESTROY), new
                HttpSubscriber<List<StoreNavBean>>(getContext(), true) {

                    @Override
                    public void onNext(List<StoreNavBean> storeNavBeen) {
                        for (StoreNavBean storeNavBean : storeNavBeen) {
                            titles.add(storeNavBean.getTitle()); //添加title
                        }
                        navTabAdapter.addList(titles);
                        popupAdapter.addList(titles);
                        //构建fragment
                        addFragment(storeNavBeen);
                        initPagerAdapter();
                    }
                });
    }


    private void initPop(){
        popupAdapter = new PopupAdapter(getContext());
        v.getRvPop().setAdapter(popupAdapter);
        popupAdapter.setOnItemClickListener(this);
    }

    @OnClick(R.id.iv_arrow_down)
    public void onShowPop(){
        v.showPopup();
    }

    private void initPagerAdapter(){
        pagerAdapter = new NavTabPagerAdapter(getChildFragmentManager(),fragments);
        viewPager.setAdapter(pagerAdapter);
        viewPager.setOnPageChangeListener(pageChangeListener);
    }


    private void addFragment(List<StoreNavBean> storeNavBeen){
        if (fragments ==null){
            fragments = new ArrayList<>();
        }
        for (int i = 0; i < titles.size(); i++) {
            fragments.add(onCreateFragment(i,storeNavBeen.get(i)));
        }
    }


    ViewPager.OnPageChangeListener pageChangeListener = new ViewPager.OnPageChangeListener() {
        @Override
        public void onPageScrolled(int position, float positionOffset, int
                positionOffsetPixels) {

        }

        @Override
        public void onPageSelected(int position) {
            if (v.getRvPop().getVisibility()==View.VISIBLE){
                v.showPopup();
                return;
            }
            magicIndicator.onPageSelected(position);
        }

        @Override
        public void onPageScrollStateChanged(int state) {

        }
    };

    private Fragment onCreateFragment(int type,StoreNavBean storeNavBean){
        Fragment fragment;
        Bundle  bundle = new Bundle();
        if (type==0){
            fragment = new StorePage1Fragment();
            bundle.putSerializable(Constant.FRAGMENT_ONE,storeNavBean);
        }else {
            fragment = new StorePage2Fragment();
            bundle.putString(Constant.FRAGMENT_OTHER,storeNavBean.getTitle());
        }
        fragment.setArguments(bundle);
        return fragment;
    }

    /**
     * 选择导航栏
     * @param position
     * @param view
     */
    @Override
    public void onItemClick(int position, View view) {
        v.showPopup();
        viewPager.setCurrentItem(position);
    }

}
