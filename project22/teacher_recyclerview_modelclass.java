package com.example.project22;

public class teacher_recyclerview_modelclass {

    String tname,temail;
    String tphone;

    public teacher_recyclerview_modelclass(String tname, String temail,String  tphone) {
        this.tname = tname;
        this.temail = temail;
        this.tphone = tphone;
    }



    public teacher_recyclerview_modelclass() {

    }

    public String getTname() {
        return tname;
    }

    public String getTemail() {
        return temail;
    }

    public String getTphone() {
        return tphone;
    }
}


