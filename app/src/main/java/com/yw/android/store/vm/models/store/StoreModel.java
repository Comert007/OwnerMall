package com.yw.android.store.vm.models.store;

import android.content.Context;
import android.support.annotation.NonNull;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.trello.rxlifecycle.LifecycleTransformer;
import com.ww.mvp.model.IModel;
import com.yw.android.store.api.StoreApi;
import com.yw.android.store.api.rx.HttpSubscriber;
import com.yw.android.store.bean.ResponseBean;
import com.yw.android.store.bean.store.BannerBean;
import com.yw.android.store.bean.store.PaginationBean;
import com.yw.android.store.bean.store.SpuGoodsAllBean;
import com.yw.android.store.bean.store.SpuGoodsBean;
import com.yw.android.store.bean.store.StoreNavBean;

import java.util.List;

import rx.functions.Func1;
import ww.com.http.rx.RxHelper;

/**
 * Created by feng on 2017/9/20.
 */

public class StoreModel implements IModel {
    @Override
    public void onAttach(@NonNull Context context) {

    }

    /**
     * 获取导航
     *
     * @param type
     * @param lifecycleTransformer
     * @param httpSubscriber
     */
    public void onNavigation(String type,
                             LifecycleTransformer lifecycleTransformer,
                             HttpSubscriber<List<StoreNavBean>> httpSubscriber) {

        StoreApi.navigation(type)
                .map(new Func1<ResponseBean, List<StoreNavBean>>() {
                    @Override
                    public List<StoreNavBean> call(ResponseBean responseBean) {
                        List<StoreNavBean> storeNavBeen = JSON.parseObject(responseBean.getData(),
                                new TypeReference<List<StoreNavBean>>() {
                                });
                        return storeNavBeen;
                    }
                })
                .compose(RxHelper.<List<StoreNavBean>>cutMain())
                .compose(lifecycleTransformer)
                .subscribe(httpSubscriber);
    }


    /**
     * 获取banner
     *
     * @param navSid
     * @param lifecycleTransformer
     * @param httpSubscriber
     */
    public void onBanner(String navSid,
                         LifecycleTransformer lifecycleTransformer,
                         HttpSubscriber<List<BannerBean>> httpSubscriber) {
        StoreApi.onBanner(navSid)
                .map(new Func1<ResponseBean, List<BannerBean>>() {
                    @Override
                    public List<BannerBean> call(ResponseBean responseBean) {
                        List<BannerBean> bannerBean = JSON.parseObject(responseBean.getData(),
                                new TypeReference<List<BannerBean>>() {
                                });
                        return bannerBean;
                    }
                }).compose(RxHelper.<List<BannerBean>>cutMain())
                .compose(lifecycleTransformer)
                .subscribe(httpSubscriber);
    }


    /**
     * 查询商品
     *
     * @param navSid
     * @param lifecycleTransformer
     * @param httpSubscriber
     */
    public void onSpu(String navSid,
                      LifecycleTransformer lifecycleTransformer,
                      HttpSubscriber<List<SpuGoodsBean>> httpSubscriber) {

        StoreApi.onSpu(navSid)
                .map(new Func1<ResponseBean, List<SpuGoodsBean>>() {
                    @Override
                    public List<SpuGoodsBean> call(ResponseBean responseBean) {
                        List<SpuGoodsBean> spuGoodsBeen = JSON.parseObject(responseBean.getData()
                                , new TypeReference<List<SpuGoodsBean>>() {
                                });
                        return spuGoodsBeen;
                    }
                }).compose(RxHelper.<List<SpuGoodsBean>>cutMain())
                .compose(lifecycleTransformer)
                .subscribe(httpSubscriber);
    }

    /**
     * 查询所有商品
     * @param pageIndex
     * @param lifecycleTransformer
     * @param httpSubscriber
     */
    public void onSpuAll(String pageIndex,
                         LifecycleTransformer lifecycleTransformer,
                         HttpSubscriber<SpuGoodsAllBean> httpSubscriber) {

        StoreApi.onSpuAll(pageIndex)
                .map(new Func1<ResponseBean, SpuGoodsAllBean>() {
                    @Override
                    public SpuGoodsAllBean call(ResponseBean responseBean) {
                        SpuGoodsAllBean spuGoodsAllBean = new SpuGoodsAllBean();
                        PaginationBean paginationBean = JSON.parseObject(responseBean
                                .getPagination(), PaginationBean.class);
                        List<SpuGoodsBean> spuGoodsBeen = JSON.parseObject(responseBean.getData()
                                , new TypeReference<List<SpuGoodsBean>>() {
                        });

                        spuGoodsAllBean.setPagination(paginationBean);
                        spuGoodsAllBean.setSpuGoodsBeen(spuGoodsBeen);
                        return spuGoodsAllBean;
                    }
                }).compose(RxHelper.<SpuGoodsAllBean>cutMain())
                .compose(lifecycleTransformer)
                .subscribe(httpSubscriber);
    }

}
