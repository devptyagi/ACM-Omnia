package com.acm.omnia.Model;

public class PastEvent {
    String eventTitle;
    String imgUrl;

    public PastEvent() {
    }

    public PastEvent(String eventTitle, String imgUrl) {
        this.eventTitle = eventTitle;
        this.imgUrl = imgUrl;
    }

    public String getEventTitle() {
        return eventTitle;
    }

    public void setEventTitle(String eventTitle) {
        this.eventTitle = eventTitle;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }
}
