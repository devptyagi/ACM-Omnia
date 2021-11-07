package com.acm.omnia.Model;

public class BlogsData {
    String title;
    String imgUrl;
    String blogLink;

    public BlogsData() {
    }


    public BlogsData(String title, String imgUrl, String blogLink) {
        this.title = title;
        this.imgUrl = imgUrl;
        this.blogLink = blogLink;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public String getBlogLink() {
        return blogLink;
    }

    public void setBlogLink(String blogLink) {
        this.blogLink = blogLink;
    }
}
