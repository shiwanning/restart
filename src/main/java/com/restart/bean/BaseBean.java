package com.restart.bean;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class BaseBean {
	@JsonIgnore
	private Page page;
	
	public BaseBean() {
	    this.page = new Page();
	}
	
	public Page getPage() {
		return page;
	}
	public void setPage(Page page) {
		this.page = page;
	}
}