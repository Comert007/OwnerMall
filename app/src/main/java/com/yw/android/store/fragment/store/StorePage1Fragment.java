package com.yw.android.store.fragment.store;

import android.support.v7.widget.GridLayoutManager;
import android.text.TextUtils;
import android.view.View;

import com.trello.rxlifecycle.FragmentEvent;
import com.youth.banner.listener.OnBannerListener;
import com.yw.android.store.R;
import com.yw.android.store.adapter.NavBottomAdapter;
import com.yw.android.store.adapter.SpecialAdapter;
import com.yw.android.store.adapter.SpecialMoreAdapter;
import com.yw.android.store.api.rx.HttpSubscriber;
import com.yw.android.store.bean.store.BannerBean;
import com.yw.android.store.bean.store.PaginationBean;
import com.yw.android.store.bean.store.PositionGroupsBean;
import com.yw.android.store.bean.store.SpuGoodsAllBean;
import com.yw.android.store.bean.store.SpuGoodsBean;
import com.yw.android.store.bean.store.StoreNavBean;
import com.yw.android.store.bean.store.StoreNavEntity;
import com.yw.android.store.config.Constant;
import com.yw.android.store.fragment.base.BaseFragment;
import com.yw.android.store.listener.OnItemClickListener;
import com.yw.android.store.vm.models.store.StoreModel;
import com.yw.android.store.vm.models.store.StorePage1View;

import java.util.ArrayList;
import java.util.List;

import ww.com.core.widget.CustomSwipeRefreshLayout;

/**
 * Created by feng on 2017/9/20.
 * 商城第一界面
 */

