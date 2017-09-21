package com.yw.android.store.vm.models;

import android.content.Context;
import android.support.annotation.NonNull;

import com.alibaba.fastjson.JSON;
import com.trello.rxlifecycle.LifecycleTransformer;
import com.ww.mvp.model.IModel;
import com.yw.android.store.api.UserApi;
import com.yw.android.store.api.rx.HttpSubscriber;
import com.yw.android.store.bean.ResponseBean;
import com.yw.android.store.bean.user.UserBean;

import rx.functions.Func1;
import ww.com.http.rx.RxHelper;

/**
 * Created by feng on 2017/9/19.
 */

public class MainModel implements IModel {

    private Context context;

    @Override
    public void onAttach(@NonNull Context context) {
        this.context =context;
    }

    /**
     *
     * @param username 登录账户
     * @param password 登录密码
     * @param lifecycle Normal: 密码2小时后过期， Extended:7天过期
     */
    public void onLogin(String username,
                        String password,
                        String lifecycle,
                        LifecycleTransformer lifecycleTransformer,
                        HttpSubscriber<UserBean> httpSubscriber){

        UserApi.onLogin(username, password, lifecycle)
                .map(new Func1<ResponseBean, UserBean>() {
                    @Override
                    public UserBean call(ResponseBean responseBean) {
                        String data = responseBean.getData();
                        UserBean user = JSON.parseObject(data,UserBean.class);
                        return user;
                    }
                }).compose(RxHelper.cutMain())
                .compose(lifecycleTransformer)
                .subscribe(httpSubscriber);
    }
}
