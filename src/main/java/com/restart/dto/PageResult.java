package com.restart.dto;

import com.restart.bean.Page;

import java.util.Collection;

public class PageResult<T> {

    private Collection<T> result;

    private Page page;

    public Collection<T> getResult() {
        return result;
    }

    public void setResult(Collection<T> result) {
        this.result = result;
    }

    public Page getPage() {
        return page;
    }

    public void setPage(Page page) {
        this.page = page;
    }

    public PageResult(Collection<T> result, Page page) {
        this.result = result;
        this.page = page;
    }

    public PageResult() {
    }
}
