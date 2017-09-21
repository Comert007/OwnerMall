package com.yw.android.store.api.convert;

import com.alibaba.fastjson.JSONObject;
import com.yw.android.store.api.ApiException;
import com.yw.android.store.bean.ResponseBean;
import com.yw.android.store.config.Constant;

import rx.functions.Func1;

/**
 * Created by 10142 on 2016/10/10.
 */
public class ResponseFunc implements Func1<String, ResponseBean> {
    @Override
    public ResponseBean call(String string) {
        JSONObject jsonObject = JSONObject.parseObject(string);
        ResponseBean bean = ResponseBean.parseObject(jsonObject);
        if (bean != null) {
            if (Constant.STATUS_OK.equals(bean.getState()))
                return bean;
            else {
                throw new ApiException(bean);
            }
        } else {
            throw new ApiException("check network");
        }

    }
}