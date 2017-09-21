package com.yw.android.store.api;

import com.yw.android.store.bean.ResponseBean;
import com.yw.android.store.config.Constant;

/**
 * Created by feng on 2017/9/19.
 * 请求过程中异常
 */

public class ApiException extends RuntimeException {

    private String msg;
    private ResponseBean response;

    public ApiException(String msg) {
        super(msg);
        this.msg = msg;
    }

    public ApiException(ResponseBean response) {
        this.response = response;
    }

    public ApiException(Exception e) {
        super(e);
    }

    @Override
    public String getMessage() {
        if (response==null){
            return msg;
        }else {
            return response.getMessage();
        }
    }

    public int getCode(){
        if (response==null){
            return Constant.CODE_ERROR;
        }else {
            return response.getCode();
        }
    }

    public ResponseBean getResponse() {
        return response;
    }
}