public class StorePage1Fragment extends BaseFragment<StorePage1View, StoreModel> implements
        OnItemClickListener {

    private List<String> urls;
    private StoreNavBean storeNavBean;

    private NavBottomAdapter navBottomAdapter; //底部可滚动title

    private SpecialAdapter specialAdapter; //专场adapter
    private SpecialMoreAdapter specialMoreAdapter; //更多专场adapter

    private int bottomCurPosition;

    private List<StoreNavEntity> middle;
    private List<StoreNavEntity> bottom;

    private int curPageIndex =0;

    @Override
    protected int getLayoutResId() {
        return R.layout.fragment_page1;
    }

    @Override
    protected void init() {
        storeNavBean = (StoreNavBean) getArguments().getSerializable(Constant.FRAGMENT_ONE);
        if (storeNavBean != null) {
            initTitle(storeNavBean);
//            onBanner(storeNavBean);
            initRecy();
        }
    }


    /**
     * 初始化 Middle 和Bottom 的Title
     *
     * @param storeNavBean
     */
    private void initTitle(StoreNavBean storeNavBean) {
        navBottomAdapter = new NavBottomAdapter(getContext());
        navBottomAdapter.setOnItemClickListener(this);
        v.getRvHor().setAdapter(navBottomAdapter);

        PositionGroupsBean groupsBean = storeNavBean.getPositionGroups();
        List<StoreNavEntity> middle = groupsBean.getMiddle();
        List<StoreNavEntity> bottom = groupsBean.getBottom();
        if (middle != null && middle.size() > 0) {
            v.setMiddleTitle(middle.get(0).getTitle());
        }

        if (bottom != null && bottom.size() > 0) {
            navBottomAdapter.addList(bottom);
        }

    }


    /**
     * 开启banner
     *
     * @param bannerBeen
     */
    private void startBanner(List<BannerBean> bannerBeen) {
        if (urls == null) {
            urls = new ArrayList<>();
        }
        urls.clear();

        if (bannerBeen != null && bannerBeen.size() > 0) {
            for (BannerBean bannerBean : bannerBeen) {
                urls.add(bannerBean.getImagePath());
            }
        }

        v.setUrls(urls);
        v.startBanner();

        v.getBanner().setOnBannerListener(new OnBannerListener() {
            @Override
            public void OnBannerClick(int position) {
                showToast("点击了" + position + "位置");
            }
        });
        v.addBanner();
    }


    private void onBanner(final StoreNavBean storeNavBean) {
        String sid = storeNavBean.getSid();
        if (TextUtils.isEmpty(sid)) {
            showToast(getResources().getString(R.string.sid_error));
            return;
        }
        m.onBanner(sid, bindUntilEvent(FragmentEvent.DESTROY), new
                HttpSubscriber<List<BannerBean>>(getContext(), false) {

                    @Override
                    public void onNext(List<BannerBean> bannerBeen) {
                        startBanner(bannerBeen);
                        onMiddleSpu(storeNavBean);
                    }
                });

    }


    private void onMiddleSpu(StoreNavBean storeNavBean) {
        PositionGroupsBean groupsBean = storeNavBean.getPositionGroups();
        middle = groupsBean.getMiddle();
        bottom = groupsBean.getBottom();

        m.onSpu(middle.get(0).getSid(), bindUntilEvent(FragmentEvent.DESTROY),
                new HttpSubscriber<List<SpuGoodsBean>>(getContext(), false) {
                    @Override
                    public void onNext(List<SpuGoodsBean> spuGoodsBeen) {
                        specialAdapter.addList(spuGoodsBeen);

                        bottomCurPosition = 0;
                        onBottomSpu(bottom, bottomCurPosition);
                    }
                });
    }


    private void onBottomSpu(List<StoreNavEntity> bottom, int index) {

        m.onSpu(bottom.get(index).getSid(), bindUntilEvent(FragmentEvent.DESTROY),
                new HttpSubscriber<List<SpuGoodsBean>>(getContext(), true) {
                    @Override
                    public void onNext(List<SpuGoodsBean> spuGoodsBeen) {

                        SpuGoodsBean spu = new SpuGoodsBean();
                        spu.setGuessLike("1");
                        spuGoodsBeen.add(spu);

                        modifyRecyGridManager(spuGoodsBeen.size());
                        specialMoreAdapter.addList(spuGoodsBeen);

                        curPageIndex =0;
                        onSpuAll(curPageIndex);
                    }
                });
    }

    /**
     * 获取猜你喜欢商品
     * @param pageIndex 第几页
     */
    private void onSpuAll(int pageIndex) {
        m.onSpuAll(pageIndex+"", bindUntilEvent(FragmentEvent.DESTROY), new
                HttpSubscriber<SpuGoodsAllBean>(getContext(), true) {
            @Override
            public void onNext(SpuGoodsAllBean spuGoodsAllBean) {
                PaginationBean pagination = spuGoodsAllBean.getPagination();
                List<SpuGoodsBean> spuGoodsBeen = spuGoodsAllBean.getSpuGoodsBeen();

                if (spuGoodsBeen!=null && spuGoodsBeen.size()>0){
                    curPageIndex = pagination.getPageIndex();

                    specialMoreAdapter.appendList(spuGoodsBeen);
                    if (curPageIndex< pagination.getPageCount()){
                        curPageIndex++;
                    }else {
                        v.getCsr().setEnableRefresh(false);
                    }
                    v.getCsr().setRefreshFinished();
                }

            }
        });
    }

    /**
     * 初始化recycleView
     */
    private void initRecy() {

        specialAdapter = new SpecialAdapter(getContext());
        specialMoreAdapter = new SpecialMoreAdapter(getContext());

        v.getRvSpecial().setAdapter(specialAdapter);


        v.getCsr().setEnableRefresh(true);
        v.getCsr().setFooterRefreshAble(true);
        v.getCsr().setOnSwipeRefreshListener(new CustomSwipeRefreshLayout
                .OnSwipeRefreshLayoutListener() {

            @Override
            public void onHeaderRefreshing() {
//                v.getCsr().setRefreshFinished();
                onBanner(storeNavBean);
            }

            @Override
            public void onFooterRefreshing() {

//                v.getCrv().addFooterView(v.getFootView());
                onSpuAll(curPageIndex);
            }
        });

        v.getCrv().setAdapter(specialMoreAdapter);

    }

    /**
     * bottom 滑动点击事件
     *
     * @param position
     * @param v
     */
    @Override
    public void onItemClick(int position, View v) {
        //如果点击就是当前事件不作处理
        if (bottomCurPosition == position) {
            return;
        }

        bottomCurPosition = position;
        onBottomSpu(bottom, bottomCurPosition);

    }

    private void modifyRecyGridManager(final int index) {
        ((GridLayoutManager) v.getCrv().getLayoutManager()).setSpanSizeLookup(
                new GridLayoutManager.SpanSizeLookup() {
                    @Override
                    public int getSpanSize(int position) {
                        if (position == 0 || position == index) {
                            return 2;
                        } else
                            return 1;
                    }
                });
    }

    private void test(){
        GridLayoutManager manager = ((GridLayoutManager) v.getCrv().getLayoutManager());
        manager.scrollToPositionWithOffset(0,0);
        manager.setStackFromEnd(true);
    }

    @Override
    public void onResume() {
        super.onResume();
        onBanner(storeNavBean);
    }
}
