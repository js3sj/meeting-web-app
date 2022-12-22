package com.assessment.meeting.models;

public enum Category {
    CODE_MONKEY("CodeMonkey"),
    HUB("Hub"),
    SHORT("Short"),
    TEAM_BUILDING("TeamBuilding");
    private final String valueName;
    private Category(String valueName) {
        this.valueName = valueName;
    }
    public String getValueName() {
        return this.valueName;
    }
}
