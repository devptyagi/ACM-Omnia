package com.acm.omnia.Model;

public class EventsData {
    String title;
    String date;
    String time;
    String month;


    public EventsData() {
    }

    public EventsData(String title, String date, String time, String month) {
        this.title = title;
        this.date = date;
        this.time = time;
        this.month = month;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getMonth() {
        return month;
    }

    public void setMonth(String month) {
        this.month = month;
    }
}
