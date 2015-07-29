package net.sls.dto.product;

import java.util.ArrayList;
import java.util.List;

public class PavilionAreaExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public PavilionAreaExample() {
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

        public Criteria andPavilionIdIsNull() {
            addCriterion("pavilionId is null");
            return (Criteria) this;
        }

        public Criteria andPavilionIdIsNotNull() {
            addCriterion("pavilionId is not null");
            return (Criteria) this;
        }

        public Criteria andPavilionIdEqualTo(Integer value) {
            addCriterion("pavilionId =", value, "pavilionId");
            return (Criteria) this;
        }

        public Criteria andPavilionIdNotEqualTo(Integer value) {
            addCriterion("pavilionId <>", value, "pavilionId");
            return (Criteria) this;
        }

        public Criteria andPavilionIdGreaterThan(Integer value) {
            addCriterion("pavilionId >", value, "pavilionId");
            return (Criteria) this;
        }

        public Criteria andPavilionIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("pavilionId >=", value, "pavilionId");
            return (Criteria) this;
        }

        public Criteria andPavilionIdLessThan(Integer value) {
            addCriterion("pavilionId <", value, "pavilionId");
            return (Criteria) this;
        }

        public Criteria andPavilionIdLessThanOrEqualTo(Integer value) {
            addCriterion("pavilionId <=", value, "pavilionId");
            return (Criteria) this;
        }

        public Criteria andPavilionIdIn(List<Integer> values) {
            addCriterion("pavilionId in", values, "pavilionId");
            return (Criteria) this;
        }

        public Criteria andPavilionIdNotIn(List<Integer> values) {
            addCriterion("pavilionId not in", values, "pavilionId");
            return (Criteria) this;
        }

        public Criteria andPavilionIdBetween(Integer value1, Integer value2) {
            addCriterion("pavilionId between", value1, value2, "pavilionId");
            return (Criteria) this;
        }

        public Criteria andPavilionIdNotBetween(Integer value1, Integer value2) {
            addCriterion("pavilionId not between", value1, value2, "pavilionId");
            return (Criteria) this;
        }

        public Criteria andPaNameIsNull() {
            addCriterion("paName is null");
            return (Criteria) this;
        }

        public Criteria andPaNameIsNotNull() {
            addCriterion("paName is not null");
            return (Criteria) this;
        }

        public Criteria andPaNameEqualTo(String value) {
            addCriterion("paName =", value, "paName");
            return (Criteria) this;
        }

        public Criteria andPaNameNotEqualTo(String value) {
            addCriterion("paName <>", value, "paName");
            return (Criteria) this;
        }

        public Criteria andPaNameGreaterThan(String value) {
            addCriterion("paName >", value, "paName");
            return (Criteria) this;
        }

        public Criteria andPaNameGreaterThanOrEqualTo(String value) {
            addCriterion("paName >=", value, "paName");
            return (Criteria) this;
        }

        public Criteria andPaNameLessThan(String value) {
            addCriterion("paName <", value, "paName");
            return (Criteria) this;
        }

        public Criteria andPaNameLessThanOrEqualTo(String value) {
            addCriterion("paName <=", value, "paName");
            return (Criteria) this;
        }

        public Criteria andPaNameLike(String value) {
            addCriterion("paName like", value, "paName");
            return (Criteria) this;
        }

        public Criteria andPaNameNotLike(String value) {
            addCriterion("paName not like", value, "paName");
            return (Criteria) this;
        }

        public Criteria andPaNameIn(List<String> values) {
            addCriterion("paName in", values, "paName");
            return (Criteria) this;
        }

        public Criteria andPaNameNotIn(List<String> values) {
            addCriterion("paName not in", values, "paName");
            return (Criteria) this;
        }

        public Criteria andPaNameBetween(String value1, String value2) {
            addCriterion("paName between", value1, value2, "paName");
            return (Criteria) this;
        }

        public Criteria andPaNameNotBetween(String value1, String value2) {
            addCriterion("paName not between", value1, value2, "paName");
            return (Criteria) this;
        }

        public Criteria andIsUseIsNull() {
            addCriterion("isUse is null");
            return (Criteria) this;
        }

        public Criteria andIsUseIsNotNull() {
            addCriterion("isUse is not null");
            return (Criteria) this;
        }

        public Criteria andIsUseEqualTo(Integer value) {
            addCriterion("isUse =", value, "isUse");
            return (Criteria) this;
        }

        public Criteria andIsUseNotEqualTo(Integer value) {
            addCriterion("isUse <>", value, "isUse");
            return (Criteria) this;
        }

        public Criteria andIsUseGreaterThan(Integer value) {
            addCriterion("isUse >", value, "isUse");
            return (Criteria) this;
        }

        public Criteria andIsUseGreaterThanOrEqualTo(Integer value) {
            addCriterion("isUse >=", value, "isUse");
            return (Criteria) this;
        }

        public Criteria andIsUseLessThan(Integer value) {
            addCriterion("isUse <", value, "isUse");
            return (Criteria) this;
        }

        public Criteria andIsUseLessThanOrEqualTo(Integer value) {
            addCriterion("isUse <=", value, "isUse");
            return (Criteria) this;
        }

        public Criteria andIsUseIn(List<Integer> values) {
            addCriterion("isUse in", values, "isUse");
            return (Criteria) this;
        }

        public Criteria andIsUseNotIn(List<Integer> values) {
            addCriterion("isUse not in", values, "isUse");
            return (Criteria) this;
        }

        public Criteria andIsUseBetween(Integer value1, Integer value2) {
            addCriterion("isUse between", value1, value2, "isUse");
            return (Criteria) this;
        }

        public Criteria andIsUseNotBetween(Integer value1, Integer value2) {
            addCriterion("isUse not between", value1, value2, "isUse");
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