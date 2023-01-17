package io.tortoise.base.domain;

import java.util.ArrayList;
import java.util.List;

public class DatasetTableFieldExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public DatasetTableFieldExample() {
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

        public Criteria andIdEqualTo(String value) {
            addCriterion("id =", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotEqualTo(String value) {
            addCriterion("id <>", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThan(String value) {
            addCriterion("id >", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThanOrEqualTo(String value) {
            addCriterion("id >=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThan(String value) {
            addCriterion("id <", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThanOrEqualTo(String value) {
            addCriterion("id <=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLike(String value) {
            addCriterion("id like", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotLike(String value) {
            addCriterion("id not like", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdIn(List<String> values) {
            addCriterion("id in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotIn(List<String> values) {
            addCriterion("id not in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdBetween(String value1, String value2) {
            addCriterion("id between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotBetween(String value1, String value2) {
            addCriterion("id not between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andTableIdIsNull() {
            addCriterion("table_id is null");
            return (Criteria) this;
        }

        public Criteria andTableIdIsNotNull() {
            addCriterion("table_id is not null");
            return (Criteria) this;
        }

        public Criteria andTableIdEqualTo(String value) {
            addCriterion("table_id =", value, "tableId");
            return (Criteria) this;
        }

        public Criteria andTableIdNotEqualTo(String value) {
            addCriterion("table_id <>", value, "tableId");
            return (Criteria) this;
        }

        public Criteria andTableIdGreaterThan(String value) {
            addCriterion("table_id >", value, "tableId");
            return (Criteria) this;
        }

        public Criteria andTableIdGreaterThanOrEqualTo(String value) {
            addCriterion("table_id >=", value, "tableId");
            return (Criteria) this;
        }

        public Criteria andTableIdLessThan(String value) {
            addCriterion("table_id <", value, "tableId");
            return (Criteria) this;
        }

        public Criteria andTableIdLessThanOrEqualTo(String value) {
            addCriterion("table_id <=", value, "tableId");
            return (Criteria) this;
        }

        public Criteria andTableIdLike(String value) {
            addCriterion("table_id like", value, "tableId");
            return (Criteria) this;
        }

        public Criteria andTableIdNotLike(String value) {
            addCriterion("table_id not like", value, "tableId");
            return (Criteria) this;
        }

        public Criteria andTableIdIn(List<String> values) {
            addCriterion("table_id in", values, "tableId");
            return (Criteria) this;
        }

        public Criteria andTableIdNotIn(List<String> values) {
            addCriterion("table_id not in", values, "tableId");
            return (Criteria) this;
        }

        public Criteria andTableIdBetween(String value1, String value2) {
            addCriterion("table_id between", value1, value2, "tableId");
            return (Criteria) this;
        }

        public Criteria andTableIdNotBetween(String value1, String value2) {
            addCriterion("table_id not between", value1, value2, "tableId");
            return (Criteria) this;
        }

        public Criteria andOriginNameIsNull() {
            addCriterion("origin_name is null");
            return (Criteria) this;
        }

        public Criteria andOriginNameIsNotNull() {
            addCriterion("origin_name is not null");
            return (Criteria) this;
        }

        public Criteria andOriginNameEqualTo(String value) {
            addCriterion("origin_name =", value, "originName");
            return (Criteria) this;
        }

        public Criteria andOriginNameNotEqualTo(String value) {
            addCriterion("origin_name <>", value, "originName");
            return (Criteria) this;
        }

        public Criteria andOriginNameGreaterThan(String value) {
            addCriterion("origin_name >", value, "originName");
            return (Criteria) this;
        }

        public Criteria andOriginNameGreaterThanOrEqualTo(String value) {
            addCriterion("origin_name >=", value, "originName");
            return (Criteria) this;
        }

        public Criteria andOriginNameLessThan(String value) {
            addCriterion("origin_name <", value, "originName");
            return (Criteria) this;
        }

        public Criteria andOriginNameLessThanOrEqualTo(String value) {
            addCriterion("origin_name <=", value, "originName");
            return (Criteria) this;
        }

        public Criteria andOriginNameLike(String value) {
            addCriterion("origin_name like", value, "originName");
            return (Criteria) this;
        }

        public Criteria andOriginNameNotLike(String value) {
            addCriterion("origin_name not like", value, "originName");
            return (Criteria) this;
        }

        public Criteria andOriginNameIn(List<String> values) {
            addCriterion("origin_name in", values, "originName");
            return (Criteria) this;
        }

        public Criteria andOriginNameNotIn(List<String> values) {
            addCriterion("origin_name not in", values, "originName");
            return (Criteria) this;
        }

        public Criteria andOriginNameBetween(String value1, String value2) {
            addCriterion("origin_name between", value1, value2, "originName");
            return (Criteria) this;
        }

        public Criteria andOriginNameNotBetween(String value1, String value2) {
            addCriterion("origin_name not between", value1, value2, "originName");
            return (Criteria) this;
        }

        public Criteria andNameIsNull() {
            addCriterion("`name` is null");
            return (Criteria) this;
        }

        public Criteria andNameIsNotNull() {
            addCriterion("`name` is not null");
            return (Criteria) this;
        }

        public Criteria andNameEqualTo(String value) {
            addCriterion("`name` =", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameNotEqualTo(String value) {
            addCriterion("`name` <>", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameGreaterThan(String value) {
            addCriterion("`name` >", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameGreaterThanOrEqualTo(String value) {
            addCriterion("`name` >=", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameLessThan(String value) {
            addCriterion("`name` <", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameLessThanOrEqualTo(String value) {
            addCriterion("`name` <=", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameLike(String value) {
            addCriterion("`name` like", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameNotLike(String value) {
            addCriterion("`name` not like", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameIn(List<String> values) {
            addCriterion("`name` in", values, "name");
            return (Criteria) this;
        }

        public Criteria andNameNotIn(List<String> values) {
            addCriterion("`name` not in", values, "name");
            return (Criteria) this;
        }

        public Criteria andNameBetween(String value1, String value2) {
            addCriterion("`name` between", value1, value2, "name");
            return (Criteria) this;
        }

        public Criteria andNameNotBetween(String value1, String value2) {
            addCriterion("`name` not between", value1, value2, "name");
            return (Criteria) this;
        }

        public Criteria andTortoiseNameIsNull() {
            addCriterion("tortoise_name is null");
            return (Criteria) this;
        }

        public Criteria andTortoiseNameIsNotNull() {
            addCriterion("tortoise_name is not null");
            return (Criteria) this;
        }

        public Criteria andTortoiseNameEqualTo(String value) {
            addCriterion("tortoise_name =", value, "tortoiseName");
            return (Criteria) this;
        }

        public Criteria andTortoiseNameNotEqualTo(String value) {
            addCriterion("tortoise_name <>", value, "tortoiseName");
            return (Criteria) this;
        }

        public Criteria andTortoiseNameGreaterThan(String value) {
            addCriterion("tortoise_name >", value, "tortoiseName");
            return (Criteria) this;
        }

        public Criteria andTortoiseNameGreaterThanOrEqualTo(String value) {
            addCriterion("tortoise_name >=", value, "tortoiseName");
            return (Criteria) this;
        }

        public Criteria andTortoiseNameLessThan(String value) {
            addCriterion("tortoise_name <", value, "tortoiseName");
            return (Criteria) this;
        }

        public Criteria andTortoiseNameLessThanOrEqualTo(String value) {
            addCriterion("tortoise_name <=", value, "tortoiseName");
            return (Criteria) this;
        }

        public Criteria andTortoiseNameLike(String value) {
            addCriterion("tortoise_name like", value, "tortoiseName");
            return (Criteria) this;
        }

        public Criteria andTortoiseNameNotLike(String value) {
            addCriterion("tortoise_name not like", value, "tortoiseName");
            return (Criteria) this;
        }

        public Criteria andTortoiseNameIn(List<String> values) {
            addCriterion("tortoise_name in", values, "tortoiseName");
            return (Criteria) this;
        }

        public Criteria andTortoiseNameNotIn(List<String> values) {
            addCriterion("tortoise_name not in", values, "tortoiseName");
            return (Criteria) this;
        }

        public Criteria andTortoiseNameBetween(String value1, String value2) {
            addCriterion("tortoise_name between", value1, value2, "tortoiseName");
            return (Criteria) this;
        }

        public Criteria andTortoiseNameNotBetween(String value1, String value2) {
            addCriterion("tortoise_name not between", value1, value2, "tortoiseName");
            return (Criteria) this;
        }

        public Criteria andTypeIsNull() {
            addCriterion("`type` is null");
            return (Criteria) this;
        }

        public Criteria andTypeIsNotNull() {
            addCriterion("`type` is not null");
            return (Criteria) this;
        }

        public Criteria andTypeEqualTo(String value) {
            addCriterion("`type` =", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeNotEqualTo(String value) {
            addCriterion("`type` <>", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeGreaterThan(String value) {
            addCriterion("`type` >", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeGreaterThanOrEqualTo(String value) {
            addCriterion("`type` >=", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeLessThan(String value) {
            addCriterion("`type` <", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeLessThanOrEqualTo(String value) {
            addCriterion("`type` <=", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeLike(String value) {
            addCriterion("`type` like", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeNotLike(String value) {
            addCriterion("`type` not like", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeIn(List<String> values) {
            addCriterion("`type` in", values, "type");
            return (Criteria) this;
        }

        public Criteria andTypeNotIn(List<String> values) {
            addCriterion("`type` not in", values, "type");
            return (Criteria) this;
        }

        public Criteria andTypeBetween(String value1, String value2) {
            addCriterion("`type` between", value1, value2, "type");
            return (Criteria) this;
        }

        public Criteria andTypeNotBetween(String value1, String value2) {
            addCriterion("`type` not between", value1, value2, "type");
            return (Criteria) this;
        }

        public Criteria andSizeIsNull() {
            addCriterion("`size` is null");
            return (Criteria) this;
        }

        public Criteria andSizeIsNotNull() {
            addCriterion("`size` is not null");
            return (Criteria) this;
        }

        public Criteria andSizeEqualTo(Integer value) {
            addCriterion("`size` =", value, "size");
            return (Criteria) this;
        }

        public Criteria andSizeNotEqualTo(Integer value) {
            addCriterion("`size` <>", value, "size");
            return (Criteria) this;
        }

        public Criteria andSizeGreaterThan(Integer value) {
            addCriterion("`size` >", value, "size");
            return (Criteria) this;
        }

        public Criteria andSizeGreaterThanOrEqualTo(Integer value) {
            addCriterion("`size` >=", value, "size");
            return (Criteria) this;
        }

        public Criteria andSizeLessThan(Integer value) {
            addCriterion("`size` <", value, "size");
            return (Criteria) this;
        }

        public Criteria andSizeLessThanOrEqualTo(Integer value) {
            addCriterion("`size` <=", value, "size");
            return (Criteria) this;
        }

        public Criteria andSizeIn(List<Integer> values) {
            addCriterion("`size` in", values, "size");
            return (Criteria) this;
        }

        public Criteria andSizeNotIn(List<Integer> values) {
            addCriterion("`size` not in", values, "size");
            return (Criteria) this;
        }

        public Criteria andSizeBetween(Integer value1, Integer value2) {
            addCriterion("`size` between", value1, value2, "size");
            return (Criteria) this;
        }

        public Criteria andSizeNotBetween(Integer value1, Integer value2) {
            addCriterion("`size` not between", value1, value2, "size");
            return (Criteria) this;
        }

        public Criteria andDeTypeIsNull() {
            addCriterion("de_type is null");
            return (Criteria) this;
        }

        public Criteria andDeTypeIsNotNull() {
            addCriterion("de_type is not null");
            return (Criteria) this;
        }

        public Criteria andDeTypeEqualTo(Integer value) {
            addCriterion("de_type =", value, "deType");
            return (Criteria) this;
        }

        public Criteria andDeTypeNotEqualTo(Integer value) {
            addCriterion("de_type <>", value, "deType");
            return (Criteria) this;
        }

        public Criteria andDeTypeGreaterThan(Integer value) {
            addCriterion("de_type >", value, "deType");
            return (Criteria) this;
        }

        public Criteria andDeTypeGreaterThanOrEqualTo(Integer value) {
            addCriterion("de_type >=", value, "deType");
            return (Criteria) this;
        }

        public Criteria andDeTypeLessThan(Integer value) {
            addCriterion("de_type <", value, "deType");
            return (Criteria) this;
        }

        public Criteria andDeTypeLessThanOrEqualTo(Integer value) {
            addCriterion("de_type <=", value, "deType");
            return (Criteria) this;
        }

        public Criteria andDeTypeIn(List<Integer> values) {
            addCriterion("de_type in", values, "deType");
            return (Criteria) this;
        }

        public Criteria andDeTypeNotIn(List<Integer> values) {
            addCriterion("de_type not in", values, "deType");
            return (Criteria) this;
        }

        public Criteria andDeTypeBetween(Integer value1, Integer value2) {
            addCriterion("de_type between", value1, value2, "deType");
            return (Criteria) this;
        }

        public Criteria andDeTypeNotBetween(Integer value1, Integer value2) {
            addCriterion("de_type not between", value1, value2, "deType");
            return (Criteria) this;
        }

        public Criteria andDeExtractTypeIsNull() {
            addCriterion("de_extract_type is null");
            return (Criteria) this;
        }

        public Criteria andDeExtractTypeIsNotNull() {
            addCriterion("de_extract_type is not null");
            return (Criteria) this;
        }

        public Criteria andDeExtractTypeEqualTo(Integer value) {
            addCriterion("de_extract_type =", value, "deExtractType");
            return (Criteria) this;
        }

        public Criteria andDeExtractTypeNotEqualTo(Integer value) {
            addCriterion("de_extract_type <>", value, "deExtractType");
            return (Criteria) this;
        }

        public Criteria andDeExtractTypeGreaterThan(Integer value) {
            addCriterion("de_extract_type >", value, "deExtractType");
            return (Criteria) this;
        }

        public Criteria andDeExtractTypeGreaterThanOrEqualTo(Integer value) {
            addCriterion("de_extract_type >=", value, "deExtractType");
            return (Criteria) this;
        }

        public Criteria andDeExtractTypeLessThan(Integer value) {
            addCriterion("de_extract_type <", value, "deExtractType");
            return (Criteria) this;
        }

        public Criteria andDeExtractTypeLessThanOrEqualTo(Integer value) {
            addCriterion("de_extract_type <=", value, "deExtractType");
            return (Criteria) this;
        }

        public Criteria andDeExtractTypeIn(List<Integer> values) {
            addCriterion("de_extract_type in", values, "deExtractType");
            return (Criteria) this;
        }

        public Criteria andDeExtractTypeNotIn(List<Integer> values) {
            addCriterion("de_extract_type not in", values, "deExtractType");
            return (Criteria) this;
        }

        public Criteria andDeExtractTypeBetween(Integer value1, Integer value2) {
            addCriterion("de_extract_type between", value1, value2, "deExtractType");
            return (Criteria) this;
        }

        public Criteria andDeExtractTypeNotBetween(Integer value1, Integer value2) {
            addCriterion("de_extract_type not between", value1, value2, "deExtractType");
            return (Criteria) this;
        }

        public Criteria andCheckedIsNull() {
            addCriterion("`checked` is null");
            return (Criteria) this;
        }

        public Criteria andCheckedIsNotNull() {
            addCriterion("`checked` is not null");
            return (Criteria) this;
        }

        public Criteria andCheckedEqualTo(Boolean value) {
            addCriterion("`checked` =", value, "checked");
            return (Criteria) this;
        }

        public Criteria andCheckedNotEqualTo(Boolean value) {
            addCriterion("`checked` <>", value, "checked");
            return (Criteria) this;
        }

        public Criteria andCheckedGreaterThan(Boolean value) {
            addCriterion("`checked` >", value, "checked");
            return (Criteria) this;
        }

        public Criteria andCheckedGreaterThanOrEqualTo(Boolean value) {
            addCriterion("`checked` >=", value, "checked");
            return (Criteria) this;
        }

        public Criteria andCheckedLessThan(Boolean value) {
            addCriterion("`checked` <", value, "checked");
            return (Criteria) this;
        }

        public Criteria andCheckedLessThanOrEqualTo(Boolean value) {
            addCriterion("`checked` <=", value, "checked");
            return (Criteria) this;
        }

        public Criteria andCheckedIn(List<Boolean> values) {
            addCriterion("`checked` in", values, "checked");
            return (Criteria) this;
        }

        public Criteria andCheckedNotIn(List<Boolean> values) {
            addCriterion("`checked` not in", values, "checked");
            return (Criteria) this;
        }

        public Criteria andCheckedBetween(Boolean value1, Boolean value2) {
            addCriterion("`checked` between", value1, value2, "checked");
            return (Criteria) this;
        }

        public Criteria andCheckedNotBetween(Boolean value1, Boolean value2) {
            addCriterion("`checked` not between", value1, value2, "checked");
            return (Criteria) this;
        }

        public Criteria andColumnIndexIsNull() {
            addCriterion("column_index is null");
            return (Criteria) this;
        }

        public Criteria andColumnIndexIsNotNull() {
            addCriterion("column_index is not null");
            return (Criteria) this;
        }

        public Criteria andColumnIndexEqualTo(Integer value) {
            addCriterion("column_index =", value, "columnIndex");
            return (Criteria) this;
        }

        public Criteria andColumnIndexNotEqualTo(Integer value) {
            addCriterion("column_index <>", value, "columnIndex");
            return (Criteria) this;
        }

        public Criteria andColumnIndexGreaterThan(Integer value) {
            addCriterion("column_index >", value, "columnIndex");
            return (Criteria) this;
        }

        public Criteria andColumnIndexGreaterThanOrEqualTo(Integer value) {
            addCriterion("column_index >=", value, "columnIndex");
            return (Criteria) this;
        }

        public Criteria andColumnIndexLessThan(Integer value) {
            addCriterion("column_index <", value, "columnIndex");
            return (Criteria) this;
        }

        public Criteria andColumnIndexLessThanOrEqualTo(Integer value) {
            addCriterion("column_index <=", value, "columnIndex");
            return (Criteria) this;
        }

        public Criteria andColumnIndexIn(List<Integer> values) {
            addCriterion("column_index in", values, "columnIndex");
            return (Criteria) this;
        }

        public Criteria andColumnIndexNotIn(List<Integer> values) {
            addCriterion("column_index not in", values, "columnIndex");
            return (Criteria) this;
        }

        public Criteria andColumnIndexBetween(Integer value1, Integer value2) {
            addCriterion("column_index between", value1, value2, "columnIndex");
            return (Criteria) this;
        }

        public Criteria andColumnIndexNotBetween(Integer value1, Integer value2) {
            addCriterion("column_index not between", value1, value2, "columnIndex");
            return (Criteria) this;
        }

        public Criteria andLastSyncTimeIsNull() {
            addCriterion("last_sync_time is null");
            return (Criteria) this;
        }

        public Criteria andLastSyncTimeIsNotNull() {
            addCriterion("last_sync_time is not null");
            return (Criteria) this;
        }

        public Criteria andLastSyncTimeEqualTo(Long value) {
            addCriterion("last_sync_time =", value, "lastSyncTime");
            return (Criteria) this;
        }

        public Criteria andLastSyncTimeNotEqualTo(Long value) {
            addCriterion("last_sync_time <>", value, "lastSyncTime");
            return (Criteria) this;
        }

        public Criteria andLastSyncTimeGreaterThan(Long value) {
            addCriterion("last_sync_time >", value, "lastSyncTime");
            return (Criteria) this;
        }

        public Criteria andLastSyncTimeGreaterThanOrEqualTo(Long value) {
            addCriterion("last_sync_time >=", value, "lastSyncTime");
            return (Criteria) this;
        }

        public Criteria andLastSyncTimeLessThan(Long value) {
            addCriterion("last_sync_time <", value, "lastSyncTime");
            return (Criteria) this;
        }

        public Criteria andLastSyncTimeLessThanOrEqualTo(Long value) {
            addCriterion("last_sync_time <=", value, "lastSyncTime");
            return (Criteria) this;
        }

        public Criteria andLastSyncTimeIn(List<Long> values) {
            addCriterion("last_sync_time in", values, "lastSyncTime");
            return (Criteria) this;
        }

        public Criteria andLastSyncTimeNotIn(List<Long> values) {
            addCriterion("last_sync_time not in", values, "lastSyncTime");
            return (Criteria) this;
        }

        public Criteria andLastSyncTimeBetween(Long value1, Long value2) {
            addCriterion("last_sync_time between", value1, value2, "lastSyncTime");
            return (Criteria) this;
        }

        public Criteria andLastSyncTimeNotBetween(Long value1, Long value2) {
            addCriterion("last_sync_time not between", value1, value2, "lastSyncTime");
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