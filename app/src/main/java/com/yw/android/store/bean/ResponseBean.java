package com.yw.android.store.bean;

import android.text.TextUtils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.yw.android.store.config.Constant;

/**
 * Created by feng on 2017/9/19.
 * 接口返回结构
 */

public class ResponseBean {

    private String state; //请求结果状态
    private int code; //状态码
    private String message; //提示
    private String data; //数据
    private String pagination; // 记录

    public static ResponseBean parseObject(JSONObject json) {
        ResponseBean bean = new ResponseBean();

        try {
            bean.setState(json.getString("state"));
        } catch (Exception e) {
            bean.setState(Constant.STATUS_ERROR);
            bean.setMessage("数据解析错误!");
        }

        try {
            bean.setCode(json.getInteger("code"));
        } catch (Exception e) {
            bean.setCode(Constant.CODE_ERROR);
        }


        try {
            String msg = json.getString("message");
            if (!TextUtils.isEmpty(msg)) {
                bean.setMessage(msg);
            }
        } catch (Exception e) {
            bean.setState(Constant.STATUS_ERROR);
            bean.setMessage("数据解析错误!");
        }


        try {
            String pagination = json.getString("pagination");
            if (!TextUtils.isEmpty(pagination)){
                bean.setPagination(pagination);
            }
        }catch (Exception e){
            bean.setState(Constant.STATUS_ERROR);
            bean.setMessage("数据解析错误!");
        }

        try {
            String data = json.getString("data");
            if (!TextUtils.isEmpty(data)) {
                bean.setData(data);
            }

        } catch (Exception e) {
            bean.setState(Constant.STATUS_ERROR);
            bean.setMessage("数据解析错误!");
        }

        return bean;
    }


    public JSONObject toJsonObject() {
        if (TextUtils.isEmpty(data)) {
            return null;
        }

        return JSONObject.parseObject(data);
    }

    public JSONArray toJsonArray() {
        if (TextUtils.isEmpty(data)) {
            return null;
        }

        return JSON.parseArray(data);
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getPagination() {
        return pagination;
    }

    public void setPagination(String pagination) {
        this.pagination = pagination;
    }

    @Override
    public String toString() {
        return "ResponseBean{" +
                "state='" + state + '\'' +
                ", code=" + code +
                ", message='" + message + '\'' +
                ", data='" + data + '\'' +
                ", pagination='" + pagination + '\'' +
                '}';
    }
}
