package com.pojo;

public class Environment {

    private String url;
    private int MAX_COUNT_ATTEMPT;

    public int getMAX_COUNT() {
        return MAX_COUNT_ATTEMPT;
    }

    public void setMAX_COUNT(int MAX_COUNT_ATTEMPT) {
        this.MAX_COUNT_ATTEMPT = MAX_COUNT_ATTEMPT;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

}
