package com.yw.android.store.bean.store;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by feng on 2017/9/20.
 */

public class PositionGroupsBean implements Serializable{
    private ArrayList<StoreNavEntity> Top;
    private ArrayList<StoreNavEntity> Middle;
    private ArrayList<StoreNavEntity> Bottom;

    public ArrayList<StoreNavEntity> getMiddle() {
        return Middle;
    }

    public void setMiddle(ArrayList<StoreNavEntity> middle) {
        Middle = middle;
    }

    public ArrayList<StoreNavEntity> getBottom() {
        return Bottom;
    }

    public void setBottom(ArrayList<StoreNavEntity> bottom) {
        Bottom = bottom;
    }

    public ArrayList<StoreNavEntity> getTop() {
        return Top;
    }

    public void setTop(ArrayList<StoreNavEntity> top) {
        Top = top;
    }


    @Override
    public String toString() {
        return "PositionGroupsBean{" +
                "Top=" + Top +
                ", Middle=" + Middle +
                ", Bottom=" + Bottom +
                '}';
    }
}
