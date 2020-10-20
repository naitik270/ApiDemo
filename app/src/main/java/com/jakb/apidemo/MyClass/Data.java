package com.jakb.apidemo.MyClass;

import java.util.ArrayList;
import java.util.List;

public class Data {
    int total,page,pageRecords,pageSize;

    List<ImageList> list = new ArrayList<>();

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public int getPageRecords() {
        return pageRecords;
    }

    public void setPageRecords(int pageRecords) {
        this.pageRecords = pageRecords;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public List<ImageList> getLists() {
        return list;
    }

    public void setLists(List<ImageList> lists) {
        this.list = lists;
    }
}
