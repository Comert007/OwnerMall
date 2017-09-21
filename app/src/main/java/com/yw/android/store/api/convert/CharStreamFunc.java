package com.yw.android.store.api.convert;


import com.yw.android.store.api.ApiException;

import java.io.Reader;

import okhttp3.ResponseBody;
import rx.functions.Func1;

/**
 * Created by 10142 on 2016/10/10.
 */
public class CharStreamFunc implements Func1<ResponseBody, Reader> {
    @Override
    public Reader call(ResponseBody responseBody) {
        if (responseBody != null)
            return responseBody.charStream();
        else
            throw new ApiException("check network");
    }
}