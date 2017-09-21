package com.yw.android.store.api.convert;


import com.yw.android.store.api.ApiException;

import java.io.IOException;

import okhttp3.ResponseBody;
import rx.functions.Func1;

/**
 * Created by 10142 on 2016/10/10.
 */
public class StringFunc implements Func1<ResponseBody, String> {
    @Override
    public String call(ResponseBody responseBody) {
        if (responseBody != null) {
            try {
                return responseBody.string();
            } catch (IOException e) {
                e.printStackTrace();
                throw new ApiException("app error");
            }
        } else {
            throw new ApiException("check network");
        }
    }
}
