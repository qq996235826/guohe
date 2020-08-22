package com.guohe.app.model;

public class Gpa {
    private String uid;

    private String semester1;

    private String semester2;

    private String semester3;

    private String semester4;

    private String semester5;

    private String semester6;

    private String semester7;

    private String semester8;

    private String average;

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid == null ? null : uid.trim();
    }

    public String getSemester1() {
        return semester1;
    }

    public void setSemester1(String semester1) {
        this.semester1 = semester1 == null ? null : semester1.trim();
    }

    public String getSemester2() {
        return semester2;
    }

    public void setSemester2(String semester2) {
        this.semester2 = semester2 == null ? null : semester2.trim();
    }

    public String getSemester3() {
        return semester3;
    }

    public void setSemester3(String semester3) {
        this.semester3 = semester3 == null ? null : semester3.trim();
    }

    public String getSemester4() {
        return semester4;
    }

    public void setSemester4(String semester4) {
        this.semester4 = semester4 == null ? null : semester4.trim();
    }

    public String getSemester5() {
        return semester5;
    }

    public void setSemester5(String semester5) {
        this.semester5 = semester5 == null ? null : semester5.trim();
    }

    public String getSemester6() {
        return semester6;
    }

    public void setSemester6(String semester6) {
        this.semester6 = semester6 == null ? null : semester6.trim();
    }

    public String getSemester7() {
        return semester7;
    }

    public void setSemester7(String semester7) {
        this.semester7 = semester7 == null ? null : semester7.trim();
    }

    public String getSemester8() {
        return semester8;
    }

    public void setSemester8(String semester8) {
        this.semester8 = semester8 == null ? null : semester8.trim();
    }

    public String getAverage() {
        return average;
    }

    public void setAverage(String average) {
        this.average = average == null ? null : average.trim();
    }
}