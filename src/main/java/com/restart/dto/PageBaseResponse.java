package com.restart.dto;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class PageBaseResponse<T> extends BaseResponse {

    private PageResult<T> pageResult;

    private Boolean hasMore;

    public PageResult<T> getPageResult() {
        return pageResult;
    }

    public void setPageResult(PageResult<T> pageResult) {
        this.pageResult = pageResult;
    }

    public PageBaseResponse() {
    }
}
