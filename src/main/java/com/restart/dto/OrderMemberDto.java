package com.restart.dto;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.io.Serializable;


@JsonInclude(JsonInclude.Include.NON_NULL)
public class OrderMemberDto implements Serializable {

    //商户主键
    private Long id;

    private String token;

    //登录用户名
    private Long userName;

    //消费人数
    private Integer number;

    //消费金额
    private Double price;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public Long getUserName() {
        return userName;
    }

    public void setUserName(Long userName) {
        this.userName = userName;
    }

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }
}
