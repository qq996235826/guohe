package com.guohe.app.model;

import java.util.ArrayList;
import java.util.List;

public class SignDataExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public SignDataExample() {
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

        public Criteria andIdEqualTo(Long value) {
            addCriterion("id =", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotEqualTo(Long value) {
            addCriterion("id <>", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThan(Long value) {
            addCriterion("id >", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThanOrEqualTo(Long value) {
            addCriterion("id >=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThan(Long value) {
            addCriterion("id <", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThanOrEqualTo(Long value) {
            addCriterion("id <=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdIn(List<Long> values) {
            addCriterion("id in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotIn(List<Long> values) {
            addCriterion("id not in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdBetween(Long value1, Long value2) {
            addCriterion("id between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotBetween(Long value1, Long value2) {
            addCriterion("id not between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andSignIdIsNull() {
            addCriterion("signId is null");
            return (Criteria) this;
        }

        public Criteria andSignIdIsNotNull() {
            addCriterion("signId is not null");
            return (Criteria) this;
        }

        public Criteria andSignIdEqualTo(Integer value) {
            addCriterion("signId =", value, "signId");
            return (Criteria) this;
        }

        public Criteria andSignIdNotEqualTo(Integer value) {
            addCriterion("signId <>", value, "signId");
            return (Criteria) this;
        }

        public Criteria andSignIdGreaterThan(Integer value) {
            addCriterion("signId >", value, "signId");
            return (Criteria) this;
        }

        public Criteria andSignIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("signId >=", value, "signId");
            return (Criteria) this;
        }

        public Criteria andSignIdLessThan(Integer value) {
            addCriterion("signId <", value, "signId");
            return (Criteria) this;
        }

        public Criteria andSignIdLessThanOrEqualTo(Integer value) {
            addCriterion("signId <=", value, "signId");
            return (Criteria) this;
        }

        public Criteria andSignIdIn(List<Integer> values) {
            addCriterion("signId in", values, "signId");
            return (Criteria) this;
        }

        public Criteria andSignIdNotIn(List<Integer> values) {
            addCriterion("signId not in", values, "signId");
            return (Criteria) this;
        }

        public Criteria andSignIdBetween(Integer value1, Integer value2) {
            addCriterion("signId between", value1, value2, "signId");
            return (Criteria) this;
        }

        public Criteria andSignIdNotBetween(Integer value1, Integer value2) {
            addCriterion("signId not between", value1, value2, "signId");
            return (Criteria) this;
        }

        public Criteria andSignTimeIsNull() {
            addCriterion("signTime is null");
            return (Criteria) this;
        }

        public Criteria andSignTimeIsNotNull() {
            addCriterion("signTime is not null");
            return (Criteria) this;
        }

        public Criteria andSignTimeEqualTo(Long value) {
            addCriterion("signTime =", value, "signTime");
            return (Criteria) this;
        }

        public Criteria andSignTimeNotEqualTo(Long value) {
            addCriterion("signTime <>", value, "signTime");
            return (Criteria) this;
        }

        public Criteria andSignTimeGreaterThan(Long value) {
            addCriterion("signTime >", value, "signTime");
            return (Criteria) this;
        }

        public Criteria andSignTimeGreaterThanOrEqualTo(Long value) {
            addCriterion("signTime >=", value, "signTime");
            return (Criteria) this;
        }

        public Criteria andSignTimeLessThan(Long value) {
            addCriterion("signTime <", value, "signTime");
            return (Criteria) this;
        }

        public Criteria andSignTimeLessThanOrEqualTo(Long value) {
            addCriterion("signTime <=", value, "signTime");
            return (Criteria) this;
        }

        public Criteria andSignTimeIn(List<Long> values) {
            addCriterion("signTime in", values, "signTime");
            return (Criteria) this;
        }

        public Criteria andSignTimeNotIn(List<Long> values) {
            addCriterion("signTime not in", values, "signTime");
            return (Criteria) this;
        }

        public Criteria andSignTimeBetween(Long value1, Long value2) {
            addCriterion("signTime between", value1, value2, "signTime");
            return (Criteria) this;
        }

        public Criteria andSignTimeNotBetween(Long value1, Long value2) {
            addCriterion("signTime not between", value1, value2, "signTime");
            return (Criteria) this;
        }

        public Criteria andSignedIsNull() {
            addCriterion("signed is null");
            return (Criteria) this;
        }

        public Criteria andSignedIsNotNull() {
            addCriterion("signed is not null");
            return (Criteria) this;
        }

        public Criteria andSignedEqualTo(Integer value) {
            addCriterion("signed =", value, "signed");
            return (Criteria) this;
        }

        public Criteria andSignedNotEqualTo(Integer value) {
            addCriterion("signed <>", value, "signed");
            return (Criteria) this;
        }

        public Criteria andSignedGreaterThan(Integer value) {
            addCriterion("signed >", value, "signed");
            return (Criteria) this;
        }

        public Criteria andSignedGreaterThanOrEqualTo(Integer value) {
            addCriterion("signed >=", value, "signed");
            return (Criteria) this;
        }

        public Criteria andSignedLessThan(Integer value) {
            addCriterion("signed <", value, "signed");
            return (Criteria) this;
        }

        public Criteria andSignedLessThanOrEqualTo(Integer value) {
            addCriterion("signed <=", value, "signed");
            return (Criteria) this;
        }

        public Criteria andSignedIn(List<Integer> values) {
            addCriterion("signed in", values, "signed");
            return (Criteria) this;
        }

        public Criteria andSignedNotIn(List<Integer> values) {
            addCriterion("signed not in", values, "signed");
            return (Criteria) this;
        }

        public Criteria andSignedBetween(Integer value1, Integer value2) {
            addCriterion("signed between", value1, value2, "signed");
            return (Criteria) this;
        }

        public Criteria andSignedNotBetween(Integer value1, Integer value2) {
            addCriterion("signed not between", value1, value2, "signed");
            return (Criteria) this;
        }

        public Criteria andStuNumIsNull() {
            addCriterion("stuNum is null");
            return (Criteria) this;
        }

        public Criteria andStuNumIsNotNull() {
            addCriterion("stuNum is not null");
            return (Criteria) this;
        }

        public Criteria andStuNumEqualTo(String value) {
            addCriterion("stuNum =", value, "stuNum");
            return (Criteria) this;
        }

        public Criteria andStuNumNotEqualTo(String value) {
            addCriterion("stuNum <>", value, "stuNum");
            return (Criteria) this;
        }

        public Criteria andStuNumGreaterThan(String value) {
            addCriterion("stuNum >", value, "stuNum");
            return (Criteria) this;
        }

        public Criteria andStuNumGreaterThanOrEqualTo(String value) {
            addCriterion("stuNum >=", value, "stuNum");
            return (Criteria) this;
        }

        public Criteria andStuNumLessThan(String value) {
            addCriterion("stuNum <", value, "stuNum");
            return (Criteria) this;
        }

        public Criteria andStuNumLessThanOrEqualTo(String value) {
            addCriterion("stuNum <=", value, "stuNum");
            return (Criteria) this;
        }

        public Criteria andStuNumLike(String value) {
            addCriterion("stuNum like", value, "stuNum");
            return (Criteria) this;
        }

        public Criteria andStuNumNotLike(String value) {
            addCriterion("stuNum not like", value, "stuNum");
            return (Criteria) this;
        }

        public Criteria andStuNumIn(List<String> values) {
            addCriterion("stuNum in", values, "stuNum");
            return (Criteria) this;
        }

        public Criteria andStuNumNotIn(List<String> values) {
            addCriterion("stuNum not in", values, "stuNum");
            return (Criteria) this;
        }

        public Criteria andStuNumBetween(String value1, String value2) {
            addCriterion("stuNum between", value1, value2, "stuNum");
            return (Criteria) this;
        }

        public Criteria andStuNumNotBetween(String value1, String value2) {
            addCriterion("stuNum not between", value1, value2, "stuNum");
            return (Criteria) this;
        }

        public Criteria andLongitudeIsNull() {
            addCriterion("longitude is null");
            return (Criteria) this;
        }

        public Criteria andLongitudeIsNotNull() {
            addCriterion("longitude is not null");
            return (Criteria) this;
        }

        public Criteria andLongitudeEqualTo(Double value) {
            addCriterion("longitude =", value, "longitude");
            return (Criteria) this;
        }

        public Criteria andLongitudeNotEqualTo(Double value) {
            addCriterion("longitude <>", value, "longitude");
            return (Criteria) this;
        }

        public Criteria andLongitudeGreaterThan(Double value) {
            addCriterion("longitude >", value, "longitude");
            return (Criteria) this;
        }

        public Criteria andLongitudeGreaterThanOrEqualTo(Double value) {
            addCriterion("longitude >=", value, "longitude");
            return (Criteria) this;
        }

        public Criteria andLongitudeLessThan(Double value) {
            addCriterion("longitude <", value, "longitude");
            return (Criteria) this;
        }

        public Criteria andLongitudeLessThanOrEqualTo(Double value) {
            addCriterion("longitude <=", value, "longitude");
            return (Criteria) this;
        }

        public Criteria andLongitudeIn(List<Double> values) {
            addCriterion("longitude in", values, "longitude");
            return (Criteria) this;
        }

        public Criteria andLongitudeNotIn(List<Double> values) {
            addCriterion("longitude not in", values, "longitude");
            return (Criteria) this;
        }

        public Criteria andLongitudeBetween(Double value1, Double value2) {
            addCriterion("longitude between", value1, value2, "longitude");
            return (Criteria) this;
        }

        public Criteria andLongitudeNotBetween(Double value1, Double value2) {
            addCriterion("longitude not between", value1, value2, "longitude");
            return (Criteria) this;
        }

        public Criteria andLatitudeIsNull() {
            addCriterion("latitude is null");
            return (Criteria) this;
        }

        public Criteria andLatitudeIsNotNull() {
            addCriterion("latitude is not null");
            return (Criteria) this;
        }

        public Criteria andLatitudeEqualTo(Double value) {
            addCriterion("latitude =", value, "latitude");
            return (Criteria) this;
        }

        public Criteria andLatitudeNotEqualTo(Double value) {
            addCriterion("latitude <>", value, "latitude");
            return (Criteria) this;
        }

        public Criteria andLatitudeGreaterThan(Double value) {
            addCriterion("latitude >", value, "latitude");
            return (Criteria) this;
        }

        public Criteria andLatitudeGreaterThanOrEqualTo(Double value) {
            addCriterion("latitude >=", value, "latitude");
            return (Criteria) this;
        }

        public Criteria andLatitudeLessThan(Double value) {
            addCriterion("latitude <", value, "latitude");
            return (Criteria) this;
        }

        public Criteria andLatitudeLessThanOrEqualTo(Double value) {
            addCriterion("latitude <=", value, "latitude");
            return (Criteria) this;
        }

        public Criteria andLatitudeIn(List<Double> values) {
            addCriterion("latitude in", values, "latitude");
            return (Criteria) this;
        }

        public Criteria andLatitudeNotIn(List<Double> values) {
            addCriterion("latitude not in", values, "latitude");
            return (Criteria) this;
        }

        public Criteria andLatitudeBetween(Double value1, Double value2) {
            addCriterion("latitude between", value1, value2, "latitude");
            return (Criteria) this;
        }

        public Criteria andLatitudeNotBetween(Double value1, Double value2) {
            addCriterion("latitude not between", value1, value2, "latitude");
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