package net.sls.dto.product;

import java.util.ArrayList;
import java.util.List;

public class GoodsDetailExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public GoodsDetailExample() {
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

        public Criteria andDescriptionIsNull() {
            addCriterion("description is null");
            return (Criteria) this;
        }

        public Criteria andDescriptionIsNotNull() {
            addCriterion("description is not null");
            return (Criteria) this;
        }

        public Criteria andDescriptionEqualTo(String value) {
            addCriterion("description =", value, "description");
            return (Criteria) this;
        }

        public Criteria andDescriptionNotEqualTo(String value) {
            addCriterion("description <>", value, "description");
            return (Criteria) this;
        }

        public Criteria andDescriptionGreaterThan(String value) {
            addCriterion("description >", value, "description");
            return (Criteria) this;
        }

        public Criteria andDescriptionGreaterThanOrEqualTo(String value) {
            addCriterion("description >=", value, "description");
            return (Criteria) this;
        }

        public Criteria andDescriptionLessThan(String value) {
            addCriterion("description <", value, "description");
            return (Criteria) this;
        }

        public Criteria andDescriptionLessThanOrEqualTo(String value) {
            addCriterion("description <=", value, "description");
            return (Criteria) this;
        }

        public Criteria andDescriptionLike(String value) {
            addCriterion("description like", value, "description");
            return (Criteria) this;
        }

        public Criteria andDescriptionNotLike(String value) {
            addCriterion("description not like", value, "description");
            return (Criteria) this;
        }

        public Criteria andDescriptionIn(List<String> values) {
            addCriterion("description in", values, "description");
            return (Criteria) this;
        }

        public Criteria andDescriptionNotIn(List<String> values) {
            addCriterion("description not in", values, "description");
            return (Criteria) this;
        }

        public Criteria andDescriptionBetween(String value1, String value2) {
            addCriterion("description between", value1, value2, "description");
            return (Criteria) this;
        }

        public Criteria andDescriptionNotBetween(String value1, String value2) {
            addCriterion("description not between", value1, value2, "description");
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

        public Criteria andPhotoUrl4IsNull() {
            addCriterion("photoUrl4 is null");
            return (Criteria) this;
        }

        public Criteria andPhotoUrl4IsNotNull() {
            addCriterion("photoUrl4 is not null");
            return (Criteria) this;
        }

        public Criteria andPhotoUrl4EqualTo(String value) {
            addCriterion("photoUrl4 =", value, "photoUrl4");
            return (Criteria) this;
        }

        public Criteria andPhotoUrl4NotEqualTo(String value) {
            addCriterion("photoUrl4 <>", value, "photoUrl4");
            return (Criteria) this;
        }

        public Criteria andPhotoUrl4GreaterThan(String value) {
            addCriterion("photoUrl4 >", value, "photoUrl4");
            return (Criteria) this;
        }

        public Criteria andPhotoUrl4GreaterThanOrEqualTo(String value) {
            addCriterion("photoUrl4 >=", value, "photoUrl4");
            return (Criteria) this;
        }

        public Criteria andPhotoUrl4LessThan(String value) {
            addCriterion("photoUrl4 <", value, "photoUrl4");
            return (Criteria) this;
        }

        public Criteria andPhotoUrl4LessThanOrEqualTo(String value) {
            addCriterion("photoUrl4 <=", value, "photoUrl4");
            return (Criteria) this;
        }

        public Criteria andPhotoUrl4Like(String value) {
            addCriterion("photoUrl4 like", value, "photoUrl4");
            return (Criteria) this;
        }

        public Criteria andPhotoUrl4NotLike(String value) {
            addCriterion("photoUrl4 not like", value, "photoUrl4");
            return (Criteria) this;
        }

        public Criteria andPhotoUrl4In(List<String> values) {
            addCriterion("photoUrl4 in", values, "photoUrl4");
            return (Criteria) this;
        }

        public Criteria andPhotoUrl4NotIn(List<String> values) {
            addCriterion("photoUrl4 not in", values, "photoUrl4");
            return (Criteria) this;
        }

        public Criteria andPhotoUrl4Between(String value1, String value2) {
            addCriterion("photoUrl4 between", value1, value2, "photoUrl4");
            return (Criteria) this;
        }

        public Criteria andPhotoUrl4NotBetween(String value1, String value2) {
            addCriterion("photoUrl4 not between", value1, value2, "photoUrl4");
            return (Criteria) this;
        }

        public Criteria andPhotoUrlIsNull() {
            addCriterion("photoUrl is null");
            return (Criteria) this;
        }

        public Criteria andPhotoUrlIsNotNull() {
            addCriterion("photoUrl is not null");
            return (Criteria) this;
        }

        public Criteria andPhotoUrlEqualTo(String value) {
            addCriterion("photoUrl =", value, "photoUrl");
            return (Criteria) this;
        }

        public Criteria andPhotoUrlNotEqualTo(String value) {
            addCriterion("photoUrl <>", value, "photoUrl");
            return (Criteria) this;
        }

        public Criteria andPhotoUrlGreaterThan(String value) {
            addCriterion("photoUrl >", value, "photoUrl");
            return (Criteria) this;
        }

        public Criteria andPhotoUrlGreaterThanOrEqualTo(String value) {
            addCriterion("photoUrl >=", value, "photoUrl");
            return (Criteria) this;
        }

        public Criteria andPhotoUrlLessThan(String value) {
            addCriterion("photoUrl <", value, "photoUrl");
            return (Criteria) this;
        }

        public Criteria andPhotoUrlLessThanOrEqualTo(String value) {
            addCriterion("photoUrl <=", value, "photoUrl");
            return (Criteria) this;
        }

        public Criteria andPhotoUrlLike(String value) {
            addCriterion("photoUrl like", value, "photoUrl");
            return (Criteria) this;
        }

        public Criteria andPhotoUrlNotLike(String value) {
            addCriterion("photoUrl not like", value, "photoUrl");
            return (Criteria) this;
        }

        public Criteria andPhotoUrlIn(List<String> values) {
            addCriterion("photoUrl in", values, "photoUrl");
            return (Criteria) this;
        }

        public Criteria andPhotoUrlNotIn(List<String> values) {
            addCriterion("photoUrl not in", values, "photoUrl");
            return (Criteria) this;
        }

        public Criteria andPhotoUrlBetween(String value1, String value2) {
            addCriterion("photoUrl between", value1, value2, "photoUrl");
            return (Criteria) this;
        }

        public Criteria andPhotoUrlNotBetween(String value1, String value2) {
            addCriterion("photoUrl not between", value1, value2, "photoUrl");
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