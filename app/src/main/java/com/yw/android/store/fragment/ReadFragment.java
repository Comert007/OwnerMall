package com.yw.android.store.fragment;

import com.ww.mvp.model.VoidModel;
import com.ww.mvp.view.VoidView;
import com.yw.android.store.R;
import com.yw.android.store.fragment.base.BaseFragment;

/**
 * Created by feng on 2017/9/19.
 */

public class ReadFragment extends BaseFragment<VoidView,VoidModel> {
    @Override
    protected int getLayoutResId() {
        return R.layout.fragment_read;
    }

    @Override
    protected void init() {

    }
}
