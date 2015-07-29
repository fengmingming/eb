package net.sls.dto.activity;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

public class CouponExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public CouponExample() {
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

        public Criteria andNameIsNull() {
            addCriterion("name is null");
            return (Criteria) this;
        }

        public Criteria andNameIsNotNull() {
            addCriterion("name is not null");
            return (Criteria) this;
        }

        public Criteria andNameEqualTo(String value) {
            addCriterion("name =", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameNotEqualTo(String value) {
            addCriterion("name <>", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameGreaterThan(String value) {
            addCriterion("name >", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameGreaterThanOrEqualTo(String value) {
            addCriterion("name >=", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameLessThan(String value) {
            addCriterion("name <", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameLessThanOrEqualTo(String value) {
            addCriterion("name <=", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameLike(String value) {
            addCriterion("name like", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameNotLike(String value) {
            addCriterion("name not like", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameIn(List<String> values) {
            addCriterion("name in", values, "name");
            return (Criteria) this;
        }

        public Criteria andNameNotIn(List<String> values) {
            addCriterion("name not in", values, "name");
            return (Criteria) this;
        }

        public Criteria andNameBetween(String value1, String value2) {
            addCriterion("name between", value1, value2, "name");
            return (Criteria) this;
        }

        public Criteria andNameNotBetween(String value1, String value2) {
            addCriterion("name not between", value1, value2, "name");
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

        public Criteria andMinimumIsNull() {
            addCriterion("minimum is null");
            return (Criteria) this;
        }

        public Criteria andMinimumIsNotNull() {
            addCriterion("minimum is not null");
            return (Criteria) this;
        }

        public Criteria andMinimumEqualTo(Integer value) {
            addCriterion("minimum =", value, "minimum");
            return (Criteria) this;
        }

        public Criteria andMinimumNotEqualTo(Integer value) {
            addCriterion("minimum <>", value, "minimum");
            return (Criteria) this;
        }

        public Criteria andMinimumGreaterThan(Integer value) {
            addCriterion("minimum >", value, "minimum");
            return (Criteria) this;
        }

        public Criteria andMinimumGreaterThanOrEqualTo(Integer value) {
            addCriterion("minimum >=", value, "minimum");
            return (Criteria) this;
        }

        public Criteria andMinimumLessThan(Integer value) {
            addCriterion("minimum <", value, "minimum");
            return (Criteria) this;
        }

        public Criteria andMinimumLessThanOrEqualTo(Integer value) {
            addCriterion("minimum <=", value, "minimum");
            return (Criteria) this;
        }

        public Criteria andMinimumIn(List<Integer> values) {
            addCriterion("minimum in", values, "minimum");
            return (Criteria) this;
        }

        public Criteria andMinimumNotIn(List<Integer> values) {
            addCriterion("minimum not in", values, "minimum");
            return (Criteria) this;
        }

        public Criteria andMinimumBetween(Integer value1, Integer value2) {
            addCriterion("minimum between", value1, value2, "minimum");
            return (Criteria) this;
        }

        public Criteria andMinimumNotBetween(Integer value1, Integer value2) {
            addCriterion("minimum not between", value1, value2, "minimum");
            return (Criteria) this;
        }

        public Criteria andTotalIsNull() {
            addCriterion("total is null");
            return (Criteria) this;
        }

        public Criteria andTotalIsNotNull() {
            addCriterion("total is not null");
            return (Criteria) this;
        }

        public Criteria andTotalEqualTo(Integer value) {
            addCriterion("total =", value, "total");
            return (Criteria) this;
        }

        public Criteria andTotalNotEqualTo(Integer value) {
            addCriterion("total <>", value, "total");
            return (Criteria) this;
        }

        public Criteria andTotalGreaterThan(Integer value) {
            addCriterion("total >", value, "total");
            return (Criteria) this;
        }

        public Criteria andTotalGreaterThanOrEqualTo(Integer value) {
            addCriterion("total >=", value, "total");
            return (Criteria) this;
        }

        public Criteria andTotalLessThan(Integer value) {
            addCriterion("total <", value, "total");
            return (Criteria) this;
        }

        public Criteria andTotalLessThanOrEqualTo(Integer value) {
            addCriterion("total <=", value, "total");
            return (Criteria) this;
        }

        public Criteria andTotalIn(List<Integer> values) {
            addCriterion("total in", values, "total");
            return (Criteria) this;
        }

        public Criteria andTotalNotIn(List<Integer> values) {
            addCriterion("total not in", values, "total");
            return (Criteria) this;
        }

        public Criteria andTotalBetween(Integer value1, Integer value2) {
            addCriterion("total between", value1, value2, "total");
            return (Criteria) this;
        }

        public Criteria andTotalNotBetween(Integer value1, Integer value2) {
            addCriterion("total not between", value1, value2, "total");
            return (Criteria) this;
        }

        public Criteria andNumIsNull() {
            addCriterion("num is null");
            return (Criteria) this;
        }

        public Criteria andNumIsNotNull() {
            addCriterion("num is not null");
            return (Criteria) this;
        }

        public Criteria andNumEqualTo(Integer value) {
            addCriterion("num =", value, "num");
            return (Criteria) this;
        }

        public Criteria andNumNotEqualTo(Integer value) {
            addCriterion("num <>", value, "num");
            return (Criteria) this;
        }

        public Criteria andNumGreaterThan(Integer value) {
            addCriterion("num >", value, "num");
            return (Criteria) this;
        }

        public Criteria andNumGreaterThanOrEqualTo(Integer value) {
            addCriterion("num >=", value, "num");
            return (Criteria) this;
        }

        public Criteria andNumLessThan(Integer value) {
            addCriterion("num <", value, "num");
            return (Criteria) this;
        }

        public Criteria andNumLessThanOrEqualTo(Integer value) {
            addCriterion("num <=", value, "num");
            return (Criteria) this;
        }

        public Criteria andNumIn(List<Integer> values) {
            addCriterion("num in", values, "num");
            return (Criteria) this;
        }

        public Criteria andNumNotIn(List<Integer> values) {
            addCriterion("num not in", values, "num");
            return (Criteria) this;
        }

        public Criteria andNumBetween(Integer value1, Integer value2) {
            addCriterion("num between", value1, value2, "num");
            return (Criteria) this;
        }

        public Criteria andNumNotBetween(Integer value1, Integer value2) {
            addCriterion("num not between", value1, value2, "num");
            return (Criteria) this;
        }

        public Criteria andLimitTypeIsNull() {
            addCriterion("limitType is null");
            return (Criteria) this;
        }

        public Criteria andLimitTypeIsNotNull() {
            addCriterion("limitType is not null");
            return (Criteria) this;
        }

        public Criteria andLimitTypeEqualTo(Integer value) {
            addCriterion("limitType =", value, "limitType");
            return (Criteria) this;
        }

        public Criteria andLimitTypeNotEqualTo(Integer value) {
            addCriterion("limitType <>", value, "limitType");
            return (Criteria) this;
        }

        public Criteria andLimitTypeGreaterThan(Integer value) {
            addCriterion("limitType >", value, "limitType");
            return (Criteria) this;
        }

        public Criteria andLimitTypeGreaterThanOrEqualTo(Integer value) {
            addCriterion("limitType >=", value, "limitType");
            return (Criteria) this;
        }

        public Criteria andLimitTypeLessThan(Integer value) {
            addCriterion("limitType <", value, "limitType");
            return (Criteria) this;
        }

        public Criteria andLimitTypeLessThanOrEqualTo(Integer value) {
            addCriterion("limitType <=", value, "limitType");
            return (Criteria) this;
        }

        public Criteria andLimitTypeIn(List<Integer> values) {
            addCriterion("limitType in", values, "limitType");
            return (Criteria) this;
        }

        public Criteria andLimitTypeNotIn(List<Integer> values) {
            addCriterion("limitType not in", values, "limitType");
            return (Criteria) this;
        }

        public Criteria andLimitTypeBetween(Integer value1, Integer value2) {
            addCriterion("limitType between", value1, value2, "limitType");
            return (Criteria) this;
        }

        public Criteria andLimitTypeNotBetween(Integer value1, Integer value2) {
            addCriterion("limitType not between", value1, value2, "limitType");
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

        public Criteria andLimitCatIsNull() {
            addCriterion("limitCat is null");
            return (Criteria) this;
        }

        public Criteria andLimitCatIsNotNull() {
            addCriterion("limitCat is not null");
            return (Criteria) this;
        }

        public Criteria andLimitCatEqualTo(Long value) {
            addCriterion("limitCat =", value, "limitCat");
            return (Criteria) this;
        }

        public Criteria andLimitCatNotEqualTo(Long value) {
            addCriterion("limitCat <>", value, "limitCat");
            return (Criteria) this;
        }

        public Criteria andLimitCatGreaterThan(Long value) {
            addCriterion("limitCat >", value, "limitCat");
            return (Criteria) this;
        }

        public Criteria andLimitCatGreaterThanOrEqualTo(Long value) {
            addCriterion("limitCat >=", value, "limitCat");
            return (Criteria) this;
        }

        public Criteria andLimitCatLessThan(Long value) {
            addCriterion("limitCat <", value, "limitCat");
            return (Criteria) this;
        }

        public Criteria andLimitCatLessThanOrEqualTo(Long value) {
            addCriterion("limitCat <=", value, "limitCat");
            return (Criteria) this;
        }

        public Criteria andLimitCatIn(List<Long> values) {
            addCriterion("limitCat in", values, "limitCat");
            return (Criteria) this;
        }

        public Criteria andLimitCatNotIn(List<Long> values) {
            addCriterion("limitCat not in", values, "limitCat");
            return (Criteria) this;
        }

        public Criteria andLimitCatBetween(Long value1, Long value2) {
            addCriterion("limitCat between", value1, value2, "limitCat");
            return (Criteria) this;
        }

        public Criteria andLimitCatNotBetween(Long value1, Long value2) {
            addCriterion("limitCat not between", value1, value2, "limitCat");
            return (Criteria) this;
        }

        public Criteria andGenDateIsNull() {
            addCriterion("genDate is null");
            return (Criteria) this;
        }

        public Criteria andGenDateIsNotNull() {
            addCriterion("genDate is not null");
            return (Criteria) this;
        }

        public Criteria andGenDateEqualTo(Date value) {
            addCriterion("genDate =", value, "genDate");
            return (Criteria) this;
        }

        public Criteria andGenDateNotEqualTo(Date value) {
            addCriterion("genDate <>", value, "genDate");
            return (Criteria) this;
        }

        public Criteria andGenDateGreaterThan(Date value) {
            addCriterion("genDate >", value, "genDate");
            return (Criteria) this;
        }

        public Criteria andGenDateGreaterThanOrEqualTo(Date value) {
            addCriterion("genDate >=", value, "genDate");
            return (Criteria) this;
        }

        public Criteria andGenDateLessThan(Date value) {
            addCriterion("genDate <", value, "genDate");
            return (Criteria) this;
        }

        public Criteria andGenDateLessThanOrEqualTo(Date value) {
            addCriterion("genDate <=", value, "genDate");
            return (Criteria) this;
        }

        public Criteria andGenDateIn(List<Date> values) {
            addCriterion("genDate in", values, "genDate");
            return (Criteria) this;
        }

        public Criteria andGenDateNotIn(List<Date> values) {
            addCriterion("genDate not in", values, "genDate");
            return (Criteria) this;
        }

        public Criteria andGenDateBetween(Date value1, Date value2) {
            addCriterion("genDate between", value1, value2, "genDate");
            return (Criteria) this;
        }

        public Criteria andGenDateNotBetween(Date value1, Date value2) {
            addCriterion("genDate not between", value1, value2, "genDate");
            return (Criteria) this;
        }

        public Criteria andIsDelIsNull() {
            addCriterion("isDel is null");
            return (Criteria) this;
        }

        public Criteria andIsDelIsNotNull() {
            addCriterion("isDel is not null");
            return (Criteria) this;
        }

        public Criteria andIsDelEqualTo(Integer value) {
            addCriterion("isDel =", value, "isDel");
            return (Criteria) this;
        }

        public Criteria andIsDelNotEqualTo(Integer value) {
            addCriterion("isDel <>", value, "isDel");
            return (Criteria) this;
        }

        public Criteria andIsDelGreaterThan(Integer value) {
            addCriterion("isDel >", value, "isDel");
            return (Criteria) this;
        }

        public Criteria andIsDelGreaterThanOrEqualTo(Integer value) {
            addCriterion("isDel >=", value, "isDel");
            return (Criteria) this;
        }

        public Criteria andIsDelLessThan(Integer value) {
            addCriterion("isDel <", value, "isDel");
            return (Criteria) this;
        }

        public Criteria andIsDelLessThanOrEqualTo(Integer value) {
            addCriterion("isDel <=", value, "isDel");
            return (Criteria) this;
        }

        public Criteria andIsDelIn(List<Integer> values) {
            addCriterion("isDel in", values, "isDel");
            return (Criteria) this;
        }

        public Criteria andIsDelNotIn(List<Integer> values) {
            addCriterion("isDel not in", values, "isDel");
            return (Criteria) this;
        }

        public Criteria andIsDelBetween(Integer value1, Integer value2) {
            addCriterion("isDel between", value1, value2, "isDel");
            return (Criteria) this;
        }

        public Criteria andIsDelNotBetween(Integer value1, Integer value2) {
            addCriterion("isDel not between", value1, value2, "isDel");
            return (Criteria) this;
        }

        public Criteria andStartdateIsNull() {
            addCriterion("startdate is null");
            return (Criteria) this;
        }

        public Criteria andStartdateIsNotNull() {
            addCriterion("startdate is not null");
            return (Criteria) this;
        }

        public Criteria andStartdateEqualTo(Date value) {
            addCriterion("startdate =", value, "startdate");
            return (Criteria) this;
        }

        public Criteria andStartdateNotEqualTo(Date value) {
            addCriterion("startdate <>", value, "startdate");
            return (Criteria) this;
        }

        public Criteria andStartdateGreaterThan(Date value) {
            addCriterion("startdate >", value, "startdate");
            return (Criteria) this;
        }

        public Criteria andStartdateGreaterThanOrEqualTo(Date value) {
            addCriterion("startdate >=", value, "startdate");
            return (Criteria) this;
        }

        public Criteria andStartdateLessThan(Date value) {
            addCriterion("startdate <", value, "startdate");
            return (Criteria) this;
        }

        public Criteria andStartdateLessThanOrEqualTo(Date value) {
            addCriterion("startdate <=", value, "startdate");
            return (Criteria) this;
        }

        public Criteria andStartdateIn(List<Date> values) {
            addCriterion("startdate in", values, "startdate");
            return (Criteria) this;
        }

        public Criteria andStartdateNotIn(List<Date> values) {
            addCriterion("startdate not in", values, "startdate");
            return (Criteria) this;
        }

        public Criteria andStartdateBetween(Date value1, Date value2) {
            addCriterion("startdate between", value1, value2, "startdate");
            return (Criteria) this;
        }

        public Criteria andStartdateNotBetween(Date value1, Date value2) {
            addCriterion("startdate not between", value1, value2, "startdate");
            return (Criteria) this;
        }

        public Criteria andEnddateIsNull() {
            addCriterion("enddate is null");
            return (Criteria) this;
        }

        public Criteria andEnddateIsNotNull() {
            addCriterion("enddate is not null");
            return (Criteria) this;
        }

        public Criteria andEnddateEqualTo(Date value) {
            addCriterion("enddate =", value, "enddate");
            return (Criteria) this;
        }

        public Criteria andEnddateNotEqualTo(Date value) {
            addCriterion("enddate <>", value, "enddate");
            return (Criteria) this;
        }

        public Criteria andEnddateGreaterThan(Date value) {
            addCriterion("enddate >", value, "enddate");
            return (Criteria) this;
        }

        public Criteria andEnddateGreaterThanOrEqualTo(Date value) {
            addCriterion("enddate >=", value, "enddate");
            return (Criteria) this;
        }

        public Criteria andEnddateLessThan(Date value) {
            addCriterion("enddate <", value, "enddate");
            return (Criteria) this;
        }

        public Criteria andEnddateLessThanOrEqualTo(Date value) {
            addCriterion("enddate <=", value, "enddate");
            return (Criteria) this;
        }

        public Criteria andEnddateIn(List<Date> values) {
            addCriterion("enddate in", values, "enddate");
            return (Criteria) this;
        }

        public Criteria andEnddateNotIn(List<Date> values) {
            addCriterion("enddate not in", values, "enddate");
            return (Criteria) this;
        }

        public Criteria andEnddateBetween(Date value1, Date value2) {
            addCriterion("enddate between", value1, value2, "enddate");
            return (Criteria) this;
        }

        public Criteria andEnddateNotBetween(Date value1, Date value2) {
            addCriterion("enddate not between", value1, value2, "enddate");
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