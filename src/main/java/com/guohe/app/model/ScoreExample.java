package com.guohe.app.model;

import java.util.ArrayList;
import java.util.List;

public class ScoreExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public ScoreExample() {
        oredCriteria = new ArrayList<>();
    }

    public void setOrderByClause(String orderByClause) {
        this.orderByClause = orderByClause;
    }

    public String getOrderByClause() {
        return orderByClause;
    }

    public void setDistinct(boolean distinct) {
        this.distinct = distinct;
    }

    public boolean isDistinct() {
        return distinct;
    }

    public List<Criteria> getOredCriteria() {
        return oredCriteria;
    }

    public void or(Criteria criteria) {
        oredCriteria.add(criteria);
    }

    public Criteria or() {
        Criteria criteria = createCriteriaInternal();
        oredCriteria.add(criteria);
        return criteria;
    }

    public Criteria createCriteria() {
        Criteria criteria = createCriteriaInternal();
        if (oredCriteria.size() == 0) {
            oredCriteria.add(criteria);
        }
        return criteria;
    }

    protected Criteria createCriteriaInternal() {
        Criteria criteria = new Criteria();
        return criteria;
    }

    public void clear() {
        oredCriteria.clear();
        orderByClause = null;
        distinct = false;
    }

    protected abstract static class GeneratedCriteria {
        protected List<Criterion> criteria;

        protected GeneratedCriteria() {
            super();
            criteria = new ArrayList<>();
        }

        public boolean isValid() {
            return criteria.size() > 0;
        }

        public List<Criterion> getAllCriteria() {
            return criteria;
        }

        public List<Criterion> getCriteria() {
            return criteria;
        }

        protected void addCriterion(String condition) {
            if (condition == null) {
                throw new RuntimeException("Value for condition cannot be null");
            }
            criteria.add(new Criterion(condition));
        }

        protected void addCriterion(String condition, Object value, String property) {
            if (value == null) {
                throw new RuntimeException("Value for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value));
        }

        protected void addCriterion(String condition, Object value1, Object value2, String property) {
            if (value1 == null || value2 == null) {
                throw new RuntimeException("Between values for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value1, value2));
        }

        public Criteria andIdIsNull() {
            addCriterion("id is null");
            return (Criteria) this;
        }

        public Criteria andIdIsNotNull() {
            addCriterion("id is not null");
            return (Criteria) this;
        }

        public Criteria andIdEqualTo(Integer value) {
            addCriterion("id =", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotEqualTo(Integer value) {
            addCriterion("id <>", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThan(Integer value) {
            addCriterion("id >", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("id >=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThan(Integer value) {
            addCriterion("id <", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThanOrEqualTo(Integer value) {
            addCriterion("id <=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdIn(List<Integer> values) {
            addCriterion("id in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotIn(List<Integer> values) {
            addCriterion("id not in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdBetween(Integer value1, Integer value2) {
            addCriterion("id between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotBetween(Integer value1, Integer value2) {
            addCriterion("id not between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andUidIsNull() {
            addCriterion("uid is null");
            return (Criteria) this;
        }

        public Criteria andUidIsNotNull() {
            addCriterion("uid is not null");
            return (Criteria) this;
        }

        public Criteria andUidEqualTo(String value) {
            addCriterion("uid =", value, "uid");
            return (Criteria) this;
        }

        public Criteria andUidNotEqualTo(String value) {
            addCriterion("uid <>", value, "uid");
            return (Criteria) this;
        }

        public Criteria andUidGreaterThan(String value) {
            addCriterion("uid >", value, "uid");
            return (Criteria) this;
        }

        public Criteria andUidGreaterThanOrEqualTo(String value) {
            addCriterion("uid >=", value, "uid");
            return (Criteria) this;
        }

        public Criteria andUidLessThan(String value) {
            addCriterion("uid <", value, "uid");
            return (Criteria) this;
        }

        public Criteria andUidLessThanOrEqualTo(String value) {
            addCriterion("uid <=", value, "uid");
            return (Criteria) this;
        }

        public Criteria andUidLike(String value) {
            addCriterion("uid like", value, "uid");
            return (Criteria) this;
        }

        public Criteria andUidNotLike(String value) {
            addCriterion("uid not like", value, "uid");
            return (Criteria) this;
        }

        public Criteria andUidIn(List<String> values) {
            addCriterion("uid in", values, "uid");
            return (Criteria) this;
        }

        public Criteria andUidNotIn(List<String> values) {
            addCriterion("uid not in", values, "uid");
            return (Criteria) this;
        }

        public Criteria andUidBetween(String value1, String value2) {
            addCriterion("uid between", value1, value2, "uid");
            return (Criteria) this;
        }

        public Criteria andUidNotBetween(String value1, String value2) {
            addCriterion("uid not between", value1, value2, "uid");
            return (Criteria) this;
        }

        public Criteria andStartSemesterIsNull() {
            addCriterion("startSemester is null");
            return (Criteria) this;
        }

        public Criteria andStartSemesterIsNotNull() {
            addCriterion("startSemester is not null");
            return (Criteria) this;
        }

        public Criteria andStartSemesterEqualTo(String value) {
            addCriterion("startSemester =", value, "startSemester");
            return (Criteria) this;
        }

        public Criteria andStartSemesterNotEqualTo(String value) {
            addCriterion("startSemester <>", value, "startSemester");
            return (Criteria) this;
        }

        public Criteria andStartSemesterGreaterThan(String value) {
            addCriterion("startSemester >", value, "startSemester");
            return (Criteria) this;
        }

        public Criteria andStartSemesterGreaterThanOrEqualTo(String value) {
            addCriterion("startSemester >=", value, "startSemester");
            return (Criteria) this;
        }

        public Criteria andStartSemesterLessThan(String value) {
            addCriterion("startSemester <", value, "startSemester");
            return (Criteria) this;
        }

        public Criteria andStartSemesterLessThanOrEqualTo(String value) {
            addCriterion("startSemester <=", value, "startSemester");
            return (Criteria) this;
        }

        public Criteria andStartSemesterLike(String value) {
            addCriterion("startSemester like", value, "startSemester");
            return (Criteria) this;
        }

        public Criteria andStartSemesterNotLike(String value) {
            addCriterion("startSemester not like", value, "startSemester");
            return (Criteria) this;
        }

        public Criteria andStartSemesterIn(List<String> values) {
            addCriterion("startSemester in", values, "startSemester");
            return (Criteria) this;
        }

        public Criteria andStartSemesterNotIn(List<String> values) {
            addCriterion("startSemester not in", values, "startSemester");
            return (Criteria) this;
        }

        public Criteria andStartSemesterBetween(String value1, String value2) {
            addCriterion("startSemester between", value1, value2, "startSemester");
            return (Criteria) this;
        }

        public Criteria andStartSemesterNotBetween(String value1, String value2) {
            addCriterion("startSemester not between", value1, value2, "startSemester");
            return (Criteria) this;
        }

        public Criteria andCourseNameIsNull() {
            addCriterion("courseName is null");
            return (Criteria) this;
        }

        public Criteria andCourseNameIsNotNull() {
            addCriterion("courseName is not null");
            return (Criteria) this;
        }

        public Criteria andCourseNameEqualTo(String value) {
            addCriterion("courseName =", value, "courseName");
            return (Criteria) this;
        }

        public Criteria andCourseNameNotEqualTo(String value) {
            addCriterion("courseName <>", value, "courseName");
            return (Criteria) this;
        }

        public Criteria andCourseNameGreaterThan(String value) {
            addCriterion("courseName >", value, "courseName");
            return (Criteria) this;
        }

        public Criteria andCourseNameGreaterThanOrEqualTo(String value) {
            addCriterion("courseName >=", value, "courseName");
            return (Criteria) this;
        }

        public Criteria andCourseNameLessThan(String value) {
            addCriterion("courseName <", value, "courseName");
            return (Criteria) this;
        }

        public Criteria andCourseNameLessThanOrEqualTo(String value) {
            addCriterion("courseName <=", value, "courseName");
            return (Criteria) this;
        }

        public Criteria andCourseNameLike(String value) {
            addCriterion("courseName like", value, "courseName");
            return (Criteria) this;
        }

        public Criteria andCourseNameNotLike(String value) {
            addCriterion("courseName not like", value, "courseName");
            return (Criteria) this;
        }

        public Criteria andCourseNameIn(List<String> values) {
            addCriterion("courseName in", values, "courseName");
            return (Criteria) this;
        }

        public Criteria andCourseNameNotIn(List<String> values) {
            addCriterion("courseName not in", values, "courseName");
            return (Criteria) this;
        }

        public Criteria andCourseNameBetween(String value1, String value2) {
            addCriterion("courseName between", value1, value2, "courseName");
            return (Criteria) this;
        }

        public Criteria andCourseNameNotBetween(String value1, String value2) {
            addCriterion("courseName not between", value1, value2, "courseName");
            return (Criteria) this;
        }

        public Criteria andScoreIsNull() {
            addCriterion("score is null");
            return (Criteria) this;
        }

        public Criteria andScoreIsNotNull() {
            addCriterion("score is not null");
            return (Criteria) this;
        }

        public Criteria andScoreEqualTo(String value) {
            addCriterion("score =", value, "score");
            return (Criteria) this;
        }

        public Criteria andScoreNotEqualTo(String value) {
            addCriterion("score <>", value, "score");
            return (Criteria) this;
        }

        public Criteria andScoreGreaterThan(String value) {
            addCriterion("score >", value, "score");
            return (Criteria) this;
        }

        public Criteria andScoreGreaterThanOrEqualTo(String value) {
            addCriterion("score >=", value, "score");
            return (Criteria) this;
        }

        public Criteria andScoreLessThan(String value) {
            addCriterion("score <", value, "score");
            return (Criteria) this;
        }

        public Criteria andScoreLessThanOrEqualTo(String value) {
            addCriterion("score <=", value, "score");
            return (Criteria) this;
        }

        public Criteria andScoreLike(String value) {
            addCriterion("score like", value, "score");
            return (Criteria) this;
        }

        public Criteria andScoreNotLike(String value) {
            addCriterion("score not like", value, "score");
            return (Criteria) this;
        }

        public Criteria andScoreIn(List<String> values) {
            addCriterion("score in", values, "score");
            return (Criteria) this;
        }

        public Criteria andScoreNotIn(List<String> values) {
            addCriterion("score not in", values, "score");
            return (Criteria) this;
        }

        public Criteria andScoreBetween(String value1, String value2) {
            addCriterion("score between", value1, value2, "score");
            return (Criteria) this;
        }

        public Criteria andScoreNotBetween(String value1, String value2) {
            addCriterion("score not between", value1, value2, "score");
            return (Criteria) this;
        }

        public Criteria andCreditIsNull() {
            addCriterion("credit is null");
            return (Criteria) this;
        }

        public Criteria andCreditIsNotNull() {
            addCriterion("credit is not null");
            return (Criteria) this;
        }

        public Criteria andCreditEqualTo(String value) {
            addCriterion("credit =", value, "credit");
            return (Criteria) this;
        }

        public Criteria andCreditNotEqualTo(String value) {
            addCriterion("credit <>", value, "credit");
            return (Criteria) this;
        }

        public Criteria andCreditGreaterThan(String value) {
            addCriterion("credit >", value, "credit");
            return (Criteria) this;
        }

        public Criteria andCreditGreaterThanOrEqualTo(String value) {
            addCriterion("credit >=", value, "credit");
            return (Criteria) this;
        }

        public Criteria andCreditLessThan(String value) {
            addCriterion("credit <", value, "credit");
            return (Criteria) this;
        }

        public Criteria andCreditLessThanOrEqualTo(String value) {
            addCriterion("credit <=", value, "credit");
            return (Criteria) this;
        }

        public Criteria andCreditLike(String value) {
            addCriterion("credit like", value, "credit");
            return (Criteria) this;
        }

        public Criteria andCreditNotLike(String value) {
            addCriterion("credit not like", value, "credit");
            return (Criteria) this;
        }

        public Criteria andCreditIn(List<String> values) {
            addCriterion("credit in", values, "credit");
            return (Criteria) this;
        }

        public Criteria andCreditNotIn(List<String> values) {
            addCriterion("credit not in", values, "credit");
            return (Criteria) this;
        }

        public Criteria andCreditBetween(String value1, String value2) {
            addCriterion("credit between", value1, value2, "credit");
            return (Criteria) this;
        }

        public Criteria andCreditNotBetween(String value1, String value2) {
            addCriterion("credit not between", value1, value2, "credit");
            return (Criteria) this;
        }

        public Criteria andExaminationMethodIsNull() {
            addCriterion("examinationMethod is null");
            return (Criteria) this;
        }

        public Criteria andExaminationMethodIsNotNull() {
            addCriterion("examinationMethod is not null");
            return (Criteria) this;
        }

        public Criteria andExaminationMethodEqualTo(String value) {
            addCriterion("examinationMethod =", value, "examinationMethod");
            return (Criteria) this;
        }

        public Criteria andExaminationMethodNotEqualTo(String value) {
            addCriterion("examinationMethod <>", value, "examinationMethod");
            return (Criteria) this;
        }

        public Criteria andExaminationMethodGreaterThan(String value) {
            addCriterion("examinationMethod >", value, "examinationMethod");
            return (Criteria) this;
        }

        public Criteria andExaminationMethodGreaterThanOrEqualTo(String value) {
            addCriterion("examinationMethod >=", value, "examinationMethod");
            return (Criteria) this;
        }

        public Criteria andExaminationMethodLessThan(String value) {
            addCriterion("examinationMethod <", value, "examinationMethod");
            return (Criteria) this;
        }

        public Criteria andExaminationMethodLessThanOrEqualTo(String value) {
            addCriterion("examinationMethod <=", value, "examinationMethod");
            return (Criteria) this;
        }

        public Criteria andExaminationMethodLike(String value) {
            addCriterion("examinationMethod like", value, "examinationMethod");
            return (Criteria) this;
        }

        public Criteria andExaminationMethodNotLike(String value) {
            addCriterion("examinationMethod not like", value, "examinationMethod");
            return (Criteria) this;
        }

        public Criteria andExaminationMethodIn(List<String> values) {
            addCriterion("examinationMethod in", values, "examinationMethod");
            return (Criteria) this;
        }

        public Criteria andExaminationMethodNotIn(List<String> values) {
            addCriterion("examinationMethod not in", values, "examinationMethod");
            return (Criteria) this;
        }

        public Criteria andExaminationMethodBetween(String value1, String value2) {
            addCriterion("examinationMethod between", value1, value2, "examinationMethod");
            return (Criteria) this;
        }

        public Criteria andExaminationMethodNotBetween(String value1, String value2) {
            addCriterion("examinationMethod not between", value1, value2, "examinationMethod");
            return (Criteria) this;
        }

        public Criteria andCourseAttributeIsNull() {
            addCriterion("courseAttribute is null");
            return (Criteria) this;
        }

        public Criteria andCourseAttributeIsNotNull() {
            addCriterion("courseAttribute is not null");
            return (Criteria) this;
        }

        public Criteria andCourseAttributeEqualTo(String value) {
            addCriterion("courseAttribute =", value, "courseAttribute");
            return (Criteria) this;
        }

        public Criteria andCourseAttributeNotEqualTo(String value) {
            addCriterion("courseAttribute <>", value, "courseAttribute");
            return (Criteria) this;
        }

        public Criteria andCourseAttributeGreaterThan(String value) {
            addCriterion("courseAttribute >", value, "courseAttribute");
            return (Criteria) this;
        }

        public Criteria andCourseAttributeGreaterThanOrEqualTo(String value) {
            addCriterion("courseAttribute >=", value, "courseAttribute");
            return (Criteria) this;
        }

        public Criteria andCourseAttributeLessThan(String value) {
            addCriterion("courseAttribute <", value, "courseAttribute");
            return (Criteria) this;
        }

        public Criteria andCourseAttributeLessThanOrEqualTo(String value) {
            addCriterion("courseAttribute <=", value, "courseAttribute");
            return (Criteria) this;
        }

        public Criteria andCourseAttributeLike(String value) {
            addCriterion("courseAttribute like", value, "courseAttribute");
            return (Criteria) this;
        }

        public Criteria andCourseAttributeNotLike(String value) {
            addCriterion("courseAttribute not like", value, "courseAttribute");
            return (Criteria) this;
        }

        public Criteria andCourseAttributeIn(List<String> values) {
            addCriterion("courseAttribute in", values, "courseAttribute");
            return (Criteria) this;
        }

        public Criteria andCourseAttributeNotIn(List<String> values) {
            addCriterion("courseAttribute not in", values, "courseAttribute");
            return (Criteria) this;
        }

        public Criteria andCourseAttributeBetween(String value1, String value2) {
            addCriterion("courseAttribute between", value1, value2, "courseAttribute");
            return (Criteria) this;
        }

        public Criteria andCourseAttributeNotBetween(String value1, String value2) {
            addCriterion("courseAttribute not between", value1, value2, "courseAttribute");
            return (Criteria) this;
        }

        public Criteria andAlternativeCourseNumberIsNull() {
            addCriterion("alternativeCourseNumber is null");
            return (Criteria) this;
        }

        public Criteria andAlternativeCourseNumberIsNotNull() {
            addCriterion("alternativeCourseNumber is not null");
            return (Criteria) this;
        }

        public Criteria andAlternativeCourseNumberEqualTo(String value) {
            addCriterion("alternativeCourseNumber =", value, "alternativeCourseNumber");
            return (Criteria) this;
        }

        public Criteria andAlternativeCourseNumberNotEqualTo(String value) {
            addCriterion("alternativeCourseNumber <>", value, "alternativeCourseNumber");
            return (Criteria) this;
        }

        public Criteria andAlternativeCourseNumberGreaterThan(String value) {
            addCriterion("alternativeCourseNumber >", value, "alternativeCourseNumber");
            return (Criteria) this;
        }

        public Criteria andAlternativeCourseNumberGreaterThanOrEqualTo(String value) {
            addCriterion("alternativeCourseNumber >=", value, "alternativeCourseNumber");
            return (Criteria) this;
        }

        public Criteria andAlternativeCourseNumberLessThan(String value) {
            addCriterion("alternativeCourseNumber <", value, "alternativeCourseNumber");
            return (Criteria) this;
        }

        public Criteria andAlternativeCourseNumberLessThanOrEqualTo(String value) {
            addCriterion("alternativeCourseNumber <=", value, "alternativeCourseNumber");
            return (Criteria) this;
        }

        public Criteria andAlternativeCourseNumberLike(String value) {
            addCriterion("alternativeCourseNumber like", value, "alternativeCourseNumber");
            return (Criteria) this;
        }

        public Criteria andAlternativeCourseNumberNotLike(String value) {
            addCriterion("alternativeCourseNumber not like", value, "alternativeCourseNumber");
            return (Criteria) this;
        }

        public Criteria andAlternativeCourseNumberIn(List<String> values) {
            addCriterion("alternativeCourseNumber in", values, "alternativeCourseNumber");
            return (Criteria) this;
        }

        public Criteria andAlternativeCourseNumberNotIn(List<String> values) {
            addCriterion("alternativeCourseNumber not in", values, "alternativeCourseNumber");
            return (Criteria) this;
        }

        public Criteria andAlternativeCourseNumberBetween(String value1, String value2) {
            addCriterion("alternativeCourseNumber between", value1, value2, "alternativeCourseNumber");
            return (Criteria) this;
        }

        public Criteria andAlternativeCourseNumberNotBetween(String value1, String value2) {
            addCriterion("alternativeCourseNumber not between", value1, value2, "alternativeCourseNumber");
            return (Criteria) this;
        }

        public Criteria andAlternativeCourseNameIsNull() {
            addCriterion("alternativeCourseName is null");
            return (Criteria) this;
        }

        public Criteria andAlternativeCourseNameIsNotNull() {
            addCriterion("alternativeCourseName is not null");
            return (Criteria) this;
        }

        public Criteria andAlternativeCourseNameEqualTo(String value) {
            addCriterion("alternativeCourseName =", value, "alternativeCourseName");
            return (Criteria) this;
        }

        public Criteria andAlternativeCourseNameNotEqualTo(String value) {
            addCriterion("alternativeCourseName <>", value, "alternativeCourseName");
            return (Criteria) this;
        }

        public Criteria andAlternativeCourseNameGreaterThan(String value) {
            addCriterion("alternativeCourseName >", value, "alternativeCourseName");
            return (Criteria) this;
        }

        public Criteria andAlternativeCourseNameGreaterThanOrEqualTo(String value) {
            addCriterion("alternativeCourseName >=", value, "alternativeCourseName");
            return (Criteria) this;
        }

        public Criteria andAlternativeCourseNameLessThan(String value) {
            addCriterion("alternativeCourseName <", value, "alternativeCourseName");
            return (Criteria) this;
        }

        public Criteria andAlternativeCourseNameLessThanOrEqualTo(String value) {
            addCriterion("alternativeCourseName <=", value, "alternativeCourseName");
            return (Criteria) this;
        }

        public Criteria andAlternativeCourseNameLike(String value) {
            addCriterion("alternativeCourseName like", value, "alternativeCourseName");
            return (Criteria) this;
        }

        public Criteria andAlternativeCourseNameNotLike(String value) {
            addCriterion("alternativeCourseName not like", value, "alternativeCourseName");
            return (Criteria) this;
        }

        public Criteria andAlternativeCourseNameIn(List<String> values) {
            addCriterion("alternativeCourseName in", values, "alternativeCourseName");
            return (Criteria) this;
        }

        public Criteria andAlternativeCourseNameNotIn(List<String> values) {
            addCriterion("alternativeCourseName not in", values, "alternativeCourseName");
            return (Criteria) this;
        }

        public Criteria andAlternativeCourseNameBetween(String value1, String value2) {
            addCriterion("alternativeCourseName between", value1, value2, "alternativeCourseName");
            return (Criteria) this;
        }

        public Criteria andAlternativeCourseNameNotBetween(String value1, String value2) {
            addCriterion("alternativeCourseName not between", value1, value2, "alternativeCourseName");
            return (Criteria) this;
        }

        public Criteria andMarkOfScoreIsNull() {
            addCriterion("markOfScore is null");
            return (Criteria) this;
        }

        public Criteria andMarkOfScoreIsNotNull() {
            addCriterion("markOfScore is not null");
            return (Criteria) this;
        }

        public Criteria andMarkOfScoreEqualTo(String value) {
            addCriterion("markOfScore =", value, "markOfScore");
            return (Criteria) this;
        }

        public Criteria andMarkOfScoreNotEqualTo(String value) {
            addCriterion("markOfScore <>", value, "markOfScore");
            return (Criteria) this;
        }

        public Criteria andMarkOfScoreGreaterThan(String value) {
            addCriterion("markOfScore >", value, "markOfScore");
            return (Criteria) this;
        }

        public Criteria andMarkOfScoreGreaterThanOrEqualTo(String value) {
            addCriterion("markOfScore >=", value, "markOfScore");
            return (Criteria) this;
        }

        public Criteria andMarkOfScoreLessThan(String value) {
            addCriterion("markOfScore <", value, "markOfScore");
            return (Criteria) this;
        }

        public Criteria andMarkOfScoreLessThanOrEqualTo(String value) {
            addCriterion("markOfScore <=", value, "markOfScore");
            return (Criteria) this;
        }

        public Criteria andMarkOfScoreLike(String value) {
            addCriterion("markOfScore like", value, "markOfScore");
            return (Criteria) this;
        }

        public Criteria andMarkOfScoreNotLike(String value) {
            addCriterion("markOfScore not like", value, "markOfScore");
            return (Criteria) this;
        }

        public Criteria andMarkOfScoreIn(List<String> values) {
            addCriterion("markOfScore in", values, "markOfScore");
            return (Criteria) this;
        }

        public Criteria andMarkOfScoreNotIn(List<String> values) {
            addCriterion("markOfScore not in", values, "markOfScore");
            return (Criteria) this;
        }

        public Criteria andMarkOfScoreBetween(String value1, String value2) {
            addCriterion("markOfScore between", value1, value2, "markOfScore");
            return (Criteria) this;
        }

        public Criteria andMarkOfScoreNotBetween(String value1, String value2) {
            addCriterion("markOfScore not between", value1, value2, "markOfScore");
            return (Criteria) this;
        }
    }

    public static class Criteria extends GeneratedCriteria {
        protected Criteria() {
            super();
        }
    }

    public static class Criterion {
        private String condition;

        private Object value;

        private Object secondValue;

        private boolean noValue;

        private boolean singleValue;

        private boolean betweenValue;

        private boolean listValue;

        private String typeHandler;

        public String getCondition() {
            return condition;
        }

        public Object getValue() {
            return value;
        }

        public Object getSecondValue() {
            return secondValue;
        }

        public boolean isNoValue() {
            return noValue;
        }

        public boolean isSingleValue() {
            return singleValue;
        }

        public boolean isBetweenValue() {
            return betweenValue;
        }

        public boolean isListValue() {
            return listValue;
        }

        public String getTypeHandler() {
            return typeHandler;
        }

        protected Criterion(String condition) {
            super();
            this.condition = condition;
            this.typeHandler = null;
            this.noValue = true;
        }

        protected Criterion(String condition, Object value, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.typeHandler = typeHandler;
            if (value instanceof List<?>) {
                this.listValue = true;
            } else {
                this.singleValue = true;
            }
        }

        protected Criterion(String condition, Object value) {
            this(condition, value, null);
        }

        protected Criterion(String condition, Object value, Object secondValue, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.secondValue = secondValue;
            this.typeHandler = typeHandler;
            this.betweenValue = true;
        }

        protected Criterion(String condition, Object value, Object secondValue) {
            this(condition, value, secondValue, null);
        }
    }
}