package com.yw.android.store.api.convert;


import com.yw.android.store.api.ApiException;

import java.io.InputStream;

import okhttp3.ResponseBody;
import rx.functions.Func1;

/**
 * Created by 10142 on 2016/10/10.
 */
public class BytesStreamFunc implements Func1<ResponseBody, InputStream> {
    @Override
    public InputStream call(ResponseBody responseBody) {
        if (responseBody != null)
            return responseBody.byteStream();
        else
            throw new ApiException("check network");
    }
}