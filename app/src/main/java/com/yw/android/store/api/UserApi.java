package com.yw.android.store.api;

import android.text.TextUtils;

import com.yw.android.store.bean.ResponseBean;

import rx.Observable;
import ww.com.http.core.AjaxParams;

/**
 * Created by feng on 2017/9/19.
 */

public class UserApi extends BaseApi {

    //登录
    public final static Observable<ResponseBean> onLogin(String username,
                                                         String password,
                                                         String lifecycle){
        String url = getUrl("/security/login");
        AjaxParams params = getBaseParams();
        params.addParameters("username",username);
        params.addParameters("password",password);
        if (!TextUtils.isEmpty(lifecycle)) {
            params.addParameters("lifecycle", lifecycle);
        }
        return onPost(url,params);
    }

    public final static Observable<ResponseBean> onLogin(String username,
                                                         String password){
        return onLogin(username, password,null);
    }
}
