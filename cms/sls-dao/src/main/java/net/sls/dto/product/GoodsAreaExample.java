package net.sls.dto.product;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class GoodsAreaExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public GoodsAreaExample() {
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

        public Criteria andProvinceIdIsNull() {
            addCriterion("provinceId is null");
            return (Criteria) this;
        }

        public Criteria andProvinceIdIsNotNull() {
            addCriterion("provinceId is not null");
            return (Criteria) this;
        }

        public Criteria andProvinceIdEqualTo(Integer value) {
            addCriterion("provinceId =", value, "provinceId");
            return (Criteria) this;
        }

        public Criteria andProvinceIdNotEqualTo(Integer value) {
            addCriterion("provinceId <>", value, "provinceId");
            return (Criteria) this;
        }

        public Criteria andProvinceIdGreaterThan(Integer value) {
            addCriterion("provinceId >", value, "provinceId");
            return (Criteria) this;
        }

        public Criteria andProvinceIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("provinceId >=", value, "provinceId");
            return (Criteria) this;
        }

        public Criteria andProvinceIdLessThan(Integer value) {
            addCriterion("provinceId <", value, "provinceId");
            return (Criteria) this;
        }

        public Criteria andProvinceIdLessThanOrEqualTo(Integer value) {
            addCriterion("provinceId <=", value, "provinceId");
            return (Criteria) this;
        }

        public Criteria andProvinceIdIn(List<Integer> values) {
            addCriterion("provinceId in", values, "provinceId");
            return (Criteria) this;
        }

        public Criteria andProvinceIdNotIn(List<Integer> values) {
            addCriterion("provinceId not in", values, "provinceId");
            return (Criteria) this;
        }

        public Criteria andProvinceIdBetween(Integer value1, Integer value2) {
            addCriterion("provinceId between", value1, value2, "provinceId");
            return (Criteria) this;
        }

        public Criteria andProvinceIdNotBetween(Integer value1, Integer value2) {
            addCriterion("provinceId not between", value1, value2, "provinceId");
            return (Criteria) this;
        }

        public Criteria andCityIdIsNull() {
            addCriterion("cityId is null");
            return (Criteria) this;
        }

        public Criteria andCityIdIsNotNull() {
            addCriterion("cityId is not null");
            return (Criteria) this;
        }

        public Criteria andCityIdEqualTo(Integer value) {
            addCriterion("cityId =", value, "cityId");
            return (Criteria) this;
        }

        public Criteria andCityIdNotEqualTo(Integer value) {
            addCriterion("cityId <>", value, "cityId");
            return (Criteria) this;
        }

        public Criteria andCityIdGreaterThan(Integer value) {
            addCriterion("cityId >", value, "cityId");
            return (Criteria) this;
        }

        public Criteria andCityIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("cityId >=", value, "cityId");
            return (Criteria) this;
        }

        public Criteria andCityIdLessThan(Integer value) {
            addCriterion("cityId <", value, "cityId");
            return (Criteria) this;
        }

        public Criteria andCityIdLessThanOrEqualTo(Integer value) {
            addCriterion("cityId <=", value, "cityId");
            return (Criteria) this;
        }

        public Criteria andCityIdIn(List<Integer> values) {
            addCriterion("cityId in", values, "cityId");
            return (Criteria) this;
        }

        public Criteria andCityIdNotIn(List<Integer> values) {
            addCriterion("cityId not in", values, "cityId");
            return (Criteria) this;
        }

        public Criteria andCityIdBetween(Integer value1, Integer value2) {
            addCriterion("cityId between", value1, value2, "cityId");
            return (Criteria) this;
        }

        public Criteria andCityIdNotBetween(Integer value1, Integer value2) {
            addCriterion("cityId not between", value1, value2, "cityId");
            return (Criteria) this;
        }

        public Criteria andDistrictIdIsNull() {
            addCriterion("districtId is null");
            return (Criteria) this;
        }

        public Criteria andDistrictIdIsNotNull() {
            addCriterion("districtId is not null");
            return (Criteria) this;
        }

        public Criteria andDistrictIdEqualTo(Integer value) {
            addCriterion("districtId =", value, "districtId");
            return (Criteria) this;
        }

        public Criteria andDistrictIdNotEqualTo(Integer value) {
            addCriterion("districtId <>", value, "districtId");
            return (Criteria) this;
        }

        public Criteria andDistrictIdGreaterThan(Integer value) {
            addCriterion("districtId >", value, "districtId");
            return (Criteria) this;
        }

        public Criteria andDistrictIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("districtId >=", value, "districtId");
            return (Criteria) this;
        }

        public Criteria andDistrictIdLessThan(Integer value) {
            addCriterion("districtId <", value, "districtId");
            return (Criteria) this;
        }

        public Criteria andDistrictIdLessThanOrEqualTo(Integer value) {
            addCriterion("districtId <=", value, "districtId");
            return (Criteria) this;
        }

        public Criteria andDistrictIdIn(List<Integer> values) {
            addCriterion("districtId in", values, "districtId");
            return (Criteria) this;
        }

        public Criteria andDistrictIdNotIn(List<Integer> values) {
            addCriterion("districtId not in", values, "districtId");
            return (Criteria) this;
        }

        public Criteria andDistrictIdBetween(Integer value1, Integer value2) {
            addCriterion("districtId between", value1, value2, "districtId");
            return (Criteria) this;
        }

        public Criteria andDistrictIdNotBetween(Integer value1, Integer value2) {
            addCriterion("districtId not between", value1, value2, "districtId");
            return (Criteria) this;
        }

        public Criteria andCommunityIdIsNull() {
            addCriterion("communityId is null");
            return (Criteria) this;
        }

        public Criteria andCommunityIdIsNotNull() {
            addCriterion("communityId is not null");
            return (Criteria) this;
        }

        public Criteria andCommunityIdEqualTo(Integer value) {
            addCriterion("communityId =", value, "communityId");
            return (Criteria) this;
        }

        public Criteria andCommunityIdNotEqualTo(Integer value) {
            addCriterion("communityId <>", value, "communityId");
            return (Criteria) this;
        }

        public Criteria andCommunityIdGreaterThan(Integer value) {
            addCriterion("communityId >", value, "communityId");
            return (Criteria) this;
        }

        public Criteria andCommunityIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("communityId >=", value, "communityId");
            return (Criteria) this;
        }

        public Criteria andCommunityIdLessThan(Integer value) {
            addCriterion("communityId <", value, "communityId");
            return (Criteria) this;
        }

        public Criteria andCommunityIdLessThanOrEqualTo(Integer value) {
            addCriterion("communityId <=", value, "communityId");
            return (Criteria) this;
        }

        public Criteria andCommunityIdIn(List<Integer> values) {
            addCriterion("communityId in", values, "communityId");
            return (Criteria) this;
        }

        public Criteria andCommunityIdNotIn(List<Integer> values) {
            addCriterion("communityId not in", values, "communityId");
            return (Criteria) this;
        }

        public Criteria andCommunityIdBetween(Integer value1, Integer value2) {
            addCriterion("communityId between", value1, value2, "communityId");
            return (Criteria) this;
        }

        public Criteria andCommunityIdNotBetween(Integer value1, Integer value2) {
            addCriterion("communityId not between", value1, value2, "communityId");
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

        public Criteria andLimitCodeIsNull() {
            addCriterion("limitCode is null");
            return (Criteria) this;
        }

        public Criteria andLimitCodeIsNotNull() {
            addCriterion("limitCode is not null");
            return (Criteria) this;
        }

        public Criteria andLimitCodeEqualTo(String value) {
            addCriterion("limitCode =", value, "limitCode");
            return (Criteria) this;
        }

        public Criteria andLimitCodeNotEqualTo(String value) {
            addCriterion("limitCode <>", value, "limitCode");
            return (Criteria) this;
        }

        public Criteria andLimitCodeGreaterThan(String value) {
            addCriterion("limitCode >", value, "limitCode");
            return (Criteria) this;
        }

        public Criteria andLimitCodeGreaterThanOrEqualTo(String value) {
            addCriterion("limitCode >=", value, "limitCode");
            return (Criteria) this;
        }

        public Criteria andLimitCodeLessThan(String value) {
            addCriterion("limitCode <", value, "limitCode");
            return (Criteria) this;
        }

        public Criteria andLimitCodeLessThanOrEqualTo(String value) {
            addCriterion("limitCode <=", value, "limitCode");
            return (Criteria) this;
        }

        public Criteria andLimitCodeLike(String value) {
            addCriterion("limitCode like", value, "limitCode");
            return (Criteria) this;
        }

        public Criteria andLimitCodeNotLike(String value) {
            addCriterion("limitCode not like", value, "limitCode");
            return (Criteria) this;
        }

        public Criteria andLimitCodeIn(List<String> values) {
            addCriterion("limitCode in", values, "limitCode");
            return (Criteria) this;
        }

        public Criteria andLimitCodeNotIn(List<String> values) {
            addCriterion("limitCode not in", values, "limitCode");
            return (Criteria) this;
        }

        public Criteria andLimitCodeBetween(String value1, String value2) {
            addCriterion("limitCode between", value1, value2, "limitCode");
            return (Criteria) this;
        }

        public Criteria andLimitCodeNotBetween(String value1, String value2) {
            addCriterion("limitCode not between", value1, value2, "limitCode");
            return (Criteria) this;
        }

        public Criteria andIsLimitIsNull() {
            addCriterion("isLimit is null");
            return (Criteria) this;
        }

        public Criteria andIsLimitIsNotNull() {
            addCriterion("isLimit is not null");
            return (Criteria) this;
        }

        public Criteria andIsLimitEqualTo(Boolean value) {
            addCriterion("isLimit =", value, "isLimit");
            return (Criteria) this;
        }

        public Criteria andIsLimitNotEqualTo(Boolean value) {
            addCriterion("isLimit <>", value, "isLimit");
            return (Criteria) this;
        }

        public Criteria andIsLimitGreaterThan(Boolean value) {
            addCriterion("isLimit >", value, "isLimit");
            return (Criteria) this;
        }

        public Criteria andIsLimitGreaterThanOrEqualTo(Boolean value) {
            addCriterion("isLimit >=", value, "isLimit");
            return (Criteria) this;
        }

        public Criteria andIsLimitLessThan(Boolean value) {
            addCriterion("isLimit <", value, "isLimit");
            return (Criteria) this;
        }

        public Criteria andIsLimitLessThanOrEqualTo(Boolean value) {
            addCriterion("isLimit <=", value, "isLimit");
            return (Criteria) this;
        }

        public Criteria andIsLimitIn(List<Boolean> values) {
            addCriterion("isLimit in", values, "isLimit");
            return (Criteria) this;
        }

        public Criteria andIsLimitNotIn(List<Boolean> values) {
            addCriterion("isLimit not in", values, "isLimit");
            return (Criteria) this;
        }

        public Criteria andIsLimitBetween(Boolean value1, Boolean value2) {
            addCriterion("isLimit between", value1, value2, "isLimit");
            return (Criteria) this;
        }

        public Criteria andIsLimitNotBetween(Boolean value1, Boolean value2) {
            addCriterion("isLimit not between", value1, value2, "isLimit");
            return (Criteria) this;
        }

        public Criteria andNumberIsNull() {
            addCriterion("number is null");
            return (Criteria) this;
        }

        public Criteria andNumberIsNotNull() {
            addCriterion("number is not null");
            return (Criteria) this;
        }

        public Criteria andNumberEqualTo(Integer value) {
            addCriterion("number =", value, "number");
            return (Criteria) this;
        }

        public Criteria andNumberNotEqualTo(Integer value) {
            addCriterion("number <>", value, "number");
            return (Criteria) this;
        }

        public Criteria andNumberGreaterThan(Integer value) {
            addCriterion("number >", value, "number");
            return (Criteria) this;
        }

        public Criteria andNumberGreaterThanOrEqualTo(Integer value) {
            addCriterion("number >=", value, "number");
            return (Criteria) this;
        }

        public Criteria andNumberLessThan(Integer value) {
            addCriterion("number <", value, "number");
            return (Criteria) this;
        }

        public Criteria andNumberLessThanOrEqualTo(Integer value) {
            addCriterion("number <=", value, "number");
            return (Criteria) this;
        }

        public Criteria andNumberIn(List<Integer> values) {
            addCriterion("number in", values, "number");
            return (Criteria) this;
        }

        public Criteria andNumberNotIn(List<Integer> values) {
            addCriterion("number not in", values, "number");
            return (Criteria) this;
        }

        public Criteria andNumberBetween(Integer value1, Integer value2) {
            addCriterion("number between", value1, value2, "number");
            return (Criteria) this;
        }

        public Criteria andNumberNotBetween(Integer value1, Integer value2) {
            addCriterion("number not between", value1, value2, "number");
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