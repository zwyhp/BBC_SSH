package com.ssh.bbc.util;

import java.util.List;

public class PageUtil {
    private int total;  /*当前页*/
    private int pageNum; /*总页数*/
    private int pageSize    ; /*页面大小*/
    private int allDataSize; /*总数据条数*/
    private List data;

    public PageUtil(int total, int pageSize, int allDataSize, List data) {
        this.total = total;
        this.pageSize = pageSize;
        this.allDataSize = allDataSize;
        this.data = data;
        this.pageNum = (int)allDataSize/pageSize +1;
    }


    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public int getPageNum() {
        return pageNum;
    }

    public void setPageNum(int pageNum) {
        this.pageNum = pageNum;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public int getAllDataSize() {
        return allDataSize;
    }

    public void setAllDataSize(int allDataSize) {
        this.allDataSize = allDataSize;
    }

    public List getData() {
        return data;
    }

    public void setData(List data) {
        this.data = data;
    }

}
