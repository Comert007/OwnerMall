package com.yw.android.store.adapter;

import android.content.Context;
import android.view.View;
import android.widget.TextView;

import com.yw.android.store.R;
import com.yw.android.store.listener.OnItemClickListener;

import butterknife.BindView;
import ww.com.core.adapter.RvAdapter;
import ww.com.core.adapter.RvViewHolder;

/**
 * Created by feng on 2017/9/21.
 */

public class PopupAdapter extends RvAdapter<String> {

    private OnItemClickListener onItemClickListener;

    public PopupAdapter(Context context) {
        super(context);
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    @Override
    protected int getItemLayoutResId(int i) {
        return R.layout.item_popup;
    }

    @Override
    protected RvViewHolder<String> getViewHolder(int i, View view) {
        return new PopupViewHolder(view);
    }

    class PopupViewHolder extends RvViewHolder<String>{
        @BindView(R.id.tv_pop_show)
        TextView tvPopShow;

        public PopupViewHolder(final View itemView) {
            super(itemView);

        }

        @Override
        public void onBindData(int i, String s) {
            tvPopShow.setText(s);

            tvPopShow.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    if (onItemClickListener!=null){
                        onItemClickListener.onItemClick(position,itemView);
                    }
                }
            });
        }
    }
}
