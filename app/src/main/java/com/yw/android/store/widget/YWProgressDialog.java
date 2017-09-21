package com.yw.android.store.widget;


import android.app.Dialog;
import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.yw.android.store.R;

import butterknife.ButterKnife;
import ww.com.core.ScreenUtil;

/**
 * Created by feng on 2017/9/19.
 * 请求dialog弹框
 */

public class YWProgressDialog extends Dialog {

    private View rootView;
    private TextView tvMessage;


    public YWProgressDialog(@NonNull Context context) {
        super(context, R.style.CustomerDialogStyle);

        rootView = LayoutInflater.from(context)
                .inflate(R.layout.view_progress_dialog, null);
        ScreenUtil.scale(rootView);
        tvMessage = ButterKnife.findById(rootView, R.id.tv_message);

        setContentView(rootView);
    }

    public void setMessage(CharSequence charSequence) {
        tvMessage.setText(charSequence);
    }

}
