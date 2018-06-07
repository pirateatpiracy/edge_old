package com.geekcap.informit.hbaseexample;

public class PageView
{

    private String userId;
    private String page;

    public PageView() {
    }

    public PageView(String userId, String page) {
        this.userId = userId;
        this.page = page;

    }
    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }
    public String getPage() {
        return page;
    }

    public void setPage(String page) {
        this.page = page;
    }

}