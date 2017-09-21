package com.yw.android.store.api;

import android.text.TextUtils;

import com.yw.android.store.bean.ResponseBean;

import rx.Observable;
import ww.com.http.core.AjaxParams;


/**
 * Created by feng on 2017/9/20.
 */

public class StoreApi extends BaseApi {

    public  final static Observable<ResponseBean> navigation(String type){

        if (TextUtils.isEmpty(type)){
            type = "mall";
        }
        String url = getUrl("/navigation/"+type);
       return onGet(url,new AjaxParams());

    }


    /**
     * 获取banner
     * @param navSid 导航sid
     * @return
     */
    public final static Observable<ResponseBean> onBanner(String navSid){
        String url = getUrl("/banner/"+navSid);
        return onGet(url,new AjaxParams());
    }


    /**
     * 商品查询
     * @param navSid
     * @return
     */
    public final static Observable<ResponseBean> onSpu(String navSid){
        String url = getUrl("/spu/app");
        AjaxParams params = new AjaxParams();
        params.addParameters("navigationSid",navSid);

        return onGet(url,params);
    }

    /**
     * 查询热销产品
     * @param pageIndex
     * @return
     */
    public final static Observable<ResponseBean> onSpuAll(String pageIndex){
        String url = getUrl("/spu/app/all");
        AjaxParams params = new AjaxParams();
        params.addParameters("pageIndex",pageIndex);

        return onGet(url,params);
    }

}
