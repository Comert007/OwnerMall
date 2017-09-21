package com.yw.android.store.bean.store;

import java.util.List;

/**
 * Created by feng on 2017/9/21.
 */

public class SpuGoodsAllBean {

    private PaginationBean pagination;
    private List<SpuGoodsBean> spuGoodsBeen;

    public PaginationBean getPagination() {
        return pagination;
    }

    public void setPagination(PaginationBean pagination) {
        this.pagination = pagination;
    }

    public List<SpuGoodsBean> getSpuGoodsBeen() {
        return spuGoodsBeen;
    }

    public void setSpuGoodsBeen(List<SpuGoodsBean> spuGoodsBeen) {
        this.spuGoodsBeen = spuGoodsBeen;
    }
}
