package com.yw.android.store.activity;

import android.support.v4.app.Fragment;
import android.view.View;

import com.ww.mvp.view.VoidView;
import com.yw.android.store.R;
import com.yw.android.store.activity.base.BaseActivity;
import com.yw.android.store.fragment.ReadFragment;
import com.yw.android.store.fragment.StoreFragment;
import com.yw.android.store.fragment.UserFragment;
import com.yw.android.store.vm.models.MainModel;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindViews;
import ww.com.core.adapter.MenuTabAdapter;

public class MainActivity extends BaseActivity<VoidView,MainModel> {
    @BindViews({R.id.tab_read_layout,R.id.tab_store_layout,R.id.tab_mine_layout})
    List<View> menus;
    @BindViews({R.id.tab_read_image,R.id.tab_store_image,R.id.tab_mine_image})
    List<View> images;
    @BindViews({R.id.tab_read_text,R.id.tab_store_text,R.id.tab_mine_text})
    List<View> texts;

    private MenuTabAdapter adapter;
    private List<Fragment> fragments;

    @Override
    protected int getLayoutResId() {
        return R.layout.activity_main;
    }

    @Override
    protected void init() {
        fragments = new ArrayList<>();
        fragments.add(new ReadFragment());
        fragments.add(new StoreFragment());
        fragments.add(new UserFragment());

        adapter = new MenuTabAdapter(this,menus,fragments, R.id.main_content);
        adapter.setIsAnimation(true);
        adapter.setOnMenuClickListener(new MenuTabAdapter.OnMenuClickListener() {
            @Override
            public void onDoubleClick(View view) {

            }

            @Override
            public void onClick(View v) {
                switch (v.getId()) {
                    case R.id.tab_read_layout:
                        adapter.changeMenuStatus(0);
                        changeMenuStatus(0);
                        adapter.changeMenu(0);
                        break;
                    case R.id.tab_store_layout:
                        adapter.changeMenuStatus(1);
                        changeMenuStatus(1);
                        adapter.changeMenu(1);
                        break;
                    case R.id.tab_mine_layout:
                        adapter.changeMenuStatus(2);
                        changeMenuStatus(2);
                        adapter.changeMenu(2);
                        break;
                }
            }
        });


        adapter.changeMenuStatus(0);
        changeMenuStatus(0);
        adapter.changeMenu(0);
    }


    public void changeMenuStatus(int index) {
        int imageSize = images.size();
        for (int i = 0; i < imageSize; i++) {
            if (i == index) {
                this.images.get(i).setSelected(true);
                this.texts.get(i).setSelected(true);
            } else {
                this.images.get(i).setSelected(false);
                this.texts.get(i).setSelected(false);
            }
        }
    }
}
