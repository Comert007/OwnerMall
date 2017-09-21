package com.yw.android.store.bean.store;

/**
 * Created by feng on 2017/9/21.
 */

public class PaginationBean {


    /**
     * totalRow : 1
     * pageSize : 10
     * pageIndex : null
     * pageCount : 1
     */

    private String totalRow;
    private String pageSize;
    private int pageIndex;
    private int pageCount;

    public String getTotalRow() {
        return totalRow;
    }

    public void setTotalRow(String totalRow) {
        this.totalRow = totalRow;
    }

    public String getPageSize() {
        return pageSize;
    }

    public void setPageSize(String pageSize) {
        this.pageSize = pageSize;
    }

    public int getPageIndex() {
        return pageIndex;
    }

    public void setPageIndex(int pageIndex) {
        this.pageIndex = pageIndex;
    }

    public int getPageCount() {
        return pageCount;
    }

    public void setPageCount(int pageCount) {
        this.pageCount = pageCount;
    }

    @Override
    public String toString() {
        return "PaginationBean{" +
                "totalRow='" + totalRow + '\'' +
                ", pageSize='" + pageSize + '\'' +
                ", pageIndex=" + pageIndex +
                ", pageCount='" + pageCount + '\'' +
                '}';
    }
}
