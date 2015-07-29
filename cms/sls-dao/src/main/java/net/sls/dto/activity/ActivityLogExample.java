package net.sls.dto.activity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ActivityLogExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public ActivityLogExample() {
        oredCriteria = new ArrayList<Criteria>();
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
            criteria = new ArrayList<Criterion>();
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

        public Criteria andActIdIsNull() {
            addCriterion("actId is null");
            return (Criteria) this;
        }

        public Criteria andActIdIsNotNull() {
            addCriterion("actId is not null");
            return (Criteria) this;
        }

        public Criteria andActIdEqualTo(Long value) {
            addCriterion("actId =", value, "actId");
            return (Criteria) this;
        }

        public Criteria andActIdNotEqualTo(Long value) {
            addCriterion("actId <>", value, "actId");
            return (Criteria) this;
        }

        public Criteria andActIdGreaterThan(Long value) {
            addCriterion("actId >", value, "actId");
            return (Criteria) this;
        }

        public Criteria andActIdGreaterThanOrEqualTo(Long value) {
            addCriterion("actId >=", value, "actId");
            return (Criteria) this;
        }

        public Criteria andActIdLessThan(Long value) {
            addCriterion("actId <", value, "actId");
            return (Criteria) this;
        }

        public Criteria andActIdLessThanOrEqualTo(Long value) {
            addCriterion("actId <=", value, "actId");
            return (Criteria) this;
        }

        public Criteria andActIdIn(List<Long> values) {
            addCriterion("actId in", values, "actId");
            return (Criteria) this;
        }

        public Criteria andActIdNotIn(List<Long> values) {
            addCriterion("actId not in", values, "actId");
            return (Criteria) this;
        }

        public Criteria andActIdBetween(Long value1, Long value2) {
            addCriterion("actId between", value1, value2, "actId");
            return (Criteria) this;
        }

        public Criteria andActIdNotBetween(Long value1, Long value2) {
            addCriterion("actId not between", value1, value2, "actId");
            return (Criteria) this;
        }

        public Criteria andActTypeIsNull() {
            addCriterion("actType is null");
            return (Criteria) this;
        }

        public Criteria andActTypeIsNotNull() {
            addCriterion("actType is not null");
            return (Criteria) this;
        }

        public Criteria andActTypeEqualTo(Integer value) {
            addCriterion("actType =", value, "actType");
            return (Criteria) this;
        }

        public Criteria andActTypeNotEqualTo(Integer value) {
            addCriterion("actType <>", value, "actType");
            return (Criteria) this;
        }

        public Criteria andActTypeGreaterThan(Integer value) {
            addCriterion("actType >", value, "actType");
            return (Criteria) this;
        }

        public Criteria andActTypeGreaterThanOrEqualTo(Integer value) {
            addCriterion("actType >=", value, "actType");
            return (Criteria) this;
        }

        public Criteria andActTypeLessThan(Integer value) {
            addCriterion("actType <", value, "actType");
            return (Criteria) this;
        }

        public Criteria andActTypeLessThanOrEqualTo(Integer value) {
            addCriterion("actType <=", value, "actType");
            return (Criteria) this;
        }

        public Criteria andActTypeIn(List<Integer> values) {
            addCriterion("actType in", values, "actType");
            return (Criteria) this;
        }

        public Criteria andActTypeNotIn(List<Integer> values) {
            addCriterion("actType not in", values, "actType");
            return (Criteria) this;
        }

        public Criteria andActTypeBetween(Integer value1, Integer value2) {
            addCriterion("actType between", value1, value2, "actType");
            return (Criteria) this;
        }

        public Criteria andActTypeNotBetween(Integer value1, Integer value2) {
            addCriterion("actType not between", value1, value2, "actType");
            return (Criteria) this;
        }

        public Criteria andOperatorIdIsNull() {
            addCriterion("operatorId is null");
            return (Criteria) this;
        }

        public Criteria andOperatorIdIsNotNull() {
            addCriterion("operatorId is not null");
            return (Criteria) this;
        }

        public Criteria andOperatorIdEqualTo(Long value) {
            addCriterion("operatorId =", value, "operatorId");
            return (Criteria) this;
        }

        public Criteria andOperatorIdNotEqualTo(Long value) {
            addCriterion("operatorId <>", value, "operatorId");
            return (Criteria) this;
        }

        public Criteria andOperatorIdGreaterThan(Long value) {
            addCriterion("operatorId >", value, "operatorId");
            return (Criteria) this;
        }

        public Criteria andOperatorIdGreaterThanOrEqualTo(Long value) {
            addCriterion("operatorId >=", value, "operatorId");
            return (Criteria) this;
        }

        public Criteria andOperatorIdLessThan(Long value) {
            addCriterion("operatorId <", value, "operatorId");
            return (Criteria) this;
        }

        public Criteria andOperatorIdLessThanOrEqualTo(Long value) {
            addCriterion("operatorId <=", value, "operatorId");
            return (Criteria) this;
        }

        public Criteria andOperatorIdIn(List<Long> values) {
            addCriterion("operatorId in", values, "operatorId");
            return (Criteria) this;
        }

        public Criteria andOperatorIdNotIn(List<Long> values) {
            addCriterion("operatorId not in", values, "operatorId");
            return (Criteria) this;
        }

        public Criteria andOperatorIdBetween(Long value1, Long value2) {
            addCriterion("operatorId between", value1, value2, "operatorId");
            return (Criteria) this;
        }

        public Criteria andOperatorIdNotBetween(Long value1, Long value2) {
            addCriterion("operatorId not between", value1, value2, "operatorId");
            return (Criteria) this;
        }

        public Criteria andOperatorTimeIsNull() {
            addCriterion("operatorTime is null");
            return (Criteria) this;
        }

        public Criteria andOperatorTimeIsNotNull() {
            addCriterion("operatorTime is not null");
            return (Criteria) this;
        }

        public Criteria andOperatorTimeEqualTo(Date value) {
            addCriterion("operatorTime =", value, "operatorTime");
            return (Criteria) this;
        }

        public Criteria andOperatorTimeNotEqualTo(Date value) {
            addCriterion("operatorTime <>", value, "operatorTime");
            return (Criteria) this;
        }

        public Criteria andOperatorTimeGreaterThan(Date value) {
            addCriterion("operatorTime >", value, "operatorTime");
            return (Criteria) this;
        }

        public Criteria andOperatorTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("operatorTime >=", value, "operatorTime");
            return (Criteria) this;
        }

        public Criteria andOperatorTimeLessThan(Date value) {
            addCriterion("operatorTime <", value, "operatorTime");
            return (Criteria) this;
        }

        public Criteria andOperatorTimeLessThanOrEqualTo(Date value) {
            addCriterion("operatorTime <=", value, "operatorTime");
            return (Criteria) this;
        }

        public Criteria andOperatorTimeIn(List<Date> values) {
            addCriterion("operatorTime in", values, "operatorTime");
            return (Criteria) this;
        }

        public Criteria andOperatorTimeNotIn(List<Date> values) {
            addCriterion("operatorTime not in", values, "operatorTime");
            return (Criteria) this;
        }

        public Criteria andOperatorTimeBetween(Date value1, Date value2) {
            addCriterion("operatorTime between", value1, value2, "operatorTime");
            return (Criteria) this;
        }

        public Criteria andOperatorTimeNotBetween(Date value1, Date value2) {
            addCriterion("operatorTime not between", value1, value2, "operatorTime");
            return (Criteria) this;
        }

        public Criteria andRemarkIsNull() {
            addCriterion("remark is null");
            return (Criteria) this;
        }

        public Criteria andRemarkIsNotNull() {
            addCriterion("remark is not null");
            return (Criteria) this;
        }

        public Criteria andRemarkEqualTo(String value) {
            addCriterion("remark =", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkNotEqualTo(String value) {
            addCriterion("remark <>", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkGreaterThan(String value) {
            addCriterion("remark >", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkGreaterThanOrEqualTo(String value) {
            addCriterion("remark >=", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkLessThan(String value) {
            addCriterion("remark <", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkLessThanOrEqualTo(String value) {
            addCriterion("remark <=", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkLike(String value) {
            addCriterion("remark like", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkNotLike(String value) {
            addCriterion("remark not like", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkIn(List<String> values) {
            addCriterion("remark in", values, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkNotIn(List<String> values) {
            addCriterion("remark not in", values, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkBetween(String value1, String value2) {
            addCriterion("remark between", value1, value2, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkNotBetween(String value1, String value2) {
            addCriterion("remark not between", value1, value2, "remark");
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