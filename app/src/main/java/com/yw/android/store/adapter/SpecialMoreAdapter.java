package com.yw.android.store.adapter;

import android.content.Context;
import android.support.annotation.Nullable;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.TextUtils;
import android.text.style.StrikethroughSpan;
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
 * 更多专场adapter
 */

public class SpecialMoreAdapter extends RvAdapter<SpuGoodsBean> {

    public final static int VIEW_NORMAL = 0;
    public final static int VIEW_GUESS = 1;

    public SpecialMoreAdapter(Context context) {
        super(context);
    }

    @Override
    protected int getItemLayoutResId(int viewType) {
        if (viewType == VIEW_NORMAL){
            return R.layout.item_special_more;
        }else {
            return R.layout.item_special_more_guess;
        }

    }

    @Override
    public int getItemViewType(int position) {
        SpuGoodsBean spuGoodsBean = getList().get(position);
        if (TextUtils.equals("0",spuGoodsBean.getGuessLike())){
            return VIEW_NORMAL;
        }else {
            return VIEW_GUESS;
        }
    }


    @Override
    protected RvViewHolder<SpuGoodsBean> getViewHolder(int i, View view) {

        if ( i== VIEW_NORMAL){
            return new SpecialMoreViewHolder(view);
        }else {
            return new GuessLikeViewHolder(view);
        }
    }



    class SpecialMoreViewHolder extends RvViewHolder<SpuGoodsBean>{
        @Nullable
        @BindView(R.id.iv_special_more)
        ImageView ivSpecialMore;
        @Nullable
        @BindView(R.id.tv_special_more)
        TextView tvSpecialMore;
        @Nullable
        @BindView(R.id.tv_special_more_regular)
        TextView tvSpecialMoreRegular;
        @Nullable
        @BindView(R.id.tv_special_more_selling)
        TextView tvSpecialMoreSelling;

        public SpecialMoreViewHolder(View itemView) {
            super(itemView);
        }

        @Override
        public void onBindData(int i, SpuGoodsBean spuGoodsBean) {
            ImageLoader.getInstance().displayImage(spuGoodsBean.getMainImagePath(),ivSpecialMore,
                    BaseApplication.getDisplayImageOptions(R.mipmap.ic_default));

            tvSpecialMore.setText(spuGoodsBean.getTitle());
            if (!TextUtils.isEmpty(spuGoodsBean.getRegularPrice())) {
                String regularPrice = "￥" + spuGoodsBean.getRegularPrice();
                SpannableString spa = new SpannableString(regularPrice);
                spa.setSpan(new StrikethroughSpan(), 0, regularPrice.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);

                tvSpecialMoreRegular.setText(spa);
            }

            if (!TextUtils.isEmpty(spuGoodsBean.getSellingPrice())) {
                tvSpecialMoreSelling.setText("￥" + spuGoodsBean.getSellingPrice());
            }
        }
    }

    class GuessLikeViewHolder extends RvViewHolder<SpuGoodsBean>{

        public GuessLikeViewHolder(View itemView) {
            super(itemView);
        }

        @Override
        public void onBindData(int i, SpuGoodsBean spuGoodsBean) {

        }
    }
}
