package org.coffeeprojects.pmda.feature.changelog.jirabean;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ItemHistoryJiraBean {

    private String field;

    @JsonProperty("fieldtype")
    private String fieldType;

    private String fieldId;

    private String from;

    private String fromString;

    private String to;

    private String toString;

    public String getField() {
        return field;
    }

    public ItemHistoryJiraBean setField(String field) {
        this.field = field;
        return this;
    }

    public String getFieldType() {
        return fieldType;
    }

    public ItemHistoryJiraBean setFieldType(String fieldType) {
        this.fieldType = fieldType;
        return this;
    }

    public String getFieldId() {
        return fieldId;
    }

    public ItemHistoryJiraBean setFieldId(String fieldId) {
        this.fieldId = fieldId;
        return this;
    }

    public String getFrom() {
        return from;
    }

    public ItemHistoryJiraBean setFrom(String from) {
        this.from = from;
        return this;
    }

    public String getFromString() {
        return fromString;
    }

    public ItemHistoryJiraBean setFromString(String fromString) {
        this.fromString = fromString;
        return this;
    }

    public String getTo() {
        return to;
    }

    public ItemHistoryJiraBean setTo(String to) {
        this.to = to;
        return this;
    }

    public String getToString() {
        return toString;
    }

    public ItemHistoryJiraBean setToString(String toString) {
        this.toString = toString;
        return this;
    }

    @Override
    public String toString() {
        return "ItemHistoryJiraBean{" +
                "field='" + field + '\'' +
                ", fieldType='" + fieldType + '\'' +
                ", fieldId='" + fieldId + '\'' +
                ", from='" + from + '\'' +
                ", fromString='" + fromString + '\'' +
                ", to='" + to + '\'' +
                ", toString='" + toString + '\'' +
                '}';
    }
}
