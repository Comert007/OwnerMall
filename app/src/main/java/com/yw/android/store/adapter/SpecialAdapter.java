package com.yw.android.store.adapter;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.nostra13.universalimageloader.core.ImageLoader;
import com.yw.android.store.BaseApplication;
import com.yw.android.store.R;
import com.yw.android.store.bean.store.SpuGoodsBean;

import butterknife.BindView;
import ww.com.core.adapter.RvAdapter;
import ww.com.core.adapter.RvViewHolder;

/**
 * Created by feng on 2017/9/20.
 * 专场adapter
 */

public class SpecialAdapter extends RvAdapter<SpuGoodsBean> {

    public SpecialAdapter(Context context) {
        super(context);
    }

    @Override
    protected int getItemLayoutResId(int i) {
        return R.layout.item_special;
    }

    @Override
    protected RvViewHolder<SpuGoodsBean> getViewHolder(int i, View view) {
        return new SpecialViewHolder(view);
    }

    class SpecialViewHolder extends RvViewHolder<SpuGoodsBean>{
        @BindView(R.id.iv_special)
        ImageView ivSpecial;
        @BindView(R.id.tv_intro)
        TextView tvIntro;
        @BindView(R.id.tv_special_price)
        TextView tvSpecialPrice;

        public SpecialViewHolder(View itemView) {
            super(itemView);
        }

        @Override
        public void onBindData(int i, SpuGoodsBean spuGoodsBean) {
            ImageLoader.getInstance().displayImage(spuGoodsBean.getMainImagePath(),ivSpecial,
                    BaseApplication.getDisplayImageOptions(R.mipmap.ic_default));
            tvIntro.setText(spuGoodsBean.getTitle());
            tvSpecialPrice.setText("￥"+spuGoodsBean.getSellingPrice());
        }
    }
}
