package net.sls.dto.product;

import java.util.ArrayList;
import java.util.List;

public class GoodsCategoryExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public GoodsCategoryExample() {
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

        public Criteria andOneIdIsNull() {
            addCriterion("oneId is null");
            return (Criteria) this;
        }

        public Criteria andOneIdIsNotNull() {
            addCriterion("oneId is not null");
            return (Criteria) this;
        }

        public Criteria andOneIdEqualTo(Long value) {
            addCriterion("oneId =", value, "oneId");
            return (Criteria) this;
        }

        public Criteria andOneIdNotEqualTo(Long value) {
            addCriterion("oneId <>", value, "oneId");
            return (Criteria) this;
        }

        public Criteria andOneIdGreaterThan(Long value) {
            addCriterion("oneId >", value, "oneId");
            return (Criteria) this;
        }

        public Criteria andOneIdGreaterThanOrEqualTo(Long value) {
            addCriterion("oneId >=", value, "oneId");
            return (Criteria) this;
        }

        public Criteria andOneIdLessThan(Long value) {
            addCriterion("oneId <", value, "oneId");
            return (Criteria) this;
        }

        public Criteria andOneIdLessThanOrEqualTo(Long value) {
            addCriterion("oneId <=", value, "oneId");
            return (Criteria) this;
        }

        public Criteria andOneIdIn(List<Long> values) {
            addCriterion("oneId in", values, "oneId");
            return (Criteria) this;
        }

        public Criteria andOneIdNotIn(List<Long> values) {
            addCriterion("oneId not in", values, "oneId");
            return (Criteria) this;
        }

        public Criteria andOneIdBetween(Long value1, Long value2) {
            addCriterion("oneId between", value1, value2, "oneId");
            return (Criteria) this;
        }

        public Criteria andOneIdNotBetween(Long value1, Long value2) {
            addCriterion("oneId not between", value1, value2, "oneId");
            return (Criteria) this;
        }

        public Criteria andTwoIdIsNull() {
            addCriterion("twoId is null");
            return (Criteria) this;
        }

        public Criteria andTwoIdIsNotNull() {
            addCriterion("twoId is not null");
            return (Criteria) this;
        }

        public Criteria andTwoIdEqualTo(Long value) {
            addCriterion("twoId =", value, "twoId");
            return (Criteria) this;
        }

        public Criteria andTwoIdNotEqualTo(Long value) {
            addCriterion("twoId <>", value, "twoId");
            return (Criteria) this;
        }

        public Criteria andTwoIdGreaterThan(Long value) {
            addCriterion("twoId >", value, "twoId");
            return (Criteria) this;
        }

        public Criteria andTwoIdGreaterThanOrEqualTo(Long value) {
            addCriterion("twoId >=", value, "twoId");
            return (Criteria) this;
        }

        public Criteria andTwoIdLessThan(Long value) {
            addCriterion("twoId <", value, "twoId");
            return (Criteria) this;
        }

        public Criteria andTwoIdLessThanOrEqualTo(Long value) {
            addCriterion("twoId <=", value, "twoId");
            return (Criteria) this;
        }

        public Criteria andTwoIdIn(List<Long> values) {
            addCriterion("twoId in", values, "twoId");
            return (Criteria) this;
        }

        public Criteria andTwoIdNotIn(List<Long> values) {
            addCriterion("twoId not in", values, "twoId");
            return (Criteria) this;
        }

        public Criteria andTwoIdBetween(Long value1, Long value2) {
            addCriterion("twoId between", value1, value2, "twoId");
            return (Criteria) this;
        }

        public Criteria andTwoIdNotBetween(Long value1, Long value2) {
            addCriterion("twoId not between", value1, value2, "twoId");
            return (Criteria) this;
        }

        public Criteria andThreeIdIsNull() {
            addCriterion("threeId is null");
            return (Criteria) this;
        }

        public Criteria andThreeIdIsNotNull() {
            addCriterion("threeId is not null");
            return (Criteria) this;
        }

        public Criteria andThreeIdEqualTo(Long value) {
            addCriterion("threeId =", value, "threeId");
            return (Criteria) this;
        }

        public Criteria andThreeIdNotEqualTo(Long value) {
            addCriterion("threeId <>", value, "threeId");
            return (Criteria) this;
        }

        public Criteria andThreeIdGreaterThan(Long value) {
            addCriterion("threeId >", value, "threeId");
            return (Criteria) this;
        }

        public Criteria andThreeIdGreaterThanOrEqualTo(Long value) {
            addCriterion("threeId >=", value, "threeId");
            return (Criteria) this;
        }

        public Criteria andThreeIdLessThan(Long value) {
            addCriterion("threeId <", value, "threeId");
            return (Criteria) this;
        }

        public Criteria andThreeIdLessThanOrEqualTo(Long value) {
            addCriterion("threeId <=", value, "threeId");
            return (Criteria) this;
        }

        public Criteria andThreeIdIn(List<Long> values) {
            addCriterion("threeId in", values, "threeId");
            return (Criteria) this;
        }

        public Criteria andThreeIdNotIn(List<Long> values) {
            addCriterion("threeId not in", values, "threeId");
            return (Criteria) this;
        }

        public Criteria andThreeIdBetween(Long value1, Long value2) {
            addCriterion("threeId between", value1, value2, "threeId");
            return (Criteria) this;
        }

        public Criteria andThreeIdNotBetween(Long value1, Long value2) {
            addCriterion("threeId not between", value1, value2, "threeId");
            return (Criteria) this;
        }

        public Criteria andGoodsIdIsNull() {
            addCriterion("goodsId is null");
            return (Criteria) this;
        }

        public Criteria andGoodsIdIsNotNull() {
            addCriterion("goodsId is not null");
            return (Criteria) this;
        }

        public Criteria andGoodsIdEqualTo(Long value) {
            addCriterion("goodsId =", value, "goodsId");
            return (Criteria) this;
        }

        public Criteria andGoodsIdNotEqualTo(Long value) {
            addCriterion("goodsId <>", value, "goodsId");
            return (Criteria) this;
        }

        public Criteria andGoodsIdGreaterThan(Long value) {
            addCriterion("goodsId >", value, "goodsId");
            return (Criteria) this;
        }

        public Criteria andGoodsIdGreaterThanOrEqualTo(Long value) {
            addCriterion("goodsId >=", value, "goodsId");
            return (Criteria) this;
        }

        public Criteria andGoodsIdLessThan(Long value) {
            addCriterion("goodsId <", value, "goodsId");
            return (Criteria) this;
        }

        public Criteria andGoodsIdLessThanOrEqualTo(Long value) {
            addCriterion("goodsId <=", value, "goodsId");
            return (Criteria) this;
        }

        public Criteria andGoodsIdIn(List<Long> values) {
            addCriterion("goodsId in", values, "goodsId");
            return (Criteria) this;
        }

        public Criteria andGoodsIdNotIn(List<Long> values) {
            addCriterion("goodsId not in", values, "goodsId");
            return (Criteria) this;
        }

        public Criteria andGoodsIdBetween(Long value1, Long value2) {
            addCriterion("goodsId between", value1, value2, "goodsId");
            return (Criteria) this;
        }

        public Criteria andGoodsIdNotBetween(Long value1, Long value2) {
            addCriterion("goodsId not between", value1, value2, "goodsId");
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