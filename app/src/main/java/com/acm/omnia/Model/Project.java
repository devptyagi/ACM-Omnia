package com.acm.omnia.Model;

public class Project {
    String title;
    String imgUrl;

    public Project() {
    }

    public Project(String title, String url) {
        this.title = title;
        this.imgUrl = url;
    }

    public String getTitle() {
        return title;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }
}
