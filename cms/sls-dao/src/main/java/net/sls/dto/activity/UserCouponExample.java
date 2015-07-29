package net.sls.dto.activity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class UserCouponExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public UserCouponExample() {
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

        public Criteria andCodeIsNull() {
            addCriterion("code is null");
            return (Criteria) this;
        }

        public Criteria andCodeIsNotNull() {
            addCriterion("code is not null");
            return (Criteria) this;
        }

        public Criteria andCodeEqualTo(String value) {
            addCriterion("code =", value, "code");
            return (Criteria) this;
        }

        public Criteria andCodeNotEqualTo(String value) {
            addCriterion("code <>", value, "code");
            return (Criteria) this;
        }

        public Criteria andCodeGreaterThan(String value) {
            addCriterion("code >", value, "code");
            return (Criteria) this;
        }

        public Criteria andCodeGreaterThanOrEqualTo(String value) {
            addCriterion("code >=", value, "code");
            return (Criteria) this;
        }

        public Criteria andCodeLessThan(String value) {
            addCriterion("code <", value, "code");
            return (Criteria) this;
        }

        public Criteria andCodeLessThanOrEqualTo(String value) {
            addCriterion("code <=", value, "code");
            return (Criteria) this;
        }

        public Criteria andCodeLike(String value) {
            addCriterion("code like", value, "code");
            return (Criteria) this;
        }

        public Criteria andCodeNotLike(String value) {
            addCriterion("code not like", value, "code");
            return (Criteria) this;
        }

        public Criteria andCodeIn(List<String> values) {
            addCriterion("code in", values, "code");
            return (Criteria) this;
        }

        public Criteria andCodeNotIn(List<String> values) {
            addCriterion("code not in", values, "code");
            return (Criteria) this;
        }

        public Criteria andCodeBetween(String value1, String value2) {
            addCriterion("code between", value1, value2, "code");
            return (Criteria) this;
        }

        public Criteria andCodeNotBetween(String value1, String value2) {
            addCriterion("code not between", value1, value2, "code");
            return (Criteria) this;
        }

        public Criteria andCouponIdIsNull() {
            addCriterion("couponId is null");
            return (Criteria) this;
        }

        public Criteria andCouponIdIsNotNull() {
            addCriterion("couponId is not null");
            return (Criteria) this;
        }

        public Criteria andCouponIdEqualTo(Long value) {
            addCriterion("couponId =", value, "couponId");
            return (Criteria) this;
        }

        public Criteria andCouponIdNotEqualTo(Long value) {
            addCriterion("couponId <>", value, "couponId");
            return (Criteria) this;
        }

        public Criteria andCouponIdGreaterThan(Long value) {
            addCriterion("couponId >", value, "couponId");
            return (Criteria) this;
        }

        public Criteria andCouponIdGreaterThanOrEqualTo(Long value) {
            addCriterion("couponId >=", value, "couponId");
            return (Criteria) this;
        }

        public Criteria andCouponIdLessThan(Long value) {
            addCriterion("couponId <", value, "couponId");
            return (Criteria) this;
        }

        public Criteria andCouponIdLessThanOrEqualTo(Long value) {
            addCriterion("couponId <=", value, "couponId");
            return (Criteria) this;
        }

        public Criteria andCouponIdIn(List<Long> values) {
            addCriterion("couponId in", values, "couponId");
            return (Criteria) this;
        }

        public Criteria andCouponIdNotIn(List<Long> values) {
            addCriterion("couponId not in", values, "couponId");
            return (Criteria) this;
        }

        public Criteria andCouponIdBetween(Long value1, Long value2) {
            addCriterion("couponId between", value1, value2, "couponId");
            return (Criteria) this;
        }

        public Criteria andCouponIdNotBetween(Long value1, Long value2) {
            addCriterion("couponId not between", value1, value2, "couponId");
            return (Criteria) this;
        }

        public Criteria andUsrIdIsNull() {
            addCriterion("usrId is null");
            return (Criteria) this;
        }

        public Criteria andUsrIdIsNotNull() {
            addCriterion("usrId is not null");
            return (Criteria) this;
        }

        public Criteria andUsrIdEqualTo(Long value) {
            addCriterion("usrId =", value, "usrId");
            return (Criteria) this;
        }

        public Criteria andUsrIdNotEqualTo(Long value) {
            addCriterion("usrId <>", value, "usrId");
            return (Criteria) this;
        }

        public Criteria andUsrIdGreaterThan(Long value) {
            addCriterion("usrId >", value, "usrId");
            return (Criteria) this;
        }

        public Criteria andUsrIdGreaterThanOrEqualTo(Long value) {
            addCriterion("usrId >=", value, "usrId");
            return (Criteria) this;
        }

        public Criteria andUsrIdLessThan(Long value) {
            addCriterion("usrId <", value, "usrId");
            return (Criteria) this;
        }

        public Criteria andUsrIdLessThanOrEqualTo(Long value) {
            addCriterion("usrId <=", value, "usrId");
            return (Criteria) this;
        }

        public Criteria andUsrIdIn(List<Long> values) {
            addCriterion("usrId in", values, "usrId");
            return (Criteria) this;
        }

        public Criteria andUsrIdNotIn(List<Long> values) {
            addCriterion("usrId not in", values, "usrId");
            return (Criteria) this;
        }

        public Criteria andUsrIdBetween(Long value1, Long value2) {
            addCriterion("usrId between", value1, value2, "usrId");
            return (Criteria) this;
        }

        public Criteria andUsrIdNotBetween(Long value1, Long value2) {
            addCriterion("usrId not between", value1, value2, "usrId");
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

        public Criteria andConsumptionDateIsNull() {
            addCriterion("consumptionDate is null");
            return (Criteria) this;
        }

        public Criteria andConsumptionDateIsNotNull() {
            addCriterion("consumptionDate is not null");
            return (Criteria) this;
        }

        public Criteria andConsumptionDateEqualTo(Date value) {
            addCriterion("consumptionDate =", value, "consumptionDate");
            return (Criteria) this;
        }

        public Criteria andConsumptionDateNotEqualTo(Date value) {
            addCriterion("consumptionDate <>", value, "consumptionDate");
            return (Criteria) this;
        }

        public Criteria andConsumptionDateGreaterThan(Date value) {
            addCriterion("consumptionDate >", value, "consumptionDate");
            return (Criteria) this;
        }

        public Criteria andConsumptionDateGreaterThanOrEqualTo(Date value) {
            addCriterion("consumptionDate >=", value, "consumptionDate");
            return (Criteria) this;
        }

        public Criteria andConsumptionDateLessThan(Date value) {
            addCriterion("consumptionDate <", value, "consumptionDate");
            return (Criteria) this;
        }

        public Criteria andConsumptionDateLessThanOrEqualTo(Date value) {
            addCriterion("consumptionDate <=", value, "consumptionDate");
            return (Criteria) this;
        }

        public Criteria andConsumptionDateIn(List<Date> values) {
            addCriterion("consumptionDate in", values, "consumptionDate");
            return (Criteria) this;
        }

        public Criteria andConsumptionDateNotIn(List<Date> values) {
            addCriterion("consumptionDate not in", values, "consumptionDate");
            return (Criteria) this;
        }

        public Criteria andConsumptionDateBetween(Date value1, Date value2) {
            addCriterion("consumptionDate between", value1, value2, "consumptionDate");
            return (Criteria) this;
        }

        public Criteria andConsumptionDateNotBetween(Date value1, Date value2) {
            addCriterion("consumptionDate not between", value1, value2, "consumptionDate");
            return (Criteria) this;
        }

        public Criteria andOrderIdIsNull() {
            addCriterion("orderId is null");
            return (Criteria) this;
        }

        public Criteria andOrderIdIsNotNull() {
            addCriterion("orderId is not null");
            return (Criteria) this;
        }

        public Criteria andOrderIdEqualTo(Long value) {
            addCriterion("orderId =", value, "orderId");
            return (Criteria) this;
        }

        public Criteria andOrderIdNotEqualTo(Long value) {
            addCriterion("orderId <>", value, "orderId");
            return (Criteria) this;
        }

        public Criteria andOrderIdGreaterThan(Long value) {
            addCriterion("orderId >", value, "orderId");
            return (Criteria) this;
        }

        public Criteria andOrderIdGreaterThanOrEqualTo(Long value) {
            addCriterion("orderId >=", value, "orderId");
            return (Criteria) this;
        }

        public Criteria andOrderIdLessThan(Long value) {
            addCriterion("orderId <", value, "orderId");
            return (Criteria) this;
        }

        public Criteria andOrderIdLessThanOrEqualTo(Long value) {
            addCriterion("orderId <=", value, "orderId");
            return (Criteria) this;
        }

        public Criteria andOrderIdIn(List<Long> values) {
            addCriterion("orderId in", values, "orderId");
            return (Criteria) this;
        }

        public Criteria andOrderIdNotIn(List<Long> values) {
            addCriterion("orderId not in", values, "orderId");
            return (Criteria) this;
        }

        public Criteria andOrderIdBetween(Long value1, Long value2) {
            addCriterion("orderId between", value1, value2, "orderId");
            return (Criteria) this;
        }

        public Criteria andOrderIdNotBetween(Long value1, Long value2) {
            addCriterion("orderId not between", value1, value2, "orderId");
            return (Criteria) this;
        }

        public Criteria andOrderNumIsNull() {
            addCriterion("orderNum is null");
            return (Criteria) this;
        }

        public Criteria andOrderNumIsNotNull() {
            addCriterion("orderNum is not null");
            return (Criteria) this;
        }

        public Criteria andOrderNumEqualTo(String value) {
            addCriterion("orderNum =", value, "orderNum");
            return (Criteria) this;
        }

        public Criteria andOrderNumNotEqualTo(String value) {
            addCriterion("orderNum <>", value, "orderNum");
            return (Criteria) this;
        }

        public Criteria andOrderNumGreaterThan(String value) {
            addCriterion("orderNum >", value, "orderNum");
            return (Criteria) this;
        }

        public Criteria andOrderNumGreaterThanOrEqualTo(String value) {
            addCriterion("orderNum >=", value, "orderNum");
            return (Criteria) this;
        }

        public Criteria andOrderNumLessThan(String value) {
            addCriterion("orderNum <", value, "orderNum");
            return (Criteria) this;
        }

        public Criteria andOrderNumLessThanOrEqualTo(String value) {
            addCriterion("orderNum <=", value, "orderNum");
            return (Criteria) this;
        }

        public Criteria andOrderNumLike(String value) {
            addCriterion("orderNum like", value, "orderNum");
            return (Criteria) this;
        }

        public Criteria andOrderNumNotLike(String value) {
            addCriterion("orderNum not like", value, "orderNum");
            return (Criteria) this;
        }

        public Criteria andOrderNumIn(List<String> values) {
            addCriterion("orderNum in", values, "orderNum");
            return (Criteria) this;
        }

        public Criteria andOrderNumNotIn(List<String> values) {
            addCriterion("orderNum not in", values, "orderNum");
            return (Criteria) this;
        }

        public Criteria andOrderNumBetween(String value1, String value2) {
            addCriterion("orderNum between", value1, value2, "orderNum");
            return (Criteria) this;
        }

        public Criteria andOrderNumNotBetween(String value1, String value2) {
            addCriterion("orderNum not between", value1, value2, "orderNum");
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