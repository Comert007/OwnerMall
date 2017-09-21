package com.yw.android.store.fragment.store;

import android.widget.TextView;

import com.ww.mvp.model.VoidModel;
import com.ww.mvp.view.VoidView;
import com.yw.android.store.R;
import com.yw.android.store.config.Constant;
import com.yw.android.store.fragment.base.BaseFragment;

import butterknife.BindView;

/**
 * Created by feng on 2017/9/20.
 * 商城其他界面
 */

public class StorePage2Fragment extends BaseFragment<VoidView,VoidModel> {
    @BindView(R.id.tv_page2)
    TextView tvPage2;

    @Override
    protected int getLayoutResId() {
        return R.layout.fragment_page2;
    }

    @Override
    protected void init() {

        String title = getArguments().getString(Constant.FRAGMENT_OTHER,"default");
        tvPage2.setText(title);
    }
}
