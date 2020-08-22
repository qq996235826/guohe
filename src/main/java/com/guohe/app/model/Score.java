package com.guohe.app.model;

public class Score {
    private Integer id;

    private String uid;

    private String startSemester;

    private String courseName;

    private String score;

    private String credit;

    private String examinationMethod;

    private String courseAttribute;

    private String alternativeCourseNumber;

    private String alternativeCourseName;

    private String markOfScore;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid == null ? null : uid.trim();
    }

    public String getStartSemester() {
        return startSemester;
    }

    public void setStartSemester(String startSemester) {
        this.startSemester = startSemester == null ? null : startSemester.trim();
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName == null ? null : courseName.trim();
    }

    public String getScore() {
        return score;
    }

    public void setScore(String score) {
        this.score = score == null ? null : score.trim();
    }

    public String getCredit() {
        return credit;
    }

    public void setCredit(String credit) {
        this.credit = credit == null ? null : credit.trim();
    }

    public String getExaminationMethod() {
        return examinationMethod;
    }

    public void setExaminationMethod(String examinationMethod) {
        this.examinationMethod = examinationMethod == null ? null : examinationMethod.trim();
    }

    public String getCourseAttribute() {
        return courseAttribute;
    }

    public void setCourseAttribute(String courseAttribute) {
        this.courseAttribute = courseAttribute == null ? null : courseAttribute.trim();
    }

    public String getAlternativeCourseNumber() {
        return alternativeCourseNumber;
    }

    public void setAlternativeCourseNumber(String alternativeCourseNumber) {
        this.alternativeCourseNumber = alternativeCourseNumber == null ? null : alternativeCourseNumber.trim();
    }

    public String getAlternativeCourseName() {
        return alternativeCourseName;
    }

    public void setAlternativeCourseName(String alternativeCourseName) {
        this.alternativeCourseName = alternativeCourseName == null ? null : alternativeCourseName.trim();
    }

    public String getMarkOfScore() {
        return markOfScore;
    }

    public void setMarkOfScore(String markOfScore) {
        this.markOfScore = markOfScore == null ? null : markOfScore.trim();
    }
}