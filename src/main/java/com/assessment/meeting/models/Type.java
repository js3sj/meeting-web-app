package com.assessment.meeting.models;

public enum Type {
    LIVE("Live"), IN_PERSON("InPerson");
    private final String valueName;
    private Type(String valueName) {
        this.valueName = valueName;
    }
    public String getValueName() {
        return this.valueName;
    }
}
