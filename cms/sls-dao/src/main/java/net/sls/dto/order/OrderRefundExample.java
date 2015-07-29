package net.sls.dto.order;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class OrderRefundExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public OrderRefundExample() {
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

        public Criteria andNewOrderIdIsNull() {
            addCriterion("newOrderId is null");
            return (Criteria) this;
        }

        public Criteria andNewOrderIdIsNotNull() {
            addCriterion("newOrderId is not null");
            return (Criteria) this;
        }

        public Criteria andNewOrderIdEqualTo(Long value) {
            addCriterion("newOrderId =", value, "newOrderId");
            return (Criteria) this;
        }

        public Criteria andNewOrderIdNotEqualTo(Long value) {
            addCriterion("newOrderId <>", value, "newOrderId");
            return (Criteria) this;
        }

        public Criteria andNewOrderIdGreaterThan(Long value) {
            addCriterion("newOrderId >", value, "newOrderId");
            return (Criteria) this;
        }

        public Criteria andNewOrderIdGreaterThanOrEqualTo(Long value) {
            addCriterion("newOrderId >=", value, "newOrderId");
            return (Criteria) this;
        }

        public Criteria andNewOrderIdLessThan(Long value) {
            addCriterion("newOrderId <", value, "newOrderId");
            return (Criteria) this;
        }

        public Criteria andNewOrderIdLessThanOrEqualTo(Long value) {
            addCriterion("newOrderId <=", value, "newOrderId");
            return (Criteria) this;
        }

        public Criteria andNewOrderIdIn(List<Long> values) {
            addCriterion("newOrderId in", values, "newOrderId");
            return (Criteria) this;
        }

        public Criteria andNewOrderIdNotIn(List<Long> values) {
            addCriterion("newOrderId not in", values, "newOrderId");
            return (Criteria) this;
        }

        public Criteria andNewOrderIdBetween(Long value1, Long value2) {
            addCriterion("newOrderId between", value1, value2, "newOrderId");
            return (Criteria) this;
        }

        public Criteria andNewOrderIdNotBetween(Long value1, Long value2) {
            addCriterion("newOrderId not between", value1, value2, "newOrderId");
            return (Criteria) this;
        }

        public Criteria andOrderDetailIdIsNull() {
            addCriterion("orderDetailId is null");
            return (Criteria) this;
        }

        public Criteria andOrderDetailIdIsNotNull() {
            addCriterion("orderDetailId is not null");
            return (Criteria) this;
        }

        public Criteria andOrderDetailIdEqualTo(Long value) {
            addCriterion("orderDetailId =", value, "orderDetailId");
            return (Criteria) this;
        }

        public Criteria andOrderDetailIdNotEqualTo(Long value) {
            addCriterion("orderDetailId <>", value, "orderDetailId");
            return (Criteria) this;
        }

        public Criteria andOrderDetailIdGreaterThan(Long value) {
            addCriterion("orderDetailId >", value, "orderDetailId");
            return (Criteria) this;
        }

        public Criteria andOrderDetailIdGreaterThanOrEqualTo(Long value) {
            addCriterion("orderDetailId >=", value, "orderDetailId");
            return (Criteria) this;
        }

        public Criteria andOrderDetailIdLessThan(Long value) {
            addCriterion("orderDetailId <", value, "orderDetailId");
            return (Criteria) this;
        }

        public Criteria andOrderDetailIdLessThanOrEqualTo(Long value) {
            addCriterion("orderDetailId <=", value, "orderDetailId");
            return (Criteria) this;
        }

        public Criteria andOrderDetailIdIn(List<Long> values) {
            addCriterion("orderDetailId in", values, "orderDetailId");
            return (Criteria) this;
        }

        public Criteria andOrderDetailIdNotIn(List<Long> values) {
            addCriterion("orderDetailId not in", values, "orderDetailId");
            return (Criteria) this;
        }

        public Criteria andOrderDetailIdBetween(Long value1, Long value2) {
            addCriterion("orderDetailId between", value1, value2, "orderDetailId");
            return (Criteria) this;
        }

        public Criteria andOrderDetailIdNotBetween(Long value1, Long value2) {
            addCriterion("orderDetailId not between", value1, value2, "orderDetailId");
            return (Criteria) this;
        }

        public Criteria andStateIsNull() {
            addCriterion("state is null");
            return (Criteria) this;
        }

        public Criteria andStateIsNotNull() {
            addCriterion("state is not null");
            return (Criteria) this;
        }

        public Criteria andStateEqualTo(Integer value) {
            addCriterion("state =", value, "state");
            return (Criteria) this;
        }

        public Criteria andStateNotEqualTo(Integer value) {
            addCriterion("state <>", value, "state");
            return (Criteria) this;
        }

        public Criteria andStateGreaterThan(Integer value) {
            addCriterion("state >", value, "state");
            return (Criteria) this;
        }

        public Criteria andStateGreaterThanOrEqualTo(Integer value) {
            addCriterion("state >=", value, "state");
            return (Criteria) this;
        }

        public Criteria andStateLessThan(Integer value) {
            addCriterion("state <", value, "state");
            return (Criteria) this;
        }

        public Criteria andStateLessThanOrEqualTo(Integer value) {
            addCriterion("state <=", value, "state");
            return (Criteria) this;
        }

        public Criteria andStateIn(List<Integer> values) {
            addCriterion("state in", values, "state");
            return (Criteria) this;
        }

        public Criteria andStateNotIn(List<Integer> values) {
            addCriterion("state not in", values, "state");
            return (Criteria) this;
        }

        public Criteria andStateBetween(Integer value1, Integer value2) {
            addCriterion("state between", value1, value2, "state");
            return (Criteria) this;
        }

        public Criteria andStateNotBetween(Integer value1, Integer value2) {
            addCriterion("state not between", value1, value2, "state");
            return (Criteria) this;
        }

        public Criteria andOriginIsNull() {
            addCriterion("origin is null");
            return (Criteria) this;
        }

        public Criteria andOriginIsNotNull() {
            addCriterion("origin is not null");
            return (Criteria) this;
        }

        public Criteria andOriginEqualTo(Integer value) {
            addCriterion("origin =", value, "origin");
            return (Criteria) this;
        }

        public Criteria andOriginNotEqualTo(Integer value) {
            addCriterion("origin <>", value, "origin");
            return (Criteria) this;
        }

        public Criteria andOriginGreaterThan(Integer value) {
            addCriterion("origin >", value, "origin");
            return (Criteria) this;
        }

        public Criteria andOriginGreaterThanOrEqualTo(Integer value) {
            addCriterion("origin >=", value, "origin");
            return (Criteria) this;
        }

        public Criteria andOriginLessThan(Integer value) {
            addCriterion("origin <", value, "origin");
            return (Criteria) this;
        }

        public Criteria andOriginLessThanOrEqualTo(Integer value) {
            addCriterion("origin <=", value, "origin");
            return (Criteria) this;
        }

        public Criteria andOriginIn(List<Integer> values) {
            addCriterion("origin in", values, "origin");
            return (Criteria) this;
        }

        public Criteria andOriginNotIn(List<Integer> values) {
            addCriterion("origin not in", values, "origin");
            return (Criteria) this;
        }

        public Criteria andOriginBetween(Integer value1, Integer value2) {
            addCriterion("origin between", value1, value2, "origin");
            return (Criteria) this;
        }

        public Criteria andOriginNotBetween(Integer value1, Integer value2) {
            addCriterion("origin not between", value1, value2, "origin");
            return (Criteria) this;
        }

        public Criteria andCreateTimeIsNull() {
            addCriterion("createTime is null");
            return (Criteria) this;
        }

        public Criteria andCreateTimeIsNotNull() {
            addCriterion("createTime is not null");
            return (Criteria) this;
        }

        public Criteria andCreateTimeEqualTo(Date value) {
            addCriterion("createTime =", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeNotEqualTo(Date value) {
            addCriterion("createTime <>", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeGreaterThan(Date value) {
            addCriterion("createTime >", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("createTime >=", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeLessThan(Date value) {
            addCriterion("createTime <", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeLessThanOrEqualTo(Date value) {
            addCriterion("createTime <=", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeIn(List<Date> values) {
            addCriterion("createTime in", values, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeNotIn(List<Date> values) {
            addCriterion("createTime not in", values, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeBetween(Date value1, Date value2) {
            addCriterion("createTime between", value1, value2, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeNotBetween(Date value1, Date value2) {
            addCriterion("createTime not between", value1, value2, "createTime");
            return (Criteria) this;
        }

        public Criteria andTypeIsNull() {
            addCriterion("type is null");
            return (Criteria) this;
        }

        public Criteria andTypeIsNotNull() {
            addCriterion("type is not null");
            return (Criteria) this;
        }

        public Criteria andTypeEqualTo(Integer value) {
            addCriterion("type =", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeNotEqualTo(Integer value) {
            addCriterion("type <>", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeGreaterThan(Integer value) {
            addCriterion("type >", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeGreaterThanOrEqualTo(Integer value) {
            addCriterion("type >=", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeLessThan(Integer value) {
            addCriterion("type <", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeLessThanOrEqualTo(Integer value) {
            addCriterion("type <=", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeIn(List<Integer> values) {
            addCriterion("type in", values, "type");
            return (Criteria) this;
        }

        public Criteria andTypeNotIn(List<Integer> values) {
            addCriterion("type not in", values, "type");
            return (Criteria) this;
        }

        public Criteria andTypeBetween(Integer value1, Integer value2) {
            addCriterion("type between", value1, value2, "type");
            return (Criteria) this;
        }

        public Criteria andTypeNotBetween(Integer value1, Integer value2) {
            addCriterion("type not between", value1, value2, "type");
            return (Criteria) this;
        }

        public Criteria andRefundPriceIsNull() {
            addCriterion("refundPrice is null");
            return (Criteria) this;
        }

        public Criteria andRefundPriceIsNotNull() {
            addCriterion("refundPrice is not null");
            return (Criteria) this;
        }

        public Criteria andRefundPriceEqualTo(BigDecimal value) {
            addCriterion("refundPrice =", value, "refundPrice");
            return (Criteria) this;
        }

        public Criteria andRefundPriceNotEqualTo(BigDecimal value) {
            addCriterion("refundPrice <>", value, "refundPrice");
            return (Criteria) this;
        }

        public Criteria andRefundPriceGreaterThan(BigDecimal value) {
            addCriterion("refundPrice >", value, "refundPrice");
            return (Criteria) this;
        }

        public Criteria andRefundPriceGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("refundPrice >=", value, "refundPrice");
            return (Criteria) this;
        }

        public Criteria andRefundPriceLessThan(BigDecimal value) {
            addCriterion("refundPrice <", value, "refundPrice");
            return (Criteria) this;
        }

        public Criteria andRefundPriceLessThanOrEqualTo(BigDecimal value) {
            addCriterion("refundPrice <=", value, "refundPrice");
            return (Criteria) this;
        }

        public Criteria andRefundPriceIn(List<BigDecimal> values) {
            addCriterion("refundPrice in", values, "refundPrice");
            return (Criteria) this;
        }

        public Criteria andRefundPriceNotIn(List<BigDecimal> values) {
            addCriterion("refundPrice not in", values, "refundPrice");
            return (Criteria) this;
        }

        public Criteria andRefundPriceBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("refundPrice between", value1, value2, "refundPrice");
            return (Criteria) this;
        }

        public Criteria andRefundPriceNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("refundPrice not between", value1, value2, "refundPrice");
            return (Criteria) this;
        }

        public Criteria andMoneyWayIsNull() {
            addCriterion("moneyWay is null");
            return (Criteria) this;
        }

        public Criteria andMoneyWayIsNotNull() {
            addCriterion("moneyWay is not null");
            return (Criteria) this;
        }

        public Criteria andMoneyWayEqualTo(Integer value) {
            addCriterion("moneyWay =", value, "moneyWay");
            return (Criteria) this;
        }

        public Criteria andMoneyWayNotEqualTo(Integer value) {
            addCriterion("moneyWay <>", value, "moneyWay");
            return (Criteria) this;
        }

        public Criteria andMoneyWayGreaterThan(Integer value) {
            addCriterion("moneyWay >", value, "moneyWay");
            return (Criteria) this;
        }

        public Criteria andMoneyWayGreaterThanOrEqualTo(Integer value) {
            addCriterion("moneyWay >=", value, "moneyWay");
            return (Criteria) this;
        }

        public Criteria andMoneyWayLessThan(Integer value) {
            addCriterion("moneyWay <", value, "moneyWay");
            return (Criteria) this;
        }

        public Criteria andMoneyWayLessThanOrEqualTo(Integer value) {
            addCriterion("moneyWay <=", value, "moneyWay");
            return (Criteria) this;
        }

        public Criteria andMoneyWayIn(List<Integer> values) {
            addCriterion("moneyWay in", values, "moneyWay");
            return (Criteria) this;
        }

        public Criteria andMoneyWayNotIn(List<Integer> values) {
            addCriterion("moneyWay not in", values, "moneyWay");
            return (Criteria) this;
        }

        public Criteria andMoneyWayBetween(Integer value1, Integer value2) {
            addCriterion("moneyWay between", value1, value2, "moneyWay");
            return (Criteria) this;
        }

        public Criteria andMoneyWayNotBetween(Integer value1, Integer value2) {
            addCriterion("moneyWay not between", value1, value2, "moneyWay");
            return (Criteria) this;
        }

        public Criteria andAccountIsNull() {
            addCriterion("account is null");
            return (Criteria) this;
        }

        public Criteria andAccountIsNotNull() {
            addCriterion("account is not null");
            return (Criteria) this;
        }

        public Criteria andAccountEqualTo(String value) {
            addCriterion("account =", value, "account");
            return (Criteria) this;
        }

        public Criteria andAccountNotEqualTo(String value) {
            addCriterion("account <>", value, "account");
            return (Criteria) this;
        }

        public Criteria andAccountGreaterThan(String value) {
            addCriterion("account >", value, "account");
            return (Criteria) this;
        }

        public Criteria andAccountGreaterThanOrEqualTo(String value) {
            addCriterion("account >=", value, "account");
            return (Criteria) this;
        }

        public Criteria andAccountLessThan(String value) {
            addCriterion("account <", value, "account");
            return (Criteria) this;
        }

        public Criteria andAccountLessThanOrEqualTo(String value) {
            addCriterion("account <=", value, "account");
            return (Criteria) this;
        }

        public Criteria andAccountLike(String value) {
            addCriterion("account like", value, "account");
            return (Criteria) this;
        }

        public Criteria andAccountNotLike(String value) {
            addCriterion("account not like", value, "account");
            return (Criteria) this;
        }

        public Criteria andAccountIn(List<String> values) {
            addCriterion("account in", values, "account");
            return (Criteria) this;
        }

        public Criteria andAccountNotIn(List<String> values) {
            addCriterion("account not in", values, "account");
            return (Criteria) this;
        }

        public Criteria andAccountBetween(String value1, String value2) {
            addCriterion("account between", value1, value2, "account");
            return (Criteria) this;
        }

        public Criteria andAccountNotBetween(String value1, String value2) {
            addCriterion("account not between", value1, value2, "account");
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

        public Criteria andPhotoUrl1IsNull() {
            addCriterion("photoUrl1 is null");
            return (Criteria) this;
        }

        public Criteria andPhotoUrl1IsNotNull() {
            addCriterion("photoUrl1 is not null");
            return (Criteria) this;
        }

        public Criteria andPhotoUrl1EqualTo(String value) {
            addCriterion("photoUrl1 =", value, "photoUrl1");
            return (Criteria) this;
        }

        public Criteria andPhotoUrl1NotEqualTo(String value) {
            addCriterion("photoUrl1 <>", value, "photoUrl1");
            return (Criteria) this;
        }

        public Criteria andPhotoUrl1GreaterThan(String value) {
            addCriterion("photoUrl1 >", value, "photoUrl1");
            return (Criteria) this;
        }

        public Criteria andPhotoUrl1GreaterThanOrEqualTo(String value) {
            addCriterion("photoUrl1 >=", value, "photoUrl1");
            return (Criteria) this;
        }

        public Criteria andPhotoUrl1LessThan(String value) {
            addCriterion("photoUrl1 <", value, "photoUrl1");
            return (Criteria) this;
        }

        public Criteria andPhotoUrl1LessThanOrEqualTo(String value) {
            addCriterion("photoUrl1 <=", value, "photoUrl1");
            return (Criteria) this;
        }

        public Criteria andPhotoUrl1Like(String value) {
            addCriterion("photoUrl1 like", value, "photoUrl1");
            return (Criteria) this;
        }

        public Criteria andPhotoUrl1NotLike(String value) {
            addCriterion("photoUrl1 not like", value, "photoUrl1");
            return (Criteria) this;
        }

        public Criteria andPhotoUrl1In(List<String> values) {
            addCriterion("photoUrl1 in", values, "photoUrl1");
            return (Criteria) this;
        }

        public Criteria andPhotoUrl1NotIn(List<String> values) {
            addCriterion("photoUrl1 not in", values, "photoUrl1");
            return (Criteria) this;
        }

        public Criteria andPhotoUrl1Between(String value1, String value2) {
            addCriterion("photoUrl1 between", value1, value2, "photoUrl1");
            return (Criteria) this;
        }

        public Criteria andPhotoUrl1NotBetween(String value1, String value2) {
            addCriterion("photoUrl1 not between", value1, value2, "photoUrl1");
            return (Criteria) this;
        }

        public Criteria andPhotoUrl2IsNull() {
            addCriterion("photoUrl2 is null");
            return (Criteria) this;
        }

        public Criteria andPhotoUrl2IsNotNull() {
            addCriterion("photoUrl2 is not null");
            return (Criteria) this;
        }

        public Criteria andPhotoUrl2EqualTo(String value) {
            addCriterion("photoUrl2 =", value, "photoUrl2");
            return (Criteria) this;
        }

        public Criteria andPhotoUrl2NotEqualTo(String value) {
            addCriterion("photoUrl2 <>", value, "photoUrl2");
            return (Criteria) this;
        }

        public Criteria andPhotoUrl2GreaterThan(String value) {
            addCriterion("photoUrl2 >", value, "photoUrl2");
            return (Criteria) this;
        }

        public Criteria andPhotoUrl2GreaterThanOrEqualTo(String value) {
            addCriterion("photoUrl2 >=", value, "photoUrl2");
            return (Criteria) this;
        }

        public Criteria andPhotoUrl2LessThan(String value) {
            addCriterion("photoUrl2 <", value, "photoUrl2");
            return (Criteria) this;
        }

        public Criteria andPhotoUrl2LessThanOrEqualTo(String value) {
            addCriterion("photoUrl2 <=", value, "photoUrl2");
            return (Criteria) this;
        }

        public Criteria andPhotoUrl2Like(String value) {
            addCriterion("photoUrl2 like", value, "photoUrl2");
            return (Criteria) this;
        }

        public Criteria andPhotoUrl2NotLike(String value) {
            addCriterion("photoUrl2 not like", value, "photoUrl2");
            return (Criteria) this;
        }

        public Criteria andPhotoUrl2In(List<String> values) {
            addCriterion("photoUrl2 in", values, "photoUrl2");
            return (Criteria) this;
        }

        public Criteria andPhotoUrl2NotIn(List<String> values) {
            addCriterion("photoUrl2 not in", values, "photoUrl2");
            return (Criteria) this;
        }

        public Criteria andPhotoUrl2Between(String value1, String value2) {
            addCriterion("photoUrl2 between", value1, value2, "photoUrl2");
            return (Criteria) this;
        }

        public Criteria andPhotoUrl2NotBetween(String value1, String value2) {
            addCriterion("photoUrl2 not between", value1, value2, "photoUrl2");
            return (Criteria) this;
        }

        public Criteria andPhotoUrl3IsNull() {
            addCriterion("photoUrl3 is null");
            return (Criteria) this;
        }

        public Criteria andPhotoUrl3IsNotNull() {
            addCriterion("photoUrl3 is not null");
            return (Criteria) this;
        }

        public Criteria andPhotoUrl3EqualTo(String value) {
            addCriterion("photoUrl3 =", value, "photoUrl3");
            return (Criteria) this;
        }

        public Criteria andPhotoUrl3NotEqualTo(String value) {
            addCriterion("photoUrl3 <>", value, "photoUrl3");
            return (Criteria) this;
        }

        public Criteria andPhotoUrl3GreaterThan(String value) {
            addCriterion("photoUrl3 >", value, "photoUrl3");
            return (Criteria) this;
        }

        public Criteria andPhotoUrl3GreaterThanOrEqualTo(String value) {
            addCriterion("photoUrl3 >=", value, "photoUrl3");
            return (Criteria) this;
        }

        public Criteria andPhotoUrl3LessThan(String value) {
            addCriterion("photoUrl3 <", value, "photoUrl3");
            return (Criteria) this;
        }

        public Criteria andPhotoUrl3LessThanOrEqualTo(String value) {
            addCriterion("photoUrl3 <=", value, "photoUrl3");
            return (Criteria) this;
        }

        public Criteria andPhotoUrl3Like(String value) {
            addCriterion("photoUrl3 like", value, "photoUrl3");
            return (Criteria) this;
        }

        public Criteria andPhotoUrl3NotLike(String value) {
            addCriterion("photoUrl3 not like", value, "photoUrl3");
            return (Criteria) this;
        }

        public Criteria andPhotoUrl3In(List<String> values) {
            addCriterion("photoUrl3 in", values, "photoUrl3");
            return (Criteria) this;
        }

        public Criteria andPhotoUrl3NotIn(List<String> values) {
            addCriterion("photoUrl3 not in", values, "photoUrl3");
            return (Criteria) this;
        }

        public Criteria andPhotoUrl3Between(String value1, String value2) {
            addCriterion("photoUrl3 between", value1, value2, "photoUrl3");
            return (Criteria) this;
        }

        public Criteria andPhotoUrl3NotBetween(String value1, String value2) {
            addCriterion("photoUrl3 not between", value1, value2, "photoUrl3");
            return (Criteria) this;
        }

        public Criteria andPickupWayIsNull() {
            addCriterion("pickupWay is null");
            return (Criteria) this;
        }

        public Criteria andPickupWayIsNotNull() {
            addCriterion("pickupWay is not null");
            return (Criteria) this;
        }

        public Criteria andPickupWayEqualTo(Integer value) {
            addCriterion("pickupWay =", value, "pickupWay");
            return (Criteria) this;
        }

        public Criteria andPickupWayNotEqualTo(Integer value) {
            addCriterion("pickupWay <>", value, "pickupWay");
            return (Criteria) this;
        }

        public Criteria andPickupWayGreaterThan(Integer value) {
            addCriterion("pickupWay >", value, "pickupWay");
            return (Criteria) this;
        }

        public Criteria andPickupWayGreaterThanOrEqualTo(Integer value) {
            addCriterion("pickupWay >=", value, "pickupWay");
            return (Criteria) this;
        }

        public Criteria andPickupWayLessThan(Integer value) {
            addCriterion("pickupWay <", value, "pickupWay");
            return (Criteria) this;
        }

        public Criteria andPickupWayLessThanOrEqualTo(Integer value) {
            addCriterion("pickupWay <=", value, "pickupWay");
            return (Criteria) this;
        }

        public Criteria andPickupWayIn(List<Integer> values) {
            addCriterion("pickupWay in", values, "pickupWay");
            return (Criteria) this;
        }

        public Criteria andPickupWayNotIn(List<Integer> values) {
            addCriterion("pickupWay not in", values, "pickupWay");
            return (Criteria) this;
        }

        public Criteria andPickupWayBetween(Integer value1, Integer value2) {
            addCriterion("pickupWay between", value1, value2, "pickupWay");
            return (Criteria) this;
        }

        public Criteria andPickupWayNotBetween(Integer value1, Integer value2) {
            addCriterion("pickupWay not between", value1, value2, "pickupWay");
            return (Criteria) this;
        }

        public Criteria andDeliveryTypeIsNull() {
            addCriterion("deliveryType is null");
            return (Criteria) this;
        }

        public Criteria andDeliveryTypeIsNotNull() {
            addCriterion("deliveryType is not null");
            return (Criteria) this;
        }

        public Criteria andDeliveryTypeEqualTo(Integer value) {
            addCriterion("deliveryType =", value, "deliveryType");
            return (Criteria) this;
        }

        public Criteria andDeliveryTypeNotEqualTo(Integer value) {
            addCriterion("deliveryType <>", value, "deliveryType");
            return (Criteria) this;
        }

        public Criteria andDeliveryTypeGreaterThan(Integer value) {
            addCriterion("deliveryType >", value, "deliveryType");
            return (Criteria) this;
        }

        public Criteria andDeliveryTypeGreaterThanOrEqualTo(Integer value) {
            addCriterion("deliveryType >=", value, "deliveryType");
            return (Criteria) this;
        }

        public Criteria andDeliveryTypeLessThan(Integer value) {
            addCriterion("deliveryType <", value, "deliveryType");
            return (Criteria) this;
        }

        public Criteria andDeliveryTypeLessThanOrEqualTo(Integer value) {
            addCriterion("deliveryType <=", value, "deliveryType");
            return (Criteria) this;
        }

        public Criteria andDeliveryTypeIn(List<Integer> values) {
            addCriterion("deliveryType in", values, "deliveryType");
            return (Criteria) this;
        }

        public Criteria andDeliveryTypeNotIn(List<Integer> values) {
            addCriterion("deliveryType not in", values, "deliveryType");
            return (Criteria) this;
        }

        public Criteria andDeliveryTypeBetween(Integer value1, Integer value2) {
            addCriterion("deliveryType between", value1, value2, "deliveryType");
            return (Criteria) this;
        }

        public Criteria andDeliveryTypeNotBetween(Integer value1, Integer value2) {
            addCriterion("deliveryType not between", value1, value2, "deliveryType");
            return (Criteria) this;
        }

        public Criteria andProvinceIdTIsNull() {
            addCriterion("provinceIdT is null");
            return (Criteria) this;
        }

        public Criteria andProvinceIdTIsNotNull() {
            addCriterion("provinceIdT is not null");
            return (Criteria) this;
        }

        public Criteria andProvinceIdTEqualTo(Integer value) {
            addCriterion("provinceIdT =", value, "provinceIdT");
            return (Criteria) this;
        }

        public Criteria andProvinceIdTNotEqualTo(Integer value) {
            addCriterion("provinceIdT <>", value, "provinceIdT");
            return (Criteria) this;
        }

        public Criteria andProvinceIdTGreaterThan(Integer value) {
            addCriterion("provinceIdT >", value, "provinceIdT");
            return (Criteria) this;
        }

        public Criteria andProvinceIdTGreaterThanOrEqualTo(Integer value) {
            addCriterion("provinceIdT >=", value, "provinceIdT");
            return (Criteria) this;
        }

        public Criteria andProvinceIdTLessThan(Integer value) {
            addCriterion("provinceIdT <", value, "provinceIdT");
            return (Criteria) this;
        }

        public Criteria andProvinceIdTLessThanOrEqualTo(Integer value) {
            addCriterion("provinceIdT <=", value, "provinceIdT");
            return (Criteria) this;
        }

        public Criteria andProvinceIdTIn(List<Integer> values) {
            addCriterion("provinceIdT in", values, "provinceIdT");
            return (Criteria) this;
        }

        public Criteria andProvinceIdTNotIn(List<Integer> values) {
            addCriterion("provinceIdT not in", values, "provinceIdT");
            return (Criteria) this;
        }

        public Criteria andProvinceIdTBetween(Integer value1, Integer value2) {
            addCriterion("provinceIdT between", value1, value2, "provinceIdT");
            return (Criteria) this;
        }

        public Criteria andProvinceIdTNotBetween(Integer value1, Integer value2) {
            addCriterion("provinceIdT not between", value1, value2, "provinceIdT");
            return (Criteria) this;
        }

        public Criteria andCityIdTIsNull() {
            addCriterion("cityIdT is null");
            return (Criteria) this;
        }

        public Criteria andCityIdTIsNotNull() {
            addCriterion("cityIdT is not null");
            return (Criteria) this;
        }

        public Criteria andCityIdTEqualTo(Integer value) {
            addCriterion("cityIdT =", value, "cityIdT");
            return (Criteria) this;
        }

        public Criteria andCityIdTNotEqualTo(Integer value) {
            addCriterion("cityIdT <>", value, "cityIdT");
            return (Criteria) this;
        }

        public Criteria andCityIdTGreaterThan(Integer value) {
            addCriterion("cityIdT >", value, "cityIdT");
            return (Criteria) this;
        }

        public Criteria andCityIdTGreaterThanOrEqualTo(Integer value) {
            addCriterion("cityIdT >=", value, "cityIdT");
            return (Criteria) this;
        }

        public Criteria andCityIdTLessThan(Integer value) {
            addCriterion("cityIdT <", value, "cityIdT");
            return (Criteria) this;
        }

        public Criteria andCityIdTLessThanOrEqualTo(Integer value) {
            addCriterion("cityIdT <=", value, "cityIdT");
            return (Criteria) this;
        }

        public Criteria andCityIdTIn(List<Integer> values) {
            addCriterion("cityIdT in", values, "cityIdT");
            return (Criteria) this;
        }

        public Criteria andCityIdTNotIn(List<Integer> values) {
            addCriterion("cityIdT not in", values, "cityIdT");
            return (Criteria) this;
        }

        public Criteria andCityIdTBetween(Integer value1, Integer value2) {
            addCriterion("cityIdT between", value1, value2, "cityIdT");
            return (Criteria) this;
        }

        public Criteria andCityIdTNotBetween(Integer value1, Integer value2) {
            addCriterion("cityIdT not between", value1, value2, "cityIdT");
            return (Criteria) this;
        }

        public Criteria andDistrictIdTIsNull() {
            addCriterion("districtIdT is null");
            return (Criteria) this;
        }

        public Criteria andDistrictIdTIsNotNull() {
            addCriterion("districtIdT is not null");
            return (Criteria) this;
        }

        public Criteria andDistrictIdTEqualTo(Integer value) {
            addCriterion("districtIdT =", value, "districtIdT");
            return (Criteria) this;
        }

        public Criteria andDistrictIdTNotEqualTo(Integer value) {
            addCriterion("districtIdT <>", value, "districtIdT");
            return (Criteria) this;
        }

        public Criteria andDistrictIdTGreaterThan(Integer value) {
            addCriterion("districtIdT >", value, "districtIdT");
            return (Criteria) this;
        }

        public Criteria andDistrictIdTGreaterThanOrEqualTo(Integer value) {
            addCriterion("districtIdT >=", value, "districtIdT");
            return (Criteria) this;
        }

        public Criteria andDistrictIdTLessThan(Integer value) {
            addCriterion("districtIdT <", value, "districtIdT");
            return (Criteria) this;
        }

        public Criteria andDistrictIdTLessThanOrEqualTo(Integer value) {
            addCriterion("districtIdT <=", value, "districtIdT");
            return (Criteria) this;
        }

        public Criteria andDistrictIdTIn(List<Integer> values) {
            addCriterion("districtIdT in", values, "districtIdT");
            return (Criteria) this;
        }

        public Criteria andDistrictIdTNotIn(List<Integer> values) {
            addCriterion("districtIdT not in", values, "districtIdT");
            return (Criteria) this;
        }

        public Criteria andDistrictIdTBetween(Integer value1, Integer value2) {
            addCriterion("districtIdT between", value1, value2, "districtIdT");
            return (Criteria) this;
        }

        public Criteria andDistrictIdTNotBetween(Integer value1, Integer value2) {
            addCriterion("districtIdT not between", value1, value2, "districtIdT");
            return (Criteria) this;
        }

        public Criteria andCommunityIdTIsNull() {
            addCriterion("communityIdT is null");
            return (Criteria) this;
        }

        public Criteria andCommunityIdTIsNotNull() {
            addCriterion("communityIdT is not null");
            return (Criteria) this;
        }

        public Criteria andCommunityIdTEqualTo(Integer value) {
            addCriterion("communityIdT =", value, "communityIdT");
            return (Criteria) this;
        }

        public Criteria andCommunityIdTNotEqualTo(Integer value) {
            addCriterion("communityIdT <>", value, "communityIdT");
            return (Criteria) this;
        }

        public Criteria andCommunityIdTGreaterThan(Integer value) {
            addCriterion("communityIdT >", value, "communityIdT");
            return (Criteria) this;
        }

        public Criteria andCommunityIdTGreaterThanOrEqualTo(Integer value) {
            addCriterion("communityIdT >=", value, "communityIdT");
            return (Criteria) this;
        }

        public Criteria andCommunityIdTLessThan(Integer value) {
            addCriterion("communityIdT <", value, "communityIdT");
            return (Criteria) this;
        }

        public Criteria andCommunityIdTLessThanOrEqualTo(Integer value) {
            addCriterion("communityIdT <=", value, "communityIdT");
            return (Criteria) this;
        }

        public Criteria andCommunityIdTIn(List<Integer> values) {
            addCriterion("communityIdT in", values, "communityIdT");
            return (Criteria) this;
        }

        public Criteria andCommunityIdTNotIn(List<Integer> values) {
            addCriterion("communityIdT not in", values, "communityIdT");
            return (Criteria) this;
        }

        public Criteria andCommunityIdTBetween(Integer value1, Integer value2) {
            addCriterion("communityIdT between", value1, value2, "communityIdT");
            return (Criteria) this;
        }

        public Criteria andCommunityIdTNotBetween(Integer value1, Integer value2) {
            addCriterion("communityIdT not between", value1, value2, "communityIdT");
            return (Criteria) this;
        }

        public Criteria andPavilionIdTIsNull() {
            addCriterion("pavilionIdT is null");
            return (Criteria) this;
        }

        public Criteria andPavilionIdTIsNotNull() {
            addCriterion("pavilionIdT is not null");
            return (Criteria) this;
        }

        public Criteria andPavilionIdTEqualTo(Integer value) {
            addCriterion("pavilionIdT =", value, "pavilionIdT");
            return (Criteria) this;
        }

        public Criteria andPavilionIdTNotEqualTo(Integer value) {
            addCriterion("pavilionIdT <>", value, "pavilionIdT");
            return (Criteria) this;
        }

        public Criteria andPavilionIdTGreaterThan(Integer value) {
            addCriterion("pavilionIdT >", value, "pavilionIdT");
            return (Criteria) this;
        }

        public Criteria andPavilionIdTGreaterThanOrEqualTo(Integer value) {
            addCriterion("pavilionIdT >=", value, "pavilionIdT");
            return (Criteria) this;
        }

        public Criteria andPavilionIdTLessThan(Integer value) {
            addCriterion("pavilionIdT <", value, "pavilionIdT");
            return (Criteria) this;
        }

        public Criteria andPavilionIdTLessThanOrEqualTo(Integer value) {
            addCriterion("pavilionIdT <=", value, "pavilionIdT");
            return (Criteria) this;
        }

        public Criteria andPavilionIdTIn(List<Integer> values) {
            addCriterion("pavilionIdT in", values, "pavilionIdT");
            return (Criteria) this;
        }

        public Criteria andPavilionIdTNotIn(List<Integer> values) {
            addCriterion("pavilionIdT not in", values, "pavilionIdT");
            return (Criteria) this;
        }

        public Criteria andPavilionIdTBetween(Integer value1, Integer value2) {
            addCriterion("pavilionIdT between", value1, value2, "pavilionIdT");
            return (Criteria) this;
        }

        public Criteria andPavilionIdTNotBetween(Integer value1, Integer value2) {
            addCriterion("pavilionIdT not between", value1, value2, "pavilionIdT");
            return (Criteria) this;
        }

        public Criteria andRemarkTIsNull() {
            addCriterion("remarkT is null");
            return (Criteria) this;
        }

        public Criteria andRemarkTIsNotNull() {
            addCriterion("remarkT is not null");
            return (Criteria) this;
        }

        public Criteria andRemarkTEqualTo(String value) {
            addCriterion("remarkT =", value, "remarkT");
            return (Criteria) this;
        }

        public Criteria andRemarkTNotEqualTo(String value) {
            addCriterion("remarkT <>", value, "remarkT");
            return (Criteria) this;
        }

        public Criteria andRemarkTGreaterThan(String value) {
            addCriterion("remarkT >", value, "remarkT");
            return (Criteria) this;
        }

        public Criteria andRemarkTGreaterThanOrEqualTo(String value) {
            addCriterion("remarkT >=", value, "remarkT");
            return (Criteria) this;
        }

        public Criteria andRemarkTLessThan(String value) {
            addCriterion("remarkT <", value, "remarkT");
            return (Criteria) this;
        }

        public Criteria andRemarkTLessThanOrEqualTo(String value) {
            addCriterion("remarkT <=", value, "remarkT");
            return (Criteria) this;
        }

        public Criteria andRemarkTLike(String value) {
            addCriterion("remarkT like", value, "remarkT");
            return (Criteria) this;
        }

        public Criteria andRemarkTNotLike(String value) {
            addCriterion("remarkT not like", value, "remarkT");
            return (Criteria) this;
        }

        public Criteria andRemarkTIn(List<String> values) {
            addCriterion("remarkT in", values, "remarkT");
            return (Criteria) this;
        }

        public Criteria andRemarkTNotIn(List<String> values) {
            addCriterion("remarkT not in", values, "remarkT");
            return (Criteria) this;
        }

        public Criteria andRemarkTBetween(String value1, String value2) {
            addCriterion("remarkT between", value1, value2, "remarkT");
            return (Criteria) this;
        }

        public Criteria andRemarkTNotBetween(String value1, String value2) {
            addCriterion("remarkT not between", value1, value2, "remarkT");
            return (Criteria) this;
        }

        public Criteria andReceiverTIsNull() {
            addCriterion("receiverT is null");
            return (Criteria) this;
        }

        public Criteria andReceiverTIsNotNull() {
            addCriterion("receiverT is not null");
            return (Criteria) this;
        }

        public Criteria andReceiverTEqualTo(String value) {
            addCriterion("receiverT =", value, "receiverT");
            return (Criteria) this;
        }

        public Criteria andReceiverTNotEqualTo(String value) {
            addCriterion("receiverT <>", value, "receiverT");
            return (Criteria) this;
        }

        public Criteria andReceiverTGreaterThan(String value) {
            addCriterion("receiverT >", value, "receiverT");
            return (Criteria) this;
        }

        public Criteria andReceiverTGreaterThanOrEqualTo(String value) {
            addCriterion("receiverT >=", value, "receiverT");
            return (Criteria) this;
        }

        public Criteria andReceiverTLessThan(String value) {
            addCriterion("receiverT <", value, "receiverT");
            return (Criteria) this;
        }

        public Criteria andReceiverTLessThanOrEqualTo(String value) {
            addCriterion("receiverT <=", value, "receiverT");
            return (Criteria) this;
        }

        public Criteria andReceiverTLike(String value) {
            addCriterion("receiverT like", value, "receiverT");
            return (Criteria) this;
        }

        public Criteria andReceiverTNotLike(String value) {
            addCriterion("receiverT not like", value, "receiverT");
            return (Criteria) this;
        }

        public Criteria andReceiverTIn(List<String> values) {
            addCriterion("receiverT in", values, "receiverT");
            return (Criteria) this;
        }

        public Criteria andReceiverTNotIn(List<String> values) {
            addCriterion("receiverT not in", values, "receiverT");
            return (Criteria) this;
        }

        public Criteria andReceiverTBetween(String value1, String value2) {
            addCriterion("receiverT between", value1, value2, "receiverT");
            return (Criteria) this;
        }

        public Criteria andReceiverTNotBetween(String value1, String value2) {
            addCriterion("receiverT not between", value1, value2, "receiverT");
            return (Criteria) this;
        }

        public Criteria andMobileTIsNull() {
            addCriterion("mobileT is null");
            return (Criteria) this;
        }

        public Criteria andMobileTIsNotNull() {
            addCriterion("mobileT is not null");
            return (Criteria) this;
        }

        public Criteria andMobileTEqualTo(String value) {
            addCriterion("mobileT =", value, "mobileT");
            return (Criteria) this;
        }

        public Criteria andMobileTNotEqualTo(String value) {
            addCriterion("mobileT <>", value, "mobileT");
            return (Criteria) this;
        }

        public Criteria andMobileTGreaterThan(String value) {
            addCriterion("mobileT >", value, "mobileT");
            return (Criteria) this;
        }

        public Criteria andMobileTGreaterThanOrEqualTo(String value) {
            addCriterion("mobileT >=", value, "mobileT");
            return (Criteria) this;
        }

        public Criteria andMobileTLessThan(String value) {
            addCriterion("mobileT <", value, "mobileT");
            return (Criteria) this;
        }

        public Criteria andMobileTLessThanOrEqualTo(String value) {
            addCriterion("mobileT <=", value, "mobileT");
            return (Criteria) this;
        }

        public Criteria andMobileTLike(String value) {
            addCriterion("mobileT like", value, "mobileT");
            return (Criteria) this;
        }

        public Criteria andMobileTNotLike(String value) {
            addCriterion("mobileT not like", value, "mobileT");
            return (Criteria) this;
        }

        public Criteria andMobileTIn(List<String> values) {
            addCriterion("mobileT in", values, "mobileT");
            return (Criteria) this;
        }

        public Criteria andMobileTNotIn(List<String> values) {
            addCriterion("mobileT not in", values, "mobileT");
            return (Criteria) this;
        }

        public Criteria andMobileTBetween(String value1, String value2) {
            addCriterion("mobileT between", value1, value2, "mobileT");
            return (Criteria) this;
        }

        public Criteria andMobileTNotBetween(String value1, String value2) {
            addCriterion("mobileT not between", value1, value2, "mobileT");
            return (Criteria) this;
        }

        public Criteria andProvinceIdFIsNull() {
            addCriterion("provinceIdF is null");
            return (Criteria) this;
        }

        public Criteria andProvinceIdFIsNotNull() {
            addCriterion("provinceIdF is not null");
            return (Criteria) this;
        }

        public Criteria andProvinceIdFEqualTo(Integer value) {
            addCriterion("provinceIdF =", value, "provinceIdF");
            return (Criteria) this;
        }

        public Criteria andProvinceIdFNotEqualTo(Integer value) {
            addCriterion("provinceIdF <>", value, "provinceIdF");
            return (Criteria) this;
        }

        public Criteria andProvinceIdFGreaterThan(Integer value) {
            addCriterion("provinceIdF >", value, "provinceIdF");
            return (Criteria) this;
        }

        public Criteria andProvinceIdFGreaterThanOrEqualTo(Integer value) {
            addCriterion("provinceIdF >=", value, "provinceIdF");
            return (Criteria) this;
        }

        public Criteria andProvinceIdFLessThan(Integer value) {
            addCriterion("provinceIdF <", value, "provinceIdF");
            return (Criteria) this;
        }

        public Criteria andProvinceIdFLessThanOrEqualTo(Integer value) {
            addCriterion("provinceIdF <=", value, "provinceIdF");
            return (Criteria) this;
        }

        public Criteria andProvinceIdFIn(List<Integer> values) {
            addCriterion("provinceIdF in", values, "provinceIdF");
            return (Criteria) this;
        }

        public Criteria andProvinceIdFNotIn(List<Integer> values) {
            addCriterion("provinceIdF not in", values, "provinceIdF");
            return (Criteria) this;
        }

        public Criteria andProvinceIdFBetween(Integer value1, Integer value2) {
            addCriterion("provinceIdF between", value1, value2, "provinceIdF");
            return (Criteria) this;
        }

        public Criteria andProvinceIdFNotBetween(Integer value1, Integer value2) {
            addCriterion("provinceIdF not between", value1, value2, "provinceIdF");
            return (Criteria) this;
        }

        public Criteria andCityIdFIsNull() {
            addCriterion("cityIdF is null");
            return (Criteria) this;
        }

        public Criteria andCityIdFIsNotNull() {
            addCriterion("cityIdF is not null");
            return (Criteria) this;
        }

        public Criteria andCityIdFEqualTo(Integer value) {
            addCriterion("cityIdF =", value, "cityIdF");
            return (Criteria) this;
        }

        public Criteria andCityIdFNotEqualTo(Integer value) {
            addCriterion("cityIdF <>", value, "cityIdF");
            return (Criteria) this;
        }

        public Criteria andCityIdFGreaterThan(Integer value) {
            addCriterion("cityIdF >", value, "cityIdF");
            return (Criteria) this;
        }

        public Criteria andCityIdFGreaterThanOrEqualTo(Integer value) {
            addCriterion("cityIdF >=", value, "cityIdF");
            return (Criteria) this;
        }

        public Criteria andCityIdFLessThan(Integer value) {
            addCriterion("cityIdF <", value, "cityIdF");
            return (Criteria) this;
        }

        public Criteria andCityIdFLessThanOrEqualTo(Integer value) {
            addCriterion("cityIdF <=", value, "cityIdF");
            return (Criteria) this;
        }

        public Criteria andCityIdFIn(List<Integer> values) {
            addCriterion("cityIdF in", values, "cityIdF");
            return (Criteria) this;
        }

        public Criteria andCityIdFNotIn(List<Integer> values) {
            addCriterion("cityIdF not in", values, "cityIdF");
            return (Criteria) this;
        }

        public Criteria andCityIdFBetween(Integer value1, Integer value2) {
            addCriterion("cityIdF between", value1, value2, "cityIdF");
            return (Criteria) this;
        }

        public Criteria andCityIdFNotBetween(Integer value1, Integer value2) {
            addCriterion("cityIdF not between", value1, value2, "cityIdF");
            return (Criteria) this;
        }

        public Criteria andDistrictIdFIsNull() {
            addCriterion("districtIdF is null");
            return (Criteria) this;
        }

        public Criteria andDistrictIdFIsNotNull() {
            addCriterion("districtIdF is not null");
            return (Criteria) this;
        }

        public Criteria andDistrictIdFEqualTo(Integer value) {
            addCriterion("districtIdF =", value, "districtIdF");
            return (Criteria) this;
        }

        public Criteria andDistrictIdFNotEqualTo(Integer value) {
            addCriterion("districtIdF <>", value, "districtIdF");
            return (Criteria) this;
        }

        public Criteria andDistrictIdFGreaterThan(Integer value) {
            addCriterion("districtIdF >", value, "districtIdF");
            return (Criteria) this;
        }

        public Criteria andDistrictIdFGreaterThanOrEqualTo(Integer value) {
            addCriterion("districtIdF >=", value, "districtIdF");
            return (Criteria) this;
        }

        public Criteria andDistrictIdFLessThan(Integer value) {
            addCriterion("districtIdF <", value, "districtIdF");
            return (Criteria) this;
        }

        public Criteria andDistrictIdFLessThanOrEqualTo(Integer value) {
            addCriterion("districtIdF <=", value, "districtIdF");
            return (Criteria) this;
        }

        public Criteria andDistrictIdFIn(List<Integer> values) {
            addCriterion("districtIdF in", values, "districtIdF");
            return (Criteria) this;
        }

        public Criteria andDistrictIdFNotIn(List<Integer> values) {
            addCriterion("districtIdF not in", values, "districtIdF");
            return (Criteria) this;
        }

        public Criteria andDistrictIdFBetween(Integer value1, Integer value2) {
            addCriterion("districtIdF between", value1, value2, "districtIdF");
            return (Criteria) this;
        }

        public Criteria andDistrictIdFNotBetween(Integer value1, Integer value2) {
            addCriterion("districtIdF not between", value1, value2, "districtIdF");
            return (Criteria) this;
        }

        public Criteria andCommunityIdFIsNull() {
            addCriterion("communityIdF is null");
            return (Criteria) this;
        }

        public Criteria andCommunityIdFIsNotNull() {
            addCriterion("communityIdF is not null");
            return (Criteria) this;
        }

        public Criteria andCommunityIdFEqualTo(Integer value) {
            addCriterion("communityIdF =", value, "communityIdF");
            return (Criteria) this;
        }

        public Criteria andCommunityIdFNotEqualTo(Integer value) {
            addCriterion("communityIdF <>", value, "communityIdF");
            return (Criteria) this;
        }

        public Criteria andCommunityIdFGreaterThan(Integer value) {
            addCriterion("communityIdF >", value, "communityIdF");
            return (Criteria) this;
        }

        public Criteria andCommunityIdFGreaterThanOrEqualTo(Integer value) {
            addCriterion("communityIdF >=", value, "communityIdF");
            return (Criteria) this;
        }

        public Criteria andCommunityIdFLessThan(Integer value) {
            addCriterion("communityIdF <", value, "communityIdF");
            return (Criteria) this;
        }

        public Criteria andCommunityIdFLessThanOrEqualTo(Integer value) {
            addCriterion("communityIdF <=", value, "communityIdF");
            return (Criteria) this;
        }

        public Criteria andCommunityIdFIn(List<Integer> values) {
            addCriterion("communityIdF in", values, "communityIdF");
            return (Criteria) this;
        }

        public Criteria andCommunityIdFNotIn(List<Integer> values) {
            addCriterion("communityIdF not in", values, "communityIdF");
            return (Criteria) this;
        }

        public Criteria andCommunityIdFBetween(Integer value1, Integer value2) {
            addCriterion("communityIdF between", value1, value2, "communityIdF");
            return (Criteria) this;
        }

        public Criteria andCommunityIdFNotBetween(Integer value1, Integer value2) {
            addCriterion("communityIdF not between", value1, value2, "communityIdF");
            return (Criteria) this;
        }

        public Criteria andPavilionIdFIsNull() {
            addCriterion("pavilionIdF is null");
            return (Criteria) this;
        }

        public Criteria andPavilionIdFIsNotNull() {
            addCriterion("pavilionIdF is not null");
            return (Criteria) this;
        }

        public Criteria andPavilionIdFEqualTo(Integer value) {
            addCriterion("pavilionIdF =", value, "pavilionIdF");
            return (Criteria) this;
        }

        public Criteria andPavilionIdFNotEqualTo(Integer value) {
            addCriterion("pavilionIdF <>", value, "pavilionIdF");
            return (Criteria) this;
        }

        public Criteria andPavilionIdFGreaterThan(Integer value) {
            addCriterion("pavilionIdF >", value, "pavilionIdF");
            return (Criteria) this;
        }

        public Criteria andPavilionIdFGreaterThanOrEqualTo(Integer value) {
            addCriterion("pavilionIdF >=", value, "pavilionIdF");
            return (Criteria) this;
        }

        public Criteria andPavilionIdFLessThan(Integer value) {
            addCriterion("pavilionIdF <", value, "pavilionIdF");
            return (Criteria) this;
        }

        public Criteria andPavilionIdFLessThanOrEqualTo(Integer value) {
            addCriterion("pavilionIdF <=", value, "pavilionIdF");
            return (Criteria) this;
        }

        public Criteria andPavilionIdFIn(List<Integer> values) {
            addCriterion("pavilionIdF in", values, "pavilionIdF");
            return (Criteria) this;
        }

        public Criteria andPavilionIdFNotIn(List<Integer> values) {
            addCriterion("pavilionIdF not in", values, "pavilionIdF");
            return (Criteria) this;
        }

        public Criteria andPavilionIdFBetween(Integer value1, Integer value2) {
            addCriterion("pavilionIdF between", value1, value2, "pavilionIdF");
            return (Criteria) this;
        }

        public Criteria andPavilionIdFNotBetween(Integer value1, Integer value2) {
            addCriterion("pavilionIdF not between", value1, value2, "pavilionIdF");
            return (Criteria) this;
        }

        public Criteria andRemarkFIsNull() {
            addCriterion("remarkF is null");
            return (Criteria) this;
        }

        public Criteria andRemarkFIsNotNull() {
            addCriterion("remarkF is not null");
            return (Criteria) this;
        }

        public Criteria andRemarkFEqualTo(String value) {
            addCriterion("remarkF =", value, "remarkF");
            return (Criteria) this;
        }

        public Criteria andRemarkFNotEqualTo(String value) {
            addCriterion("remarkF <>", value, "remarkF");
            return (Criteria) this;
        }

        public Criteria andRemarkFGreaterThan(String value) {
            addCriterion("remarkF >", value, "remarkF");
            return (Criteria) this;
        }

        public Criteria andRemarkFGreaterThanOrEqualTo(String value) {
            addCriterion("remarkF >=", value, "remarkF");
            return (Criteria) this;
        }

        public Criteria andRemarkFLessThan(String value) {
            addCriterion("remarkF <", value, "remarkF");
            return (Criteria) this;
        }

        public Criteria andRemarkFLessThanOrEqualTo(String value) {
            addCriterion("remarkF <=", value, "remarkF");
            return (Criteria) this;
        }

        public Criteria andRemarkFLike(String value) {
            addCriterion("remarkF like", value, "remarkF");
            return (Criteria) this;
        }

        public Criteria andRemarkFNotLike(String value) {
            addCriterion("remarkF not like", value, "remarkF");
            return (Criteria) this;
        }

        public Criteria andRemarkFIn(List<String> values) {
            addCriterion("remarkF in", values, "remarkF");
            return (Criteria) this;
        }

        public Criteria andRemarkFNotIn(List<String> values) {
            addCriterion("remarkF not in", values, "remarkF");
            return (Criteria) this;
        }

        public Criteria andRemarkFBetween(String value1, String value2) {
            addCriterion("remarkF between", value1, value2, "remarkF");
            return (Criteria) this;
        }

        public Criteria andRemarkFNotBetween(String value1, String value2) {
            addCriterion("remarkF not between", value1, value2, "remarkF");
            return (Criteria) this;
        }

        public Criteria andReceiverFIsNull() {
            addCriterion("receiverF is null");
            return (Criteria) this;
        }

        public Criteria andReceiverFIsNotNull() {
            addCriterion("receiverF is not null");
            return (Criteria) this;
        }

        public Criteria andReceiverFEqualTo(String value) {
            addCriterion("receiverF =", value, "receiverF");
            return (Criteria) this;
        }

        public Criteria andReceiverFNotEqualTo(String value) {
            addCriterion("receiverF <>", value, "receiverF");
            return (Criteria) this;
        }

        public Criteria andReceiverFGreaterThan(String value) {
            addCriterion("receiverF >", value, "receiverF");
            return (Criteria) this;
        }

        public Criteria andReceiverFGreaterThanOrEqualTo(String value) {
            addCriterion("receiverF >=", value, "receiverF");
            return (Criteria) this;
        }

        public Criteria andReceiverFLessThan(String value) {
            addCriterion("receiverF <", value, "receiverF");
            return (Criteria) this;
        }

        public Criteria andReceiverFLessThanOrEqualTo(String value) {
            addCriterion("receiverF <=", value, "receiverF");
            return (Criteria) this;
        }

        public Criteria andReceiverFLike(String value) {
            addCriterion("receiverF like", value, "receiverF");
            return (Criteria) this;
        }

        public Criteria andReceiverFNotLike(String value) {
            addCriterion("receiverF not like", value, "receiverF");
            return (Criteria) this;
        }

        public Criteria andReceiverFIn(List<String> values) {
            addCriterion("receiverF in", values, "receiverF");
            return (Criteria) this;
        }

        public Criteria andReceiverFNotIn(List<String> values) {
            addCriterion("receiverF not in", values, "receiverF");
            return (Criteria) this;
        }

        public Criteria andReceiverFBetween(String value1, String value2) {
            addCriterion("receiverF between", value1, value2, "receiverF");
            return (Criteria) this;
        }

        public Criteria andReceiverFNotBetween(String value1, String value2) {
            addCriterion("receiverF not between", value1, value2, "receiverF");
            return (Criteria) this;
        }

        public Criteria andMobileFIsNull() {
            addCriterion("mobileF is null");
            return (Criteria) this;
        }

        public Criteria andMobileFIsNotNull() {
            addCriterion("mobileF is not null");
            return (Criteria) this;
        }

        public Criteria andMobileFEqualTo(String value) {
            addCriterion("mobileF =", value, "mobileF");
            return (Criteria) this;
        }

        public Criteria andMobileFNotEqualTo(String value) {
            addCriterion("mobileF <>", value, "mobileF");
            return (Criteria) this;
        }

        public Criteria andMobileFGreaterThan(String value) {
            addCriterion("mobileF >", value, "mobileF");
            return (Criteria) this;
        }

        public Criteria andMobileFGreaterThanOrEqualTo(String value) {
            addCriterion("mobileF >=", value, "mobileF");
            return (Criteria) this;
        }

        public Criteria andMobileFLessThan(String value) {
            addCriterion("mobileF <", value, "mobileF");
            return (Criteria) this;
        }

        public Criteria andMobileFLessThanOrEqualTo(String value) {
            addCriterion("mobileF <=", value, "mobileF");
            return (Criteria) this;
        }

        public Criteria andMobileFLike(String value) {
            addCriterion("mobileF like", value, "mobileF");
            return (Criteria) this;
        }

        public Criteria andMobileFNotLike(String value) {
            addCriterion("mobileF not like", value, "mobileF");
            return (Criteria) this;
        }

        public Criteria andMobileFIn(List<String> values) {
            addCriterion("mobileF in", values, "mobileF");
            return (Criteria) this;
        }

        public Criteria andMobileFNotIn(List<String> values) {
            addCriterion("mobileF not in", values, "mobileF");
            return (Criteria) this;
        }

        public Criteria andMobileFBetween(String value1, String value2) {
            addCriterion("mobileF between", value1, value2, "mobileF");
            return (Criteria) this;
        }

        public Criteria andMobileFNotBetween(String value1, String value2) {
            addCriterion("mobileF not between", value1, value2, "mobileF");
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