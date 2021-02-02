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

    public void setField(String field) {
        this.field = field;
    }

    public String getFieldType() {
        return fieldType;
    }

    public void setFieldType(String fieldType) {
        this.fieldType = fieldType;
    }

    public String getFieldId() {
        return fieldId;
    }

    public void setFieldId(String fieldId) {
        this.fieldId = fieldId;
    }

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String getFromString() {
        return fromString;
    }

    public void setFromString(String fromString) {
        this.fromString = fromString;
    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public String getToString() {
        return toString;
    }

    public void setToString(String toString) {
        this.toString = toString;
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
