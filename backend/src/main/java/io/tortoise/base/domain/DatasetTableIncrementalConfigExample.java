package io.tortoise.base.domain;

import java.util.ArrayList;
import java.util.List;

public class DatasetTableIncrementalConfigExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public DatasetTableIncrementalConfigExample() {
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

        public Criteria andIncrementalDeleteIsNull() {
            addCriterion("incremental_delete is null");
            return (Criteria) this;
        }

        public Criteria andIncrementalDeleteIsNotNull() {
            addCriterion("incremental_delete is not null");
            return (Criteria) this;
        }

        public Criteria andIncrementalDeleteEqualTo(String value) {
            addCriterion("incremental_delete =", value, "incrementalDelete");
            return (Criteria) this;
        }

        public Criteria andIncrementalDeleteNotEqualTo(String value) {
            addCriterion("incremental_delete <>", value, "incrementalDelete");
            return (Criteria) this;
        }

        public Criteria andIncrementalDeleteGreaterThan(String value) {
            addCriterion("incremental_delete >", value, "incrementalDelete");
            return (Criteria) this;
        }

        public Criteria andIncrementalDeleteGreaterThanOrEqualTo(String value) {
            addCriterion("incremental_delete >=", value, "incrementalDelete");
            return (Criteria) this;
        }

        public Criteria andIncrementalDeleteLessThan(String value) {
            addCriterion("incremental_delete <", value, "incrementalDelete");
            return (Criteria) this;
        }

        public Criteria andIncrementalDeleteLessThanOrEqualTo(String value) {
            addCriterion("incremental_delete <=", value, "incrementalDelete");
            return (Criteria) this;
        }

        public Criteria andIncrementalDeleteLike(String value) {
            addCriterion("incremental_delete like", value, "incrementalDelete");
            return (Criteria) this;
        }

        public Criteria andIncrementalDeleteNotLike(String value) {
            addCriterion("incremental_delete not like", value, "incrementalDelete");
            return (Criteria) this;
        }

        public Criteria andIncrementalDeleteIn(List<String> values) {
            addCriterion("incremental_delete in", values, "incrementalDelete");
            return (Criteria) this;
        }

        public Criteria andIncrementalDeleteNotIn(List<String> values) {
            addCriterion("incremental_delete not in", values, "incrementalDelete");
            return (Criteria) this;
        }

        public Criteria andIncrementalDeleteBetween(String value1, String value2) {
            addCriterion("incremental_delete between", value1, value2, "incrementalDelete");
            return (Criteria) this;
        }

        public Criteria andIncrementalDeleteNotBetween(String value1, String value2) {
            addCriterion("incremental_delete not between", value1, value2, "incrementalDelete");
            return (Criteria) this;
        }

        public Criteria andIncrementalAddIsNull() {
            addCriterion("incremental_add is null");
            return (Criteria) this;
        }

        public Criteria andIncrementalAddIsNotNull() {
            addCriterion("incremental_add is not null");
            return (Criteria) this;
        }

        public Criteria andIncrementalAddEqualTo(String value) {
            addCriterion("incremental_add =", value, "incrementalAdd");
            return (Criteria) this;
        }

        public Criteria andIncrementalAddNotEqualTo(String value) {
            addCriterion("incremental_add <>", value, "incrementalAdd");
            return (Criteria) this;
        }

        public Criteria andIncrementalAddGreaterThan(String value) {
            addCriterion("incremental_add >", value, "incrementalAdd");
            return (Criteria) this;
        }

        public Criteria andIncrementalAddGreaterThanOrEqualTo(String value) {
            addCriterion("incremental_add >=", value, "incrementalAdd");
            return (Criteria) this;
        }

        public Criteria andIncrementalAddLessThan(String value) {
            addCriterion("incremental_add <", value, "incrementalAdd");
            return (Criteria) this;
        }

        public Criteria andIncrementalAddLessThanOrEqualTo(String value) {
            addCriterion("incremental_add <=", value, "incrementalAdd");
            return (Criteria) this;
        }

        public Criteria andIncrementalAddLike(String value) {
            addCriterion("incremental_add like", value, "incrementalAdd");
            return (Criteria) this;
        }

        public Criteria andIncrementalAddNotLike(String value) {
            addCriterion("incremental_add not like", value, "incrementalAdd");
            return (Criteria) this;
        }

        public Criteria andIncrementalAddIn(List<String> values) {
            addCriterion("incremental_add in", values, "incrementalAdd");
            return (Criteria) this;
        }

        public Criteria andIncrementalAddNotIn(List<String> values) {
            addCriterion("incremental_add not in", values, "incrementalAdd");
            return (Criteria) this;
        }

        public Criteria andIncrementalAddBetween(String value1, String value2) {
            addCriterion("incremental_add between", value1, value2, "incrementalAdd");
            return (Criteria) this;
        }

        public Criteria andIncrementalAddNotBetween(String value1, String value2) {
            addCriterion("incremental_add not between", value1, value2, "incrementalAdd");
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