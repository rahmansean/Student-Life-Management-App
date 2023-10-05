package com.example.project22;

public class CgShow_model {

    String id,result,semester;

    public CgShow_model() {
    }

    public CgShow_model(String id,String result,String semester) {
        this.id=id;
        this.result = result;
        this.semester=semester;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public String getSemester() {
        return semester;
    }

    public void setSemester(String semester) {
        this.semester = semester;
    }
}
