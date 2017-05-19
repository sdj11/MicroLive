package com.lizhi.microlive.model;

/**
 * Created by Administrator on 2017/5/13.
 */

public class FunctionItem {

    private int type;  //1、网页资源   2、apk资源   3、activity资源
    private String toIdentity;  //资源名称

    private String imgUrl;
    private String title;
    private String subtitle;

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSubtitle() {
        return subtitle;
    }

    public void setSubtitle(String subtitle) {
        this.subtitle = subtitle;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getToIdentity() {
        return toIdentity;
    }

    public void setToIdentity(String toIdentity) {
        this.toIdentity = toIdentity;
    }
}
