package net.sls.dto.pay;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

public class PrepaidCardExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public PrepaidCardExample() {
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

        protected void addCriterionForJDBCDate(String condition, Date value, String property) {
            if (value == null) {
                throw new RuntimeException("Value for " + property + " cannot be null");
            }
            addCriterion(condition, new java.sql.Date(value.getTime()), property);
        }

        protected void addCriterionForJDBCDate(String condition, List<Date> values, String property) {
            if (values == null || values.size() == 0) {
                throw new RuntimeException("Value list for " + property + " cannot be null or empty");
            }
            List<java.sql.Date> dateList = new ArrayList<java.sql.Date>();
            Iterator<Date> iter = values.iterator();
            while (iter.hasNext()) {
                dateList.add(new java.sql.Date(iter.next().getTime()));
            }
            addCriterion(condition, dateList, property);
        }

        protected void addCriterionForJDBCDate(String condition, Date value1, Date value2, String property) {
            if (value1 == null || value2 == null) {
                throw new RuntimeException("Between values for " + property + " cannot be null");
            }
            addCriterion(condition, new java.sql.Date(value1.getTime()), new java.sql.Date(value2.getTime()), property);
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

        public Criteria andBatchIsNull() {
            addCriterion("batch is null");
            return (Criteria) this;
        }

        public Criteria andBatchIsNotNull() {
            addCriterion("batch is not null");
            return (Criteria) this;
        }

        public Criteria andBatchEqualTo(String value) {
            addCriterion("batch =", value, "batch");
            return (Criteria) this;
        }

        public Criteria andBatchNotEqualTo(String value) {
            addCriterion("batch <>", value, "batch");
            return (Criteria) this;
        }

        public Criteria andBatchGreaterThan(String value) {
            addCriterion("batch >", value, "batch");
            return (Criteria) this;
        }

        public Criteria andBatchGreaterThanOrEqualTo(String value) {
            addCriterion("batch >=", value, "batch");
            return (Criteria) this;
        }

        public Criteria andBatchLessThan(String value) {
            addCriterion("batch <", value, "batch");
            return (Criteria) this;
        }

        public Criteria andBatchLessThanOrEqualTo(String value) {
            addCriterion("batch <=", value, "batch");
            return (Criteria) this;
        }

        public Criteria andBatchLike(String value) {
            addCriterion("batch like", value, "batch");
            return (Criteria) this;
        }

        public Criteria andBatchNotLike(String value) {
            addCriterion("batch not like", value, "batch");
            return (Criteria) this;
        }

        public Criteria andBatchIn(List<String> values) {
            addCriterion("batch in", values, "batch");
            return (Criteria) this;
        }

        public Criteria andBatchNotIn(List<String> values) {
            addCriterion("batch not in", values, "batch");
            return (Criteria) this;
        }

        public Criteria andBatchBetween(String value1, String value2) {
            addCriterion("batch between", value1, value2, "batch");
            return (Criteria) this;
        }

        public Criteria andBatchNotBetween(String value1, String value2) {
            addCriterion("batch not between", value1, value2, "batch");
            return (Criteria) this;
        }

        public Criteria andCardNumIsNull() {
            addCriterion("cardNum is null");
            return (Criteria) this;
        }

        public Criteria andCardNumIsNotNull() {
            addCriterion("cardNum is not null");
            return (Criteria) this;
        }

        public Criteria andCardNumEqualTo(String value) {
            addCriterion("cardNum =", value, "cardNum");
            return (Criteria) this;
        }

        public Criteria andCardNumNotEqualTo(String value) {
            addCriterion("cardNum <>", value, "cardNum");
            return (Criteria) this;
        }

        public Criteria andCardNumGreaterThan(String value) {
            addCriterion("cardNum >", value, "cardNum");
            return (Criteria) this;
        }

        public Criteria andCardNumGreaterThanOrEqualTo(String value) {
            addCriterion("cardNum >=", value, "cardNum");
            return (Criteria) this;
        }

        public Criteria andCardNumLessThan(String value) {
            addCriterion("cardNum <", value, "cardNum");
            return (Criteria) this;
        }

        public Criteria andCardNumLessThanOrEqualTo(String value) {
            addCriterion("cardNum <=", value, "cardNum");
            return (Criteria) this;
        }

        public Criteria andCardNumLike(String value) {
            addCriterion("cardNum like", value, "cardNum");
            return (Criteria) this;
        }

        public Criteria andCardNumNotLike(String value) {
            addCriterion("cardNum not like", value, "cardNum");
            return (Criteria) this;
        }

        public Criteria andCardNumIn(List<String> values) {
            addCriterion("cardNum in", values, "cardNum");
            return (Criteria) this;
        }

        public Criteria andCardNumNotIn(List<String> values) {
            addCriterion("cardNum not in", values, "cardNum");
            return (Criteria) this;
        }

        public Criteria andCardNumBetween(String value1, String value2) {
            addCriterion("cardNum between", value1, value2, "cardNum");
            return (Criteria) this;
        }

        public Criteria andCardNumNotBetween(String value1, String value2) {
            addCriterion("cardNum not between", value1, value2, "cardNum");
            return (Criteria) this;
        }

        public Criteria andPasswordIsNull() {
            addCriterion("password is null");
            return (Criteria) this;
        }

        public Criteria andPasswordIsNotNull() {
            addCriterion("password is not null");
            return (Criteria) this;
        }

        public Criteria andPasswordEqualTo(String value) {
            addCriterion("password =", value, "password");
            return (Criteria) this;
        }

        public Criteria andPasswordNotEqualTo(String value) {
            addCriterion("password <>", value, "password");
            return (Criteria) this;
        }

        public Criteria andPasswordGreaterThan(String value) {
            addCriterion("password >", value, "password");
            return (Criteria) this;
        }

        public Criteria andPasswordGreaterThanOrEqualTo(String value) {
            addCriterion("password >=", value, "password");
            return (Criteria) this;
        }

        public Criteria andPasswordLessThan(String value) {
            addCriterion("password <", value, "password");
            return (Criteria) this;
        }

        public Criteria andPasswordLessThanOrEqualTo(String value) {
            addCriterion("password <=", value, "password");
            return (Criteria) this;
        }

        public Criteria andPasswordLike(String value) {
            addCriterion("password like", value, "password");
            return (Criteria) this;
        }

        public Criteria andPasswordNotLike(String value) {
            addCriterion("password not like", value, "password");
            return (Criteria) this;
        }

        public Criteria andPasswordIn(List<String> values) {
            addCriterion("password in", values, "password");
            return (Criteria) this;
        }

        public Criteria andPasswordNotIn(List<String> values) {
            addCriterion("password not in", values, "password");
            return (Criteria) this;
        }

        public Criteria andPasswordBetween(String value1, String value2) {
            addCriterion("password between", value1, value2, "password");
            return (Criteria) this;
        }

        public Criteria andPasswordNotBetween(String value1, String value2) {
            addCriterion("password not between", value1, value2, "password");
            return (Criteria) this;
        }

        public Criteria andParValueIsNull() {
            addCriterion("parValue is null");
            return (Criteria) this;
        }

        public Criteria andParValueIsNotNull() {
            addCriterion("parValue is not null");
            return (Criteria) this;
        }

        public Criteria andParValueEqualTo(Integer value) {
            addCriterion("parValue =", value, "parValue");
            return (Criteria) this;
        }

        public Criteria andParValueNotEqualTo(Integer value) {
            addCriterion("parValue <>", value, "parValue");
            return (Criteria) this;
        }

        public Criteria andParValueGreaterThan(Integer value) {
            addCriterion("parValue >", value, "parValue");
            return (Criteria) this;
        }

        public Criteria andParValueGreaterThanOrEqualTo(Integer value) {
            addCriterion("parValue >=", value, "parValue");
            return (Criteria) this;
        }

        public Criteria andParValueLessThan(Integer value) {
            addCriterion("parValue <", value, "parValue");
            return (Criteria) this;
        }

        public Criteria andParValueLessThanOrEqualTo(Integer value) {
            addCriterion("parValue <=", value, "parValue");
            return (Criteria) this;
        }

        public Criteria andParValueIn(List<Integer> values) {
            addCriterion("parValue in", values, "parValue");
            return (Criteria) this;
        }

        public Criteria andParValueNotIn(List<Integer> values) {
            addCriterion("parValue not in", values, "parValue");
            return (Criteria) this;
        }

        public Criteria andParValueBetween(Integer value1, Integer value2) {
            addCriterion("parValue between", value1, value2, "parValue");
            return (Criteria) this;
        }

        public Criteria andParValueNotBetween(Integer value1, Integer value2) {
            addCriterion("parValue not between", value1, value2, "parValue");
            return (Criteria) this;
        }

        public Criteria andValidityStartIsNull() {
            addCriterion("validityStart is null");
            return (Criteria) this;
        }

        public Criteria andValidityStartIsNotNull() {
            addCriterion("validityStart is not null");
            return (Criteria) this;
        }

        public Criteria andValidityStartEqualTo(Date value) {
            addCriterionForJDBCDate("validityStart =", value, "validityStart");
            return (Criteria) this;
        }

        public Criteria andValidityStartNotEqualTo(Date value) {
            addCriterionForJDBCDate("validityStart <>", value, "validityStart");
            return (Criteria) this;
        }

        public Criteria andValidityStartGreaterThan(Date value) {
            addCriterionForJDBCDate("validityStart >", value, "validityStart");
            return (Criteria) this;
        }

        public Criteria andValidityStartGreaterThanOrEqualTo(Date value) {
            addCriterionForJDBCDate("validityStart >=", value, "validityStart");
            return (Criteria) this;
        }

        public Criteria andValidityStartLessThan(Date value) {
            addCriterionForJDBCDate("validityStart <", value, "validityStart");
            return (Criteria) this;
        }

        public Criteria andValidityStartLessThanOrEqualTo(Date value) {
            addCriterionForJDBCDate("validityStart <=", value, "validityStart");
            return (Criteria) this;
        }

        public Criteria andValidityStartIn(List<Date> values) {
            addCriterionForJDBCDate("validityStart in", values, "validityStart");
            return (Criteria) this;
        }

        public Criteria andValidityStartNotIn(List<Date> values) {
            addCriterionForJDBCDate("validityStart not in", values, "validityStart");
            return (Criteria) this;
        }

        public Criteria andValidityStartBetween(Date value1, Date value2) {
            addCriterionForJDBCDate("validityStart between", value1, value2, "validityStart");
            return (Criteria) this;
        }

        public Criteria andValidityStartNotBetween(Date value1, Date value2) {
            addCriterionForJDBCDate("validityStart not between", value1, value2, "validityStart");
            return (Criteria) this;
        }

        public Criteria andValidityEndIsNull() {
            addCriterion("validityEnd is null");
            return (Criteria) this;
        }

        public Criteria andValidityEndIsNotNull() {
            addCriterion("validityEnd is not null");
            return (Criteria) this;
        }

        public Criteria andValidityEndEqualTo(Date value) {
            addCriterionForJDBCDate("validityEnd =", value, "validityEnd");
            return (Criteria) this;
        }

        public Criteria andValidityEndNotEqualTo(Date value) {
            addCriterionForJDBCDate("validityEnd <>", value, "validityEnd");
            return (Criteria) this;
        }

        public Criteria andValidityEndGreaterThan(Date value) {
            addCriterionForJDBCDate("validityEnd >", value, "validityEnd");
            return (Criteria) this;
        }

        public Criteria andValidityEndGreaterThanOrEqualTo(Date value) {
            addCriterionForJDBCDate("validityEnd >=", value, "validityEnd");
            return (Criteria) this;
        }

        public Criteria andValidityEndLessThan(Date value) {
            addCriterionForJDBCDate("validityEnd <", value, "validityEnd");
            return (Criteria) this;
        }

        public Criteria andValidityEndLessThanOrEqualTo(Date value) {
            addCriterionForJDBCDate("validityEnd <=", value, "validityEnd");
            return (Criteria) this;
        }

        public Criteria andValidityEndIn(List<Date> values) {
            addCriterionForJDBCDate("validityEnd in", values, "validityEnd");
            return (Criteria) this;
        }

        public Criteria andValidityEndNotIn(List<Date> values) {
            addCriterionForJDBCDate("validityEnd not in", values, "validityEnd");
            return (Criteria) this;
        }

        public Criteria andValidityEndBetween(Date value1, Date value2) {
            addCriterionForJDBCDate("validityEnd between", value1, value2, "validityEnd");
            return (Criteria) this;
        }

        public Criteria andValidityEndNotBetween(Date value1, Date value2) {
            addCriterionForJDBCDate("validityEnd not between", value1, value2, "validityEnd");
            return (Criteria) this;
        }

        public Criteria andCheckStatusIsNull() {
            addCriterion("checkStatus is null");
            return (Criteria) this;
        }

        public Criteria andCheckStatusIsNotNull() {
            addCriterion("checkStatus is not null");
            return (Criteria) this;
        }

        public Criteria andCheckStatusEqualTo(Byte value) {
            addCriterion("checkStatus =", value, "checkStatus");
            return (Criteria) this;
        }

        public Criteria andCheckStatusNotEqualTo(Byte value) {
            addCriterion("checkStatus <>", value, "checkStatus");
            return (Criteria) this;
        }

        public Criteria andCheckStatusGreaterThan(Byte value) {
            addCriterion("checkStatus >", value, "checkStatus");
            return (Criteria) this;
        }

        public Criteria andCheckStatusGreaterThanOrEqualTo(Byte value) {
            addCriterion("checkStatus >=", value, "checkStatus");
            return (Criteria) this;
        }

        public Criteria andCheckStatusLessThan(Byte value) {
            addCriterion("checkStatus <", value, "checkStatus");
            return (Criteria) this;
        }

        public Criteria andCheckStatusLessThanOrEqualTo(Byte value) {
            addCriterion("checkStatus <=", value, "checkStatus");
            return (Criteria) this;
        }

        public Criteria andCheckStatusIn(List<Byte> values) {
            addCriterion("checkStatus in", values, "checkStatus");
            return (Criteria) this;
        }

        public Criteria andCheckStatusNotIn(List<Byte> values) {
            addCriterion("checkStatus not in", values, "checkStatus");
            return (Criteria) this;
        }

        public Criteria andCheckStatusBetween(Byte value1, Byte value2) {
            addCriterion("checkStatus between", value1, value2, "checkStatus");
            return (Criteria) this;
        }

        public Criteria andCheckStatusNotBetween(Byte value1, Byte value2) {
            addCriterion("checkStatus not between", value1, value2, "checkStatus");
            return (Criteria) this;
        }

        public Criteria andCardStatusIsNull() {
            addCriterion("cardStatus is null");
            return (Criteria) this;
        }

        public Criteria andCardStatusIsNotNull() {
            addCriterion("cardStatus is not null");
            return (Criteria) this;
        }

        public Criteria andCardStatusEqualTo(Byte value) {
            addCriterion("cardStatus =", value, "cardStatus");
            return (Criteria) this;
        }

        public Criteria andCardStatusNotEqualTo(Byte value) {
            addCriterion("cardStatus <>", value, "cardStatus");
            return (Criteria) this;
        }

        public Criteria andCardStatusGreaterThan(Byte value) {
            addCriterion("cardStatus >", value, "cardStatus");
            return (Criteria) this;
        }

        public Criteria andCardStatusGreaterThanOrEqualTo(Byte value) {
            addCriterion("cardStatus >=", value, "cardStatus");
            return (Criteria) this;
        }

        public Criteria andCardStatusLessThan(Byte value) {
            addCriterion("cardStatus <", value, "cardStatus");
            return (Criteria) this;
        }

        public Criteria andCardStatusLessThanOrEqualTo(Byte value) {
            addCriterion("cardStatus <=", value, "cardStatus");
            return (Criteria) this;
        }

        public Criteria andCardStatusIn(List<Byte> values) {
            addCriterion("cardStatus in", values, "cardStatus");
            return (Criteria) this;
        }

        public Criteria andCardStatusNotIn(List<Byte> values) {
            addCriterion("cardStatus not in", values, "cardStatus");
            return (Criteria) this;
        }

        public Criteria andCardStatusBetween(Byte value1, Byte value2) {
            addCriterion("cardStatus between", value1, value2, "cardStatus");
            return (Criteria) this;
        }

        public Criteria andCardStatusNotBetween(Byte value1, Byte value2) {
            addCriterion("cardStatus not between", value1, value2, "cardStatus");
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