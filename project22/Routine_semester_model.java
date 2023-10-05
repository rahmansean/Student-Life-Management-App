package com.example.project22;

public class Routine_semester_model {

    private String textDay;
    private String textSubject;
    private String textTeacher;
    private String textTime;


    public Routine_semester_model() {
    }

    public Routine_semester_model(String textDay, String textSubject, String textTeacher ,  String textTime) {
        this.textDay = textDay;
        this.textSubject = textSubject;
        this.textTeacher = textTeacher;
        this.textTime = textTime;
    }


    public String getTextDay() {
        return textDay;
    }

    public void setTextDay(String textDay) {
        this.textDay = textDay;
    }

    public String getTextSubject() {
        return textSubject;
    }

    public void setTextSubject(String textSubject) {
        this.textSubject = textSubject;
    }

    public String getTextTeacher() {
        return textTeacher;
    }

    public void setTextTeacher(String textTeacher) {
        this.textTeacher = textTeacher;
    }

    public String getTextTime() {
        return textTime;
    }

    public void setTextTime(String textTime) {
        this.textTime = textTime;
    }


}
