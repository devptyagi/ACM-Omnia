package com.acm.omnia.Model;

public class Event {
    String title;
    String time;
    String date;
    String month;

    public Event() {
    }

    public Event(String title, String time, String date, String month) {
        this.title = title;
        this.time = time;
        this.date = date;
        this.month = month;
    }

    public String getTitle() {
        return title;
    }

    public String getTime() {
        return time;
    }

    public String getDate() {
        return date;
    }

    public String getMonth() {
        return month;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public void setMonth(String month) {
        this.month = month;
    }

}
