package com.assessment.meeting.utils;

public enum TypeDto {
    TYPE("Type: "), LIVE("Live"), IN_PERSON("InPerson");
    private final String valueName;
    private TypeDto(String valueName) {
        this.valueName = valueName;
    }
    public String getValueName() {
        return this.valueName;
    }
}
