package com.example.project22;

import android.widget.ImageButton;

public class Task_Model {

    private String id,subject,date,time,title,details,count;

    public Task_Model() {
    }

    public Task_Model(String id, String subject, String date, String time, String title, String details, String count) {
        this.id = id;
        this.subject = subject;
        this.date = date;
        this.time = time;
        this.title = title;
        this.details = details;
        this.count = count;
    }

    public Task_Model(String subject, String date, String time, String title, String details) {
        this.subject = subject;
        this.date = date;
        this.time = time;
        this.title = title;
        this.details = details;
    }

    public Task_Model(String id, String subject, String date, String time, String title, String details) {
        this.id = id;
        this.subject = subject;
        this.date = date;
        this.time = time;
        this.title = title;
        this.details = details;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
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

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    public String getCount() {
        return count;
    }

    public void setCount(String count) {
        this.count = count;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
