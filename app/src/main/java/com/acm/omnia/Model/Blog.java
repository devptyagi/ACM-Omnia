package com.acm.omnia.Model;

public class Blog {
    String title;
    String imgUrl;
    String blogLink;

    public Blog() {
    }

    public Blog(String title, String url, String blogLink) {
        this.title = title;
        this.imgUrl = url;
        this.blogLink = blogLink;
    }

    public String getBlogLink() {
        return blogLink;
    }

    public void setBlogLink(String blogLink) {
        this.blogLink = blogLink;
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
