package com.yw.android.store.api.rx;

import android.content.Context;
import android.content.DialogInterface;
import android.os.Looper;

import com.yw.android.store.api.ApiException;
import com.yw.android.store.bean.ResponseBean;
import com.yw.android.store.utils.DialogUtil;
import com.yw.android.store.utils.ToastUtil;
import com.yw.android.store.widget.YWProgressDialog;

import rx.Subscriber;

/**
 * Created by feng on 2017/9/19.
 * 请求rx回调
 */

public abstract class HttpSubscriber<T> extends Subscriber<T> {
    private Context context;
    //弹框
    YWProgressDialog dialog;

    private boolean showDialog;

    public HttpSubscriber(Context context, boolean showDialog) {
        this.context = context;
        this.showDialog = showDialog;
        initDialog();
    }

    private void initDialog() {
        dialog = DialogUtil.obtainProgressDialog(context);
        dialog.setCancelable(false);
        dialog.setMessage("loading...");
        dialog.setCanceledOnTouchOutside(false);
        dialog.setOnCancelListener(new DialogInterface.OnCancelListener() {
            @Override
            public void onCancel(DialogInterface dialog) {
                if (!isUnsubscribed()) {
                    unsubscribe();
                    onCancelRequest();
                }
            }
        });
    }

    private void showDialog() {
        dialog.show();
    }

    private void dismissDialog() {
        dialog.dismiss();
    }

    @Override
    public void onStart() {
        super.onStart();
        if (showDialog) {
            showDialog();
        }
    }

    @Override
    public void onCompleted() {
        onEnd();
    }

    @Override
    public void onError(Throwable e) {
        e.printStackTrace();
        try {
            if (Looper.getMainLooper() != Looper.myLooper()) {
                // 不是主线程不做处理.
                return;
            }
            onEnd();
            onFail(e);
        } catch (Exception e1) {
            e1.printStackTrace();
        }
    }

    public void onFail(Throwable e) {
        if (e instanceof ApiException) {
            ResponseBean responseBean = ((ApiException) e).getResponse();
            if (responseBean!=null){
                ToastUtil.showToast(responseBean.getMessage());
            }else {
                ToastUtil.showToast(e.getMessage());
            }
        } else {
            ToastUtil.showToast(e.getMessage());
        }
    }
//
//    public void onFail(ResponseBean responseBean) {
//        ToastUtil.showToast(responseBean.getMessage());
//    }

    public void onEnd() {
        dismissDialog();
    }

    /**
     * 取消请求
     */
    protected void onCancelRequest() {
        dismissDialog();
    }

    /**
     * 刷新请求
     */
    protected void onRefreshRequest() {
        dismissDialog();
    }
}
