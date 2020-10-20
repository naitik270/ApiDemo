package com.jakb.apidemo.Classes;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ClsData {

    @SerializedName("total")
    @Expose
    private Integer total;
    @SerializedName("page")
    @Expose
    private Integer page;
    @SerializedName("pageRecords")
    @Expose
    private Integer pageRecords;
    @SerializedName("pageSize")
    @Expose
    private Integer pageSize;
    @SerializedName("list")
    @Expose
    private List<ClsList> list = null;

    public Integer getTotal() {
        return total;
    }

    public void setTotal(Integer total) {
        this.total = total;
    }

    public Integer getPage() {
        return page;
    }

    public void setPage(Integer page) {
        this.page = page;
    }

    public Integer getPageRecords() {
        return pageRecords;
    }

    public void setPageRecords(Integer pageRecords) {
        this.pageRecords = pageRecords;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public List<ClsList> getList() {
        return list;
    }

    public void setList(List<ClsList> list) {
        this.list = list;
    }
}
