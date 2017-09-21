package com.yw.android.store.adapter;

import android.content.Context;
import android.graphics.Color;
import android.view.View;
import android.widget.TextView;

import com.yw.android.store.R;
import com.yw.android.store.bean.store.StoreNavEntity;
import com.yw.android.store.listener.OnItemClickListener;

import butterknife.BindView;
import ww.com.core.Debug;
import ww.com.core.adapter.RvAdapter;
import ww.com.core.adapter.RvViewHolder;

/**
 * Created by feng on 2017/9/20.
 */

public class NavBottomAdapter extends RvAdapter<StoreNavEntity> {


    private OnItemClickListener onItemClickListener;

    public NavBottomAdapter(Context context) {
        super(context);
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    @Override
    protected int getItemLayoutResId(int i) {
        return R.layout.item_nav_bottom;
    }

    @Override
    protected RvViewHolder<StoreNavEntity> getViewHolder(int i, View view) {
        return new NavBottomViewHolder(view);
    }

    class NavBottomViewHolder extends RvViewHolder<StoreNavEntity>{
        @BindView(R.id.tv_bottom_title)
        TextView tvBottomTitle;

        public NavBottomViewHolder(View itemView) {
            super(itemView);
        }

        @Override
        public void onBindData( int index, final StoreNavEntity storeNavEntity) {

            checkIsFirst(index,storeNavEntity);
            //设置点击效果
            if (storeNavEntity.isClick()){
                tvBottomTitle.setTextColor(Color.parseColor("#b4282d"));
            }else {
                tvBottomTitle.setTextColor(Color.parseColor("#333333"));
            }

            tvBottomTitle.setText(storeNavEntity.getTitle());

            tvBottomTitle.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    for (StoreNavEntity navEntity : getList()) {
                        navEntity.setClick(false);
                    }
                    storeNavEntity.setClick(true);

                    Debug.d(getList().toString());
                    notifyDataSetChanged();

                    if (onItemClickListener!=null){
                        onItemClickListener.onItemClick(position,v);
                    }
                }
            });
        }


        /**
         * 检测是否是第一个title，如果是，那么就标红
         * @param index
         * @param storeNavEntity
         */
        private void checkIsFirst(int index, StoreNavEntity storeNavEntity){
            if (index==0){
                boolean flag =false;
                for (StoreNavEntity navEntity : getList()) {
                    if (navEntity.isClick()){
                        flag = true;
                    }
                }

                if (!flag){
                    storeNavEntity.setClick(true);
                }
            }
        }
    }


}
